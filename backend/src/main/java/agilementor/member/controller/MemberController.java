package agilementor.member.controller;

import agilementor.member.dto.response.MemberGetResponse;
import agilementor.member.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public MemberGetResponse getLoginMemberInfo(@SessionAttribute("memberId") Long memberId) {

        return memberService.getMember(memberId);
    }
}
