<html>
<head>
  <title>系统公告</title>
  <meta http-equiv=Content-Type content=text/html;charset=utf-8>
</head>
<body bgcolor="#F0FFFF">
<table>
<tr><td><center><h1>${(news_list.title)!""}</h1><br/></td></tr>
<tr><td><font size=4>发布时间:${news_list.addTime?string('yyyy-MM-dd HH:mm:ss')}</font></td></tr>
<tr><td> <p>${(news_list.content)!""}</p></td></tr>
<tr><td>&nbsp</td></tr>
</table>
</body>
</html> 