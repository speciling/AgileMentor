package agilementor.backlog.controller;

import agilementor.backlog.dto.request.StoryCreateRequest;
import agilementor.backlog.dto.response.StoryCreateResponse;
import agilementor.backlog.service.StoryService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@RequestMapping("/api/projects/{projectId}/stories")
public class StoryController {

    private final StoryService storyService;

    public StoryController(StoryService storyService) {
        this.storyService = storyService;
    }

    @PostMapping
    public StoryCreateResponse createStory(
        @Valid @RequestBody StoryCreateRequest storyCreateRequest, @PathVariable Long projectId,
        @SessionAttribute("memberId") Long memberId) {

        return storyService.createStory(memberId, projectId, storyCreateRequest);
    }

}
