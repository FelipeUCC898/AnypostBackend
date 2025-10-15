package com.anypost.domain.repo;

import com.anypost.domain.model.Publication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PublicationRepository extends JpaRepository<Publication, UUID> {

    Page<Publication> findAllByUser_IdOrderByCreatedAtDesc(UUID userId, Pageable pageable);
}