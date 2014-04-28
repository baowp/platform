<html>
<head>
  <title>新闻</title>
  <meta http-equiv=Content-Type content=text/html;charset=utf-8>
</head>
<body bgcolor="#F0FFFF">
<table>
<tr><td><center><h1>${news_list.title}</h1><br/><font size=4>发布时间:${news_list.addTime?string('yyyy-MM-dd')}</font></td></tr>
<tr><td> <p>${news_list.content}</p></td></tr>
<tr><td>(本文来源：${news_list.origin?if_exists}：${news_list.author?if_exists})</center></td></tr>
<tr><td>&nbsp</td></tr>
<tr><td  align="right"> ${page}</td></tr>
</table>
</body>
</html> 