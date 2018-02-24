package com.lei.okhttp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport{
	private String username;
	private String password;
	
	public File mPhoto;
	public String mPhotoFileName;
	
	public String upLoadInfo() throws IOException {
		
		System.out.println(username + "," + password);
		if(mPhoto == null) {
			System.out.println(mPhotoFileName + " is Null");
		}
		String dir = ServletActionContext.getServletContext().getRealPath("files");
		File file = new File(dir, "y.jpg");
		FileUtils.copyFile(mPhoto, file);
		
		return null;
	}
	
	public String postFile() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		ServletInputStream is = request.getInputStream();
		
		System.out.println("SessionId =" + request.getSession().getId());
		
		String dir = ServletActionContext.getServletContext().getRealPath("files");
		File file = new File(dir, "a.mp4");
		FileOutputStream fos = new FileOutputStream(file);
		
		int len = 0;
		byte[] buf = new byte[1024];
		
		while((len = is.read(buf)) != -1) {
			fos.write(buf, 0, len);
		}
		fos.flush();
		fos.close();
		
		return null;
	}
	
	public String postString() throws IOException {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		ServletInputStream is = request.getInputStream();
		System.out.println("SessionId =" + request.getSession().getId());
		
		StringBuilder sb = new StringBuilder();
		int len = 0;
		byte[] buf = new byte[1024];
		
		while((len = is.read(buf)) != -1) {
			sb.append(new String(buf, 0, len));
		}
		System.out.println(sb.toString());
		return null;
	}
	
	public String login() throws IOException {
		//userService.login(username,password);
		
		HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println(username + " , " + password);
		System.out.println("SessionId =" + request.getSession().getId());
		
		//与客户端进行交互
		HttpServletResponse response = ServletActionContext.getResponse();
		//拿到writer
		PrintWriter writer = response.getWriter();
		//使用writer写一些东西
		writer.write("login success!");
		writer.flush();
		
		return null;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
