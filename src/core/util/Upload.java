package core.util;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class Upload {

	private String globalLocation;

	public void setLocation(String globalLocation) {
		this.globalLocation = globalLocation;
	}

	public Upload() {
	}

	public void uploadFile(MultipartFile maltipartFile, String localLocation) throws IllegalStateException, IOException {
		File file = new File(globalLocation + localLocation);
		maltipartFile.transferTo(file);
	}
}
