package com.smartbiz;

import org.springframework.ai.autoconfigure.openai.OpenAiAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
        OpenAiAutoConfiguration.class
})

public class SmartBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartBizApplication.class, args);
    }
}
