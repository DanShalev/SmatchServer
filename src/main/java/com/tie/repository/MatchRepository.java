package com.tie.repository;

import com.tie.model.dao.Match;
import com.tie.model.dao.MatchId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MatchRepository extends JpaRepository<Match, MatchId> {

    Optional<Match> findByMatchId(MatchId matchId);

    @Transactional
    @Modifying
    @Query( value = "UPDATE Match m SET m.firstUserLike = false, m.secondUserLike=false"
                            + " WHERE m.matchId.groupId = :groupId "
                            + "AND m.firstUserLike = true AND m.secondUserLike = true "
                            + "AND ((m.matchId.firstUserId = :userId AND m.matchId.secondUserId = :otherUserId) "
                            + "OR (m.matchId.firstUserId = :otherUserId AND m.matchId.secondUserId = :userId)) ")
    int unmatchUsers(@Param("groupId") String groupId,@Param("userId") String userId,@Param("otherUserId") String otherUserId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Match m SET m.firstUserLike = false, m.secondUserLike = false "
                           + "WHERE m.matchId.groupId = :groupId "
                           + "AND m.firstUserLike = true AND m.secondUserLike = true "
                           + "AND (m.matchId.firstUserId = :userId OR m.matchId.secondUserId = :userId)")
    int unmatchAllGroupUsers(@Param("groupId") String groupId,@Param("userId") String userId);

    @Query(value = "SELECT * " +
                   "FROM matches_table " +
                   "WHERE group_id = :groupId " +
                   "AND (first_user_id = :userId OR second_user_id = :userId) " +
                   "AND first_user_like = true " +
                   "AND second_user_like = true ",
            nativeQuery = true)
    List<Match> findAllMatchesByGroupAndUserId(String groupId, String userId);
}
