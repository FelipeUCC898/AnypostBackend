package com.anypost.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anypost.domain.enums.JobStatus;
import com.anypost.domain.enums.Platform;
import com.anypost.domain.enums.PostStatus;
import com.anypost.domain.model.PlatformPost;
import com.anypost.domain.model.PostJob;
import com.anypost.domain.repo.PlatformPostRepository;
import com.anypost.domain.repo.PostJobRepository;

/**
 * Actualiza estados desde webhooks n8n. Recalcula el estado del Job.
 */
@Service
@Transactional
public class JobStatusService {

    private final PlatformPostRepository platformPostRepository;
    private final PostJobRepository postJobRepository;

    public JobStatusService(PlatformPostRepository platformPostRepository,
                            PostJobRepository postJobRepository) {
        this.platformPostRepository = platformPostRepository;
        this.postJobRepository = postJobRepository;
    }

    /**
     * Actualiza el estado de una plataforma y recalcula el estado del Job.
     * @param jobId           PostJob id
     * @param platform        Plataforma
     * @param status          Nuevo estado de la plataforma
     * @param externalPostId  Id del post en la plataforma (opcional)
     * @param errorMessage    Error si hubo
     */
    public void updatePlatformStatus(UUID jobId, Platform platform, PostStatus status,
                                     String externalPostId, String errorMessage) {
        PlatformPost pp = platformPostRepository.findByPostJob_IdAndPlatform(jobId, platform)
                .orElseThrow(() -> new IllegalArgumentException("PlatformPost no encontrado"));

        pp.setStatus(status);
        pp.setExternalPostId(externalPostId);
        pp.setErrorMessage(errorMessage);
        platformPostRepository.save(pp);

        recalcJobStatus(jobId);
    }

    /** Recalcula JobStatus en base al conjunto de PlatformPost. */
    private void recalcJobStatus(UUID jobId) {
        PostJob job = postJobRepository.findById(jobId)
                .orElseThrow(() -> new IllegalArgumentException("PostJob no encontrado"));

        List<PlatformPost> posts = platformPostRepository.findAllByPostJob_IdOrderByPlatformAsc(jobId);

        boolean anyFailed = posts.stream().anyMatch(p -> p.getStatus() == PostStatus.FAILED);
        boolean allPublished = !posts.isEmpty() && posts.stream().allMatch(p -> p.getStatus() == PostStatus.PUBLISHED);
        boolean anyPublishing = posts.stream().anyMatch(p -> p.getStatus() == PostStatus.PUBLISHING || p.getStatus() == PostStatus.QUEUED);

        if (allPublished) {
            job.setStatus(JobStatus.SUCCESS);
        } else if (anyFailed && anyPublishing) {
            job.setStatus(JobStatus.PARTIAL);
        } else if (anyFailed) {
            job.setStatus(JobStatus.FAILED);
        } else if (anyPublishing) {
            job.setStatus(JobStatus.RUNNING);
        } else {
            job.setStatus(JobStatus.PENDING);
        }

        postJobRepository.save(job);
    }
}