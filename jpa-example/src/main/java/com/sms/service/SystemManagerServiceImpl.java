package com.sms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.beans.Menu;
import com.sms.beans.Restaurant;
import com.sms.beans.RestaurantManager;
import com.sms.beans.SysUser;
import com.sms.beans.UserType;
import com.sms.dao.MenuDao;
import com.sms.dao.RestaurantDao;
import com.sms.dao.RestaurantManagerDao;
import com.sms.dao.RestaurantsDao;
import com.sms.dao.SystemManagerDao;
import com.sms.utilities.Message;

@Service
public class SystemManagerServiceImpl implements SystemManagerService{

	@Autowired
	private SystemManagerDao sysManagerDao;
	
	@Autowired
	private MenuDao menuDao;
	
	//@Autowired
	//private SysUserActivationDao sysUserActDao;
	
	@Autowired
	private RestaurantManagerDao restManagerDao;
	
	@Autowired
	private RestaurantsDao restDao;
	
	@Autowired
	private RestaurantDao restoranDao;
	
	//@Autowired
	//private MailSender mailSender;
	
	
	//fali mi i za login,kao sto je u UserServiceImpl
	
	public String registarRestManager(SysUser user, Integer restoranID) {
		
		if(user.getEmail() == null || user.getEmail().equals(""))
			return Message.EMAILERROR;
		else if(user.getPassword() == null || user.getPassword().equals(""))
			return Message.PASSWORDERROR;
		else if(user.getName() == null || user.getName().equals(""))
			return Message.NAMEERROR;
		else if(user.getLastName() == null || user.getLastName().equals(""))
			return Message.LASTNAMEERROR;
		
		
		SysUser sysUser = sysManagerDao.findByEmail(user.getEmail());
		
		if(sysUser != null)
			return Message.REGISTRATIONERROR;
		
		Restaurant restoran = restoranDao.findById(restoranID);
		
		if(restoran == null){
			return Message.REQUESTERROR;
		}
		
		
		/*SysUserActivation sysUserActivation = sysUserActDao.findByEmail(user.getEmail());
		if(sysUserActivation != null)
			sysUserActDao.delete(sysUserActivation);
		
		sysUserActivation = new SysUserActivation(user.getEmail(), user.getPassword(), user.getName(),
				user.getLastName(), user.getUserType());
		
		sysUserActivation.setActivationCode(UUID.randomUUID().toString());
		
		sysUserActDao.save(sysUserActivation);
		
		sendEmail(user.getEmail(), sysUserActivation.getActivationCode());*/
		
		
		SysUser newUser = new SysUser(user.getEmail(), user.getPassword(), user.getName(), user.getLastName(), UserType.RESTAURANTMANAGER);
		RestaurantManager rest = new RestaurantManager(newUser);
		rest.setRestaurant(restoran);
		sysManagerDao.save(newUser);
		restManagerDao.save(rest);
		
		return Message.ERRORFREE;
	}

	@Override
	public String registarRestaurant(Restaurant restaurant) {
		
		if(restaurant.getName() == null || restaurant.getName().equals(""))
			return Message.NAMEERROR;
		else if(restaurant.getDescription() == null || restaurant.getDescription().equals(""))
			return Message.DESCRIPTIONERROR;
		else if(restaurant.getCategory() == null || restaurant.getCategory().equals(""))
			return Message.CATEGORYERROR;
		
		Restaurant rest = sysManagerDao.findByName(restaurant.getName());
		
		if(rest != null)
			return Message.REGISTRATIONERROR;
		
		Restaurant newRest = new Restaurant(restaurant.getName(), restaurant.getDescription(), restaurant.getCategory());
		
		sysManagerDao.save(newRest);
		
		Menu menu = new Menu();
		menu.setRestaurant(newRest);
		menuDao.save(menu);
		
		return Message.ERRORFREE;
	}

	@Override
	public List<Restaurant> getRestaurants() {
		return restDao.findAll();
	}

	@Override
	public List<SysUser> getRestManagers() {
		return sysManagerDao.findByUserType(UserType.RESTAURANTMANAGER);
	}
	
	/*private void sendEmail(String email, String activationCode) {
		
		final SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject("Potvrda registracije");
		message.setFrom("isasms2016@gmail.com");
		message.setTo(email);
		message.setText("http://localhost:8080/activation/activate" + "/" + activationCode);

		try {
			mailSender.send(message);
		} catch (MailException e) {
			System.out.println(e.toString());
		}
		
	}*/

}
