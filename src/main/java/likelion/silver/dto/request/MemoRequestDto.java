package likelion.silver.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemoRequestDto {
    private String username;
    private String date;
    private String artist;
    private String description;
}

