package com.newbies.ecommerce.services;

import java.util.List;
import java.util.Map;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.newbies.ecommerce.entities.ResetPass;
import com.newbies.ecommerce.entities.Users;
import com.newbies.ecommerce.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CloudinaryUploadService cloudUploadService;
	
	public boolean saveUser(Users user)
	{
		if( userAlreadyPresent(user.getEmail()))
			return false;
		userRepo.save(user);
		return true;
	}
	public boolean userAlreadyPresent(String email) {
		List<Users> users = userRepo.findAllByEmail(email);
		if(!users.isEmpty())
			return true;
		return false;
	}
	public boolean otpMatcher(String otp , String otpFromCache)
	{
		System.out.println(otp + "  " +otpFromCache);
		if(otp.equalsIgnoreCase( otpFromCache))
		{
			return true;
		}
		return false;
		
	}
	public Users findUserByEmail(String email) {
		// TODO Auto-generated method stub
		List<Users> users = userRepo.findAllByEmail(email);

		return users.get(0);
		
	}
	public void update(Users user)
	{
		Users existedUser = userRepo.findAllByEmail(user.getEmail()).get(0);
		existedUser.setPassword(user.getPassword());
		userRepo.save(existedUser);
	}

	public Users updateDisplayPicture(MultipartFile file ,long userId) throws Exception
	{
		Users existedUser = userRepo.findById(userId).orElse(null);
		Map map =  cloudUploadService.upload(file);
		String imageString = map.get("secure_url").toString();
		existedUser.setImage(imageString);
		userRepo.save(existedUser);
		return existedUser;
	}
	public Users updatePassword(ResetPass resetP , long userId) throws Exception {
		
		Users existedUser = userRepo.findById(userId).orElse(null);
		if(passwordEncoder.matches(resetP.getPassword(), existedUser.getPassword())){
		existedUser.setPassword(passwordEncoder.encode(resetP.getNewPassword()));
		userRepo.save(existedUser);
		return existedUser;}
		throw new Exception(" Wrong Credentials ");
		
	}
	
}
