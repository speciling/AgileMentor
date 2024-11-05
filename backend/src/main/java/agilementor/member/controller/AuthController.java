package agilementor.member.controller;

import agilementor.member.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String redirectGoogleLogin() {
        return "redirect:" + authService.getAuthUrl();
    }

    @GetMapping("/login/code/google")
    public String getGoogleAuthCode(@RequestParam(name = "code") String code) {
        return "redirect:/login/success";
    }

}
