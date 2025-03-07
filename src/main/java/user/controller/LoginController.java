package user.controller;

import data.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/login")
    public Map<String, String> login(
            @RequestParam String loginemail,
            @RequestParam String loginpass,
            HttpSession session
    		) 
    {
        Map<String, String> map = new HashMap<>();
        boolean b = userService.loginCheck(loginemail, loginpass);

        if (b) {
            session.setMaxInactiveInterval(60 * 60 * 4);
            session.setAttribute("loginstatus", "success");
            session.setAttribute("loginemail", loginemail);
            

            // 이메일에 해당하는 프로필 사진 및 닉네임 가져오기
            String photo = userService.getUserByEmail(loginemail).getPhoto();
            String nickname = userService.getUserByEmail(loginemail).getNickname();

            session.setAttribute("loginphoto", photo);
            session.setAttribute("loginnickname", nickname); // 닉네임 저장

            map.put("result", "success");
        } else {
            map.put("result", "fail");
        }

        return map;
    }

    // 로그아웃 처리
    @GetMapping("/user/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loginstatus");
        session.removeAttribute("loginemail");
        session.removeAttribute("loginnickname"); // 닉네임 삭제
        session.removeAttribute("loginphoto");

        return "redirect:/"; // 로그아웃 후 메인페이지로 이동
    }
}
