package com.portaleps.repository;

import com.portaleps.model.Archive;
import com.portaleps.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArchiveRepository extends JpaRepository<User, Integer> {

    Archive findById(Long id);

}
