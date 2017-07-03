package com.nemanjam.ebook;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.nemanjam.ebook.service.StorageService;

@SpringBootApplication
public class EbookApplication extends SpringBootServletInitializer implements CommandLineRunner {

	@Resource
	private StorageService storageService;
	 
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(EbookApplication.class);
    }  
	
	public static void main(String[] args) {
		SpringApplication.run(EbookApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
 		//storageService.deleteAll();
 		//storageService.init();
 	}
	
}