package com.portaleps.repository;

import com.portaleps.model.entity.Archive;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArchiveRepository extends JpaRepository<Archive, Integer> {

    Optional<Archive> findById(Integer id);

}
