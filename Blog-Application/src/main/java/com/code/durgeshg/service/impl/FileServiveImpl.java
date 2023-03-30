package com.code.durgeshg.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.code.durgeshg.service.FileService;
@Service
public class FileServiveImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {

//		Filename
		String name=file.getOriginalFilename();
		System.out.println(name);
		
//      random generate
		
		String randomId=UUID.randomUUID().toString();
		String fileName1=randomId.concat(name.substring(name.lastIndexOf(".")));
		
//		full path
		String filePath=path+File.separator+fileName1;
		
//		create folder if not created
		File f =new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		
		
		
//		copy file
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		return fileName1;
	}

	@Override
	public InputStream getResourse(String path, String fileName) throws FileNotFoundException {
		String fullPath=path+fileName;
		InputStream is=new FileInputStream(fullPath);
		return is;
	}

}
