package agilementor.backlog.controller;

import agilementor.backlog.dto.response.BacklogGetResponse;
import agilementor.backlog.service.BacklogService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final BacklogService backlogService;

    public TaskController(BacklogService backlogService) {
        this.backlogService = backlogService;
    }

    @GetMapping
    public List<BacklogGetResponse> getTasks(@SessionAttribute("memberId") Long memberId) {
        return backlogService.getTasks(memberId);
    }
}
