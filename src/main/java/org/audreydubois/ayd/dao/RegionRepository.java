package org.audreydubois.ayd.dao;

import org.audreydubois.ayd.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region, Long> {

    public List<Region> findByRegionCode(String code);
    public List<Region> findByRegion(String region);
}
