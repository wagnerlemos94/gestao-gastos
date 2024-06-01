package com.digitadasistemas.gestaogastos.controller.services;

import com.digitadasistemas.gestaogastos.model.dto.LancamentoEmailDTO;
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
import java.util.stream.Collectors;

@Component
public class EmailServiceBase {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private TemplateEngine thymeleaf;
    @Autowired
    @Value("${email-cop}")
    private String email;
    @Autowired
    private TemplateEngine templateEngine;
    private final Context context = new Context(new Locale("pt", "BR"));

    @Autowired
    private LancamentoService lancamentoService;

    protected String sendMail(Object obj, String destinatario, String assunto, String templateEmail) {
        try {
            MimeMessage mail = mailSender.createMimeMessage();

            Map<String, Object> variaveis = new HashMap<>();
            variaveis.put(obj.getClass().getSimpleName(), obj);
            variaveis.entrySet().forEach(e -> this.context.setVariable(e.getKey(), e.getValue()));
            mimeMessageHelper(mail, destinatario, assunto, templateEmail);
            mailSender.send(mail);

            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar e-mail";
        }
    }

    private void mimeMessageHelper(MimeMessage mail,String destinatario, String assunto, String templateEmail) throws MessagingException {
        MimeMessageHelper helper = new MimeMessageHelper( mail, "UTF-8" );
        helper.setTo(destinatario);
        helper.setSubject(assunto);
        helper.setCc(email);

        String body = thymeleaf.process(templateEmail, context);
        helper.setText(body, true);
    }

    public void sendEmail(String to, String subject, String name) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        Context context = new Context();
        context.setVariable("name", name);
        String htmlContent = templateEngine.process("emailTemplate", context);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);

        mailSender.send(message);
    }

    public void sendEmaillancamentoPendente() throws MessagingException {

        var lancamentosPendentes = lancamentoService.findAllLancamentosPendentes().stream().map(LancamentoEmailDTO::new).collect(Collectors.toList());

       lancamentosPendentes.forEach(lancamentoPendente -> {
           MimeMessage message = mailSender.createMimeMessage();
           MimeMessageHelper helper = null;
           try {
               helper = new MimeMessageHelper(message, true);
           } catch (MessagingException e) {
               throw new RuntimeException(e);
           }

           Context context = new Context();
            context.setVariable("name", lancamentoPendente.getUsuario().getNome());
            context.setVariable("tipo", lancamentoPendente.getTipo());
            context.setVariable("mes", lancamentoPendente.getMes());
            context.setVariable("ano", lancamentoPendente.getAno());
            context.setVariable("data", lancamentoPendente.getData());
            context.setVariable("grupo", lancamentoPendente.getGrupo());
            context.setVariable("descricao", lancamentoPendente.getDescricao());
            context.setVariable("valor", lancamentoPendente.getValor());
            context.setVariable("categoria", lancamentoPendente.getCategoria());
            context.setVariable("status", lancamentoPendente.getStatus());
            String htmlContent = templateEngine.process("lancamentosPendentesTemplate", context);

            try {
                helper.setTo(lancamentoPendente.getUsuario().getEmail());
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            try {
                helper.setSubject("Lancamentos pendentes!");
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            try {
                helper.setText(htmlContent, true);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            mailSender.send(message);
        });

    }
}
