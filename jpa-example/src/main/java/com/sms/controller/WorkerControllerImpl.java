package com.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.sms.beans.Waiter;
import com.sms.service.WorkerService;

@Controller
@RequestMapping("/worker")
public class WorkerControllerImpl{

	@Autowired
	private WorkerService service;
	
	@GetMapping(path = "/waiter/{waiterEmail}")
	@ResponseBody
	public Waiter getWaiter(@PathVariable(value="waiterEmail") String email){
		return service.getWaiter(email);
	}
	
//	@GetMapping(path = "/activate/{activation_code}")
//	@ResponseBody
//	public RedirectView login(@PathVariable(value="activation_code") String activationCode)
}
