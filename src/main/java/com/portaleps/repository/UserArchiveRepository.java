package com.portaleps.repository;

import com.portaleps.model.entity.Archive;
import com.portaleps.model.entity.User;
import com.portaleps.model.entity.UserArchive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserArchiveRepository extends JpaRepository<UserArchive, Integer> {

    List<UserArchive> findByUser(User user);

    List<UserArchive> findByArchive(Archive archive);

    UserArchive findByUserAndArchive(User user, Archive archive);

    @Query(value = "SELECT * FROM user_archive ua WHERE user_id = :user AND ua.archive_id NOT IN (:newArchives)", nativeQuery = true)
    List<UserArchive> findOldArchives(User user, List<Integer> newArchives);
}
