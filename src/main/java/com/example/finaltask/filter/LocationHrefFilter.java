package com.example.finaltask.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.finaltask.utils.Script;

@WebFilter("/locationHref")
public class LocationHrefFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request,
			ServletResponse response,
			FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
		
		String url = "http://"+request.getParameter("url");
		if(url.equals("http://google")) {
			url = "http://www.gmail.com";
		}
		res.sendRedirect(url);
	}
}
