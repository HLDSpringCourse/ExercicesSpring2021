package org.lfc.SpringTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import controller.GeoController;
import controller.ItemController;

@SpringBootApplication
@ComponentScan(basePackageClasses = ItemController.class)
@ComponentScan(basePackageClasses = GeoController.class)
@ComponentScan(basePackageClasses = org.lfc.service.ItemService.class)
public class SpringTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTestApplication.class, args);
	}

}
