package com.tie.model.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name = "Users")
public class User {

    @Id
    @Column(name = "phone_number")
    private String phoneNumber;

}
