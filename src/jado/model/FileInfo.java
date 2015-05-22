package jado.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.web.multipart.MultipartFile;

import core.mail.UUIDGenerator;

public class FileInfo {
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
}
