package com.tie.model.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@Table(name = "groups_table")
public class Group {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "group")
    private Set<Subscription> subscriptions;

}
