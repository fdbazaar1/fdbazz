package com.in28minutes.rest.webservices.restfulwebservices.bean.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.rest.webservices.restfulwebservices.bean.helloworld.HelloWorldBean;

// REST Controller
@RestController
public class HelloWorldController {

	@RequestMapping(path = "/hello-World", method = RequestMethod.GET)
	public String helloMessage() {
		return "Hello World";
	}

	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloworldBean() {
		return new HelloWorldBean("Hello World");

	}

	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean helloworldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World, %s ", name));
	}

}
