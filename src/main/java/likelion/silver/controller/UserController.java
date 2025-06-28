package likelion.silver.controller;

import likelion.silver.dto.request.UserRequestDto;
import likelion.silver.dto.response.ApiResponse;
import likelion.silver.dto.response.UserResponseDto;
import likelion.silver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    // 🔹 유저 생성 (아이돌 or 팬)
    @PostMapping
    public ResponseEntity<ApiResponse> createUser(@RequestBody UserRequestDto dto) {
        UserResponseDto data = userService.createUser(dto);
        ApiResponse response = new ApiResponse(true, 201, "유저 생성 성공", data);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 🔹 전체 유저 조회
    @GetMapping
    public ResponseEntity<ApiResponse> getAllUsers() {
        List<UserResponseDto> list = userService.getAllUsers();
        ApiResponse response = new ApiResponse(true, 200, "전체 유저 조회 성공", list);
        return ResponseEntity.ok(response);
    }

    // 🔹 유저 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable Long id) {
        UserResponseDto data = userService.getUserById(id);
        ApiResponse response = new ApiResponse(true, 200, "유저 조회 성공", data);
        return ResponseEntity.ok(response);
    }
}
