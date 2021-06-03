package com.tie.controller;

import com.tie.service.MatchService;
import io.github.jav.exposerversdk.PushClientException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/match")
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;

    @PostMapping("/like/{groupId}/{likeUserId}/{otherUserId}")
    public boolean insertLike(@PathVariable String groupId, @PathVariable String likeUserId, @PathVariable String otherUserId) throws PushClientException, InterruptedException {
        return matchService.insertLike(groupId, likeUserId, otherUserId);
    }

    @PostMapping("/dislike/{groupId}/{dislikeUserId}/{otherUserId}")
    public void insertDislike(@PathVariable String groupId, @PathVariable String dislikeUserId, @PathVariable String otherUserId) {
        matchService.insertDislike(groupId, dislikeUserId, otherUserId);
    }

    @PutMapping("/update/{groupId}/{otherUserId}")
    public void unmatch(@PathVariable String groupId, @PathVariable String otherUserId, @RequestHeader String userId){
        matchService.unmatch(groupId, userId, otherUserId);
    }

    @PutMapping("/update/{groupId}")
    public void unmatchAllGroupUsers(@PathVariable String groupId, @RequestHeader String userId){
        matchService.unmatchGroup(groupId, userId);
    }


}
