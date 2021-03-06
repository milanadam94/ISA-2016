package com.sms.service;

import java.util.List;

import com.sms.beans.Offerer;
import com.sms.beans.Offerings;
import com.sms.beans.SysUser;
import com.sms.beans.Tender;

public interface OffererService {

	public List<Tender> getActiveTenders();
	
	public String createNewOfferings(Offerings offerings, Integer tenderID, String offererEmail);

	public Offerer getOfferer(String offererID);
	
	public List<Offerings> getMyAllOfferings(String offererEmail);

	public void deleteOffering(Integer offeringID);

	public String changeOfferings(Offerings offering);

	public void saveOfferer(SysUser newOfferer);
}
