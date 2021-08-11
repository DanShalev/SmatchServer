package com.tie.model.dao;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "matches_table")
public class Match {

    @EmbeddedId
    private MatchId matchId;

    @Column(name = "first_user_like")
    private Boolean firstUserLike;

    @Column(name = "second_user_like")
    private Boolean secondUserLike;

    @Column(name = "first_user_is_typing")
    private Boolean firstUserIsTyping;

    @Column(name = "second_user_is_typing")
    private Boolean secondUserIsTyping;
}
