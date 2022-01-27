package com.fullstack.server.service.implementation;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fullstack.server.model.Server;
import com.fullstack.server.repo.ServerRepo;
import com.fullstack.server.service.ServerSerivce;

import static org.springframework.data.domain.PageRequest.of;
import static com.fullstack.server.enumeration.Status.SERVER_UP;
import static com.fullstack.server.enumeration.Status.SERVER_DOWN;
import static java.lang.Boolean.TRUE;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImpl implements ServerSerivce{

	private final ServerRepo serverRepo;
	
	@Override
	public Server create(Server server) {
		log.info("Saving new server: {}", server.getName());		
		server.setImageUrl(setServerImageUrl());
		return serverRepo.save(server);
	};
	
	@Override
	public Server ping(String ipAddress) throws IOException {
		log.info("Pinging server IP: {}", ipAddress);
        Server server = serverRepo.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        // possible error here: does not receive on time so it changes to SERVER_DOWN
        server.setStatus(address.isReachable(10000) ? SERVER_UP : SERVER_DOWN);
        serverRepo.save(server);
		return server;
	};

	@Override
	public Collection<Server> list(int limit) {
		log.info("Fetching all servers..");
		return serverRepo.findAll(of(0, limit)).toList();
	};

	@Override
	public Server get(Long id) {
		log.info("Fetching server by id: {}", id);
		return serverRepo.findById(id).get();
	};

	@Override
	public Server update(Server server) {
		log.info("Updating new server: {}", server.getName());
		return serverRepo.save(server);
	};

	@Override
	public Boolean delete(Long id) {
		log.info("Deleting server by ID: {}", id);
		serverRepo.deleteById(id);
		return TRUE;
	};
	
	private String setServerImageUrl() {
		String[] imageNames = { "server1.png", "server2.png",	"server3.jpg", "server4.jpg", "server5.png", "server6.png" };		
 		return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image/" + imageNames[new Random().nextInt(6)]).toUriString();
	};	
	

};
