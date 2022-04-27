package com.gtech.demo;

import java.util.*;

import com.gtech.demo.entity.Product;
import com.gtech.demo.entity.User;
import com.gtech.demo.service_adapter.IGeneralAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
public class TestController {
	@Autowired
	private IGeneralAdapter generalAdapter;
    @GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}
    @GetMapping("/home")
	public String url() {
		return "Spring test home";
	}
	@PostMapping("/login")
	public String login(@RequestBody User user){
		try {
			String chkToken = generalAdapter.login(user.getUsername(),user.getPassword());
			return chkToken;
		} catch (Exception e) {
			throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Fail to get Data", e);
		}
	}
	@PostMapping("/register")
	public User register(@RequestBody User user){
		User us = generalAdapter.register(user);
		return us;
	}
	@PostMapping("/logout")
	public String logout(@RequestBody User user){
		String chkToken = generalAdapter.logout(user.getLogintoken());
		return chkToken;
	}
	@PostMapping("/forgotpassword")
	public String forgotPassword(@RequestBody User user){
		try {
			String resetToken = generalAdapter.forgotPassword(user.getEmail());
			return resetToken;
		} catch (Exception e) {
			throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Fail to request forgot password", e);
		}
		
	}
	@PostMapping("/resetpassword")
	public String resetPassword(@RequestBody User user){
		try {
			String chkToken = generalAdapter.resetPassword(user.getResetpassToken(),user.getPassword());
			return chkToken;
		} catch (Exception e) {
			throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Fail to reset password", e);
		}
	}
	@PostMapping("/checkprofile")
	public List<User> getProfile(@RequestBody User user){
		List<User> us = null;
		try {
			us = generalAdapter.getProfile(user.getLogintoken());
			return us;
		} catch (Exception e) {
			return new ArrayList<User>();
		}
	}
}
