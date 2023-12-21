package com.newbies.ecommerce.configs;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

@Configuration
public class CloudinaryConfig {
	
	@Bean
	public Cloudinary getCloudinary() 
	{
		
		Map config = new HashMap();
		
		config.put("cloud_name", "dzlr3u4a4");
		config.put("api_key", "691677614448193");
		config.put("api_secret", "J3j93A9EmesULig-jiIFD2yxRvw");
		config.put("secure", true);
		config.put("folder", "dsStore");
		
		return new Cloudinary(config);
		
	}

}
