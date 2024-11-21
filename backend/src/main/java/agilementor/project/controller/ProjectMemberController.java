package agilementor.project.controller;

import agilementor.member.dto.response.MemberGetResponse;
import agilementor.project.service.ProjectMemberService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@RequestMapping("/api/projects/{projectId}")
public class ProjectMemberController {

    private final ProjectMemberService projectMemberService;

    public ProjectMemberController(ProjectMemberService projectMemberService) {
        this.projectMemberService = projectMemberService;
    }

    @GetMapping("/members")
    public List<MemberGetResponse> getProjectMemberList(@SessionAttribute("memberId") Long memberId,
        @PathVariable Long projectId) {

        return projectMemberService.getProjectMemberList(memberId, projectId);
    }
}
