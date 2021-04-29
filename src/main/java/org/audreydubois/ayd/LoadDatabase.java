package org.audreydubois.ayd;

import lombok.extern.slf4j.Slf4j;
import org.audreydubois.ayd.entity.Item;
import org.audreydubois.ayd.dao.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ItemRepository repository){
        return args -> {
            log.info("Preloading " + repository.save(new Item("Chaussure", "01", "Test 1")));
            log.info("Preloading " + repository.save(new Item("Pistache", "02", "Test 2")));
            log.info("Preloading " + repository.save(new Item("Cravate", "03", "Test 3")));
            log.info("Preloading " + repository.save(new Item("Bouteille", "04", "Test 4")));
            log.info("Preloading " + repository.save(new Item("Miroir", "05", "Test 5")));
            log.info("Preloading " + repository.save(new Item("Flute", "06", "Test 6")));
            log.info("Preloading " + repository.save(new Item("Poubelle", "07", "Test 7")));
        };
    }
}
