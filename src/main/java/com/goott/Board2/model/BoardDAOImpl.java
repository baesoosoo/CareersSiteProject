package com.goott.Board2.model;

import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class BoardDAOImpl implements BoardDAO{

	private final SqlSessionTemplate sqlSessionTemplate;
	

	@Override
	public List<CodeDTO> categoryList() {
		
		return sqlSessionTemplate.selectList("Category");
	}

	@Override
	public List<CodeDTO> categorysub(String no) {
		
		 return sqlSessionTemplate.selectList("Categorysub", no);
	}

	@Override
	public List<CodeDTO> categorystep(String no) {
		
		return sqlSessionTemplate.selectList("Categorystep", no);
	}


	@Override
	public List<CodeDTO> schoolname(CodeDTO dto) {
		
		return sqlSessionTemplate.selectList("schoolname", dto);
	}

	@Override
	public List<CodeDTO> departmentCode(CodeDTO dto) {
			 
		  return sqlSessionTemplate.selectList("department", dto);
	}

	@Override
	public List<license> searchlicense(String license_name) {

		List<license> list = sqlSessionTemplate.selectList("license", license_name);
		
		return list;
	}

	@Override
	public List<ProfileDTO> profileList() {
		
		return sqlSessionTemplate.selectList("profileList");
		
	}

	@Override
	public ProfileDTO profileContent(int no) {
		
		return sqlSessionTemplate.selectOne("Con", no);
	}

	@Override
	public List<EduDTO> getEducationList(int no) {
		
		 return sqlSessionTemplate.selectList("eduList", no);
	}

	@Override
	public List<CareerDTO> getCareerList(int no) {
		
		return sqlSessionTemplate.selectList("careerList", no);
	}

	@Override
	public List<LicenseDTO> getLicenseList(int no) {
		
		return sqlSessionTemplate.selectList("licenseList", no);
	}

	@Override
	public UserDTO getuserinfo(int no) {
		
		return sqlSessionTemplate.selectOne("userinfo", no);
	}




}
