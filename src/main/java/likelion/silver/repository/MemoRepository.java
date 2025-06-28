package likelion.silver.repository;

import likelion.silver.domain.Memo;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    Optional<Memo> findByUsernameAndDate(String username, String date);
}
