package org.audreydubois.ayd;

import lombok.extern.slf4j.Slf4j;
import org.audreydubois.ayd.dao.RegionRepository;
import org.audreydubois.ayd.dto.RegionDTO;
import org.audreydubois.ayd.entity.Item;
import org.audreydubois.ayd.dao.ItemRepository;
import org.audreydubois.ayd.entity.Region;
import org.audreydubois.ayd.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    private final ItemService itemService;

    public LoadDatabase(ItemService itemService) {
        this.itemService = itemService;
    }

    @Bean
    CommandLineRunner initDatabase(ItemRepository repository, RegionRepository regionRepository){

        RegionDTO[] allRegions = itemService.findAllRegions();
        return args -> {
            for (int i = 0; i <allRegions.length ; i++) {
                log.info("Preloading " + regionRepository.save(new Region(allRegions[i].getNom(), allRegions[i].getCode())));
            }
            List<Region> regions = regionRepository.findAll();
            for (int i = 0; i <regions.size() ; i++) {
                System.out.println(regions.get(i).getId());
                System.out.println(regions.get(i).getRegion());
                System.out.println(regions.get(i).getRegionCode());
            }
            log.info("Preloading " + repository.save(new Item("Chaussure", "01", "Test 1")));
            log.info("Preloading " + repository.save(new Item("Pistache", "02", "Test 2")));
            log.info("Preloading " + repository.save(new Item("Cravate", "03", "Test 3")));
            log.info("Preloading " + repository.save(new Item("Bouteille", "04", "Test 4")));
            log.info("Preloading " + repository.save(new Item("Miroir", "05", "Test 5")));
            log.info("Preloading " + repository.save(new Item("Flute", "06", "Test 6")));
            log.info("Preloading " + repository.save(new Item("Poubelle", "07", "Test 7")));
            try{

            log.info("Preloading " + repository.save(new Item("Poubelle", regions.get(0))));
            }catch(Exception e ){
                System.out.println(e.getMessage());
            }
            //log.info("Preloading " + repository.save(new Item("Poubelle", regions.get(1))));
        };

    }
}
