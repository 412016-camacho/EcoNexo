package com.tfi.Econexo.service.auth;

public interface BlacklistedTokenService {

    void blacklistToken(String token);

    boolean isTokenBlacklisted(String token);
}
