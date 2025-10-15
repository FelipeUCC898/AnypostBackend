package com.anypost.domain.repo;

import com.anypost.domain.enums.JobStatus;
import com.anypost.domain.model.PostJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PostJobRepository extends JpaRepository<PostJob, UUID> {

    List<PostJob> findAllByPublication_Id(UUID publicationId);

    List<PostJob> findAllByStatus(JobStatus status);
}