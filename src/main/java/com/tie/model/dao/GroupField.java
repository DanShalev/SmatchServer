package com.tie.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "group_fields")
@IdClass(GroupField.class)
public class GroupField implements Serializable {

    @Id
    @Column(name = "group_id")
    private String groupId;

    @Id
    @Column(name = "field_name")
    private String fieldName;

}
