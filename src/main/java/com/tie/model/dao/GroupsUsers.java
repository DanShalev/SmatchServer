package com.tie.model.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.JoinColumn;
import java.io.Serializable;

@Embeddable
@Data
class GroupsUsersKey implements Serializable
{
    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "group_id")
    String groupId;
}


@Entity
@Data
@Table(name = "groups_users")
public class GroupsUsers
{
    @EmbeddedId
    GroupsUsersKey id;

    @ManyToOne
    @MapsId("group_id")
    @JoinColumn(name = "group_id")
    Group group;

    @ManyToOne
    @MapsId("phone_number")
    @JoinColumn(name = "phone_number")
    User user;
}