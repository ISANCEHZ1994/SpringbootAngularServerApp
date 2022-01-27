package com.fullstack.server.model;
import com.fullstack.server.enumeration.Status;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.persistence.*;
import static javax.persistence.GenerationType.AUTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author isanchez
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Server {
	
	@Id 
	@GeneratedValue(strategy = AUTO)
	private Long id; 
	
	@Column(unique = true) // we are creating a constraint so that we can't have the same IP address number
	@NotEmpty(message = "IP address cannot be EMPTY or NULL") // adding validation/exception
	private String ipAddress; 
	private String name;
	private String memory;
	private String type;
	private String imageUrl;
	private Status status;
		
};
