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
	
	
	/*
	 * authorizeTrainer
	 */
	@Test
	public void authorizeTrainer_negativeTest_userIsAssociateButRequiresTrainerPermissions() throws UnauthorizedException {
		User user = new User(1, "Bach", "Tran", "bach_tran", "pass123", "associate"); 
		
		
		Assertions.assertThrows(UnauthorizedException.class ,() -> {
			this.authService.authorizeTrainer(user);
		});
		
	}
	
	@Test
	public void authorizeTrainer_negativeTest_userIsNull() {		
		Assertions.assertThrows(UnauthorizedException.class, () -> {
			this.authService.authorizeTrainer(null);
		});
	}
	
	@Test
	public void authorizeTrainer_positiveTest_userIsATrainer() throws UnauthorizedException {
		User user = new User(1, "Bach", "Tran", "bach_tran", "pass123", "trainer");
		
		this.authService.authorizeTrainer(user);
	}
	
	/*
	 * authorizeAssociate
	 */
	@Test
	public void authorizeAssociate_negativeTest_userIsTrainerButRequiresAssociateRole() {
		User user = new User(1, "Bach", "Tran", "bach_tran", "pass123", "trainer");
		
		Assertions.assertThrows(UnauthorizedException.class, () -> {
			this.authService.authorizeAssociate(user);
		});
		
	}

	@Test
	public void authorizeAssociate_negativeTest_userIsNull() {
		Assertions.assertThrows(UnauthorizedException.class, () -> {
			this.authService.authorizeAssociate(null);
		});
	}
	
	@Test
	public void authorizeAssociate_positiveTest_userIsAnAssociate() throws UnauthorizedException {
		User user = new User(1, "Bach", "Tran", "bach_tran", "pass123", "associate");
		
		this.authService.authorizeAssociate(user);
	}
	
	
}
