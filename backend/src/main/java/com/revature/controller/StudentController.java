package com.revature.controller;

import com.revature.model.User;
import com.revature.service.AuthorizationService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class StudentController implements Controller {

	private AuthorizationService authorizationService;
	
	public StudentController() {
		this.authorizationService = new AuthorizationService();
	}
	
	// This will be a "protected" endpoint that can only be accessed when you are logged in as either admin or regular
	private Handler getStudentById = (ctx) -> {
		User user = (User) ctx.req.getSession().getAttribute("currentuser");
		this.authorizationService.authorizeRegularAndAdmin(user); // This will authorize either admins or regular users
	
		// Any other logic goes below
		String id = ctx.pathParam("studentId");
		
		
		
		
	};
	
	// This will be a protected endpoint that can only be accessed when you are logged in as an admin
	private Handler addStudent = (ctx) -> {
		User user = (User) ctx.req.getSession().getAttribute("currentuser");
		this.authorizationService.authorizeAdmin(user); // This will authorize only admins
		
		// Any other logic goes below
		
		
		
		
		
		
	};

	@Override
	public void mapEndpoints(Javalin app) {
		app.get("/students/{studentId}", getStudentById);
		app.post("/students", addStudent);
	}
	
}
