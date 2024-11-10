package agilementor.project.controller;

import agilementor.member.dto.response.MemberGetResponse;
import agilementor.project.dto.request.ProjectCreateRequest;
import agilementor.project.dto.response.ProjectResponse;
import agilementor.project.service.ProjectService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectResponse createProject(@RequestBody ProjectCreateRequest projectCreateRequest,
        @SessionAttribute("memberId") Long memberId) {

        return projectService.createProject(memberId, projectCreateRequest);
    }

    @GetMapping
    public List<ProjectResponse> getProjectList(@SessionAttribute("memberId") Long memberId) {

        return projectService.getProjectList(memberId);
    }

    @GetMapping("/{projectId}")
    public ProjectResponse getProject(@SessionAttribute("memberId") Long memberId,
        @PathVariable Long projectId) {

        return projectService.getProject(memberId, projectId);
    }

    @GetMapping("/{projectId}/members")
    public List<MemberGetResponse> getProjectMemberList(@SessionAttribute("memberId") Long memberId,
        @PathVariable Long projectId) {

        return projectService.getProjectMemberList(memberId, projectId);
    }
}
