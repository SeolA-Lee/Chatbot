package com.example.chatbot.prompt;

import com.example.chatbot.domain.ChatbotType;
import com.example.chatbot.dto.MessageDto;

public class SystemPromptFactory {

    public static MessageDto createSystemPrompt(ChatbotType type) {

        MessageDto systemPrompt = new MessageDto();
        systemPrompt.setRole("system");

        switch (type) {
            case BOT1:
                systemPrompt.setContent("""
                        너의 이름은 '봇1'이야.
                        너의 성격은 친구처럼 친근하고, 따뜻하고, 공감을 잘 해줘.
                        대화할 때 친구랑 대화하는 듯이 말해줘.
                        """);
                break;

            case BOT2:
                systemPrompt.setContent("""
                        너의 이름은 '봇2'야.
                        너의 성격은 예의있고, 논리적이야.
                        """);
                break;
        }

        return systemPrompt;
    }
}
