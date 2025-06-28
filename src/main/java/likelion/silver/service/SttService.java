package likelion.silver.service;

import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

@Service
public class SttService {

    public String transcribeAudio(String filePath) throws Exception {
        try (SpeechClient speechClient = SpeechClient.create()) {

            // 오디오 파일 읽기
            File file = new File(filePath);
            ByteString audioBytes = ByteString.readFrom(new FileInputStream(file));

            // 오디오 설정
            RecognitionConfig config = RecognitionConfig.newBuilder()
                    .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16) // WAV 파일이면 LINEAR16
                    .setLanguageCode("ko-KR") // 한국어
                    .setSampleRateHertz(16000) // 녹음 환경에 따라 조정 (WAV 보통 16000 or 44100)
                    .build();

            RecognitionAudio audio = RecognitionAudio.newBuilder()
                    .setContent(audioBytes)
                    .build();

            // 요청
            RecognizeResponse response = speechClient.recognize(config, audio);
            List<SpeechRecognitionResult> results = response.getResultsList();

            StringBuilder transcript = new StringBuilder();
            for (SpeechRecognitionResult result : results) {
                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
                transcript.append(alternative.getTranscript()).append(" ");
            }

            return transcript.toString();
        }
    }
}
