package com.scoring.services.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.scoring.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.scoring.dto.DirigeantDTO;
import com.scoring.services.IMailService;
import com.scoring.utils.DateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;



@Service
public class MailServiceImpl implements IMailService {
    
    @Autowired
    private JavaMailSender sender;

    @Autowired
    private Configuration freemarkerConfig;

	@Value("${spring.mail.username}")
	String mailSender;
	
	//@Value("${signature.mail}")
	//String signature;
	
	@Value("${url.server}")
	String url;
	
	private InternetAddress from;

	@Async
	public void sendNotification(DirigeantDTO dirigeantDTO, String msg) throws Exception {
		MimeMessage message = sender.createMimeMessage();
		Map<String, Object> model = new HashMap();
		model.put("url", url);
		model.put("text", msg);
		model.put("date", formatDateNormal(new Date()));
		String text = feedTemplate(model, "templates/velocity/notification.ftl");
	    sendMail(message, from, dirigeantDTO.getEmail(), text, "Eligibilite "+ dirigeantDTO.getEntreprise().getRaisonSociale());
	}

	@Override
	public void sendDemandeNotification(UserDTO user, String nomEntreprise) throws Exception{
		MimeMessage message = sender.createMimeMessage();
		Map<String, Object> model = new HashMap();
		model.put("url", url);
		model.put("date", formatDateNormal(new Date()));
		model.put("nom_pme", nomEntreprise);
		String text = feedTemplate(model, "templates/velocity/notification-demande.ftl");
		sendMail(message, from, user.getEmail(), text, "Demande de scoring  pour l'entreprise "+nomEntreprise);
	}

	public String formatDateNormal(Date date) {
		return DateUtils.formatDate(date);
	}

	private void sendMail(final MimeMessage message, InternetAddress from, String to, String text, String subject) throws MessagingException, UnsupportedEncodingException {
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(new InternetAddress(mailSender, "cipme.ci"));
        helper.setTo(to);
        helper.setText(text, true); // set to html
        helper.setSubject(subject);
        new Thread(new Runnable() {
			public void run() {
				sender.send(message);
			}
		}).start();
    }
    
	 private String feedTemplate(Map<String, Object> model, String templatePath) throws IOException, TemplateException {
	    freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/");
	    Template t = freemarkerConfig.getTemplate(templatePath);
	    String text = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
	    return text;
	 }
	 
}
