package com.amit.sponsoredads.dto.campaign;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampaignDto {
    @NotNull
    @Size(min = 2)
    private String name;
    @NotNull
    @FutureOrPresent
    private LocalDateTime startDate;
    @NotNull
    private List<Integer> productIds;
    @NotNull
    private double bid;
}
