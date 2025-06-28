package likelion.silver.dto.request;

import lombok.Data;

@Data
public class ChatRequestDto {
    private Long senderId;
    private Long receiverId;
    private String content;
    private boolean isFromIdol;
}