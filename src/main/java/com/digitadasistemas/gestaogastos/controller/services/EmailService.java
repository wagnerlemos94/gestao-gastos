package com.digitadasistemas.gestaogastos.controller.services;

import com.digitadasistemas.gestaogastos.model.dto.UsuarioConsultaDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Getter
@Component
@RequiredArgsConstructor
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private TemplateEngine thymeleaf;
    @Autowired
    @Value("${email-cop}")
    private String email;


    private final Context context = new Context(new Locale("pt", "BR"));

    public String sendMail(Object obj, String destinatario, String assunto) {
        try {
            MimeMessage mail = mailSender.createMimeMessage();

            Map<String, Object> variaveis = new HashMap<>();
            variaveis.put(obj.getClass().getSimpleName(), obj);
            variaveis.entrySet().forEach(e -> this.context.setVariable(e.getKey(), e.getValue()));
            mimeMessageHelper(mail, destinatario, assunto);
            mailSender.send(mail);

            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar e-mail";
        }
    }

    private void mimeMessageHelper(MimeMessage mail,String destinatario, String assunto) throws MessagingException {
        MimeMessageHelper helper = new MimeMessageHelper( mail, "UTF-8" );
        helper.setTo(destinatario);
        helper.setSubject(assunto);
        helper.setCc(email);

        String body = thymeleaf.process("mail/cadastro-usuario", context);
        helper.setText(body, true);
    }

}
