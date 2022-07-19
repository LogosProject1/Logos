package com.logos.session.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;

@Data
public class SessionRemoveUserDto {
    String knowledgeId;

    String token;
}
