package likelion.silver.service;

import jakarta.transaction.Transactional;
import likelion.silver.domain.Chat;
import likelion.silver.domain.User;
import likelion.silver.dto.request.ChatRequestDto;
import likelion.silver.dto.response.ChatResponseDto;
import likelion.silver.repository.ChatRepository;
import likelion.silver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    // 🔹 1. 채팅 저장
    @Transactional
    public ChatResponseDto saveChat(ChatRequestDto dto) {
        User sender = userRepository.findById(dto.getSenderId())
                .orElseThrow(() -> new IllegalArgumentException("보낸 사람 없음"));
        User receiver = userRepository.findById(dto.getReceiverId())
                .orElseThrow(() -> new IllegalArgumentException("받는 사람 없음"));

        Chat chat = Chat.builder()
                .sender(sender)
                .receiver(receiver)
                .content(dto.getContent())
                .isFromIdol(dto.isFromIdol())
                .build();

        Chat saved = chatRepository.save(chat);
        return ChatResponseDto.from(saved);
    }

    // 🔹 2. 팬 채팅 조회 (내가 보낸 거 + 아이돌이 나한테 보낸 거)
    public List<ChatResponseDto> getChatsForFan(Long fanId) {
        User fan = userRepository.findById(fanId)
                .orElseThrow(() -> new IllegalArgumentException("팬 없음"));

        return chatRepository.findChatsForFan(fan)
                .stream()
                .map(ChatResponseDto::from)
                .toList();
    }

    // 🔹 3. 아이돌 채팅 조회 (팬이 나한테 보낸 메시지)
    public List<ChatResponseDto> getChatsForIdol(Long idolId) {
        User idol = userRepository.findById(idolId)
                .orElseThrow(() -> new IllegalArgumentException("아이돌 없음"));

        return chatRepository.findChatsForIdol(idol)
                .stream()
                .map(ChatResponseDto::from)
                .toList();
    }
}
