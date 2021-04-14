package com.tie.model.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tie.model.dto.GroupDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "groups_table")
public class Group {

    public Group(GroupDTO groupDto) {
        this.id = groupDto.getId();
        this.name = groupDto.getName();
        this.description = groupDto.getDescription();
        this.avatarUrl = groupDto.getAvatarUrl();
        this.numberOfMembers = groupDto.getNumberOfMembers();
    }

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "number_of_members")
    private String numberOfMembers;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "group")
    private Set<Subscription> subscriptions;

}
