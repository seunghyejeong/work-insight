package com.opencloudplatform.edm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.opencloudplatform.edm.domain.Admin;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@GetMapping("/add")
	public String register() {
		return "registerForm";
	}

	@PostMapping("/save")
	public String doRegister(Admin admin) {
		if (!isValid(admin)) {

			return "foward:/register/add";
		}
		return "registerInfo";
	}

	private boolean isValid(Admin admin) {
		return false;
	}
}
