package likelion.silver.controller;

import likelion.silver.domain.Memo;
import likelion.silver.domain.StarSchedule;
import likelion.silver.dto.request.MemoRequestDto;
import likelion.silver.repository.MemoRepository;
import likelion.silver.service.StarScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    private final StarScheduleService starScheduleService;
    private final MemoRepository memoRepository;

    @Autowired
    public ScheduleController(StarScheduleService starScheduleService,
                              MemoRepository memoRepository) {
        this.starScheduleService = starScheduleService;
        this.memoRepository = memoRepository;
    }

    @GetMapping("/artist/{artist}/memo")
    public ResponseEntity<?> getScheduleAndMemo(
            @PathVariable("artist") String artist,
            @RequestParam("date") String date,
            @RequestParam("username") String username) {

        List<StarSchedule> scheduleList = starScheduleService.getSchedules(artist, date);
        Optional<Memo> memo = memoRepository.findByUsernameAndDate(username, date);

        Map<String, Object> response = new HashMap<>();
        response.put("schedules", scheduleList);
        response.put("memo", memo.orElse(null));

        return ResponseEntity.ok(response);
    }

    @PostMapping("/memo")
    public ResponseEntity<?> saveMemo(@RequestBody MemoRequestDto requestDto) {
        Memo memo = Memo.builder()
                .username(requestDto.getUsername())
                .artist(requestDto.getArtist())
                .date(requestDto.getDate())
                .description(requestDto.getDescription())
                .build();

        memoRepository.save(memo);

        return ResponseEntity.ok().body("메모가 저장되었습니다!");
    }

}
