package com.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.sms.beans.Waiter;
import com.sms.service.UserService;
import com.sms.service.WorkerService;

@Controller
@RequestMapping("/worker")
public class WorkerControllerImpl{

	@Autowired
	private WorkerService workerService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping(path = "/waiter/{waiterId}")
	@ResponseBody
	public Waiter getWaiter(@PathVariable(value="waiterId") Integer id){
		return workerService.getWaiter(id);
	}
	
	@PostMapping(path = "/waiter/saveChanges/{waiterId}", produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String saveChanges(Waiter waiter, @PathVariable(value = "waiterId") Integer id){
		
		Waiter wa = workerService.getWaiter(id);
		System.out.println(waiter.getSuitSize());
		if(waiter.getShoeSize()==(wa.getShoeSize()) && waiter.getSuitSize().equals(wa.getSuitSize()))
			return "adfgad";
			
		//	if(notModifiedRestaurant.getName().trim().equals(restaurant.getName())
		//			&& notModifiedRestaurant.getDescription().trim().equals(restaurant.getDescription())
		workerService.updateWaiterProfile(wa);
		
		return "OK";
	}
}
