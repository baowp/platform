/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "fgh.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-5           Wangjin                      initial
 */

package com.abbcc.helper;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.abbcc.common.CommonConst;
import com.abbcc.util.StringUtil;

public class JavaMailHelper {
	/**
	 * 
	 * @param from
	 *            你的邮箱地址
	 * @param username
	 *            你的邮箱用户名
	 * @param password
	 *            你的邮箱密码
	 * @param smtp
	 *            设置smtp的地址，如：163的为"smtp.163.com"
	 * @param to
	 *            收件人地址
	 * @param cc
	 *            抄送人地址
	 * @param bcc
	 *            密送人地址
	 * @param title
	 *            标题
	 * @param content
	 *            内容
	 * @param attachment
	 *            附件名称
	 * @param attachmentPath
	 *            附件地址
	 * @return
	 */
	public String sendMail(String smtp, String from, String username,
			String password, String too, String ccc, String bccc, String title,
			String content1, String attachment, String attachmentPath) {
		attachmentPath = CommonConst.REALPATH+StringUtil.pathReplace(attachmentPath);
		// 把收件人通过分号分隔出来放到数组里

		// 获得属性，并生成Session对象
		Properties props = new Properties();
		Session sendsession;
		Transport transport;
		MimeMessage message = null;
		BodyPart messageBodyPart = new MimeBodyPart();
		Multipart multipart = new MimeMultipart();
		// String from = "ykwj715@163.com";
		String to[] = too.split(";");
		String cc[] = { ccc };
		String bcc[] = { bccc };
		// String content = "<font style=\"BACKGROUND-COLOR: #666699\"
		// color=\"#ff0000\" size=\"5\">测试格式化内容测试<a
		// href=\"\">格式化内容</a>测试格<em>式化</em>内容</font>";
		String content = content1;

		try {

			sendsession = Session.getInstance(props, null);
			// 向属性中写入SMTP服务器的地址
			props.put("mail.smtp.host", smtp);
			// 设置SMTP服务器需要权限认证
			props.put("mail.smtp.auth", "true");
			// 设置输出调试信息
			// sendsession.setDebug(true);
			// 根据Session生成Message对象
			message = new MimeMessage(sendsession);
			// 设置发信人地址
			message.setFrom(new InternetAddress(from));
			// 设置收信人地址
			String toList = getMailList(to);
			InternetAddress[] iaToList = new InternetAddress().parse(toList);
			message.setRecipients(Message.RecipientType.TO, iaToList);
			if (cc != null) {
				String ccList = this.getMailList(cc);
				InternetAddress[] iaCCList = new InternetAddress()
						.parse(ccList);
				message.setRecipients(Message.RecipientType.CC, iaCCList);
			}
			if (bcc != null) {
				String bccList = this.getMailList(bcc);
				InternetAddress[] iaBCCList = new InternetAddress()
						.parse(bccList);
				message.setRecipients(Message.RecipientType.BCC, iaBCCList);
			}
			// 设置e-mail标题
			message.setSubject(title);
			// 设置e-mail发送时间
			message.setSentDate(new Date());
			// 设置e-mail内容
			message.setText(content);
			// 建立第一部分：文本正文
			messageBodyPart.setContent(content, "text/html;charset=utf-8");// 给BodyPart对象设置内容和格式/编码方式
			multipart.addBodyPart(messageBodyPart);
			if (!attachment.equals("")) {
				// 建立第二部分：附件
				messageBodyPart = new MimeBodyPart();
				// 获得附件
				DataSource source = new FileDataSource(attachmentPath);
				// 设置附件的数据处理器
				messageBodyPart.setDataHandler(new DataHandler(source));
				// 设置附件文件名
				messageBodyPart.setFileName(attachment);
				// 加入第二部分
				multipart.addBodyPart(messageBodyPart);
			}
			// 将多部分内容放到e-mail中
			message.setContent(multipart);

			// 保存对于e-mail的修改
			message.saveChanges();
			// 根据Session生成Transport对象
			transport = sendsession.getTransport("smtp");
			// 连接到SMTP服务器
			transport.connect(smtp, username, password);
			// 发送e-mail
			transport.sendMessage(message, message.getAllRecipients());
			// 关闭Transport连接
			transport.close();
		} catch (MessagingException m) {
			System.out.println(m.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * 发送邮件
	 * 
	 * @param too
	 * @param ccc
	 * @param bccc
	 * @param title
	 * @param content
	 * @param attachment
	 * @param attachmentPath
	 * @return
	 */
	public String sendMail(String too, String ccc, String bccc, String title,
			String content, String attachment, String attachmentPath) {
		return sendMail(CommonConst.SITEINFO.smtp,
				CommonConst.SITEINFO.adminemail,
				CommonConst.SITEINFO.mailusername,
				CommonConst.SITEINFO.mailpassword, too, ccc, bccc, title,
				content, attachment, attachmentPath);
	}

	// 获取收件人地址
	public String getMailList(String[] mailArray) {

		StringBuffer toList = new StringBuffer();
		int length = mailArray.length;
		if (mailArray != null && length < 2) {
			toList.append(mailArray[0]);
		} else {
			for (int i = 0; i < length; i++) {
				toList.append(mailArray[i]);
				if (i != (length - 1)) {
					toList.append(",");
				}
			}
		}

		return toList.toString();
	}

	public static void main(String[] args) {
		/*new JavaMailHelper().sendMail("smtp.163.com", "ykwj715@163.com",
				"ykwj715", "33022938", "123445914@qq.com", "", "", "title",
				"123", "1", "");*/
		new JavaMailHelper().sendMail("123445914@qq.com","","", "title", "content", "", "");
	}

}
