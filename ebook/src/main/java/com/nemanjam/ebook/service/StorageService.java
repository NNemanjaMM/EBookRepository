package com.nemanjam.ebook.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {
	
	private final Path rootLocation = Paths.get("storage");
 
	public String store(MultipartFile file){
		String fileName = null;
		try {
			fileName = file.getOriginalFilename();
			if(!fileName.endsWith(".pdf")) {
				return fileName;
			}
			Path filePath = this.rootLocation.resolve(fileName);
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
            	fileName = fileName.substring(0, fileName.length() - 4);
				fileName = fileName + "_"  + System.currentTimeMillis() + ".pdf";
            }
            Files.copy(file.getInputStream(), this.rootLocation.resolve(fileName));
        } catch (Exception e) {
        	throw new RuntimeException();
        }
		return fileName;
	}
 
    public Resource loadFile(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            } else {
            	throw new RuntimeException();
            }
        } catch (MalformedURLException e) {
        	throw new RuntimeException();
        }
    }
    
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }
 
    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage!");
        }
    }

}