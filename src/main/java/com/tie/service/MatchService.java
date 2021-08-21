package com.tie.service;

import com.tie.utils.Constants;
import com.tie.model.dao.Match;
import com.tie.model.dao.MatchId;
import com.tie.repository.MatchRepository;
import io.github.jav.exposerversdk.ExpoPushMessage;
import io.github.jav.exposerversdk.PushClientException;
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
    private final UserService userService;

    public boolean insertLike(String groupId, String likeUserId, String otherUserId) throws PushClientException, InterruptedException {
        boolean isMatch = false;
        validateIds(likeUserId,otherUserId);
        Optional<Match> matchOptional;
        if (likeUserId.compareTo(otherUserId) < 0) {
            matchOptional = matchRepository.findByMatchId(new MatchId(groupId, likeUserId, otherUserId));
            if (matchOptional.isPresent()) {
                Match match = matchOptional.get();
                match.setFirstUserLike(true);
                matchRepository.save(match);
                isMatch = match.getSecondUserLike() != null && match.getSecondUserLike();
            } else {
                Match match = new Match(new MatchId(groupId, likeUserId, otherUserId), true, null, false, false);
                matchRepository.save(match);
            }
        } else {
            matchOptional = matchRepository.findByMatchId(new MatchId(groupId, otherUserId, likeUserId));
            if (matchOptional.isPresent()) {
                Match match = matchOptional.get();
                match.setSecondUserLike(true);
                matchRepository.save(match);
                isMatch = match.getFirstUserLike() != null && match.getFirstUserLike();
            } else {
                Match match = new Match(new MatchId(groupId, otherUserId, likeUserId), null, true, false, false);
                matchRepository.save(match);
            }
        }
        if (isMatch) {
            ExpoPushMessage expoPushMessage = NotificationService.createPushMessage(userService.getUserToken(otherUserId), Constants.SMATCH_TITLE, "");
            NotificationService.push(expoPushMessage);
        }
        return isMatch;
    }

    public void insertDislike(String groupId, String dislikeUserId, String otherUserId) {
        validateIds(dislikeUserId,otherUserId);
        Optional<Match> matchOptional;
        if (dislikeUserId.compareTo(otherUserId) < 0) {
            matchOptional = matchRepository.findByMatchId(new MatchId(groupId, dislikeUserId, otherUserId));
            Match match;
            if (matchOptional.isPresent()) {
                match = matchOptional.get();
                match.setFirstUserLike(false);
            } else {
                match = new Match(new MatchId(groupId, dislikeUserId, otherUserId), false, null, false, false);
            }
            matchRepository.save(match);
        } else {
            matchOptional = matchRepository.findByMatchId(new MatchId(groupId, otherUserId, dislikeUserId));
            if (matchOptional.isPresent()) {
                Match match = matchOptional.get();
                match.setSecondUserLike(false);
                matchRepository.save(match);
            } else {
                Match match = new Match(new MatchId(groupId, otherUserId, dislikeUserId), null, false, false, false);
                matchRepository.save(match);
            }
        }
    }

    public Boolean didLikeOrDislikeOtherUser(String groupId, String userId, String otherUserId) {
        validateIds(userId,otherUserId);
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

    public void unmatch(String groupId, String userId, String otherUserId) {
        validateIds(userId, otherUserId);

        int numberOfUpdates;
        if (userId.compareTo(otherUserId) < 0) {
            numberOfUpdates = matchRepository.unmatchUsers(groupId, userId, otherUserId);
        } else {
            numberOfUpdates = matchRepository.unmatchUsers(groupId, otherUserId, userId);
        }
        if (numberOfUpdates == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("No match between userId: %s and otherUserId: %s was found", userId, otherUserId));
        }
    }

    public void unmatchGroup(String groupId, String userId) {
        if (groupId == null || userId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("No such group groupId: %s or userId: %s", groupId, userId));
        }
        matchRepository.unmatchAllGroupUsers(groupId, userId);
    }

    private static void validateIds(String userId, String otherUserId) {
        if (userId == null || otherUserId == null || userId.equals(otherUserId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Bad user id: userId: %s, otherUserId: %s.", userId, otherUserId));
        }
    }

    public Match getMatchFromIds(String groupId, String userId, String otherUserId) {
        validateIds(userId,otherUserId);
        Optional<Match> matchOptional;
        if (userId.compareTo(otherUserId) < 0) {
            matchOptional = matchRepository.findByMatchId(new MatchId(groupId, userId, otherUserId));
        } else {
            matchOptional = matchRepository.findByMatchId(new MatchId(groupId, otherUserId, userId));
        }
        if (matchOptional.isPresent()) {
            return matchOptional.get();
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                String.format("Bad request - groupId: %s, userId: %s, otherUserId: %s",
                        groupId, userId, otherUserId));
    }
}
