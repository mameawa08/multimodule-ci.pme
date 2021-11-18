package com.administration.service;

import freemarker.template.TemplateException;


import javax.mail.MessagingException;

import com.administration.dto.UserDTO;

import java.io.IOException;

public interface IMailService {

    boolean sendForgotPasswordMail(UserDTO user, String url) throws IOException, TemplateException, MessagingException;

    boolean sendPasswordResetMail(UserDTO user, String url) throws IOException, TemplateException, MessagingException;

    boolean sendRegistrationMail(UserDTO user, String password) throws IOException, TemplateException, MessagingException;

    boolean sendUpdateUserMail(UserDTO user) throws IOException, TemplateException, MessagingException;

    boolean sendActivationMail(UserDTO user, String url) throws IOException, TemplateException, MessagingException;
}
