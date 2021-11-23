package com.revature.service;

import com.revature.exception.UnauthorizedException;
import com.revature.model.User;

// Authentication is different than Authorization
// Authentication is about providing credentials to identify who you are
// Authorization is about checking whether you have the permissions to access a particular thing
public class AuthorizationService {

	public void authorizeRegularAndAdmin(User user) throws UnauthorizedException {
		// If the user is not either a regular role or admin role
		if (user == null || !(user.getUserRole().equals("regular") || user.getUserRole().equals("admin"))) {
			throw new UnauthorizedException("You must have a regular or admin role to access this resource");
		}
	}
	
	public void authorizeAdmin(User user) throws UnauthorizedException {
		if (user == null || !user.getUserRole().equals("admin")) {
			throw new UnauthorizedException("You must have an admin role to access this resource");
		}
	}
	
}
