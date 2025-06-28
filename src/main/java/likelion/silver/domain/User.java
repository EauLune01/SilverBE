package likelion.silver.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")  // 테이블 이름을 "users"로 지정
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
    private boolean idol; // ✅ 이름 변경: isIdol → idol

}