package org.sagarmala.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.sagarmala.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUitility {
	@Autowired
	Environment environment;

	public String convertMultiPartFileToFileAndUploadToServer(MultipartFile file, String filePath) throws IOException {
		if (filePath == null) {
			try {
				throw new CustomException("File Path cannot be null");
			} catch (CustomException e) {
				System.out.println("File path cannot be nul! Please provide a valid file path...");
				e.printStackTrace();
			}
		}
		Path path = null;
		path = Paths.get(filePath);
		File convFile = new File(path.toString(), file.getOriginalFilename());
		convFile.getParentFile().mkdirs();
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile.getPath().replace("\\","/");

	}
}
