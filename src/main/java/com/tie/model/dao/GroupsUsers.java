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
import java.util.Objects;

@Embeddable
@Data
class GroupsUsersKey implements Serializable
{
    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "group_id")
    String groupId;

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof GroupsUsersKey)) {
            return false;
        }

        GroupsUsersKey other = (GroupsUsersKey)o;
        return Objects.equals(this.phoneNumber, other.phoneNumber) && Objects.equals(this.groupId, other.groupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber, groupId);
    }
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