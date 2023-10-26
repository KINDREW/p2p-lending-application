package com.p2p.p2p_lending_application.services.implementations;

import com.p2p.p2p_lending_application.exceptions.TokenRefreshException;
import com.p2p.p2p_lending_application.models.RefreshToken;
import com.p2p.p2p_lending_application.repositories.RefreshTokenRepository;
import com.p2p.p2p_lending_application.repositories.UserRepository;
import com.p2p.p2p_lending_application.services.interfaces.RefreshTokenService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Value("${authentication.app.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;
    public Optional<RefreshToken> findByToken(String token){
        return refreshTokenRepository.findBytoken(token);
    }
    public RefreshToken createRefreshToken(Long userId){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(userRepository.findById(userId).get());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }
    public RefreshToken verifyExpiration(RefreshToken token) throws TokenRefreshException{
        if(token.getExpiryDate().compareTo(Instant.now())<0){
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(),"Refresh token was expired");
        }
        return token;
    }

    @Transactional
    public int deleteByUserId(Long userId){
        return refreshTokenRepository.deleteByuser(userRepository.findById(userId).get());
    }
}
