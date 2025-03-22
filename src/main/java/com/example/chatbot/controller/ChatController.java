package com.example.chatbot.controller;

import com.example.chatbot.domain.Chat;
import com.example.chatbot.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/chat")
    public Iterable<Chat> findAllChats() {
        return chatService.findAllChats();
    }

    @PostMapping("/chat/new")
    public void createChat() {
        chatService.createChat();
    }

    @GetMapping("/chat/{chatId}")
    public Chat findChatById(@PathVariable Long chatId) {
        return chatService.findChatById(chatId);
    }
}
