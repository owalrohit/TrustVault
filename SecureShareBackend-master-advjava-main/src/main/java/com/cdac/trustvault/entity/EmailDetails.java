package com.cdac.trustvault.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class EmailDetails {

 private String recipient;
 private String msgBody;
 private String subject;
 private String attachment;
public EmailDetails() {
	super();
	// TODO Auto-generated constructor stub
}
public EmailDetails(String recipient, String msgBody, String subject, String attachment) {
	super();
	this.recipient = recipient;
	this.msgBody = msgBody;
	this.subject = subject;
	this.attachment = attachment;
}
public String getRecipient() {
	return recipient;
}
public void setRecipient(String recipient) {
	this.recipient = recipient;
}
public String getMsgBody() {
	return msgBody;
}
public void setMsgBody(String msgBody) {
	this.msgBody = msgBody;
}
public String getSubject() {
	return subject;
}
public void setSubject(String subject) {
	this.subject = subject;
}
public String getAttachment() {
	return attachment;
}
public void setAttachment(String attachment) {
	this.attachment = attachment;
}
@Override
public String toString() {
	return "EmailDetails [recipient=" + recipient + ", msgBody=" + msgBody + ", subject=" + subject + ", attachment="
			+ attachment + "]";
}
 
 
 
}

