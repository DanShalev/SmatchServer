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
public class UserFieldId implements Serializable {

    @Column(name = "group_id")
    private String groupId;

    @Column(name = "group_field_id")
    private String groupFieldId;

    @Column(name = "user_id")
    private String userId;
}
