package com.newbies.ecommerce.configs;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.newbies.ecommerce.entities.Users;
import com.newbies.ecommerce.repositories.UserRepository;
import com.newbies.ecommerce.services.UserService;

@Component
public class EmailPassWordAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		System.out.println("Security context - "+SecurityContextHolder.getContext().getAuthentication());
		String email = authentication.getName();
		String pass = authentication.getCredentials().toString();
		
		if(!userService.userAlreadyPresent(email))
			throw new UsernameNotFoundException("User does not exist");
		Users user = userRepo.findAllByEmail(email).get(0); 
		String userPass = user.getPassword();
		System.out.println(pass + " " + userPass);
		if(passwordEncoder.matches(pass, userPass)) 
		{
			
			System.out.println(" matched ");
			return new UsernamePasswordAuthenticationToken(email, null ,getGrantedAuthorities(user.getAccountType()));
			
		}
		else
		{	
			throw new BadCredentialsException(" Wrong credentials ");
		}
	}
    private List<GrantedAuthority> getGrantedAuthorities(String roles )
    {
    	List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
    	
    		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+roles));
    	
    	return grantedAuthorities;
    }

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
		
	}
	
	
	
	

}
