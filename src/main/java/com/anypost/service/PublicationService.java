package com.anypost.service;

import java.util.EnumSet;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anypost.domain.enums.JobStatus;
import com.anypost.domain.enums.Platform;
import com.anypost.domain.enums.PostStatus;
import com.anypost.domain.model.PlatformPost;
import com.anypost.domain.model.PostJob;
import com.anypost.domain.model.Publication;
import com.anypost.domain.model.User;
import com.anypost.domain.repo.PlatformPostRepository;
import com.anypost.domain.repo.PostJobRepository;
import com.anypost.domain.repo.PublicationRepository;
import com.anypost.dto.publication.CreatePublicationRequest;
import com.anypost.dto.publication.PublicationResponse;
import com.anypost.dto.publication.SubmitPublicationRequest;
import com.anypost.mapper.PublicationMapper;

@Service
@Transactional(readOnly = true)
public class PublicationService {

    private final PublicationRepository publicationRepository;
    private final PostJobRepository postJobRepository;
    private final PlatformPostRepository platformPostRepository;
    private final PublicationMapper publicationMapper;

    public PublicationService(PublicationRepository publicationRepository,
                              PostJobRepository postJobRepository,
                              PlatformPostRepository platformPostRepository,
                              PublicationMapper publicationMapper) {
        this.publicationRepository = publicationRepository;
        this.postJobRepository = postJobRepository;
        this.platformPostRepository = platformPostRepository;
        this.publicationMapper = publicationMapper;
    }

    /** Crea una publicación (sin subir media; eso lo orquesta n8n). */
    @Transactional
    public PublicationResponse createPublication(User user, CreatePublicationRequest req) {
        Publication pub = publicationMapper.fromCreate(req, user);
        pub = publicationRepository.save(pub);
        return publicationMapper.toResponse(pub);
    }

    /** Lista publicaciones del usuario. */
    public Page<PublicationResponse> listByUser(UUID userId, Pageable pageable) {
        return publicationRepository.findAllByUser_IdOrderByCreatedAtDesc(userId, pageable)
                .map(publicationMapper::toResponse);
    }

    /**
     * Crea un PostJob y estados por plataforma. n8n consumirá esto para ejecutar la subida.
     * Retorna el id del Job.
     */
    @Transactional
    public UUID submitPublication(UUID publicationId, SubmitPublicationRequest req) {
        if (req.getPlatforms() == null || req.getPlatforms().isEmpty()) {
            throw new IllegalArgumentException("Debe seleccionar al menos una plataforma");
        }
        // Sanitiza duplicados
        var set = EnumSet.noneOf(Platform.class);
        req.getPlatforms().forEach(p -> { if (p != null) set.add(p); });

        Publication pub = publicationRepository.findById(publicationId)
                .orElseThrow(() -> new IllegalArgumentException("Publicación no encontrada"));

        PostJob job = new PostJob();
        job.setPublication(pub);
        job.setStatus(JobStatus.PENDING);
        job = postJobRepository.save(job);

        for (Platform p : set) {
            PlatformPost pp = new PlatformPost();
            pp.setPostJob(job);
            pp.setPlatform(p);
            pp.setStatus(PostStatus.QUEUED);
            platformPostRepository.save(pp);
        }

        // TODO: Invocar n8n (webhook/HTTP) para disparar workflow con jobId y publicationId
        return job.getId();
    }
}