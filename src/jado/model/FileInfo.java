package jado.model;

import org.springframework.web.multipart.MultipartFile;

public class FileInfo {
	String url;
	String type;
	String localLocation;
	MultipartFile file;
	

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
}
