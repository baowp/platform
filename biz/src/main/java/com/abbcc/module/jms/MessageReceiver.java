/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "MessageReceiver.java is the copyrighted,
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
 **MessageReceiver.java
 **/

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component(value = "messageReceiver")
public class MessageReceiver {
	private Log log = LogFactory.getLog(this.getClass());
    public void receive(String msg) {
    	System.out.println("消息接收成功!");
    }

}