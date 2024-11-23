package agilementor.project.entity;

import agilementor.member.entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ProjectMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectMemberId;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private boolean is_admin;

    protected ProjectMember() {
    }

    public ProjectMember(Project project, Member member, boolean is_admin) {
        this.project = project;
        this.member = member;
        this.is_admin = is_admin;
    }

    public Project getProject() {
        return project;
    }

    public Member getMember() {
        return member;
    }

    public boolean isAdmin() {
        return is_admin;
    }

    public boolean isNotAdmin() {
        return !is_admin;
    }
}


