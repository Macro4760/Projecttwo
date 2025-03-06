package user.controller;

import data.dto.UserDto;
import data.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import naver.storage.NcpObjectStorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserAddController {

    @Autowired
    private UserService userService;

    private String bucketName="bitcamp.bucket";
    @Autowired
    NcpObjectStorageService storageService;

    // 회원가입 폼
    @GetMapping("/form")
    public String userform() {
        return "user/userform";
    }

    // 이메일 중복 체크
    @GetMapping("/emailcheck")
    @ResponseBody
    public Map<String, String> emailCheck(@RequestParam String email) {
        Map<String, String> map = new HashMap<>();
        if (userService.isEmailCheck(email)) {
            map.put("result", "fail");
        } else {
            map.put("result", "success");
        }
        return map;
    }

    // 회원가입 처리
    @PostMapping("/insert")
    public String insertUser(
    		HttpServletRequest request,
    		@ModelAttribute UserDto dto,
    		@RequestParam("upload") MultipartFile upload
			)
	{
		//사진선택을 안했을경우 upload 의 파일명을 확인후
		//사진선택을 안했다면 upload하지말고 mphoto 에 "no" 저장
		System.out.println("filename:"+upload.getOriginalFilename());
		
		if(upload.getOriginalFilename().equals("")) {
			dto.setPhoto("no");
		}else {

			//네이버 스토리지에 사진 저장하기-
			//네이버 오브젝트스토리지에 사진을 업로드후 업로드한 파일명을 반환
			String uploadFilename=storageService.uploadFile(bucketName, "user", upload);
			dto.setPhoto(uploadFilename);
		}
		userService.insertUser(dto);
		
		return "redirect:../";//일단은 홈으로 이동
	}
}