package org.pyl.pylspring.configuration;

import lombok.extern.slf4j.Slf4j;
import org.pyl.pylspring.dao.ItemDAO;
import org.pyl.pylspring.entity.Item;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class H2DBConfig {

    @Bean
    CommandLineRunner initDatabase(ItemDAO itemDAO){

        return args -> {
            itemDAO.save(new Item( "toto", "52", "Pays de la Loire"));
            itemDAO.save(new Item( "titi", "01", "Guadeloupe"));
            itemDAO.save(new Item( "tutu", "28", "Normandie"));
        };
    }
}
