package com.caiobraz.servidorapi.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.caiobraz.servidorapi.entity.Token;
import com.caiobraz.servidorapi.entity.User;
import com.caiobraz.servidorapi.repository.TokenRepository;
import com.caiobraz.servidorapi.repository.UserRepository;
import com.caiobraz.servidorapi.security.dto.AuthRequest;
import com.caiobraz.servidorapi.security.dto.AuthResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    private final PasswordEncoder passwordEncoder;

    public AuthResponse authenticate(AuthRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        User user = userRepository.findByUsername(request.username()).orElseThrow();
        String jwt = jwtService.generateToken(user);
        String refresh = jwtService.generateRefreshToken(user);

        Token token = new Token();
        token.setToken(refresh);
        token.setUser(user);
        token.setExpired(false);
        token.setRevoked(false);
        tokenRepository.save(token);

        return new AuthResponse(jwt, refresh);
    }

    public AuthResponse refreshToken(String refreshToken) {
        var username = jwtService.extractUsername(refreshToken);
        var user = userRepository.findByUsername(username).orElseThrow();

        tokenRepository.findByToken(refreshToken)
                .filter(t -> !t.isExpired() && !t.isRevoked())
                .orElseThrow(() -> new RuntimeException("Refresh token inválido"));

        String newAccess = jwtService.generateToken(user);
        return new AuthResponse(newAccess, refreshToken); // ou gere um novo se quiser
    }
}
