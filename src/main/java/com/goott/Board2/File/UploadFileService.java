package com.goott.Board2.File;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileService {
	
public String upload(MultipartFile file) {
		
		boolean result = false;
		
		// File 저장
		String fileOriName = file.getOriginalFilename();
		
		String fileExtension = 
				fileOriName.substring(fileOriName.lastIndexOf("."), fileOriName.length());
		
		String uploadDir = "C:\\JAVA\\workspace(spring_boot)\\Board2\\src\\main\\resources\\image\\profile_image";
		
		UUID uuid = UUID.randomUUID();
		
		String uniqueName = uuid.toString().replaceAll("-", "");
		
		File saveFile = new File(uploadDir + "\\" + uniqueName + fileExtension);
		
		if (!saveFile.exists())
			saveFile.mkdirs();
		
		try {
			file.transferTo(saveFile); //저장할 경로에 파일 전송
			result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		if (result) {
			System.out.println("[UploadFileService] FILE UPLOAD SUCCESS!!");
			return uniqueName + fileExtension; //파일 고유한 이름 + 파일 확장자
			
		} else {
			System.out.println("[UploadFileService] FILE UPLOAD FAIL!!");
			
			return null;
			
		}

	}

}
