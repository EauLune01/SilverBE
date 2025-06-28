package likelion.silver.dto.response;

import likelion.silver.domain.Chat;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatResponseDto {
    private Long id;
    private String senderName;
    private String receiverName;
    private String content;
    private boolean isFromIdol;
    private String createdAt;

    public static ChatResponseDto from(Chat chat) {
        return new ChatResponseDto(
                chat.getId(),
                chat.getSender().getNickname(),
                chat.getReceiver().getNickname(),
                chat.getContent(),
                chat.isFromIdol(),
                chat.getCreatedAt().toString()
        );
    }
}