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
            log.info("Preloading " + repository.save(new CustomerEntity("Chaussure", "01")));
            log.info("Preloading " + repository.save(new CustomerEntity("Pistache", "02")));
            log.info("Preloading " + repository.save(new CustomerEntity("Cravate", "03")));
            log.info("Preloading " + repository.save(new CustomerEntity("Bouteille", "04")));
            log.info("Preloading " + repository.save(new CustomerEntity("Miroir", "05")));
            log.info("Preloading " + repository.save(new CustomerEntity("Flute", "06")));
            log.info("Preloading " + repository.save(new CustomerEntity("Poubelle", "07")));
        };
    }
}
