package com.tie.repository;


import com.tie.model.dao.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Integer> {
    Optional<Group> findGroupById(String groupId);
}
