package com.ceos22.spring_boot.common.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "로그인 요청 DTO")
public record LoginRequestDto(
        @Schema(description = "사용자 이름", example = "evan523") String username,
        @Schema(description = "비밀번호", example = "password123") String password
) {}
