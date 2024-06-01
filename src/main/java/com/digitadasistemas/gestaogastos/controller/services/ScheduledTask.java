package com.digitadasistemas.gestaogastos.controller.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
public class ScheduledTask {

    @Autowired
    private EmailServiceBase emailServiceBase;

    //"A tarefa agendada está sendo executada às 10:00 AM")
    @Scheduled(cron = "0 0 10 * * ?")
    public void sendEmaillancamentoPendente() throws MessagingException {
        emailServiceBase.sendEmaillancamentoPendente();
    }
}
