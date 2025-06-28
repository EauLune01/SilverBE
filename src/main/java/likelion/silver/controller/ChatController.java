package likelion.silver.controller;


import likelion.silver.dto.request.ChatRequestDto;
import likelion.silver.dto.response.ApiResponse;
import likelion.silver.dto.response.ChatResponseDto;
import likelion.silver.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chats")
public class ChatController {

    private final ChatService chatService;

    // 🔹 1. 채팅 저장 (POST)
    @PostMapping
    public ResponseEntity<ApiResponse> saveChat(@RequestBody ChatRequestDto dto) {
        ChatResponseDto response = chatService.saveChat(dto);
        ApiResponse apiResponse = new ApiResponse(true, 201, "채팅 저장 성공", response);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    // 🔹 2. 팬이 보는 채팅 조회
    @GetMapping("/fan/{userId}")
    public ResponseEntity<ApiResponse> getChatsForFan(@PathVariable Long userId) {
        List<ChatResponseDto> response = chatService.getChatsForFan(userId);
        ApiResponse apiResponse = new ApiResponse(true, 200, "팬 채팅 조회 성공", response);
        return ResponseEntity.ok(apiResponse);
    }

    // 🔹 3. 아이돌이 보는 채팅 조회
    @GetMapping("/idol/{userId}")
    public ResponseEntity<ApiResponse> getChatsForIdol(@PathVariable Long userId) {
        List<ChatResponseDto> response = chatService.getChatsForIdol(userId);
        ApiResponse apiResponse = new ApiResponse(true, 200, "아이돌 채팅 조회 성공", response);
        return ResponseEntity.ok(apiResponse);
    }
}

