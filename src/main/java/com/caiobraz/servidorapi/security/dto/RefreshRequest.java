package com.caiobraz.servidorapi.security.dto;

import jakarta.validation.constraints.NotEmpty;

public record RefreshRequest(@NotEmpty String refreshToken) {
}
