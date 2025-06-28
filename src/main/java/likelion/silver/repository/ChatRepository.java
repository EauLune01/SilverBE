package likelion.silver.repository;

import likelion.silver.domain.Chat;
import likelion.silver.domain.User;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    // 🔹 팬이 보는 채팅: 내가 보낸 거 + 아이돌이 나한테 보낸 거
    @Query("SELECT c FROM Chat c WHERE " +
            "(c.sender = :user AND c.isFromIdol = false) OR " +
            "(c.receiver = :user AND c.isFromIdol = true)")
    List<Chat> findChatsForFan(@Param("user") User user);

    // 🔹 아이돌이 보는 채팅: 팬이 나한테 보낸 메시지
    @Query("SELECT c FROM Chat c WHERE c.receiver = :idol AND c.isFromIdol = false")
    List<Chat> findChatsForIdol(@Param("idol") User idol);
}

