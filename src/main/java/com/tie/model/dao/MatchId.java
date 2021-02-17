package com.tie.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MatchId implements Serializable {

    @Column(name = "group_id")
    private String groupId;

    @Column(name = "first_user_id")
    private String firstUserId;

    @Column(name = "second_user_id")
    private String secondUserId;
}
