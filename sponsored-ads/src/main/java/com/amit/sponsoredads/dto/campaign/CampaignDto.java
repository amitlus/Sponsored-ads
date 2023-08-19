package com.amit.sponsoredads.dto.campaign;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CampaignDto {
    @Size(min = 2)
    private String name;
    @FutureOrPresent
    private Date startDate;
    private List<Integer> productIds;
    private double bid;
}
