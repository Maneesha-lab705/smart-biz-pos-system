package com.smartbiz.service.ai;


import com.smartbiz.dto.ai.SocialPostRequest;

public interface SocialMediaService {

    String generatePost(
            SocialPostRequest request
    );

}