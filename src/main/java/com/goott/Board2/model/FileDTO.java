package com.goott.Board2.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
@Data
public class FileDTO {
	
	private String profile_image;   //저장 컬럼이름 확인 해야됨
	private MultipartFile profile_ppt; // form에서 받아온 파일명 -> contoller에서 string 변환 호출되고 경로service에서 필요함
	

}
