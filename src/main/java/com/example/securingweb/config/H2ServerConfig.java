package com.example.securingweb.config;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This config exposes the in-memory H2 database so you can connect to it with a database client (such as DBeaver)
 * while your Spring Boot application is running
 */
@Configuration
public class H2ServerConfig {

	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server h2Server() throws SQLException {
		return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
	}

}
