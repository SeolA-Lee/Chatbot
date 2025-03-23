package com.example.chatbot.controller;

import com.example.chatbot.domain.Chat;
import com.example.chatbot.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/chat")
    public String findAllChats(Model model) {
        Iterable<Chat> chats = chatService.findAllChats();
        model.addAttribute("chats", chats);
        return "chatList";
    }

    @PostMapping("/chat/new")
    public String createChat() {
        Chat chat = chatService.createChat();
        return "redirect:/chat/" + chat.getId();
    }

    @GetMapping("/chat/{chatId}")
    public String findChatById(@PathVariable Long chatId, Model model) {
        Chat chat = chatService.findChatById(chatId);
        model.addAttribute("chat", chat);
        model.addAttribute("messages", chat.getMessages());
        return "chatRoom";
    }
}
