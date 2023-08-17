package com.amit.sponsoredads.dto.campaign;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CampaignDto {
    private Long id;
    private String name;
    private Date startDate;
    private List<Integer> productIds;
    private double bid;
}
