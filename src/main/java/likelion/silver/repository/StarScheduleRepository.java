package likelion.silver.repository;

import likelion.silver.domain.StarSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StarScheduleRepository extends JpaRepository<StarSchedule, Long> {
    List<StarSchedule> findAllByArtistAndDate(String artist, String date);
}
