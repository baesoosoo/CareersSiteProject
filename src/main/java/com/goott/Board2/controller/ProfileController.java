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
	
	
	@PostMapping("")
	public String Profile(FileDTO f_dto) {
		
		MultipartFile file = f_dto.getProfile_ppt(); //MultipartFile에 넣어주기만 함(upload 메서드에서 string으로 변환해줘야하기 때문)
		
		// 파일 경로 설정 및 성공 여부
		//파일 이름이 넘어옴
		String savedFileName = uploadFileService.upload(file);
		
		if (savedFileName != null) {
			f_dto.setProfile_image(savedFileName);//이미지에 넣어주기

		} 
		return "";
	}
	
	@GetMapping("/")
	public String categorygroup(Model model) {
		
		
		List<CodeDTO> category = this.boardDAOImpl.categoryList();
		
		model.addAttribute("categories", category);
		
		return "index";
	}
	
	@GetMapping("/test")
	public String categorygrouptest(Model model) {
		
		List<CodeDTO> category = this.boardDAOImpl.categoryList();
		
		model.addAttribute("categories", category);
		
		return "profile";
	}
	
	@PostMapping("com_board_group")
	@ResponseBody
	public List<CodeDTO> categorysub(@RequestParam("no") String no) {
		
		List<CodeDTO> categorysub = this.boardDAOImpl.categorysub(no);
		
		return categorysub;
	}
	
	@PostMapping("com_board_sub")
	@ResponseBody
	public List<CodeDTO> categorystep(@RequestParam("no")String no) {
		
		List<CodeDTO> categorystep =  this.boardDAOImpl.categorystep(no);
		

		return categorystep;
		
	}
	

	
	@PostMapping("search_school_by_name")
	@ResponseBody
	public List<CodeDTO> schoolName(CodeDTO dto){
			
		System.out.println(dto.getCode()+"code");
		System.out.println(dto.getName()+"name");
		List<CodeDTO> schoolName = this.boardDAOImpl.schoolname(dto);
		
		return schoolName;
		
	}
	
	
	@PostMapping("get_department")
	@ResponseBody
	public List<CodeDTO> departmentName(CodeDTO dto){
		/*
		 * Map<String, String> department = new HashMap<>();
		 * 
		 * department.put("code", code); department.put("m_name", m_name);
		 */
	    		
		
		List<CodeDTO> departmentName = this.boardDAOImpl.departmentCode(dto);
		
		return departmentName;
		
	}
	
	@PostMapping("search_certifications")
	@ResponseBody
	public List<license> searchlicense(@RequestParam("license_name") String license_name){
		
		System.out.println(license_name+"license_name");
		
		List<license> licenseList = this.boardDAOImpl.searchlicense(license_name);
		
		System.out.println(licenseList.size());
	
		
		return licenseList;
		
	}
	
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
	
	// 이력서 상세보기
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
