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
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne
    @JoinColumn(name = "invitee_id", nullable = false)
    private Member invitee;

    @ManyToOne
    @JoinColumn(name = "invitor_id", nullable = false)
    private Member invitor;

    protected Invitation() {
    }

    public Invitation(Project project, Member invitee, Member invitor) {
        this.project = project;
        this.invitee = invitee;
        this.invitor = invitor;
    }

    public Long getInvitationId() {
        return invitationId;
    }

    public Project getProject() {
        return project;
    }

    public Member getInvitee() {
        return invitee;
    }

    public Member getInvitor() {
        return invitor;
    }
}
