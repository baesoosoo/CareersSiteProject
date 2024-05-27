package com.goott.Board2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.goott.Board2.File.UploadFileService;

import com.goott.Board2.model.BoardDAOImpl;
import com.goott.Board2.model.CareerDTO;
import com.goott.Board2.model.CodeDTO;
import com.goott.Board2.model.CodeListDTO;
import com.goott.Board2.model.EduDTO;
import com.goott.Board2.model.FileDTO;
import com.goott.Board2.model.LicenseDTO;
import com.goott.Board2.model.ProfileDTO;
import com.goott.Board2.model.UserDTO;
import com.goott.Board2.model.license;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ProfileController {
	
	@Autowired
	private final BoardDAOImpl boardDAOImpl;
	
	@Autowired
	UploadFileService uploadFileService;
	
	// 파일 업로드
	@PostMapping("fileUpload")
	public String Profile(ProfileDTO dto) {
		
		MultipartFile profileImage = dto.getProfile_image(); //MultipartFile에 넣어주기만 함(upload 메서드에서 string으로 변환해줘야하기 때문)
		MultipartFile profilePpt = dto.getProfile_ppt(); //MultipartFile에 넣어주기만 함(upload 메서드에서 string으로 변환해줘야하기 때문)
		
		// 파일 경로 설정 및 성공 여부
		//파일 이름이 넘어옴
		String savedImageName = uploadFileService.upload(profileImage);
		String savedPptName = uploadFileService.upload(profilePpt);
		
		 if (savedImageName != null && savedPptName != null) {
		        dto.setProfile_image_name(savedImageName);
		        dto.setProfile_ppt_name(savedPptName);
		    } else {
		        return "uploadFail"; 
		    }

		    return "uploadSuccess";
	}
	
	
	@GetMapping("/file")
	public String categorygroup() {
	
		
		return "filetest";
	}
	
	//이력서 작성 폼
	@GetMapping("/test")
	public String categorygrouptest(Model model) {
		
		List<CodeDTO> category = this.boardDAOImpl.categoryList();
		
		model.addAttribute("categories", category);
		
		return "profile";
	}
	
	// 대분류 카테고리
	@PostMapping("com_board_group")
	@ResponseBody
	public List<CodeDTO> categorysub(@RequestParam("no") String no) {
		
		List<CodeDTO> categorysub = this.boardDAOImpl.categorysub(no);
		
		return categorysub;
	}
	
	// 중분류 카테고리 
	@PostMapping("com_board_sub")
	@ResponseBody
	public List<CodeDTO> categorystep(@RequestParam("no")String no) {
		
		List<CodeDTO> categorystep =  this.boardDAOImpl.categorystep(no);
		

		return categorystep;
		
	}
	

	// 학교구분과 학교 이름 검색 시 해당 학교 리스트 불러오기
	@PostMapping("search_school_by_name")
	@ResponseBody
	public List<CodeDTO> schoolName(CodeDTO dto){
			
		List<CodeDTO> schoolName = this.boardDAOImpl.schoolname(dto);
		
		return schoolName;
		
	}
	
	// 학교구분과 전공명 검색 시 해당 전공 데이터 불러오기
	@PostMapping("get_department")
	@ResponseBody
	public List<CodeDTO> departmentName(CodeDTO dto){
	
		List<CodeDTO> departmentName = this.boardDAOImpl.departmentCode(dto);
		
		return departmentName;
		
	}
	
	// 해당 자격증 리스트 불러오기
	@PostMapping("search_certifications")
	@ResponseBody
	public List<license> searchlicense(@RequestParam("license_name") String license_name){
		
		List<license> licenseList = this.boardDAOImpl.searchlicense(license_name);
		
		
		return licenseList;
		
	}
	
	// 데이터 넘어오는 지 test
	@PostMapping("dateReqTest")
	public String dateReqTest(Model model,@ModelAttribute(value = "CodeListDTO") CodeListDTO li) {
		System.out.println(li);
		
		/*
		 * List<CodeDTO> list = request.getParameterValues(dto);
		 * 
		 * for (int i = 0; i < list.size(); i++) { CodeDTO dto = new CodeDTO();
		 * dto.setCode(list.get(i).getCode());
		 * 
		 * System.out.println(dto.getCode()); }
		 */
		return "index";
	}
	
	//이력서 리스트로 이동
	@GetMapping("profile_list")
	public String profileList(Model model) {
		
		List<ProfileDTO> profileList = this.boardDAOImpl.profileList();
		
		model.addAttribute("ProfileList", profileList);
		
		return "profile_List";
	}
	
	// 해당 프로필 키 이력서 상세보기
	@GetMapping("profile_content")
	public String profileContent(@RequestParam("no") int no,Model model) {
		
		ProfileDTO dto = this.boardDAOImpl.profileContent(no);
	    
		UserDTO user = this.boardDAOImpl.getuserinfo(no);
		List<EduDTO> eduList = this.boardDAOImpl.getEducationList(no);
		List<CareerDTO> careerList = this.boardDAOImpl.getCareerList(no);
		List<LicenseDTO> licenseList = this.boardDAOImpl.getLicenseList(no);
	
		
	    model.addAttribute("Content", dto)
	    .addAttribute("EduList", eduList)
	    .addAttribute("CareerList", careerList)
	    .addAttribute("License", licenseList)
	    .addAttribute("UserInfo", user);
	    

	    return "profile_content";
		
	}
}
