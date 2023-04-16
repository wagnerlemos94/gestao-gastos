package com.digitadasistemas.gestaogastos.controller.services;

import com.digitadasistemas.gestaogastos.commons.EmailTemplate;
import org.springframework.stereotype.Component;

@Component
public class EmailService extends EmailServiceBase{

    public void novoUsuario(Object obj, String destinatario){
        sendMail(obj, destinatario, EmailTemplate.EMAIL_AUSSUNTO_NOVO_USUARIO, EmailTemplate.EMAIL_NOVO_USUARIO);
    }

}
