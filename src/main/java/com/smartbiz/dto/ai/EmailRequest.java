package com.smartbiz.dto.ai;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequest {
    private String emailType;       // "thank-you", "complaint", "inquiry"
    private String recipientName;
    private String recipientRole;
    private String context;
    private String keyPoints;
    private String tone;            // "formal", "semi-formal", "friendly"
}
