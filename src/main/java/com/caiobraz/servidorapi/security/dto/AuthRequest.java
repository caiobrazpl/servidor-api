package com.caiobraz.servidorapi.security.dto;

import jakarta.validation.constraints.NotEmpty;

public record AuthRequest(@NotEmpty String username, @NotEmpty String password) {
}
