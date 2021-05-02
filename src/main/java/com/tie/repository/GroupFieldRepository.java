package com.tie.repository;

import com.tie.model.dao.GroupField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface GroupFieldRepository extends JpaRepository<GroupField, Integer> {

    List<GroupField> findAllByGroupId(String groupId);

    @Query(value =  "SELECT * " +
                    "FROM group_fields " +
                    "WHERE group_id = :groupId " +
                    "AND field_id = :fieldId ",
                    nativeQuery = true)
    Optional<GroupField> findByGroupIdAndFieldId(String groupId, String fieldId);
}
