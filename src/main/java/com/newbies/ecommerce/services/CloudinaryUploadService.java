package com.newbies.ecommerce.services;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.EagerTransformation;
import com.cloudinary.Transformation;
import com.newbies.ecommerce.configs.CloudinaryConfig;

@Service
public class CloudinaryUploadService {

	@Autowired
	private Cloudinary cloudinary;
	
	public Map upload(MultipartFile file) throws Error, IOException
	{
////		if(file.getSize() >= 5000000)
////		{
//			return this.cloudinary.uploader().upload(file.getBytes(), Map.of("folder","dsStore","transformation", 
//				    new Transformation().quality(1)));
////		}
			
		return this.cloudinary.uploader().upload(file.getBytes(), Map.of("folder","ecommerce","use_filename",true));
		
	}
	
}
