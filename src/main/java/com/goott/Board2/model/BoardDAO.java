package com.goott.Board2.model;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface BoardDAO {

	//카테고리를 대분류 가져오는 메서드
	public List<CodeDTO> categoryList(); 
	
	//카테고리를 중분류 가져오는 메서드
	public List<CodeDTO> categorysub(String no);
	
	//카테고리를 소분류 가져오는 메서드
	public List<CodeDTO> categorystep(String no);
	
	//검색 학교명 학교리스트 가져오는 메서드
	public List<CodeDTO> schoolname(CodeDTO dto);
	
	//검색 학교명의 학과리스트 가져오는 메서드
	public List<CodeDTO> departmentCode(CodeDTO dto);
	
	//검색 자격증의 자격증리스트 가져오는 메서드
	public List<license> searchlicense(String c_name);
	
	//이력서 리스트 가져오는 메서드
	public List<ProfileDTO> profileList();
	
	// 이력서 상세보기 가져오는 메서드
	public ProfileDTO profileContent(int no);
	
	// 이력서 학력 가져오는 메서드
	public List<EduDTO> getEducationList(int no);
	
	// 이력서 경력 가져오는 메서드
	public List<CareerDTO> getCareerList(int no);
	
	// 이력서 자격증 가져오는 메서드
	public List<LicenseDTO> getLicenseList(int no);
	
	// 이력서 기본정보 가져오는 메서드
	public UserDTO getuserinfo(int no);
}
