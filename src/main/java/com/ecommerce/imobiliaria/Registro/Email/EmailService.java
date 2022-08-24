package com.ecommerce.imobiliaria.Registro.Email;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender {

        private final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

        private final JavaMailSender javaMailSender;

        @Override
        @Async
        public void send(String para, String email) {
            try {
                MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
                mimeMessageHelper.setText(email, true);
                mimeMessageHelper.setTo(para);
                mimeMessageHelper.setSubject("Confirmação de cadastro");
                mimeMessageHelper.setFrom("imobiliarianossolar@datalore.com.br");
                javaMailSender.send(mimeMessage);
            }catch (MessagingException e) {
                LOGGER.error("Erro ao enviar o email", e);
                throw new IllegalStateException("Erro ao enviar o email", e);
            }
        }
    }

