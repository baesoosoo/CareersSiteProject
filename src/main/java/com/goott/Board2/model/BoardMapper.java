package com.goott.Board2.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface BoardMapper {

	//카테고리를 대분류 가져오는 메서드
	public List<CodeDTO> category(); 
	
	//카테고리를 중분류 가져오는 메서드
	public List<CodeDTO> categorysub(String no);
	
	//카테고리를 소분류 가져오는 메서드
	public List<CodeDTO> categorystep(String no);
	
	//검색 학교명 학교리스트 가져오는 메서드
	public List<CodeDTO> schoolname(CodeDTO dto);
	
	//검색 학교명의 학과리스트 가져오는 메서드
	public List<CodeDTO> department(CodeDTO dto);
	
	//검색 자격증의 자격증리스트 가져오는 메서드
	public List<license> license(String c_name);
	
	//이력서 리스트 가져오는 메서드
	public List<ProfileDTO> profileList();
	
	// 이력서 상세보기 가져오는 메서드
	public ProfileDTO con(int no);
	
	// 이력서 학력 가져오는 메서드
	public List<EduDTO> eduList(int no);
	
	// 이력서 경력 가져오는 메서드
	public List<CareerDTO> careerList(int no);
	
	// 이력서 자격증 가져오는 메서드
	public List<LicenseDTO> licenseList(int no);
	
	// 이력서 기본정보 가져오는 메서드
	public UserDTO userinfo(int no);
}
