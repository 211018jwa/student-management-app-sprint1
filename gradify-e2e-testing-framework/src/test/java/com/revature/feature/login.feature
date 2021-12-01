Feature: Login

# " " double quotes are used as inline parameters for the actual gluecode methods. It will be passed in as arguments

Scenario: Invalid password, valid username (negative test)
	Given I am at the login page
	When I type in a username of "bach_tran" 
	But I type in a password of "jlkjljsdf"
	And I click the login button
	Then I should see a message of "Incorrect username and/or password"
	
Scenario: Invalid password, invalid username (negative test)
	Given I am at the login page
	When I type in a username of "lkdsfjlksdjf"
	And I type in a password of "sdfsdfsd"
	And I click the login button
	Then I should see a message of "Incorrect username and/or password"
	
Scenario: Valid password, invalid username (negative test)
	Given I am at the login page
	When I type in a username of "adsfsldjfkljlkjlkjkljlk"
	And I type in a password of "password12345"
	And I click the login button
	Then I should see a message of "Incorrect username and/or password"
	
# Scenario v. Scenario Outline
# Scenario: its a single grouping of steps that will be executed once sequentially
# Scenario Outline: a template of steps that will be executed multiple times sequentially
	
Scenario Outline: Successful associate login
	Given I am at the login page
	When I type in a username of <username>
	And I type in a password of <password>
	And I click the login button
	Then I should be redirected to the associate homepage
	
	Examples:
		| username | password |
		| "jane_doe" | "pass123"  |
		| "john_doe" | "12345"    |
		| "bob_j"    | "54afgh"   |
		| "alice_e"  | "iloveprogramming" |

		
Scenario Outline: Successful trainer login
	Given I am at the login page
	When I type in a username of <username>
	And I type in a password of <password>
	And I click the login button
	Then I should be redirected to the trainer homepage
	
	Examples:
		| username | password |
		| "bach_tran" | "password12345" |		
		| "christina_r" | "programmingisfun" |