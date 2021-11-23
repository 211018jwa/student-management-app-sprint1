package com.revature.controller;

import java.util.List;

import com.revature.dto.ChangeGradeDTO;
import com.revature.model.Assignment;
import com.revature.model.User;
import com.revature.service.AssignmentService;
import com.revature.service.AuthorizationService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class AssignmentController implements Controller {

	private AuthorizationService authService;
	private AssignmentService assignmentService;
	
	public AssignmentController() {
		this.authService = new AuthorizationService();
		this.assignmentService = new AssignmentService();
	}
	
	private Handler getAssignments = (ctx) -> {
		// guard this endpoint
		// roles permitted: trainer, associate
		User currentlyLoggedInUser = (User) ctx.req.getSession().getAttribute("currentuser");
		this.authService.authorizeAssociateAndTrainer(currentlyLoggedInUser);
		
		// If the above this.authService.authorizeAssociateAndTrainer(...) method did not throw an exception, that means
		// our program will continue to proceed to the below functionality
		List<Assignment> assignments = this.assignmentService.getAssignments(currentlyLoggedInUser);
		
		ctx.json(assignments);
	};
	
	// Who should be able to access this endpoint?
	// only trainers
	private Handler changeGrade = (ctx) -> {
		User currentlyLoggedInUser = (User) ctx.req.getSession().getAttribute("currentuser");
		this.authService.authorizeTrainer(currentlyLoggedInUser);
		
		String assignmentId = ctx.pathParam("id");
		ChangeGradeDTO dto = ctx.bodyAsClass(ChangeGradeDTO.class); // Taking the request body -> putting the data into a new object
		
		Assignment changedAssignment = this.assignmentService.changeGrade(currentlyLoggedInUser, assignmentId, dto.getGrade());
		ctx.json(changedAssignment);
	};
	
	@Override
	public void mapEndpoints(Javalin app) {
		app.get("/assignments", getAssignments);
		app.patch("/assignments/{id}/grade", changeGrade);
	}

}
