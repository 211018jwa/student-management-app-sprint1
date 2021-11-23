package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Assignment;
import com.revature.utility.JDBCUtility;

public class AssignmentDAO {

	public List<Assignment> getAllAssignments() throws SQLException {
		try (Connection con = JDBCUtility.getConnection()) {
			List<Assignment> assignments = new ArrayList<>();
			
			String sql = "SELECT * FROM httpsession_demo.assignments";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String assignmentName = rs.getString("assignment_name");
				int grade = rs.getInt("grade");
				int graderId = rs.getInt("grader_id");
				int authorId = rs.getInt("author_id");
				
				Assignment assignment = new Assignment(id, assignmentName, grade, graderId, authorId);
				
				assignments.add(assignment);
			}
			
			return assignments;
		}
	}
	
	public List<Assignment> getAllAssignmentsByAssociate(int associateId) throws SQLException {
		try (Connection con = JDBCUtility.getConnection()) {
			List<Assignment> assignments = new ArrayList<>();
			
			String sql = "SELECT * FROM httpsession_demo.assignments WHERE author_id = ?";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, associateId);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String assignmentName = rs.getString("assignment_name");
				int grade = rs.getInt("grade");
				int graderId = rs.getInt("grader_id");
				int authorId = rs.getInt("author_id");
				
				Assignment assignment = new Assignment(id, assignmentName, grade, graderId, authorId);
				
				assignments.add(assignment);
			}
			
			return assignments;
		}
	}

	public Assignment getAssignmentById(int assignmentId) throws SQLException {
		try (Connection con = JDBCUtility.getConnection()) {
			
			String sql = "SELECT * FROM httpsession_demo.assignments WHERE id = ?";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, assignmentId);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				int id = rs.getInt("id");
				String assignmentName = rs.getString("assignment_name");
				int grade = rs.getInt("grade");
				int graderId = rs.getInt("grader_id");
				int authorId = rs.getInt("author_id");
				
				return new Assignment(id, assignmentName, grade, graderId, authorId);
			} else {
				return null;
			}
			
		}
	}

	public void changeGrade(int id, int grade, int graderId) throws SQLException {
		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "UPDATE httpsession_demo.assignments "
					+ "SET "
					+ "grade = ?, "
					+ "grader_id = ? "
					+ "WHERE id = ?;";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, grade);
			pstmt.setInt(2, graderId);
			pstmt.setInt(3, id);
			
			int updatedCount = pstmt.executeUpdate();
			
			if (updatedCount != 1) {
				throw new SQLException("Something bad occurred when trying to update grade");
			}
		}
		
	}
	
}
