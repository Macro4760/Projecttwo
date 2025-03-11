package board.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import data.dto.BoardDto;
import data.dto.BoardFileDto;
import data.service.BoardFileService;
import data.service.BoardService;
import data.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import naver.storage.NcpObjectStorageService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    final BoardService boardService;
    final UserService userService;
    final BoardFileService fileService;
    final NcpObjectStorageService storageService;

    // 버켓 이름
    private String bucketName = "bitcamp-bucket130"; // 각자 자기꺼 써야함
    
    @ModelAttribute("notice")
    public BoardDto getNotice() {
        // isNotice가 true인 공지사항을 하나 가져오기
        return boardService.getNotice();
    }
    @GetMapping("/form")
    public String boardForm(
    		@RequestParam(value = "idx",defaultValue = "0") int idx,
            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
            Model model) 
    {
    	model.addAttribute("idx", idx);
        model.addAttribute("pageNum", pageNum);
        return "board/boardform";
    }

    @PostMapping("/insert")
    public String insert(
            @ModelAttribute BoardDto dto,
            @RequestParam int pageNum,
            @RequestParam("upload") List<MultipartFile> upload,
            @RequestParam("isNotice") String isNoticeStr, // 공지 여부 받아오기
            HttpSession session) {

        // 세션에서 로그인한 사용자의 이메일 가져오기
        String email = (String) session.getAttribute("loginemail");

        // 이메일을 이용해서 유저 닉네임 가져오기
        String writer = userService.getUserByEmail(email).getNickname();

        // 공지글 여부를 boolean으로 변환 (공지글이면 true, 일반 글이면 false)
        boolean isNotice = "notice".equals(isNoticeStr);

        // DTO에 정보 설정
        dto.setNotice(isNotice);  // 공지 여부 반영
        dto.setEmail(email);
        dto.setWriter(writer);

        // 게시판 내용을 DB에 저장 (idx 생성)
        boardService.insertBoard(dto);


        // 파일 업로드 처리
        if (!upload.get(0).getOriginalFilename().equals("")) {
            for (MultipartFile f : upload) {
                String filename = storageService.uploadFile(bucketName, "board", f);
                BoardFileDto bdto = new BoardFileDto();
                bdto.setIdx(dto.getIdx());
                bdto.setFilename(filename);
                // boardfile 테이블에 파일 정보 저장
                fileService.insertBoardFile(bdto);
            }
        }

        return "redirect:./list?pageNum=" + pageNum;
    }


    @GetMapping("/detail")
    public String detail(@RequestParam int idx,
            @RequestParam(defaultValue = "1") int pageNum, Model model,
            HttpSession session) {
        try {
            //조회수 1 증가
            boardService.updateReadCount(idx);

            //idx에 해당하는 dto 얻기
            BoardDto dto = boardService.getSelectByIdx(idx);
            if (dto == null) {
                throw new Exception("해당 게시물을 찾을 수 없습니다.");
            }

            //idx 글에 등록된 파일들 가져오기
            List<String> fileList = new Vector<>();
            List<BoardFileDto> flist = fileService.getFiles(idx);
            if (flist != null) {
                for (BoardFileDto fdto : flist) {
                    fileList.add(fdto.getFilename());
                }
            }

            dto.setPhotos(fileList);

            //해당 아이디에 대한 사진을 멤버테이블에서 얻기
            String userPhoto = null;
            if (dto.getEmail() != null) {
                userPhoto = userService.getUserByEmail(dto.getEmail()).getPhoto();
            }

            //로그인한 아이디에 해당하는 이름
            String loginemail = (String) session.getAttribute("loginemail");
            if (loginemail == null) {
                throw new Exception("로그인 정보가 없습니다.");
            }

            String writer = userService.getUserByEmail(loginemail).getNickname();

            //모델에 저장
            model.addAttribute("dto", dto);
            model.addAttribute("writer", writer);
            model.addAttribute("memberPhoto", userPhoto);
            model.addAttribute("pageNum", pageNum);
            model.addAttribute("naverurl", "https://kr.object.ncloudstorage.com/" + bucketName);

            return "board/boarddetail";

        } catch (Exception e) {
            e.printStackTrace(); // 예외 출력
            model.addAttribute("error", "오류 발생: " + e.getMessage());
            return "error"; // 오류 페이지로 리다이렉트
        }
    }


    @GetMapping("/updateform")
    public String updateForm(@RequestParam int idx, @RequestParam int pageNum,
                             Model model)
    {
       
        BoardDto dto=boardService.getSelectByIdx(idx);
		model.addAttribute("dto", dto);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("naverurl", "https://kr.object.ncloudstorage.com/"+bucketName);

		return "board/updateform";
	}	

    @GetMapping("/photolist")
	@ResponseBody
	public List<BoardFileDto> photoList(@RequestParam int idx)
	{
		List<BoardFileDto> list=fileService.getFiles(idx);
		return list;
	}

	//수정폼에서 각각의 사진 삭제시
	@GetMapping("/photodel")
	@ResponseBody
	public void deletePhoto(@RequestParam int num)
	{
		//스토리지에 있는 파일명 얻기
		String filename=fileService.getFilename(num);

		//스토리지에서 사진 삭제
		storageService.deleteFile(bucketName, "board", filename);

		//사진 삭제
		fileService.deleteFile(num);
	}

	//사진추가및 글수정
	@PostMapping("/update")
	public String update(
			@ModelAttribute BoardDto dto,
			@RequestParam int pageNum,
			@RequestParam("upload") List<MultipartFile> upload
			)
	{
		//제목및 내용 수정
		boardService.updateBoard(dto);
		//사진은 추가
		//파일이 있는경우에만 해당,네이버 스토리지에 저장후 파일저장(이때 필요한게 idx,filename)
		//반복문 안에서 이루어져야만 한다
		if(!upload.get(0).getOriginalFilename().equals(""))
		{
			for(MultipartFile f:upload)
			{
				String filename=storageService.uploadFile(bucketName, "board", f);
				BoardFileDto bdto=new BoardFileDto();
				bdto.setIdx(dto.getIdx());
				bdto.setFilename(filename);
				//boardfile 에 insert
				fileService.insertBoardFile(bdto);
			}
		}

		return "redirect:./detail?idx="+dto.getIdx()+"&pageNum="+pageNum;				
	}
	
	@GetMapping("/delete")
	@ResponseBody
	public void boardDelete(@RequestParam int idx)
	{
		//idx 에 해당하는 파일들 삭제
		List<BoardFileDto> filelist=fileService.getFiles(idx);
		for(BoardFileDto fdto:filelist)
		{
			String filename=fdto.getFilename();
			storageService.deleteFile(bucketName, "board", filename);
		}
		
		boardService.deleteBoard(idx);//원글을 지우면 그 글에 업로드된 파일들 db정보는 자동으로 지워짐
	}

}