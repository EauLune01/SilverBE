package likelion.silver.controller;


import likelion.silver.dto.request.ChatRequestDto;
import likelion.silver.dto.response.ChatResponseDto;
import likelion.silver.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chats")
public class ChatController {

    private final ChatService chatService;

    // 🔹 1. 채팅 저장 (POST)
    @PostMapping
    public ChatResponseDto saveChat(@RequestBody ChatRequestDto dto) {
        return chatService.saveChat(dto);
    }

    // 🔹 2. 팬이 보는 채팅 조회
    @GetMapping("/fan/{userId}")
    public List<ChatResponseDto> getChatsForFan(@PathVariable Long userId) {
        return chatService.getChatsForFan(userId);
    }

    // 🔹 3. 아이돌이 보는 채팅 조회
    @GetMapping("/idol/{userId}")
    public List<ChatResponseDto> getChatsForIdol(@PathVariable Long userId) {
        return chatService.getChatsForIdol(userId);
    }
}

