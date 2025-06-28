package likelion.silver.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import likelion.silver.domain.User;

@Data
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String nickname;
    private boolean idol;

    public static UserResponseDto from(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getNickname(),
                user.isIdol()
        );
    }
}

