package user.controller;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import data.dto.UserDto;
import data.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import naver.storage.NcpObjectStorageService;
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserDelUpdateController {
	final UserService userService;
	final NcpObjectStorageService storageService;
	//버켓이름
	private String bucketName="bitcamp.bucket";
	
	@GetMapping("/mypage")
	public String goMypage(HttpSession session, Model model) 
	{
		String email=(String)session.getAttribute("loginemail");
		
		UserDto dto=userService.getUserByEmail(email);
		
		model.addAttribute("dto", dto);
		model.addAttribute("naverurl", "https://kr.object.ncloudstorage.com/bitcamp.bucket");
		return "user/mypage";
	}
	
    // 회원 삭제
    @PostMapping("/delete")
    @ResponseBody
    public void mypageDelete(@RequestParam int id, HttpSession session) 
    {
        // 해당 이메일을 가진 사용자 삭제
        userService.deleteUser(id);

        // 세션 로그아웃 처리
        session.removeAttribute("loginstatus");
        session.removeAttribute("loginid");
        session.removeAttribute("loginphoto");
        session.invalidate();

    }
     

    // 회원 수정 처리
    @PostMapping("/update")
    @ResponseBody
    public void update(
    		HttpSession session,
    		@ModelAttribute UserDto dto,
    		@RequestParam("id") int id,
    		@RequestParam("upload") MultipartFile upload
    		) 
    {
    	//사진수정시 db 에 저장된 파일명을 받아서 스토리지에서 삭제후 추가할것
    			String oldFilename=userService.getUserById(id).getPhoto();
    			storageService.deleteFile(bucketName, "user", oldFilename);

    			//네이버 스토리지에 업로드
    			String uploadFilename=storageService.uploadFile(bucketName, "user", upload);
    			
    			// dto 에서 새파일명 설정
    			dto.setPhoto(uploadFilename);
    			//세션도 변경
    			session.setAttribute("loginphoto", uploadFilename);
    			
    			System.out.println(dto.getId());

    			userService.updateUser(dto);
    }

}