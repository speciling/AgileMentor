package agilementor.backlog.controller;

import agilementor.backlog.dto.request.BacklogCreateRequest;
import agilementor.backlog.dto.response.BacklogCreateResponse;
import agilementor.backlog.dto.response.BacklogGetResponse;
import agilementor.backlog.service.BacklogService;
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
@RequestMapping("/api/projects/{projectId}/backlogs")
public class BacklogController {

    private final BacklogService backlogService;

    public BacklogController(BacklogService backlogService) {
        this.backlogService = backlogService;
    }

    @PostMapping
    public BacklogCreateResponse createBacklog(
        @Valid @RequestBody BacklogCreateRequest backlogCreateRequest, @PathVariable Long projectId,
        @SessionAttribute("memberId") Long memberId) {

        return backlogService.createBacklog(memberId, projectId, backlogCreateRequest);
    }

    @GetMapping
    public List<BacklogGetResponse> getBacklogList(@PathVariable Long projectId,
        @SessionAttribute("memberId") Long memberId) {

        return backlogService.getBacklogList(memberId, projectId);
    }
}
