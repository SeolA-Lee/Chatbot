package com.example.chatbot.dto;

import com.example.chatbot.domain.ChatbotType;
import lombok.Data;

@Data
public class MessageDto {

    private String role;
    private String content;
    private ChatbotType type;
}
