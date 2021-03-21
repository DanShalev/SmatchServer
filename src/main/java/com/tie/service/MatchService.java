package com.tie.service;

import com.tie.model.dao.Match;
import com.tie.model.dao.MatchId;
import com.tie.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MatchService {

    private final MatchRepository matchRepository;

    public boolean insertLike(String groupId, String likeUserId, String otherUserId) {
        if (likeUserId == null || otherUserId == null || likeUserId.equals(otherUserId)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Bad user id: likedUserId: %s, otherUserId: %s.",
                    likeUserId, otherUserId));
        }
        Optional<Match> matchOptional;
        if (likeUserId.compareTo(otherUserId) < 0) {
            matchOptional = matchRepository.findByMatchId(new MatchId(groupId, likeUserId, otherUserId));
            if (matchOptional.isPresent()) {
                Match match = matchOptional.get();
                match.setFirstUserLike(true);
                matchRepository.save(match);
                return match.getSecondUserLike() != null && match.getSecondUserLike();
            } else {
                Match match = new Match(new MatchId(groupId, likeUserId, otherUserId), true, null);
                matchRepository.save(match);
                return false;
            }
        } else {
            matchOptional = matchRepository.findByMatchId(new MatchId(groupId, otherUserId, likeUserId));
            if (matchOptional.isPresent()) {
                Match match = matchOptional.get();
                match.setSecondUserLike(true);
                matchRepository.save(match);
                return match.getFirstUserLike() != null && match.getFirstUserLike();
            } else {
                Match match = new Match(new MatchId(groupId, otherUserId, likeUserId), null, true);
                matchRepository.save(match);
                return false;
            }
        }
    }

    public void insertDislike(String groupId, String dislikeUserId, String otherUserId) {
        if (dislikeUserId == null || otherUserId == null || dislikeUserId.equals(otherUserId)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Bad user id: likedUserId: %s, otherUserId: %s.",
                    dislikeUserId, otherUserId));
        }
        Optional<Match> matchOptional;
        if (dislikeUserId.compareTo(otherUserId) < 0) {
            matchOptional = matchRepository.findByMatchId(new MatchId(groupId, dislikeUserId, otherUserId));
            if (matchOptional.isPresent()) {
                Match match = matchOptional.get();
                match.setFirstUserLike(false);
                matchRepository.save(match);
            } else {
                Match match = new Match(new MatchId(groupId, dislikeUserId, otherUserId), false, null);
                matchRepository.save(match);
            }
        } else {
            matchOptional = matchRepository.findByMatchId(new MatchId(groupId, otherUserId, dislikeUserId));
            if (matchOptional.isPresent()) {
                Match match = matchOptional.get();
                match.setSecondUserLike(false);
                matchRepository.save(match);
            } else {
                Match match = new Match(new MatchId(groupId, otherUserId, dislikeUserId), null, false);
                matchRepository.save(match);
            }
        }
    }

    public Boolean didLikeOrDislikeOtherUser(String groupId, String userId, String otherUserId) {
        if (userId == null || otherUserId == null || userId.equals(otherUserId)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Bad user id: userId: %s, otherUserId: %s.",
                    userId, otherUserId));
        }

        Optional<Match> matchOptional;
        if (userId.compareTo(otherUserId) < 0) {
            matchOptional = matchRepository.findByMatchId(new MatchId(groupId, userId, otherUserId));
            if (matchOptional.isPresent()) {
                Match match = matchOptional.get();
                return match.getFirstUserLike() != null;
            }
        } else {
            matchOptional = matchRepository.findByMatchId(new MatchId(groupId, otherUserId, userId));
            if (matchOptional.isPresent()) {
                Match match = matchOptional.get();
                return match.getSecondUserLike() != null;
            }
        }
        return false;
    }
}
