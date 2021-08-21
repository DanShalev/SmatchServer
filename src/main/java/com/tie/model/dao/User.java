package com.tie.model.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Data
@Table(name = "users_table")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "sex", nullable = false)
    private String sex;

    @Column(name = "age")
    private int age;

    @Column(name = "push_notification_token")
    private String pushNotificationToken;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "image1")
    private String image1;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "image2")
    private String image2;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "image3")
    private String image3;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private Set<Subscription> subscriptions;

}
