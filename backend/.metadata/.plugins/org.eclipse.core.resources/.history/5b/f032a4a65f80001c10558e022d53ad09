package com.fullstack.server.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack.server.model.Server;

/**
 * @author isanchez
 *
 */

public interface ServerRepo extends JpaRepository<Server, Long> { // we have to give it the data type of the PRIMARY KEY (ID) from the model
	
	// we want it to look for a unique IP address
	 Server findByIpAddress(String ipAddress);
	 	 
};
