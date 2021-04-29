package org.hld.hugold.dao;

import org.hld.hugold.entity.CustomerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CustomerRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new CustomerEntity("Eslam", "37550")));
            log.info("Preloading " + repository.save(new CustomerEntity("Ali", "37000")));
            log.info("Preloading " + repository.save(new CustomerEntity("Remi", "42000")));
            log.info("Preloading " + repository.save(new CustomerEntity("Audrey", "75001")));
            log.info("Preloading " + repository.save(new CustomerEntity("Hugo", "13000")));
            log.info("Preloading " + repository.save(new CustomerEntity("Sam", "33000")));
            log.info("Preloading " + repository.save(new CustomerEntity("Jack", "32000")));
        };
    }
}
