package com.example.finaltask.config;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bouncycastle.asn1.cmc.DecryptedPOP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.finaltask.controller.UserController;
import com.example.finaltask.utils.RSA;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public Filter loginFilter() {
		return new Filter() {
			
			@Override
			public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
					throws IOException, ServletException {
				HttpServletRequest req = (HttpServletRequest) request;
				HttpServletResponse res = (HttpServletResponse) response;
				
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				
				System.out.println("encrypted username : "+username);
				System.out.println("encrypted password : "+password);
				
				HttpSession session = req.getSession();
				PrivateKey privateKey = (PrivateKey) session.getAttribute(UserController.RSA_KEY);
				RSA rsa = new RSA();
				
				try {
					username = rsa.decryptRsa(privateKey, username);
					password = rsa.decryptRsa(privateKey, password);

					System.out.println("decrypted username : "+username);
					System.out.println("decrypted password : "+password);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				request.setAttribute("username", username);
				request.setAttribute("password", password);
				chain.doFilter(request, response);
			}
		};
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.cors().disable();
		
		http.addFilterBefore(loginFilter(), UsernamePasswordAuthenticationFilter.class);
		
		http.authorizeRequests()
//		.antMatchers("/user/**").authenticated()
		.anyRequest().permitAll()
		.and()
		.formLogin()
		.usernameParameter("username")
		.loginPage("/loginForm")
		.loginProcessingUrl("/user/loginProcess")
		.defaultSuccessUrl("/")
		.failureUrl("/loginForm")
		.and()
		.logout()
		.logoutSuccessUrl("/");
	}

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encodePWD());
	}
}