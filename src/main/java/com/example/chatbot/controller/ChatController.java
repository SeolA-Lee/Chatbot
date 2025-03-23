package com.example.chatbot.controller;

import com.example.chatbot.domain.Chat;
import com.example.chatbot.domain.ChatbotType;
import com.example.chatbot.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
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

    @PostMapping("/chat/new/1")
    public String createChat1() {
        Chat chat = chatService.createChat(ChatbotType.BOT1);
        log.info("Chat Type = " + chat.getType());
        return "redirect:/chat/" + chat.getId();
    }

    @PostMapping("/chat/new/2")
    public String createChat2() {
        Chat chat = chatService.createChat(ChatbotType.BOT2);
        log.info("Chat Type = " + chat.getType());
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
