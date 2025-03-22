package com.example.chatbot.dto;

import lombok.Data;

import java.util.List;

@Data
public class ChatDto {

    private String model;
    private List<MessageDto> messages;
}
