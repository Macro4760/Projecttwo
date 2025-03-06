package user.controller;

import data.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user/login")
public class LoginController {

    @Autowired
    private UserService userService;

    // 로그인 처리
    @PostMapping
    public Map<String, String> login(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session
    ) {
        Map<String, String> map = new HashMap<>();
        if (userService.loginCheck(email, password)) {
            session.setAttribute("loginEmail", email);
            map.put("result", "success");
        } else {
            map.put("result", "fail");
        }
        return map;
    }

    // 로그아웃 처리
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loginEmail");
        return "redirect:/"; // 로그아웃 후 메인페이지로 이동
    }
}