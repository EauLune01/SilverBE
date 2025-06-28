package likelion.silver.service;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.texttospeech.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service
public class TtsService {

    @Value("${google.credentials.location}")
    private Resource credentialsPath;

    public void synthesizeText(String text) throws IOException {
        // JSON 키 파일을 불러와 Credentials 객체 생성
        GoogleCredentials credentials = GoogleCredentials
                .fromStream(credentialsPath.getInputStream())
                .createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));

        // TextToSpeechClient에 해당 credentials 적용
        TextToSpeechSettings settings = TextToSpeechSettings.newBuilder()
                .setCredentialsProvider(FixedCredentialsProvider.create(credentials))
                .build();

        try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create(settings)) {
            SynthesisInput input = SynthesisInput.newBuilder().setText(text).build();

            VoiceSelectionParams voice = VoiceSelectionParams.newBuilder()
                    .setLanguageCode("ko-KR")
                    .setSsmlGender(SsmlVoiceGender.NEUTRAL)
                    .build();

            AudioConfig audioConfig = AudioConfig.newBuilder()
                    .setAudioEncoding(AudioEncoding.MP3)
                    .build();

            SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);

            // 결과 파일 저장 (예: output.mp3)
            byte[] audioContents = response.getAudioContent().toByteArray();
            java.nio.file.Files.write(java.nio.file.Paths.get("SilverBE/src/main/resources/static/audio/output.mp3"), audioContents);
        }
    }
}

