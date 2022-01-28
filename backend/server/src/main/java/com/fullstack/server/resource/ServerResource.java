package com.fullstack.server.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fullstack.server.model.Response;
import com.fullstack.server.model.Server;
import com.fullstack.server.service.implementation.ServerServiceImpl;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;
import static com.fullstack.server.enumeration.Status.SERVER_UP;
import static java.util.Map.of;
import static java.time.LocalDateTime.now;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerResource {

	private final ServerServiceImpl ss;
	
	@GetMapping("/list")
	public ResponseEntity<Response> getServers() throws InterruptedException {		
		TimeUnit.SECONDS.sleep(3); 		
		return ResponseEntity.ok(
					Response.builder()
							.timeStamp(now())
							.data(of("servers", ss.list(30)))
							.message("Servers retrieved")
							.status(OK)
							.statusCode(OK.value())
							.build()
				);				
	};
	
	@GetMapping("/ping/{ipAddress}")
	public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAdd) throws IOException {		
		Server server = ss.ping(ipAdd);
//		System.out.println("<===========================================>");
//		System.out.println(server + " should be ip address?");
//		System.out.println(ipAdd);
//		System.out.println(server.getStatus());
//		System.out.println("<===========================================>");
//		System.out.println("");
		return ResponseEntity.ok(
					Response.builder()
							.timeStamp(now())
							.data(of("server", server))
							.message(server.getStatus() == SERVER_UP ? "Ping SUCCESS" : "Ping FAILED")
							.status(OK)
							.statusCode(OK.value())
							.build()
		);				
	};
	
	@PostMapping("/save")
	public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server) {		
		return ResponseEntity.ok(
					Response.builder()
							.timeStamp(now())
							.data(of("server", ss.create(server)))
							.message("Server Created/SAVED!")
							.status(CREATED)
							.statusCode(CREATED.value())
							.build()
				);
	};
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Response> getServer(@PathVariable("id") Long id) {		
		return ResponseEntity.ok(
					Response.builder()
							.timeStamp(now())
							.data(of("server", ss.get(id)))
							.message("Server Retrieved")
							.status(OK)
							.statusCode(OK.value())
							.build()
				);				
	};
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id) {		
			return ResponseEntity.ok(
						Response.builder()
								.timeStamp(now())
								.data(of("deleted", ss.delete(id)))
								.message("Server Deleted")
								.status(OK)
								.statusCode(OK.value())
								.build()
					);				
	};
	
	@GetMapping(path = "/image/{fileName}", produces = IMAGE_PNG_VALUE)
	public byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOException{		
		return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/Downloads/serverPics/" + fileName));		
	};	
	
};






