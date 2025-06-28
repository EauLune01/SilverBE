package likelion.silver.dto.socket;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessage {
    private Long senderId;
    private Long receiverId;
    private String content;
    private boolean isFromIdol;
}
