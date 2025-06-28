package likelion.silver.repository;

import likelion.silver.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 필요하면 닉네임으로 찾는 메서드도 추가 가능
    User findByNickname(String nickname);
}
