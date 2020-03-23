package com.example.finaltask.controller;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
 
import javax.crypto.Cipher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.finaltask.model.User;
import com.example.finaltask.security.UserDetail;
import com.example.finaltask.service.UserService;
import com.example.finaltask.utils.RSA;
import com.example.finaltask.utils.Script;

@Controller
public class UserController {
	
	@Autowired
	private UserService uSvc;
	
	@Autowired
	private BCryptPasswordEncoder passwordEnc;
	
	public static String RSA_KEY = "secretKey";
	
/////////////테스트를 위한 계정생성//(필요하기에 임시로 생성)////////////
	@GetMapping("/joinForm")
	public String joinForm() {
		return "joinForm";
	}
	
	@PostMapping("/join")
	public String join(User user) {
		
		String rawPassword = user.getPassword();
		String password = passwordEnc.encode(rawPassword);
		user.setPassword(password);
		uSvc.join(user);
		
		return "redirect:/";
	}
////////////////////////////////////////////////////////////////////////
	@GetMapping("/loginForm")
	public String loginForm(@AuthenticationPrincipal UserDetail user
			,HttpServletRequest request
			, HttpServletResponse response) {
		
		if(user!=null) {
			return "redirect:/";
		}else {
			RSA rsa = new RSA();
			rsa.initRsa(request);
			return "loginForm";
		}
		
	}
	
	@PostMapping("/user/login")
	public @ResponseBody String userLogin(String username, String password,Model model) {
		
		System.out.println("asdf");
		model.addAttribute("username",username);
		model.addAttribute("password",password);
		
		Script script = new Script();
		return script.href("/user/loginProcess");

	}
	
	
}
