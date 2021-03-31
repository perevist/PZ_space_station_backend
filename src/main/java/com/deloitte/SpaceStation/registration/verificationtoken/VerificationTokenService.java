package com.deloitte.SpaceStation.registration.verificationtoken;

import com.deloitte.SpaceStation.exception.Error;
import com.deloitte.SpaceStation.exception.SpaceStationException;
import com.deloitte.SpaceStation.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VerificationTokenService {

    private final VerificationTokenRepository verificationTokenRepository;

    public String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationToken.setExpiryDate(LocalDateTime.now().plusDays(1));
        verificationTokenRepository.save(verificationToken);
        return token;
    }

    public VerificationToken getVerificationToken(String token) {
        var verificationToken = verificationTokenRepository.findByToken(token)
                .orElseThrow(() -> new SpaceStationException(Error.INCORRECT_VERIFICATION_TOKEN));
        verifyVerificationTokenExpiryDate(verificationToken);
        return verificationToken;
    }

    private void verifyVerificationTokenExpiryDate(VerificationToken token) {
        LocalDateTime expiryDate = token.getExpiryDate();

        if (expiryDate.isBefore(LocalDateTime.now())) {
            throw new SpaceStationException(Error.VERIFICATION_TOKEN_IS_NOT_VALID);
        }
    }
}
