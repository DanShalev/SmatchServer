package com.tie.model.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;


@Entity
@Data
@Table(name = "users_table")
public class User {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "sex")
    private String sex;

    @Column(name = "age")
    private int age;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private Set<GroupsUsers> subscribedGroups;

}
