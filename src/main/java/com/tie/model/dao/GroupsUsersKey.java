package com.tie.model.dao;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@Data
public class GroupsUsersKey implements Serializable {

    @Column(name = "user_id")
    private String userId;

    @Column(name = "group_id")
    private String groupId;

}
