package com.tie.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupFieldId implements Serializable {

    @Column(name = "group_id")
    private String groupId;

    @Column(name = "field_id")
    private Integer fieldId;

    @Column(name = "field_name")
    private String fieldName;
}
