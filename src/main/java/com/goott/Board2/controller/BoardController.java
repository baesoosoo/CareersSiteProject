package com.goott.Board2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.goott.Board2.File.UploadFileService;
import com.goott.Board2.model.Board;
import com.goott.Board2.model.BoardDAO;
import com.goott.Board2.model.BoardDAOImpl;
import com.goott.Board2.model.Category;
import com.goott.Board2.model.School;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BoardController {
	
	@Autowired
	private final BoardDAOImpl boardDAOImpl;
	
	@Autowired
	UploadFileService uploadFileService;
	
	
	@PostMapping("Profile")
	public String Profile(Board board,@RequestParam("profile_image") MultipartFile profile_file) {
		
		// 파일 경로 설정 및 성공 여부
		//파일 이름이 넘어옴
		String savedFileName = uploadFileService.upload(profile_file);
		
		if (savedFileName != null) {
			board.setProfile_image(savedFileName);//이미지에 넣어주기

		} 
		return "";
	}
	
	@GetMapping("/")
	public String categorygroup(Model model) {
		
		
		List<Category> category = this.boardDAOImpl.categoryList();
		
		model.addAttribute("categories", category);
		
		return "index";
	}
	
	@GetMapping("com_board_group")
	@ResponseBody
	public List<Category> categorysub(@RequestParam("no") String no) {
		
		List<Category> categorysub = this.boardDAOImpl.categorysub(no);
		
		return categorysub;
	}
	
	@GetMapping("com_board_sub")
	@ResponseBody
	public List<Category> categorystep(@RequestParam("no")String no) {
		
		List<Category> categorystep =  this.boardDAOImpl.categorystep(no);
		

		return categorystep;
		
	}
	
	@GetMapping("com_edu_kind")
	@ResponseBody
	public List<School> schoolKind(@RequestParam("no") String code){
		
		List<School> schoolName = this.boardDAOImpl.schoolname(code);
		
		return schoolName;
		
	}
	
	
}
