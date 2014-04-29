<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <style type="text/css">  
 {  
  margin: 0;  
  padding: 0;  
 }  
 html, body {  
  height: 100%;  
  text-align: center;  
  font: 12px/1.4 Verdana, sans-serif;  
 }  
 #wrapper {  
  width: 100%;  
  min-height: 100%;  
  background: ccc;  
  margin: auto;  
  text-align: left;  
 }  
 * html #wrapper {  
  height: 100%;  
 }  
 #header {  
  background: Green;  
  height: 40px;  
 }  
 #out-content {  
  padding-bottom: 50px;  
 }  
 #out-content:after {  
   clear: both;  
   display: block;  
   font: 1px/0px serif;  
   content: ".";  
   height: 0;  
   visibility: hidden;  
 }  
 * html #out-content {  
  height: 1%;  
 }  
 #sidebar {  
  float: left;  
  background: Gray;  
  margin-right: -200px;  
  width:200px;  
 }  
 #content-box {  
  float: right;  
  width: 100%;  
  background: Olive;  
 }  
#content {  
  margin-left: 200px;  
 }  
 #footer {  
  height: 50px;  
  background: Background;  
  margin: -50px auto 0;  
 }    
 </style>  
 </head>  
 <body>  
 <div id="wrapper">  
   <div id="header">  
     <h3>header</h3>  
     <code>#header {   
     background: Green;   
     height: 40px;   
     }</code> </div>  
   <div id="out-content">  
     <div id="content-box">  
       <div id="content">  
         <h3>content</h3>  
         <code>#content {   
           float: right;   
           width: 80%;   
           background: Olive;   
         } </code></div>  
     </div>  
     <div id="sidebar">  
       <h3>sidebar</h3>  
       <code>#sidebar {   
       float: left;   
       width: 20%;   
       background: Gray;   
       }</code> </div>  
   </div>  
 </div>  
 <div id="footer">  
   <h3>footer</h3>  
   <code>sdf   
   </code> </div>  
 </body>  
 </html> 