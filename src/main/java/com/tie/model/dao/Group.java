package com.tie.model.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tie.model.dto.GroupDto;
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

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "number_of_members")
    private String numberOfMembers;

    @Column(name = "name")
    private String name;

    public Group(GroupDto groupDto) {
        this.id = groupDto.getId();
        this.name = groupDto.getName();
        this.avatarUrl = groupDto.getName();
        this.numberOfMembers = groupDto.getNumberOfMembers();
    }

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "group")
    private Set<Subscription> subscriptions;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "group")
    private Set<GroupField> fields;


}
