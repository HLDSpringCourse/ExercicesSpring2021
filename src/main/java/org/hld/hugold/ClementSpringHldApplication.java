package org.hld.hugold;

import org.apache.catalina.core.ApplicationContext;
import org.hld.hugold.entity.User;
import org.hld.hugold.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ClementSpringHldApplication {
	

	
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(ClementSpringHldApplication.class, args);
		
		UserService userService = ctx.getBean(UserService.class);
		userService.addUser(new User(1, "Jacques",40));
		userService.addUser(new User(2, "Michel",45));
		userService.addUser(new User(3, "Norbert",37));
		userService.addUser(new User(4, "Philipe",15));
	}
}
