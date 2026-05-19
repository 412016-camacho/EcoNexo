package com.tfi.Econexo.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthLoginRequestDTO(
    @NotBlank String email,
    @NotBlank String password
){}
