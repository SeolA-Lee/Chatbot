package com.example.chatbot.controller;

import com.example.chatbot.domain.Message;
import com.example.chatbot.dto.MessageDto;
import com.example.chatbot.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/chat/{chatId}")
    public String createMessage(@PathVariable Long chatId, @ModelAttribute MessageDto messageDto) {
        String content = messageDto.getContent();
        Message message = messageService.sendMessage(chatId, content);
        return "redirect:/chat/" + message.getChat().getId();
    }
}
