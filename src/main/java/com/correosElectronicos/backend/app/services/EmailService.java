package com.correosElectronicos.backend.app.services;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.correosElectronicos.backend.app.pojos.UsuarioPojo;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@Service
public class EmailService {

	private Configuration configuration;
	private JavaMailSender javaMailSender;
	
	public EmailService(Configuration configuration, JavaMailSender javaMailSender) {
		this.configuration = configuration;
		this.javaMailSender = javaMailSender;
	}
	
	private String getEmailContent(UsuarioPojo usuarioPojo) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, TemplateException, IOException {
		StringWriter stringWriter = new StringWriter();
		Map<String, Object> model = new HashMap<>();
		model.put("user", usuarioPojo);
		configuration.getTemplate("templateCorreo.ftlh").process(model, stringWriter);
		return stringWriter.getBuffer().toString();
	}
}
