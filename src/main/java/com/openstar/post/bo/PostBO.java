package com.openstar.post.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.openstar.common.FileManagerService;
import com.openstar.post.mapper.PostMapper;

@Service
public class PostBO {
	
	@Autowired
	private PostMapper postmapper;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	public void addPost(int userId, String userLoginId, String content, MultipartFile file) {
		
		
		String imagePath = null;
		
		// 업로드 할 이미지가 있을 때 업로드
		if (file != null) {
			imagePath = fileManagerService.saveFile(userLoginId, file);
		}
		
		postmapper.insertPost(userId, content, imagePath);
		
	}

}
