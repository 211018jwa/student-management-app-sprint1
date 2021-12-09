package com.revature.model;

import java.util.Objects;

public class Assignment {

	private int id;
	private String assignmentName;
	private int grade;
	private int graderId;
	private int authorId;
	
	private String authorfirstName;
	private String authorlastName;
	private String graderFirstName;
	private String graderLastName;
	
	public Assignment() {
		super();
	}

	public Assignment(int id, String assignmentName, int grade, int graderId, int authorId) {
		super();
		this.id = id;
		this.assignmentName = assignmentName;
		this.grade = grade;
		this.graderId = graderId;
		this.authorId = authorId;
	}
	
	public Assignment(int id, String assignmentName, int grade, int graderId, int authorId, String authorFirstName, String
					authorLastName, String graderFirstName, String graderLastName) {
		this.id = id;
		this.assignmentName = assignmentName;
		this.grade = grade;
		this.graderId = graderId;
		this.authorId = authorId;
		this.authorfirstName = authorFirstName;
		this.authorlastName = authorLastName;
		this.graderFirstName = graderFirstName;
		this.graderLastName = graderLastName;
		
	}

	public String getAuthorfirstName() {
		return authorfirstName;
	}

	public void setAuthorfirstName(String authorfirstName) {
		this.authorfirstName = authorfirstName;
	}

	public String getAuthorlastName() {
		return authorlastName;
	}

	public void setAuthorlastName(String authorlastName) {
		this.authorlastName = authorlastName;
	}

	public String getGraderFirstName() {
		return graderFirstName;
	}

	public void setGraderFirstName(String graderFirstName) {
		this.graderFirstName = graderFirstName;
	}

	public String getGraderLastName() {
		return graderLastName;
	}

	public void setGraderLastName(String graderLastName) {
		this.graderLastName = graderLastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAssignmentName() {
		return assignmentName;
	}

	public void setAssignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getGraderId() {
		return graderId;
	}

	public void setGraderId(int graderId) {
		this.graderId = graderId;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(assignmentName, authorId, authorfirstName, authorlastName, grade, graderFirstName, graderId,
				graderLastName, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Assignment other = (Assignment) obj;
		return Objects.equals(assignmentName, other.assignmentName) && authorId == other.authorId
				&& Objects.equals(authorfirstName, other.authorfirstName)
				&& Objects.equals(authorlastName, other.authorlastName) && grade == other.grade
				&& Objects.equals(graderFirstName, other.graderFirstName) && graderId == other.graderId
				&& Objects.equals(graderLastName, other.graderLastName) && id == other.id;
	}

	@Override
	public String toString() {
		return "Assignment [id=" + id + ", assignmentName=" + assignmentName + ", grade=" + grade + ", graderId="
				+ graderId + ", authorId=" + authorId + ", authorfirstName=" + authorfirstName + ", authorlastName="
				+ authorlastName + ", graderFirstName=" + graderFirstName + ", graderLastName=" + graderLastName + "]";
	}


}
