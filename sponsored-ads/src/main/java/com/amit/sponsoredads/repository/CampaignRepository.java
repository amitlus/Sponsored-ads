package com.amit.sponsoredads.repository;

import com.amit.sponsoredads.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    List<Campaign> findCampaignsByStartDateGreaterThanAndProductsCategory(Date date, String category);
}


