package com.tie.repository;

import com.tie.model.dao.Match;
import com.tie.model.dao.MatchId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, MatchId> {

    Optional<Match> findByMatchId(MatchId matchId);

    @Query(value = "SELECT * " +
                   "FROM matches_table " +
                   "WHERE group_id = :groupId " +
                   "AND (first_user_id = :userId OR second_user_id = :userId) " +
                   "AND first_user_like = true " +
                   "AND second_user_like = true ",
            nativeQuery = true)
    List<Match> findAllMatchesByGroupAndUserId(String groupId, String userId);
}
