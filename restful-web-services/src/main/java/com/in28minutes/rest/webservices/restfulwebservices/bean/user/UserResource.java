package com.in28minutes.rest.webservices.restfulwebservices.bean.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService userDaoService;

	@GetMapping(path = "/user")
	public List<User> findAll() {
		return userDaoService.findAll();
	}

	@GetMapping(path = "/user/{userID}")
	public org.springframework.hateoas.Resource<User> retriveUsers(@PathVariable int userID) {
		User serchedUser = userDaoService.findOne(userID);
		if (serchedUser == null) {
			throw new UserNotDefinedException("userID" + userID);
		}

		// HEATOS
		// all-users

		org.springframework.hateoas.Resource<User> userRes = new Resource<User>(serchedUser);
		ControllerLinkBuilder linkto = linkTo(methodOn(this.getClass()).findAll());
		userRes.add(linkto.withRel("all-users"));

		return userRes;
	}

	@PostMapping(path = "/user")
	public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
		User savedUser = userDaoService.save(user);
		// Since its post request
		// status sent should be as created. 201
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userID}")
				.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping(path = "/user/{userID}")
	public ResponseEntity<Object> deleteById(@PathVariable int userID) {
		User deletedUser = userDaoService.deleteOne(userID);
		if (deletedUser == null) {
			throw new UserNotDefinedException("userID" + userID);
		}
		return ResponseEntity.ok().build();

	}

}
