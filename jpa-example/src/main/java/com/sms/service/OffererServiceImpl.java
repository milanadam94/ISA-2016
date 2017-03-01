package com.sms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.beans.ActiveUser;
import com.sms.beans.Offerer;
import com.sms.beans.Offerings;
import com.sms.beans.SysUser;
import com.sms.beans.Tender;
import com.sms.beans.UserType;
import com.sms.dao.ActiveUserDao;
import com.sms.dao.OffererDao;
import com.sms.dao.OfferingsDao;
import com.sms.dao.TenderDao;
import com.sms.dao.UserDao;
import com.sms.utilities.Message;

@Service
public class OffererServiceImpl implements OffererService {


	@Autowired
	private TenderDao tenderDao;

	@Autowired
	private OfferingsDao offeringsDao;
	
	@Autowired
	private OffererDao offererDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ActiveUserDao activeUserDao;
	
	@Override
	public List<Tender> getActiveTenders() {
		// TODO Auto-generated method stub
		
		return  tenderDao.findActiveTenders();
	}

	@Override
	public Offerer getOfferer(String offererEmail) {
		// TODO Auto-generated method stub
		ActiveUser activeUser = activeUserDao.findByEmail(offererEmail);
		if(activeUser == null || !activeUser.getUserType().equals(UserType.OFFERER)){
			return null;
		}
				
		return offererDao.findByUserId(activeUser.getUser().getId());
	}
	
	@Override
	public String createNewOfferings(Offerings offerings, Integer tenderID, String offererEmail) {
		// TODO Auto-generated method stub
		Offerer offerer = getOfferer(offererEmail);
		
		if(offerer == null){
			return Message.USERNOTFOUNDERROR;
		}
		
		Tender tender = tenderDao.findById(tenderID);
		
		if(tender == null){
			return Message.REQUESTERROR;
		}
		
		offerings.setOfferer(offerer);
		offerings.setTender(tender);
		
		offeringsDao.save(offerings);
		
		return Message.ERRORFREE;
	}

	@Override
	public List<Offerings> getMyAllOfferings(String offererEmail) {
		// TODO Auto-generated method stub
		Offerer offerer = getOfferer(offererEmail);
		
		if(offerer == null){
			return null;
		}
		
		return offeringsDao.findByOfferer(offerer);
	}

	@Override
	public void deleteOffering(Integer offeringID) {
		// TODO Auto-generated method stub
		Offerings offering = offeringsDao.findById(offeringID);
		
		if(offering != null){
			offeringsDao.delete(offering);
		}
				
	}

	@Override
	public String changeOfferings(Offerings offering) {
		// TODO Auto-generated method stub
		
		if(offeringsDao.findById(offering.getId()) == null){
			return Message.REQUESTERROR;
		}
		
		offeringsDao.save(offering);
		
		return Message.ERRORFREE;
	}

	@Override
	public void saveOfferer(SysUser newOfferer) {
		// TODO Auto-generated method stub
		
		if(userDao.findByEmail(newOfferer.getEmail()) == null){
			return;
		}
		
		userDao.save(newOfferer);
	}
	
	
	
	
	
	
	
	
}
