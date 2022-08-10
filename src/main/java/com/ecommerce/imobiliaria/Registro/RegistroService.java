package com.ecommerce.imobiliaria.Registro;

import com.ecommerce.imobiliaria.Models.User;
import com.ecommerce.imobiliaria.Registro.Email.EmailSender;
import com.ecommerce.imobiliaria.Registro.Token.ConfirmationToken;
import com.ecommerce.imobiliaria.Registro.Token.ConfirmationTokenService;
import com.ecommerce.imobiliaria.Services.RoleService;
import com.ecommerce.imobiliaria.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistroService {

    private UserService userService;
    private ConfirmationTokenService confirmationTokenService;
    private EmailValidator emailValidator;
    private EmailSender emailSender;
    private RoleService roleService;


    @Transactional
    public String registro(RegistroRequest request) {
        boolean isValidEmail = emailValidator.test(request.getUsername());
        if (!isValidEmail) {
            throw new IllegalStateException("Formato de Email inválido. Esperado: exemplo@exemplo.exemplo");
        }
        String token = userService.signUpUser(
                new User(request.getNome(),
                        request.getUsername(),
                        request.getPassword(),
                        request.getIdentificacao())

        );
        roleService.salvarRoleNoUser("CONSUMIDOR", request.getUsername());
        String link = "http://localhost:8080/imobil/confirmar?token=" + token;
        emailSender.send(request.getUsername(), buildEmail(request.getNome(), link ));
        return token;

    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token).orElseThrow(() -> new IllegalArgumentException("Invalid token"));

        if (confirmationToken.getConfirmadoEm() != null) {
            throw new IllegalArgumentException("Email already confirmed");
        }

        LocalDateTime expirationDate = confirmationToken.getExpirouEm();

        if (expirationDate.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Token expired");
        }

        confirmationToken.setConfirmadoEm(LocalDateTime.now());

        userService.enableUser(confirmationToken.getUser().getUsername());

        return "Email confirmado com sucesso";


    }

    private String buildEmail(String name, String link) {
        return "<html>\n" +
                "<body>\n" +
                "<h1>Olá, " + name + "!</h1>\n" +
                "<h2> Obrigado por se cadastrar na Imobiliaria!</h2>"+
                "<p>Confirme seu email clicando no link abaixo</p>\n" +
                "<a href=\"" + link + "\">Clique aqui para confirmar seu email!</a>\n" +
                "<p>O link expira em 15 minutos</p>\n" +
                "</body>\n" +
                "</html>";
    }


}