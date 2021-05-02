package com.tie.repository;

import com.tie.model.dao.UserField;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserFieldRepository extends JpaRepository<UserField, Integer> {
    List<UserField> findAllByUserIdAndGroupId(String userId, String groupId);
}
