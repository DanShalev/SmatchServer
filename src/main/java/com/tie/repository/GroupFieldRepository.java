package com.tie.repository;

import com.tie.model.dao.GroupField;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface GroupFieldRepository extends JpaRepository<GroupField, Integer> {

    List<GroupField> findAllByGroupId(String groupId);
}
