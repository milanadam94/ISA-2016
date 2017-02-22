package com.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.sms.service.SysUserActivationService;
import com.sms.utilities.Message;

@Controller
@RequestMapping("/activation")
public class SysUserActivationController {
	
	@Autowired
	private SysUserActivationService sysUserActivationService;
	
	@GetMapping(path = "/activate/{activation_code}")
	@ResponseBody
	public RedirectView login(@PathVariable(value="activation_code") String activationCode) {
		// UZETI CONFIRMATION CODE I POSLATI NA KONTROLER - ON BRISE IZ TABELE ACTIVATION I STAVLJA U SYSUSER- UZETI KOD IZSYSUSERCONT
		String result = sysUserActivationService.activate(activationCode);
		if(result.equals(Message.ERRORFREE))
			return new RedirectView("/ActivationPage/ActivationSuccededPage.html");
		else
			return new RedirectView("/ActivationPage/ActivationFailedPage.html");
	}
	
}
