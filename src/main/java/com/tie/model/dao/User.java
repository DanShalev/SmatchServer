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
@Table(name = "Users")
public class User {

    @Id
    @Column(name = "phone_number")
    private String phoneNumber;

}
