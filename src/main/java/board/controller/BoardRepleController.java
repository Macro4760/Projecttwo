package board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import data.dto.BoardRepleDto;
import data.service.BoardRepleService;
import data.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import naver.storage.NcpObjectStorageService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardRepleController {
	final UserService userService;
	final BoardRepleService repleService;
	final NcpObjectStorageService storageService;
	
	
	private String bucketName="bitcamp.bucket";//각자 자기꺼 써야함
	
	@PostMapping("/addreple")
	public void addReple(@RequestParam int idx,@RequestParam String message,
			HttpSession session)
	{
		//로그인한 아이디
		String loginemail=(String)session.getAttribute("loginemail");
		
		//클래스명.builder() 로 시작하여 값을 셋팅 후 build()를 호출하여 객체 생성
		BoardRepleDto dto=BoardRepleDto.builder()
				.idx(idx)
				.message(message)
				.email(loginemail)
				.build();
		
		repleService.insertReple(dto);
	}
	
	@GetMapping("/replelist")
	public List<BoardRepleDto> getRepleList(@RequestParam int idx)
	{
		System.out.println("idx="+idx);
		List<BoardRepleDto> rlist=repleService.getSelectReples(idx);
		for(int i=0;i<rlist.size();i++)
		{
			String writer=userService.getUserByEmail(rlist.get(i).getEmail()).getNickname();
			String profilePhoto=userService.getUserByEmail(rlist.get(i).getEmail()).getPhoto();
			rlist.get(i).setWriter(writer);//댓글 작성자 저장
			rlist.get(i).setProfile(profilePhoto);//댓글 작성자 프로필사진 저장			
		}
		return rlist;
	}
}
