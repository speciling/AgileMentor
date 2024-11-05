package agilementor.member.controller;

import agilementor.member.dto.ParsedIdToken;
import agilementor.member.service.AuthService;
import agilementor.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final MemberService memberService;

    public AuthController(AuthService authService, MemberService memberService) {
        this.authService = authService;
        this.memberService = memberService;
    }

    @GetMapping("/login")
    public String redirectGoogleLogin() {
        return "redirect:" + authService.getAuthUrl();
    }

    @GetMapping("/login/code/google")
    public String getGoogleAuthCode(@RequestParam(name = "code") String code, HttpSession httpSession) {
        String idToken;

        try {
            idToken = authService.requestIdToken(code);
        } catch (Exception e) {
            // todo: 로그인 실패 페이지 추가
            return "redirect:/";
        }

        Long memberId = memberService.registerOrUpdateMember(idToken);
        httpSession.setAttribute("memberId", memberId);

        return "redirect:/dashboard";
    }
}
