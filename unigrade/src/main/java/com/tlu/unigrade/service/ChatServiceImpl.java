package com.tlu.unigrade.service;

import org.springframework.stereotype.Service;

import com.google.genai.Chat;
import com.tlu.unigrade.component.UserChatSession;
import com.tlu.unigrade.dto.chat.ChatDTO;
import com.tlu.unigrade.enums.ChatRole;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Getter
public class ChatServiceImpl implements ChatService {

    private final UserChatSession userChatSession;

    @Override
    public ChatDTO chat(String message) {
        userChatSession.init();
        Chat chat = userChatSession.getChat();
        ChatDTO dto = new ChatDTO();
        dto.setRole(ChatRole.model);
        dto.setContent(chat.sendMessage(message).text());
        return dto;
    }
}
