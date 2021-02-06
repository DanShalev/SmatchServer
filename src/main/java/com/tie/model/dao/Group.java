package com.tie.model.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tie.model.dto.GroupDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@Table(name = "groups_table")
public class Group {

    public Group(GroupDto groupDto) {
        this.id = groupDto.getId();
        this.name = groupDto.getName();
        this.description = groupDto.getDescription();
    }

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

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "group")
    private Set<GroupField> fields;


}
