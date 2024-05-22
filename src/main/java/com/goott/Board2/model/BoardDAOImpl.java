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
	public List<Board> BoardList() {
		
		return sqlSessionTemplate.selectList("List");
		
		
	}

	@Override
	public List<Category> categoryList() {
		
		return sqlSessionTemplate.selectList("Category");
	}

	@Override
	public List<Category> categorysub(String no) {
		
		 return sqlSessionTemplate.selectList("Categorysub", no);
	}

	@Override
	public List<Category> categorystep(String no) {
		
		return sqlSessionTemplate.selectList("Categorystep", no);
	}

	@Override
	public List<School> schoolname(String code) {
		
		return sqlSessionTemplate.selectList("schoolname", code);
	}




	
	

}
