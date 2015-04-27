package core.util;

import jado.model.FileInfo;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class Upload {
	
	private String globalLocation;
	
	@Value("${image.location}")
	public void setLocation(String globalLocation) {
		this.globalLocation = globalLocation;
	}
	
	public Upload() { }
	
	public void uploadFile(MultipartFile maltipartFile, String localLocation) throws IllegalStateException, IOException {
		File file = new File(globalLocation+localLocation);
		maltipartFile.transferTo(file);
	}

}
