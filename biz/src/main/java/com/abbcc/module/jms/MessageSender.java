/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "MessageSender.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-3-6           yixiugg                      initial
**/

package com.abbcc.module.jms;
/**
 **MessageSender.java
 **/
import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session ;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext ;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator ;
import org.springframework.stereotype.Component;

@Component(value = "sender")
public class MessageSender {
    @Resource(name = "jmsTemplate") 
    private JmsTemplate jmsTemplate;
    public void sendString(String queueName, final String msg) {
    	System.out.println("进入到了messageSender.java.................................................................");
        jmsTemplate.send(queueName, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
            	
                return session.createTextMessage(msg);
            } 
        });
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        MessageSender sender = (MessageSender) ctx.getBean ("messageSender");
        sender.sendString("ABBCCMAIL", "aaaaa");
    }
	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
}

