package likelion.silver.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nickname;  // 예: "임영웅", "팬1", "팬2"

    @Column(nullable = false)
    private boolean isIdol;   // true면 아이돌(임영웅), false면 팬

    // 생성자 예시
    public User(String nickname, boolean isIdol) {
        this.nickname = nickname;
        this.isIdol = isIdol;
    }
}