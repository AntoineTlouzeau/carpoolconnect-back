package com.incubateur.carpoolconnect.services.interfaces;

import org.thymeleaf.context.Context;

public interface EmailService {

    public void sendSimpleMessage(String to, String subject, String text);

    public void sendEmailWithHtmlTemplate(String to, String subject, String templateName, Context context);
}
