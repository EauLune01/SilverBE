package likelion.silver.controller;

import likelion.silver.dto.request.ChatRequestDto;
import likelion.silver.dto.response.ApiResponse;
import likelion.silver.dto.response.ChatResponseDto;
import likelion.silver.dto.socket.ChatMessage;
import likelion.silver.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatSocketController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatService chatService;

    @MessageMapping("/chat.send") // 클라이언트는 /app/chat.send로 메시지 보냄
    public void sendMessage(@Payload ChatMessage message) {
        // 1. DB 저장
        ChatResponseDto saved = chatService.saveChat(
                new ChatRequestDto(
                        message.getSenderId(),
                        message.getReceiverId(),
                        message.getContent(),
                        message.isFromIdol()
                )
        );

        // 2. 응답 포맷 감싸기 (수업시간에 배운 ApiResponse)
        ApiResponse response = new ApiResponse(
                true,
                200,
                "채팅 저장 및 전송 성공",
                saved
        );

        // 3. 구독자에게 전송 (-> 클라이언트는 ApiResponse 구조로 받음)
        messagingTemplate.convertAndSend("/topic/messages/" + message.getReceiverId(), response);
    }
}
