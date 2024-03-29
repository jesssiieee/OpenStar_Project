package com.openstar.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component // spring bean == autowired
public class FileManagerService {
	
	// 실제 업로드 된 이미지가 저장될 경로(서버)
	// ★★★ \\images 뒤에 '/'를 붙여줘야 뒤에 추가 경로가 붙었을 때 정상작동
//	public static final String FILE_UPLOAD_PATH = "D:\\Limhyeona\\6_spring_project\\MEMO\\memo_workspace\\images/";
	public static final String FILE_UPLOAD_PATH = "/Users/jessie/Desktop/jessie/7_openstar/openstar/openstar_workspace/images/";
	
	
	// input: File 원본, userLoginId(폴더명)
	// ouput: 이미지 경로
	public String saveFile(String loginId, MultipartFile file) {
		// 폴더(디렉토리) 생성
		// 예: aaaa_182173810/sun.png
		String  directoryName = loginId + "_" + System.currentTimeMillis(); // 파일명 중복 방지를 위해 생성시간폴더를 생성한 뒤 이미지 저장
		String filePath = FILE_UPLOAD_PATH + directoryName; // "D:\\Limhyeona\\6_spring_project\\MEMO\\memo_workspace\\images/aaaa_182173810"
		
		File directory = new File(filePath);
		if(directory.mkdir() == false) {
			// 폴더 생성 실패 시, 이미지 경로는 null로 리턴
			return null;
		} 
		
		// 파일 업로드: byte 단위로 업로드
		try {
			byte[] bytes = file.getBytes();
			// ★★★★★ 한글 이름 이미지는 올릴 수 없으므로 나중에 영문자로 바꿔서 올리기 
			Path path = Paths.get(filePath + "/" + file.getOriginalFilename());
			// import java.nio.file.paths
			Files.write(path, bytes); // 실제 파일 업로드
		} catch (IOException e) {
			e.printStackTrace();
			return null; // 이미지 업로드 실패 시, null return
		}
		
		// http://localhost/images/aaaa_182173810 <== path mapping
		// 파일 업로드가 성공했으면 웹 이미지 url path를 리턴
		// 주소는 이렇게 될 것이다.(예언)
		// /images/aaaa_182173810/sun.png
		return "/images/" + directoryName + "/" + file.getOriginalFilename();
		// /images/aaaa_1705483559044/cat-8361048_1280.jpg
		
	}
	
	// input: 기존 imagepath
	// output: X
	public void deleteFile(String imagePath) { // /images/aaaa_1705560320800/cat-8361048_1280.jpg
		// "D:\\Limhyeona\\6_spring_project\\MEMO\\memo_workspace\\images/" +  /images/aaaa_1705560320800/cat-8361048_1280.jpg
		// => "D:\\Limhyeona\\6_spring_project\\MEMO\\memo_workspace\\images/aaaa_1705560320800/cat-8361048_1280.jpg
		// 주소에 겹치는 /images/를 지운다.
		Path path = Paths.get(FILE_UPLOAD_PATH + imagePath.replace("/images/", "")); 
		
		// 삭제할 이미지가 존재하는가
		if(Files.exists(path)) {
			// 이미지 삭제
			try {
				Files.delete(path);
			} catch (IOException e) {
//				e.printStackTrace(); // 로거로 대체
				log.info("[FileManagerService] 이미지 삭제 실패, path:{}", path.toString());
				return;
			}
			
			// 폴더(디렉토리) 삭제
			// 이미지의 경로에서 부모단계로 올라가면 파일을 지정할 수 있음
			path = path.getParent();
			if(Files.exists(path)) {
				try {
					Files.delete(path);
				} catch (IOException e) {
//					e.printStackTrace();
					log.info("[FileManagerService] 폴더 삭제 실패, path:{}", path.toString());
					// 마지막 단계이므로 return은 굳이 필요 없음
				}
			}
		}
		
	}

}
