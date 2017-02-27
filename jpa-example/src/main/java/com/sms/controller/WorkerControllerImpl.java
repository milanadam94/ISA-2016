package com.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sms.beans.Drink;
import com.sms.beans.Food;
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
	
	@GetMapping(path = "/waiter/{userId}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Waiter getWaiter(@PathVariable(value="userId") Integer userId){
		return workerService.getWaiterByUserId(userId);
	}

	
	@PostMapping(path = "/waiter/saveChanges", produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String saveChanges(@RequestBody Waiter waiter){
		return workerService.updateWaiterProfile(waiter);
	}
	
	@GetMapping(path = "/waiter/getFoods/{userId}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Food> getFoods(@PathVariable(value="userId") Integer userId){
		return workerService.getFoods(userId);
	}
	@GetMapping(path = "/waiter/getDrinks/{userId}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Drink> getDrinks(@PathVariable(value="userId") Integer userId){
		return workerService.getDrinks(userId);
	}
	
	@PostMapping(path = "/waiter/addOrderDrink", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void addOrderDrink(@RequestBody Drink drink){
		workerService.addOrderDrink(drink);
	}
	
	@PostMapping(path = "/waiter/addOrderFood", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void addOrderFood(@RequestBody Food food){
		workerService.addOrderFood(food);
	}
}
