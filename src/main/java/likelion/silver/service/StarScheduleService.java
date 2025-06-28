package likelion.silver.service;

import likelion.silver.domain.StarSchedule;
import likelion.silver.repository.StarScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StarScheduleService {

    private final StarScheduleRepository starScheduleRepository;

    public List<StarSchedule> getSchedules(String artist, String date) {
        return starScheduleRepository.findAllByArtistAndDate(artist, date);
    }
}
