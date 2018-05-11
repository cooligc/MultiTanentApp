package com.skc.multitanent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MultiTanentAppApplication {

	public static void main(String[] args) {
		/*System.setProperty("http.proxyHost", "web-proxy.sgp.hp.com");
		System.setProperty("http.proxyPort", "8080");
		System.setProperty("https.proxyHost", "web-proxy.sgp.hp.com");
		System.setProperty("https.proxyPort", "8080");*/
		SpringApplication.run(MultiTanentAppApplication.class, args);
	}
}
