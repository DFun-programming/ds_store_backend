package com.newbies.ecommerce.configs;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.newbies.ecommerce.filters.JwtGeneratorFilter;
import com.newbies.ecommerce.filters.JwtValidatorFilter;
import com.newbies.ecommerce.helpers.RestAuthenticationEntryPoint;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableMethodSecurity(
		  prePostEnabled = true, 
		  securedEnabled = true, 
		  jsr250Enabled = true)
public class SecurityConfigure {

	 @Autowired
	   private RestAuthenticationEntryPoint authenticationEntryPoint;
	 
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception
	{
		http.authorizeHttpRequests().requestMatchers("/auth/v1/register","/auth/v1/new-password","upload/pic","/category/v1/getAllSubCategory","/category/v1/getAllSubCategory/**","/category/v1/allCategories" , "/auth/v1/sendpasswordToken","/auth/v1/forgot-pass","/auth/v1/sendotp").permitAll(). anyRequest().authenticated().and()
			.cors().configurationSource(new CorsConfigurationSource() {
				
				@Override
				public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
					// TODO Auto-generated method stub
					CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
                    config.setAllowedMethods(Collections.singletonList("*"));
                    config.setAllowCredentials(true);
                    config.setAllowedHeaders(Collections.singletonList("*"));
                    config.setExposedHeaders(Arrays.asList("Authorization"));
                    config.setMaxAge(3600L);
                    return config;
				}
			}).and().csrf().disable()
			.formLogin().and().httpBasic(httpBasic->httpBasic.authenticationEntryPoint(authenticationEntryPoint))
			.addFilterAfter(new JwtGeneratorFilter(), BasicAuthenticationFilter.class)
			.addFilterBefore(new JwtValidatorFilter(), BasicAuthenticationFilter.class);
//			.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
		return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(11);
	}
}
