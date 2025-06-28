package likelion.silver.controller;

import likelion.silver.service.SttService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequiredArgsConstructor
public class SttController {

    private final SttService sttService;

    @PostMapping("/stt")
    public ResponseEntity<String> transcribe(@RequestParam("file") MultipartFile file) {
        try {
            // 저장할 임시 경로
            String tempPath = "temp_audio.wav";
            file.transferTo(new File(tempPath));

            String text = sttService.transcribeAudio(tempPath);
            return ResponseEntity.ok(text);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("STT 변환 오류: " + e.getMessage());
        }
    }
}