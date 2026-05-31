package com.tfi.Econexo.service.impl.auth;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.tfi.Econexo.model.auth.BlacklistedToken;
import com.tfi.Econexo.repository.auth.BlacklistedTokenRepository;
import com.tfi.Econexo.service.auth.BlacklistedTokenService;
import com.tfi.Econexo.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class BlacklistedTokenServiceImpl implements BlacklistedTokenService {

    private final BlacklistedTokenRepository repository;
    private final JwtUtils jwtUtils;


    @Override
    public void blacklistToken(String token) {
        if (repository.existsByToken(token)) {return;}

        DecodedJWT decodedJWT = jwtUtils.validateToken(token);
        Date expirationDate = decodedJWT.getExpiresAt();

        repository.save(new BlacklistedToken(token, expirationDate));
    }

    @Override
    public boolean isTokenBlacklisted(String token) {
        return repository.existsByToken(token);
    }
}
