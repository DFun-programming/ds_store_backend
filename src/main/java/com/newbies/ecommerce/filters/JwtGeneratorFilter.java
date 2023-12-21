package com.newbies.ecommerce.filters;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.newbies.ecommerce.constants.SecurityConstants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtGeneratorFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			//Sha256
			SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_SECRET.getBytes(StandardCharsets.UTF_8));
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Date today = new Date();
			System.out.println(" I am running ");
			
			Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));
			var jwtToken = Jwts.builder().setIssuer("Ecommerce")
							.setIssuedAt(new Date() )
							.claim("username",auth.getName() )
							.claim("authorities", getStudentRoles((List<GrantedAuthority>) auth.getAuthorities()))
							.setExpiration(tomorrow)
							.signWith(key).compact();
			
			System.out.println("token "+jwtToken);
			response.setHeader(SecurityConstants.JWT_HEADER, jwtToken);	
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		
		 filterChain.doFilter(request, response);
	}

    private String getStudentRoles(List<GrantedAuthority> collection) {
        Set<String> authoritiesSet = new HashSet<>();
        for (GrantedAuthority authority : collection) {
            authoritiesSet.add(authority.getAuthority());
        }
        
        return String.join(",", authoritiesSet);
    }
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		// TODO Auto-generated method stub
		return (!request.getServletPath().equals("/auth/v1/user"));
	}
	
	
	

}
