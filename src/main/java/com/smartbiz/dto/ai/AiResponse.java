package com.smartbiz.dto.ai;

import lombok.*;

@Data
@AllArgsConstructor
public class AiResponse {
    private boolean success;  // 1st
    private String result;    // 2nd
    private String error;     // 3rd
}