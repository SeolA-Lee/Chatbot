package com.example.chatbot.controller;

import com.example.chatbot.domain.Message;
import com.example.chatbot.dto.MessageDto;
import com.example.chatbot.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/chat/{chatId}")
    public Message createMessage(@PathVariable Long chatId, @RequestBody MessageDto messageDto) {
        String content = messageDto.getContent();
        return messageService.sendMessage(chatId, content);
    }
}
