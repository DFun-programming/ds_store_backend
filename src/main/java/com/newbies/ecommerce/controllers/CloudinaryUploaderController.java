package com.newbies.ecommerce.controllers;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.newbies.ecommerce.services.CloudinaryUploadService;



@RestController
public class CloudinaryUploaderController {

	@Autowired
	private CloudinaryUploadService cloudinaryUploadService;
	
	@PostMapping("upload/pic")
	ResponseEntity<Map> uploadPhoto(@RequestParam("image") MultipartFile file)
	{
		try {
			return new ResponseEntity<Map> (cloudinaryUploadService.upload(file),HttpStatusCode.valueOf(201));
		} catch (IOException | Error e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
