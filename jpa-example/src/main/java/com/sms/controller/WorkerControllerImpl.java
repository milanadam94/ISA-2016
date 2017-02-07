package com.sms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WorkerControllerImpl{

	@GetMapping("/test")
	@ResponseBody
	public String getPopara()
	{
		return "popara";
	}
	
}
