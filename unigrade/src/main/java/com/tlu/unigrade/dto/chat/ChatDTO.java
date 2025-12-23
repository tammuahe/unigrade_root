package com.tlu.unigrade.dto.chat;
import com.tlu.unigrade.enums.ChatRole;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ChatDTO {
    private ChatRole role;
    private String content;
}
