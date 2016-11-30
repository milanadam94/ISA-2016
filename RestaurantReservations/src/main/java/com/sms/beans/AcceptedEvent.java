package com.sms.beans;


public class AcceptedEvent {
	
	private Event events;

	public AcceptedEvent(Event events) {
		super();
		this.events = events;
	}

	public AcceptedEvent() {
		super();
	}

	public Event getEvents() {
		return events;
	}
	
	public void setEvents(Event events) {
		this.events = events;
	}

}