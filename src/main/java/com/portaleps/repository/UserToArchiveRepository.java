package com.portaleps.repository;

import com.portaleps.model.Archive;
import com.portaleps.model.User;
import com.portaleps.model.UserToArchive;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserToArchiveRepository extends JpaRepository<UserToArchive, Integer> {

    List<UserToArchive> findByUser(User user);

    List<Archive> findByArchive(Archive archive);
}
