package com.newbies.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.newbies.ecommerce.entities.EmailDetails;
import com.newbies.ecommerce.entities.ResetPass;
import com.newbies.ecommerce.entities.Users;
import com.newbies.ecommerce.services.EmailService;
import com.newbies.ecommerce.services.OtpGeneratorService;
import com.newbies.ecommerce.services.UserService;


@RestController
@RequestMapping("/auth/v1")
public class UserController {
	
	   @Autowired
	   private EmailService emailService;
	   @Autowired
	   private EmailDetails emailDetails;
	   @Autowired
	   private OtpGeneratorService otpGeneratorService;
	   @Autowired
	   private UserService userService;
	   @Autowired
	   private PasswordEncoder passwordEncoder;
	   
	
	@PostMapping("/register")
	public ResponseEntity<String> saveUser(@RequestBody Users user)
	{
		try {
			boolean saved = false;
			System.out.println(user.toString());
			if(user.getName().equals("") && user.getEmail().equals("") && user.getPassword().equals("") && user.getPhnNo().equals("")
					&& user.getAddress().equals("") && user.getAccountType().equals("") && user.getOtp().equals("")  )
			{
				return new ResponseEntity<>(" All fields are required ",HttpStatusCode.valueOf(400));
			}
			System.out.println("Executing");

			Integer otpCodeFromCache =otpGeneratorService.getOPTByKey(user.getEmail());
			String otpCode = String.valueOf(otpCodeFromCache);
			if(userService.otpMatcher(otpCode, user.getOtp())) {
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				saved = userService.saveUser(user);
			}
			else {
				return new ResponseEntity<>("Otp Does not match or expired ",HttpStatusCode.valueOf(401));
			}
			
			if(saved)
				return new ResponseEntity<>(" User Successfully registered ",HttpStatusCode.valueOf(201));
			else
				return new ResponseEntity<>(" User with this email already exists ",HttpStatusCode.valueOf(401));
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>( e.getMessage() ,HttpStatusCode.valueOf(500));
		}
			
	}
	@PostMapping("/sendotp")
	public ResponseEntity<String> sendMail(@RequestBody Users user)
	    {
			try {
				String email = user.getEmail();
				emailDetails.setRecipient(email);
				otpGeneratorService.generateOTP(email);
				Integer otp = otpGeneratorService.getOPTByKey(email);
				emailDetails.setSubject(" Email verification ");
				emailDetails.setMsgBody(" Hello sir/madam Your otp is : "+otp );
				if(userService.userAlreadyPresent(email))
				{
					
					
					return new ResponseEntity<>(" User with this email already exists ",HttpStatusCode.valueOf(401));
				}
		        String status
		            = emailService.sendSimpleMail(emailDetails);
		 
		        return new ResponseEntity<>(status,HttpStatusCode.valueOf(201));
			} catch (Exception e) {
				// TODO: handle exception
				return new ResponseEntity<>(e.getMessage(),HttpStatusCode.valueOf(500));
			}
	    }
	
	@GetMapping("/user")
	public ResponseEntity<Users> getUser(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		return new ResponseEntity<Users>( userService.findUserByEmail(email),HttpStatusCode.valueOf(201));
		
	}
	@GetMapping("/test")
	public ResponseEntity<String> testControl(){
		return new ResponseEntity<String>(
				" Hi Ami Baccha balok " , 
				HttpStatusCode.valueOf(200) );
	}
	@PostMapping("/sendpasswordToken")
	public ResponseEntity<String>sendToken(@RequestBody Users user){
		
		String email = user.getEmail();
		System.out.println("Email from send token "+email);
		emailDetails.setRecipient(email);
		Integer token = otpGeneratorService.generateOTP("token-"+email);
		System.out.println("token "+token);
		emailDetails.setSubject(" Reset Password ");
		emailDetails.setMsgBody(" Hello sir/madam Your reset password token is is : "+token );
		if(!userService.userAlreadyPresent(email))
		{
			return new ResponseEntity<>(" User with this email does not exist ",HttpStatusCode.valueOf(401));
		}
        String status
            = emailService.sendSimpleMail(emailDetails);
 
        return new ResponseEntity<>(status + " "+token ,HttpStatusCode.valueOf(201));
		
	}
	@PutMapping("/forgot-pass")
	public ResponseEntity<String> forgotPass(@RequestBody Users user){
		
		//mail with Link and token
		System.out.println("Email"+user.getEmail());
		Integer otpCodeFromCache =otpGeneratorService.getOPTByKey("token-"+user.getEmail());
		String otpCode = String.valueOf(otpCodeFromCache);
		System.out.println(otpCodeFromCache + " and then value is  "+otpCode);
		if(userService.otpMatcher(otpCode, user.getOtp())) {
			
			return new ResponseEntity<String>("Otp Matched successfully",HttpStatusCode.valueOf(201));
			
		}
		else {
			return new ResponseEntity<>("Otp Does not match or expired ",HttpStatusCode.valueOf(401));
		}
		
		
	}
	@PutMapping("/new-password")
	public ResponseEntity<String>saveNewPass(@RequestBody Users user)
	{
		try {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userService.update(user);
			return new ResponseEntity<String>("Password updated successfully",HttpStatusCode.valueOf(201));
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(" An fatal Error Occurred",HttpStatusCode.valueOf(401));
		}
		
	}
	@PutMapping("/updateImage/{userId}")
	public ResponseEntity<Users> updateD( @RequestParam("displayPicture") MultipartFile image , @PathVariable String userId) throws Exception
	{
		long id = Long.parseLong(userId);
		Users user = null;
		try
		{
			user = userService.updateDisplayPicture(image, id);
		}
		catch(Exception e)
		{
			throw new Exception("Problem in upload");
		}
		
		return new ResponseEntity<Users>(user,HttpStatusCode.valueOf(201));
		
		
	}
	@PostMapping("/reset-password")
	public ResponseEntity<Users> resetPassword(@RequestBody ResetPass resetP , @RequestParam("user") long userId) throws Exception
	{
		Users user= null;
		
		try
		{
			user = userService.updatePassword(resetP,userId);
			return new ResponseEntity<Users>(user,HttpStatusCode.valueOf(201));
		}
		catch(Exception e)
		{
			return new ResponseEntity(null,HttpStatusCode.valueOf(400));
		}
		
		
		
		
	}
	
}
