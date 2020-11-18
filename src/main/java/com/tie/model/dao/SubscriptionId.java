package com.tie.model.dao;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SubscriptionId implements Serializable {

    @Column(name = "group_id")
    private String groupId;

    @Column(name = "user_id")
    private String userId;

}
