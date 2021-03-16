package com.tie.repository;

import com.tie.model.dao.Match;
import com.tie.model.dao.MatchId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, Integer> {

    Optional<Match> findByMatchId(MatchId matchId);

}
