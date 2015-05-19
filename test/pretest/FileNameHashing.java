package pretest;

import java.io.File;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mail.UUIDGenerator;

public class FileNameHashing {
	private static final Logger logger = LoggerFactory.getLogger(FileNameHashing.class);
	
	@Test
	public void 파일_이름을_해시화_하자() throws Exception {
		String originalFilePath = "/path/to/img.jpg"; 
		String newFilePath = UUIDGenerator.createUUID();
		String fileType = fileTypeExtract(originalFilePath);
		File changedFile = new File(newFilePath + "." + fileType);
		logger.debug(changedFile.getName());
	}

	private String fileTypeExtract(String originalFilePath) {
		return originalFilePath.split("\\.")[1];
	}
}
