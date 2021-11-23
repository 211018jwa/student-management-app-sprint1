package com.revature.service;

import java.security.InvalidParameterException;
import java.sql.SQLException;
import java.util.List;

import com.revature.dao.AssignmentDAO;
import com.revature.exception.AssignmentAlreadyGradedException;
import com.revature.exception.AssignmentNotFoundException;
import com.revature.model.Assignment;
import com.revature.model.User;

public class AssignmentService {

	private AssignmentDAO assignmentDao;
	
	public AssignmentService() {
		this.assignmentDao = new AssignmentDAO();
	}
	
	public AssignmentService(AssignmentDAO assignmentDao) {
		this.assignmentDao = assignmentDao;
	}
	
	// If the currently logged in User is an associate, we should only grab the assignments that belong to that associate
	// If the currently logged in User is a trainer, we should grab ALL assignments
	public List<Assignment> getAssignments(User currentlyLoggedInUser) throws SQLException {
		List<Assignment> assignments = null;
		
		if (currentlyLoggedInUser.getUserRole().equals("trainer")) {
			assignments = this.assignmentDao.getAllAssignments();
		} else if (currentlyLoggedInUser.getUserRole().equals("associate")) {
			assignments = this.assignmentDao.getAllAssignmentsByAssociate(currentlyLoggedInUser.getId());
		}
		
		return assignments;
	}

	// We only want to be able to assign a grade once
	// Once we have already graded something, it is permanent, you can't change it from there
	
	// 0. Check if the assignment exists or not
	// 1. Check if the assignment already has a grade
	// 		- if it does, throw an AlreadyGradedException
	// 2. If it doesn't already have a grade, proceed onwards to assign a grade to the assignment
	public Assignment changeGrade(User currentlyLoggedInUser, String assignmentId, int grade) throws SQLException, AssignmentNotFoundException, AssignmentAlreadyGradedException {
		try {
			int id = Integer.parseInt(assignmentId);
			
			Assignment assignment = this.assignmentDao.getAssignmentById(id);
			
			// 0
			if (assignment == null) {
				throw new AssignmentNotFoundException("Assignment with id " + assignmentId + " was not found");
			}
			
			// 1
			if (assignment.getGraderId() == 0) { // if it's 0, it means there's no grader for the assignment yet
				this.assignmentDao.changeGrade(id, grade, currentlyLoggedInUser.getId());
			} else { // if it has already been graded by someone, and we're trying to change the grade here, that shouldn't be allowed
				throw new AssignmentAlreadyGradedException("Assignment has already been graded, so we cannot assign another grade to the"
						+ " assignment");
			}
			
			return this.assignmentDao.getAssignmentById(id);
		} catch(NumberFormatException e) {
			throw new InvalidParameterException("Assignment id supplied must be an int");
		}
		
		
	}
	
}
