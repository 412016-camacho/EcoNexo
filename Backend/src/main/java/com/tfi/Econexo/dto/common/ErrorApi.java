package com.tfi.Econexo.dto.common;

import lombok.Builder;

@Builder
public record ErrorApi (
        String timestamp,
        Integer status,
        String error,
        String message
) {
}
