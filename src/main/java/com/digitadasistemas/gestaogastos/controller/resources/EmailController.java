package com.digitadasistemas.gestaogastos.controller.resources;

import com.digitadasistemas.gestaogastos.commons.EmailTemplate;
import com.digitadasistemas.gestaogastos.controller.services.EmailService;
import com.digitadasistemas.gestaogastos.model.dto.UsuarioConsultaDTO;
import com.digitadasistemas.gestaogastos.model.dto.UsuarioInputDTO;
import com.digitadasistemas.gestaogastos.model.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping(path = "/email")
public class EmailController {

    @Autowired
    private EmailService emailService;
    @Autowired(required = false)
    private SimpleMailMessage simpleMailMessage;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private TemplateEngine thymeleaf;

    @GetMapping("/sand")
    public String sendEmail(){
        simpleMailMessage.setText("Hello from Spring Boot Application");
        simpleMailMessage.setTo("wagner.copertine@gmail.com");
        try {
            mailSender.send(simpleMailMessage);
            return "Email enviado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar email.";
        }
    }
    @GetMapping("/sand-html/novo-usuario")
    public String sendMail() {
        try {
            UsuarioInputDTO usuario = new UsuarioInputDTO();
            usuario.setEmail("wagner@email");
            usuario.setLogin("85806128539");
            usuario.setNome("wagner cupertino lemos");
            usuario.setSenha("123123");

            emailService.novoUsuario(usuario,"wagner.lemos94@hotmail.com");

            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar e-mail";
        }
    }
}
