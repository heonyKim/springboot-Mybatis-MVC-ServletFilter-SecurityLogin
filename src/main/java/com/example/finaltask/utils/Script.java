package com.example.finaltask.utils;

public class Script {
	
	public String href(String location) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script>");
		sb.append("location.href='" + location + "'");
		sb.append("</script>");

		return sb.toString();
	}
}