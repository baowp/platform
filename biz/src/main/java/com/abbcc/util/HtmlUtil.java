package com.abbcc.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HtmlUtil {
	 public static String getHTML(String pageURL, String encoding) {

		  StringBuilder pageHTML = new StringBuilder();

		  try {

		   URL url = new URL(pageURL);

		   HttpURLConnection connection = (HttpURLConnection) url
		     .openConnection();

		   connection.setRequestProperty("User-Agent", "MSIE 7.0");

		   BufferedReader br = new BufferedReader(new InputStreamReader(
		     connection.getInputStream(), encoding));

		   String line = null;

		   while ((line = br.readLine()) != null) {

		    pageHTML.append(line);

		    pageHTML.append("\r\n");

		   }

		   connection.disconnect();

		  } catch (Exception e) {

		   e.printStackTrace();

		  }

		  return pageHTML.toString();

	}
}
