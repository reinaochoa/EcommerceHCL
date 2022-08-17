package com.ecommerce.email;

import javax.validation.ValidationException;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

	private EmailConfig ec;
	public FeedbackController(EmailConfig ec) {
		this.ec =ec;
	}
	@PostMapping
	public void sendFeedback(@RequestBody Feedback fb, BindingResult br) {
		
		if(br.hasErrors()) {
			throw new ValidationException("Feedback: Not valid");
		}
		
		JavaMailSenderImpl mSend = new JavaMailSenderImpl();
		mSend.setHost(this.ec.getHost());
		mSend.setPort(this.ec.getPort());
		mSend.setUsername(this.ec.getUsername());
		mSend.setPassword(this.ec.getPassword());
				
		SimpleMailMessage mm = new SimpleMailMessage();
		mm.setFrom(fb.getEmail());
		mm.setTo("co@feedback.com");
		mm.setSubject("New feedback from " + fb.getName());
		mm.setText(fb.getFeedback());
		
		
		mSend.send(mm);

		
		
		
		
	}
}
