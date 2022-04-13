package com.administration.service.impl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;


import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.administration.dto.UserDTO;
import com.administration.service.IMailService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailServiceImpl implements IMailService {
    
    @Autowired
    private JavaMailSender sender;

    @Autowired
    private Configuration freemarkerConfig;

    @Value("${url.server}")
    String url;

    @Value("${spring.mail.username}")
    String mailSender;

    private InternetAddress from;

    @PostConstruct
    public void init() throws UnsupportedEncodingException {
        from = new InternetAddress(mailSender, "Agence CI PME");
    }

    @Override
    public boolean sendForgotPasswordMail(UserDTO user, String url) throws IOException, TemplateException, MessagingException {
        MimeMessage message = sender.createMimeMessage();
        Map<String, Object> model = setUserInTemplate(user);
        model.put("url", url);
        String text = feedTemplate(model, "templates/velocity/mdp-oublie.ftl");
        sendMail(message, from, user.getEmail(),text,"Nouveau Mot de Passe");
        return true;
    }

    @Override
    public boolean sendPasswordResetMail(UserDTO user, String url) throws IOException, TemplateException, MessagingException {
        MimeMessage message = sender.createMimeMessage();
        Map<String, Object> model = setUserInTemplate(user);
        model.put("url", url);
        model.put("motdepasse", user.getPassword());
        String text = feedTemplate(model, "templates/velocity/mdp_modifier.ftl");
        sendMail(message, from, user.getEmail(), text, "Réinitialisation de votre mot de passe");

        return true;
    }

    @Override
    public boolean sendRegistrationMail(UserDTO user, String password) throws IOException, TemplateException, MessagingException {
        MimeMessage message = sender.createMimeMessage();
        Map<String, Object> model = setUserInTemplate(user);
        model.put("url", url);
        model.put("motdepasse", password);
        String text = feedTemplate(model, "templates/velocity/connexion-template.ftl");
        sendMail(message, from, user.getEmail(), text, "Invitation");

        return true;
    }

    @Override
    public boolean sendUpdateUserMail(UserDTO user) throws IOException, TemplateException, MessagingException {
        MimeMessage message = sender.createMimeMessage();
        Map<String, Object> model = setUserInTemplate(user);
        model.put("url", url);
        model.put("email", user.getEmail());
        model.put("profil", user.getProfil().getLibelle());
        String text = feedTemplate(model, "templates/velocity/modif-user.ftl");
        sendMail(message, from, user.getEmail(), text, "Modification Utilisateur");
        return false;
    }

    @Override
    public boolean sendActivationMail(UserDTO user, String url) throws IOException, TemplateException, MessagingException {
        MimeMessage message = sender.createMimeMessage();
        Map<String, Object> model = setUserInTemplate(user);
        model.put("url", url);
        String text = feedTemplate(model, "templates/velocity/activation.ftl");
        sendMail(message, from, user.getEmail(),text,"Activation du compte");
        return true;
    }

    //Private methods

    private Map<String, Object> setUserInTemplate( UserDTO user) {
        Map<String, Object> model = new HashMap<>();
        model.put("nomUser", user.getNom());
        model.put("prenomUser", user.getPrenom());
        model.put("login", user.getUsername());

        return model;
    }

    private String feedTemplate(Map<String, Object> model, String templatePath) throws IOException, TemplateException {
        freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/");
        Template t = freemarkerConfig.getTemplate(templatePath);
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
        return text;
    }

    private void sendMail(MimeMessage message, InternetAddress from, String to, String text, String subject) throws MessagingException {
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setText(text, true); // set to html
        helper.setSubject(subject);
        new Thread(() -> {
            sender.send(message);
        }).start();
    }
}
