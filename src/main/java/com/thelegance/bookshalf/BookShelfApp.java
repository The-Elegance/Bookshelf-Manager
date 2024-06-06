package com.thelegance.bookshalf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.thelegance.bookshalf.model")
@SpringBootApplication
public class BookShelfApp {

	public static void main(String[] args) {
		SpringApplication.run(BookShelfApp.class, args);
	}

}
