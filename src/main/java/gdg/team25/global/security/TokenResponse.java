package gdg.team25.global.security;

import jakarta.validation.constraints.NotBlank;

public record TokenResponse(@NotBlank String access_token, @NotBlank String refresh_token, String token_type, long expired_at, long expired_refresh_token_at) {

    public static TokenResponse of(String accessToken, String refreshToken, long accessExpireIn, long refreshExpireIn) {
        return new TokenResponse(accessToken, refreshToken, "Bearer", 1800000, 604800000);
    }
}
