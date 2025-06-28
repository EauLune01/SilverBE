package likelion.silver.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRequestDto {
    private Long senderId;
    private Long receiverId;
    private String content;
    private boolean isFromIdol;
}
