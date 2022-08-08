package com.ecommerce.imobiliaria.Registro.Token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken confirmationToken) {
        confirmationTokenRepository.save(confirmationToken);
    }
    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }


    public void setExpirouEm(String token ) {
        confirmationTokenRepository.findByToken(token).ifPresent(confirmationToken -> {
            LocalDateTime LocalDateTime = java.time.LocalDateTime.now();
            confirmationToken.setExpirouEm(LocalDateTime.now().plusMinutes(15));
            confirmationTokenRepository.save(confirmationToken);
        });
    }
}
