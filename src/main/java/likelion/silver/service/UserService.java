package likelion.silver.service;

import likelion.silver.domain.User;
import likelion.silver.dto.request.UserRequestDto;
import likelion.silver.dto.response.UserResponseDto;
import likelion.silver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 유저 생성
    public UserResponseDto createUser(UserRequestDto dto) {
        User user = User.builder()
                .nickname(dto.getNickname())
                .idol(dto.isIdol())
                .build();

        User saved = userRepository.save(user);
        return UserResponseDto.from(saved);
    }

    // 전체 유저 조회
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserResponseDto::from)
                .collect(Collectors.toList());
    }

    // 단건 조회
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
        return UserResponseDto.from(user);
    }
}

