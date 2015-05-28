package jado.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.web.multipart.MultipartFile;

import core.exception.NotSupportFileSizeException;
import core.exception.NotSupportFileTypeException;
import core.mail.UUIDGenerator;

public class FileInfo {
	private Long MAX_SUPPORT_FILE_SIZE = (long) (1024 *1024 * 2);
	private String url;
	private String type;
	private String localLocation;
	private MultipartFile file;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	public void setLocalLocation(String localLocation) {
		this.localLocation = localLocation;
	}

	public String getLocalLocation() {
		return localLocation;
	}
	public void updateLocalLocation(){
		String fileType = file.getContentType().replace('/', '.');
		this.localLocation += "/" + url + fileType;
	}

	public void setFileNameByUUID() {
		String date = new SimpleDateFormat("yyMMdd", Locale.KOREAN).format(new Date());
		String uuid = UUIDGenerator.createUUID();
		url = "img"+date+uuid;
	}

	
	public void checkUploadFIle(){
		checkFileTypeIsImage();
		checkFileSize();
	}
	
	private void checkFileTypeIsImage() throws NotSupportFileTypeException{
		String contentType = file.getContentType();
		if( ! ("image/gif".equals(contentType) || "image/jpeg".equals(contentType) || "image/bmp".equals(contentType))) {
			throw new NotSupportFileTypeException("지원하지 않는 형식의 파일이 업로드 되었습니다. gif, jpg, jpeg, bmp 파일만 업로드 가능하니다");
		}
	}

	private void checkFileSize() {
		if(file.getSize() > MAX_SUPPORT_FILE_SIZE) {
			throw new NotSupportFileSizeException("업로드한 파일의 크기가 너무 큽니다. 업로드 가능한 파일의 최대 크기는" + MAX_SUPPORT_FILE_SIZE /1024/1024+ "MB 입니다");
		}
	}
}
