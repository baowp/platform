<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="/user/free_user/style/css.css" rel="stylesheet" type="text/css" />
<link href="/user/free_user/style/div.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function addPage(){
 var ctrl = (navigator.userAgent.toLowerCase()).indexOf('mac') != -1 ? 'Command/Cmd' : 'CTRL';
 if (document.all){
 window.external.addFavorite('http://51archetype.com/','东方五金平台');
 }else if (window.sidebar){
 window.sidebar.addPanel('东方五金平台', 'h51archetype.comcc.net/', "");
 }else {
 alert('您可以尝试通过快捷键' + ctrl + ' + D 加入到收藏夹~');
 }
} 
</script>
<script src="/js/jquery.js" type="text/javascript"></script>
<script src="/js/AC_RunActiveContent.js" type="text/javascript"></script>