package agilementor.project.entity;

import agilementor.member.entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invitationId;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    protected Invitation() {
    }

    public Invitation(Project project, Member member) {
        this.project = project;
        this.member = member;
    }

    public Long getInvitationId() {
        return invitationId;
    }

    public Project getProject() {
        return project;
    }

    public Member getMember() {
        return member;
    }
}
