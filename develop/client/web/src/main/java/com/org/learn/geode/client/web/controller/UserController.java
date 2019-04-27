package com.org.learn.geode.client.web.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.learn.geode.client.web.form.User;

@Controller
public class UserController {
	
	private List<User> users = Arrays.asList(
		      new User("mithu", "tokder"),
		      new User("jim", "carrey"));
	
	@GetMapping("/users")
	@ResponseBody
	public List<User> getUsers() {
		return users;
	}
	
	@GetMapping("/userPage")
    public String getUserProfilePage() {
		System.out.println("In userPage controller");
        return "user";
    }

}
