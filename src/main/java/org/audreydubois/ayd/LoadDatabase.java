package org.audreydubois.ayd;

import org.audreydubois.ayd.entity.Item;
import org.audreydubois.ayd.repository.ItemRepository;
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
            log.info("Preloading " + repository.save(new Item("Chaussure")));
            log.info("Preloading " + repository.save(new Item("Pistache")));
            log.info("Preloading " + repository.save(new Item("Cravate")));
            log.info("Preloading " + repository.save(new Item("Bouteille")));
            log.info("Preloading " + repository.save(new Item("Miroir")));
            log.info("Preloading " + repository.save(new Item("Flute")));
            log.info("Preloading " + repository.save(new Item("Poubelle")));
        };
    }
}
