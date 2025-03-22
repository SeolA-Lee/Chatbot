package com.example.chatbot;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class ChatController {

    private final OpenAiChatModel openAiChatModel;

    @PostMapping("/chat")
    public Map<String, String> chat(@RequestBody String message) {

        Map<String, String> response = new HashMap<>();

        String openAiResponse = openAiChatModel.call(message);
        response.put("openaiResponse", openAiResponse);

        return response;
    }
}
