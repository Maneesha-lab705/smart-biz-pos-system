package com.smartbiz.service.ai;

import com.smartbiz.dto.ai.EmailRequest;

public interface EmailComposerService {

    String composeEmail(
            EmailRequest request
    );

}