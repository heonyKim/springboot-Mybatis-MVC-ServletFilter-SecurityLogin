package com.example.finaltask.controller;

import javax.servlet.ServletRequest;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.finaltask.security.UserDetail;

@Controller
public class IndexController {

	@GetMapping("")
	public String indexRedirect() {
		return "redirect:/";
	}
	
	@GetMapping("/")
	public String index(@AuthenticationPrincipal UserDetail user) {
		return "index";
	}
	
	@PostMapping("/locationHref")
	public String locationHref(ServletRequest request){
		return "url";
	}
	
	
}
