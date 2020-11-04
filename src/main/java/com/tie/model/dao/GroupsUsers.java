package com.tie.model.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Getter
@Setter
@ToString
@Table(name = "GroupsUsers")
public class GroupsUsers {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "group_id")
    private String groupId;
}
