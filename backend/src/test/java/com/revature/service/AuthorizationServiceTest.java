package com.revature.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.exception.UnauthorizedException;
import com.revature.model.User;

public class AuthorizationServiceTest {

	private AuthorizationService authService;
	
	@BeforeEach
	public void setup() {
		this.authService = new AuthorizationService();
	}
	
	@Test
	public void authorizeAssociateAndTrainer_negativeTest_userIsAssociateButRequiresTrainerPermissions() throws UnauthorizedException {
		User user = new User(1, "Bach", "Tran", "bach_tran", "pass123", "associate"); 
		
		
		Assertions.assertThrows(UnauthorizedException.class ,() -> {
			this.authService.authorizeTrainer(user);
		});
		
	}
	
}
