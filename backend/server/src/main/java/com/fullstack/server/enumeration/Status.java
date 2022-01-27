package com.fullstack.server.enumeration;

/**
 * @author isanchez
 *
 */

public enum Status {
	
	// servers will have two statuses
	SERVER_UP("SERVER_UP"),
	SERVER_DOWN("SERVER_DOWN");

	private final String status;
	
	// Constructor 
	Status(String status){
		this.status = status;				
	};
	
	public String getStatus() {
		return this.status;
	};
	
	
	
	
};
