package com.jy.study.oauth2.resourceServerAlbums;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
public class ResourceServerAlbumsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResourceServerAlbumsApplication.class, args);
	}

}
