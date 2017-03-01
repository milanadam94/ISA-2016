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

import com.sms.beans.Bartender;
import com.sms.beans.Cook;
import com.sms.beans.Drink;
import com.sms.beans.DrinkOrder;
import com.sms.beans.Food;
import com.sms.beans.FoodOrder;
import com.sms.beans.GuestOrder;
import com.sms.beans.SysUser;
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
	@PostMapping(path = "/waiter/firstLogin", produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String saveFirstLogin(@RequestBody Waiter waiter){
		return workerService.saveFirstLogin(waiter);
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
	
	@PostMapping(path = "/waiter/saveGuestOrder/{userId}")
	@ResponseBody
	public void addGuestOrder(@PathVariable(value="userId") Integer userId){
		workerService.saveGuestOrder(userId);
	}
	
	@GetMapping(path = "/waiter/getGuestOrders/{userId}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<GuestOrder> getGuestOrders(@PathVariable(value="userId") Integer userId){
		return workerService.getGuestOrders(userId);
	}
	
	@PostMapping(path = "waiter/deleteGuestOrder/{id}")
	@ResponseBody
	public void deleteGuestOrder(@PathVariable(value="id") Integer id){
		workerService.deleteGuestOrder(id);
	}
	
	@GetMapping(path = "/waiter/getTotal/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Integer getTotal(@PathVariable(value="orderId") Integer orderId){
		return workerService.getTotal(orderId);
	}
	
	//sanker
	@GetMapping(path = "/bartender/{userId}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Bartender getBartender(@PathVariable(value = "userId") Integer userId){
		return workerService.getBartenderByUserId(userId);
	}
	
	@PostMapping(path = "/bartender/saveChanges", produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String saveChanges(@RequestBody Bartender bartender){
		return workerService.updateBartenderProfile(bartender);
	}
	
	@PostMapping(path = "/bartender/firstLogin", produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String saveFirstLogin(@RequestBody Bartender bartender){
		return workerService.saveFirstLogin(bartender);
	}
	
	@GetMapping(path = "/bartender/getDrinkOrders/{userId}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<DrinkOrder> getDrinkOrders(@PathVariable(value = "userId") Integer userId){
		return workerService.getDrinkOrders(userId);
	}
	@PostMapping(path = "/bartender/setDrinkOrder", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void setDrinkOrderPrepared(@RequestBody DrinkOrder drinkOrder){
		workerService.setDrinkOrderPrepared(drinkOrder);
	}
	
	//kuvari
	@GetMapping(path = "/cook/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Cook getCook(@PathVariable(value = "userId") Integer userId){
		return workerService.getCookByUserId(userId);
	}
	
	@PostMapping(path = "/cook/saveChanges", produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String saveChanges(@RequestBody Cook cook){
		return workerService.updateCookProfile(cook);
	}
	@PostMapping(path = "/cook/firstLogin", produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String saveFirstLogin(@RequestBody Cook cook){
		return workerService.saveFirstLogin(cook);
	}
	
	@GetMapping(path = "/cook/getFoodOrders/{userId}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<FoodOrder> getFoodOrders(@PathVariable(value = "userId") Integer userId){
		return workerService.getFoodOrders(userId);
	}
	
	@PostMapping(path = "/cook/startPrepareFood", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void setStartPrepare(@RequestBody FoodOrder order){
		workerService.setStartPrepareFood(order);
	}
	
	@PostMapping(path = "/cook/prepareFoodDone", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void prepareFoodDone(@RequestBody FoodOrder order){
		workerService.setPrepareFoodDone(order);
	}
	//svim radnicima
	@GetMapping(path = "/firstLogin/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean getFirstLogin(@PathVariable(value = "userId") Integer userId){
		return workerService.getFirstLogin(userId);
	}
	
	@GetMapping(path = "/waiters/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Waiter> getWaiters(@PathVariable(value="userId") Integer userId){
		return workerService.getWaiters(userId);
	}
	
	@GetMapping(path = "/cooks/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Cook> getCooks(@PathVariable(value="userId") Integer userId){
		return workerService.getCooks(userId);
	}
	
	@GetMapping(path = "/bartenders/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Bartender> getBartenders(@PathVariable(value="userId") Integer userId){
		return workerService.getBartenders(userId);
	}
}
