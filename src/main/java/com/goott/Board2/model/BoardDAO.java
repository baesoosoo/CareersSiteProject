package com.goott.Board2.model;

import java.util.List;

import org.springframework.stereotype.Repository;
@Repository
public interface BoardDAO {
	
	
	//전체 목록을 보여주는 메서드
	public List<Board> BoardList();

	//카테고리를 대분류 가져오는 메서드
	public List<Category> categoryList(); 
	
	//카테고리를 중분류 가져오는 메서드
	public List<Category> categorysub(String no);
	
	//카테고리를 소분류 가져오는 메서드
	public List<Category> categorystep(String no);
	
	// 해당 학교명 가져오는 메서드
	public List<School> schoolname(String code);
}
