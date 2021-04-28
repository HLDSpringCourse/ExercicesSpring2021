package com.tp.tommyp;

import com.tp.tommyp.Repository.ItemRepository;
import com.tp.tommyp.entity.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tommy
 * @created 27/04/2021 - 10:30
 * @project tommyp
 */
@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ItemRepository repository){
        return args -> {
            log.info("Preloading " + repository.save(new Item("Test")));
            log.info("Preloading " + repository.save(new Item("Blabla")));
            log.info("Preloading " + repository.save(new Item("Jklm")));
        };
    }
}
