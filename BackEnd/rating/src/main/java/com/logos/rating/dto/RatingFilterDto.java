package com.logos.rating.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class RatingFilterDto {
    private String knowledgeId;

    private String rate;

    private String userEmail;
}
