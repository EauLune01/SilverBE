package likelion.silver.controller;
import likelion.silver.service.TtsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
@RestController
@RequiredArgsConstructor
public class TtsController {
    private final TtsService googleTTSService;

    @CrossOrigin(origins = "http://localhost:3000")  // React 서버 주소
    @PostMapping("/tts")
    public ResponseEntity<byte[]> generateTTS(@RequestParam String text) {
        try {
            // 1. 텍스트를 음성으로 변환 (mp3 생성)
            googleTTSService.synthesizeText(text);  // 내부에서 output.mp3 저장한다고 가정

            // 2. mp3 파일 로드
            File file = new File("SilverBE/src/main/resources/static/audio/output.mp3");
            byte[] audio = Files.readAllBytes(file.toPath());

            // 3. audio/mpeg 형식으로 응답
            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf("audio/mpeg"))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"tts.mp3\"")
                    .body(audio);

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(("TTS 오류: " + e.getMessage()).getBytes());
        }
    }
//    private final TtsService googleTTSService;
//
//    @PostMapping("/tts")
//    public ResponseEntity<String> generateTTS(@RequestParam String text) {
//        try {
//            googleTTSService.synthesizeText(text);
//            return ResponseEntity.ok("TTS 생성 완료");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("TTS 오류: " + e.getMessage());
//        }
//    }
//
//    @GetMapping("/tts")
//    public ResponseEntity<byte[]> getAudio() throws IOException {
//        File file = new File("SilverBE/src/main/resources/static/audio/output.mp3");
//
//        byte[] audio = Files.readAllBytes(file.toPath());
//        return ResponseEntity.ok()
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"tts.mp3\"")
//                .body(audio);
//    }
}
