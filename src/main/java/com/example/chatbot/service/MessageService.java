package com.example.chatbot.service;

import com.example.chatbot.domain.Chat;
import com.example.chatbot.domain.Message;
import com.example.chatbot.dto.ChatDto;
import com.example.chatbot.dto.MessageDto;
import com.example.chatbot.prompt.SystemPromptFactory;
import com.example.chatbot.repository.MessageRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class MessageService {

    private final ChatService chatService;
    private final MessageRepository messageRepository;

    @Autowired
    private WebClient webClient;

    @Autowired
    private ObjectMapper objectMapper;

    public Message sendMessage(Long chatId, String content) {
        Chat chat = chatService.findChatById(chatId);
        log.info("sendMessage(chat.getType) = " + chat.getType());

        Message message = new Message();
        message.setChat(chat);
        message.setRole("user");
        message.setContent(content);
        messageRepository.save(message);

        List<Message> messages = chat.getMessages();
        List<MessageDto> messageDtos = new ArrayList<>();

        MessageDto systemPrompt = SystemPromptFactory.createSystemPrompt(chat.getType());
        messageDtos.add(systemPrompt);

        for (Message m : messages) {
            MessageDto messageDto = new MessageDto();
            messageDto.setRole(m.getRole());
            messageDto.setContent(m.getContent());
            messageDtos.add(messageDto);
            log.info("messageDto = " + messageDto);
            log.info("messageDtos = " + messageDtos);
        }

        ChatDto chatDto = new ChatDto();
        chatDto.setMessages(messageDtos);
        chatDto.setModel("gpt-4o-mini");

        try {
            String response = webClient.post()
                    .uri("https://api.openai.com/v1/chat/completions")
                    .bodyValue(chatDto)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            JsonNode root = objectMapper.readTree(response);
            String assistantContent = root.path("choices").get(0).path("message").path("content").asText();
            Integer totalTokens = root.path("usage").path("total_tokens").asInt();

            Message responseMessage = new Message();
            responseMessage.setChat(chat);
            responseMessage.setModel(chatDto.getModel());
            responseMessage.setRole("assistant");
            responseMessage.setContent(assistantContent);
            responseMessage.setTokenCount(totalTokens);
            messageRepository.save(responseMessage);

            return responseMessage;

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
}
