package com.policynepal.autocare.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system")
public class SystemController {
	
	@GetMapping("/exit")
	public void shutdown() {
		System.exit(0);
	}

	@GetMapping("/test")
	public String testSystem() {
		return "System is up";
	}
}
