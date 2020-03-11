package com.bridgelabz.fundoonote.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonote.dto.Register;
import com.bridgelabz.fundoonote.dto.UserLogin;
import com.bridgelabz.fundoonote.entity.User;
import com.bridgelabz.fundoonote.response.Response;
import com.bridgelabz.fundoonote.service.UserService;
import com.bridgelabz.fundoonote.utility.JwtGenerator;

@RestController
@RequestMapping("/users")
//@PropertySource("classpath:message.property")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtGenerator generator;
	
//	@Autowired
//	private Environment env;

	/*
	 * API for user login
	 */

	@PostMapping(value = "/login")
	public ResponseEntity<Response> loginUser(@Valid @RequestBody UserLogin user,BindingResult result) {
		if(result.hasErrors())
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new Response(result.getAllErrors().get(0).getDefaultMessage(),200));
		User results = userService.login(user);
		if (results != null) {
			  // generator.jwtToken(results.getUid());
			return ResponseEntity.status(HttpStatus.ACCEPTED)
			              .body(new Response("user login succesfully..",200));
		}
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//				.body(new Response("Loggined in Failed"));
		return null;

	}
	
	/* API for user register */

	@PostMapping(value = "/add-user")
	public ResponseEntity<Response> register(@Valid @RequestBody Register userRecord,BindingResult result) {
		if(result.hasErrors())
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new Response(result.getAllErrors().get(0).getDefaultMessage(),200));
		User user = userService.register(userRecord);
		if (user != null) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new Response("user register successfully..",200));
		}
		return null; 
	}

	@PostMapping(value = "/{emailId}")
	public ResponseEntity<Response> emailVerify(@PathVariable String emailId) {

		String result = userService.emailVerify(emailId);
		if (result != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new Response("email sent Successfully",200));
		}
		return null; 
	}

	/* API for user Forgot Passsword */

	@PutMapping(value = "/{newPassword}/{token}")
	public ResponseEntity<Response> forgetPassword(@PathVariable String newPassword,@PathVariable String token) {

		User result = userService.forgotPassword(newPassword,token);
		if (result != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new Response("Password Updated Successfully",200));
		}
		return null; 
	}

	/* API for verifying the token generated for the email */

	@GetMapping(value="/verify/{token}")
	public ResponseEntity<Response> verify(@PathVariable("token") String token) throws Exception {
		boolean verification = userService.verify(token);
		if (verification) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("verified",200));
		}
		//return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("not verified"));
		return null;
	}
	
	@GetMapping(value="/{token}") 
	public User getUser(@PathVariable String token) 
	{
		return userService.getUser(token); 
	}

	/* API for Getting the all users */

	/*
	 * @GetMapping(value="/user") public List<User> getAllUsers() { return
	 * userService.getUsers(); }
	 * 
	 * /* API for Deleting the user
	 * 
	 * @DeleteMapping(value = "/user/delete/{id}") public void
	 * deleteUser(@PathVariable String id) { User result =
	 * userService.removeUser(id); if(result!=null){ System.out.println("@@@"); } }
	 */
}
