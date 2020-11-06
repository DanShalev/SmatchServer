package com.tie.model.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Set;


@Entity
@Data
@Table(name = "users_table")
public class User {

    @Id
    @Column(name = "id")
    private String id;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<GroupsUsers> subscribedGroups;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                '}';
    }
}
