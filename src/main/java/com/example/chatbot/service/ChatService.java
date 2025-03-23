package com.example.chatbot.service;

import com.example.chatbot.domain.Chat;
import com.example.chatbot.domain.ChatbotType;
import com.example.chatbot.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChatService {

    private final ChatRepository chatRepository;

    public Chat createChat(ChatbotType type) {
        Chat chat = new Chat();
        chat.setType(type);
        chatRepository.save(chat);
        return chat;
    }

    public Iterable<Chat> findAllChats() {
        return chatRepository.findAll();
    }

    public void deleteChat(Long chatId) {
        chatRepository.deleteById(chatId);
    }

    public Chat findChatById(Long chatId) {
        return chatRepository.findById(chatId).orElse(null);
    }
}
