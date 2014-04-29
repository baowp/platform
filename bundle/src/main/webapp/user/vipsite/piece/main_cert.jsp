<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:action namespace="/vip" name="*/pieceCert">
	<s:param name="enterpriseId" value="enterpriseId"/>
</s:action>
<div id="cert" class="bodyCont moveChild">
	<s:hidden name="piece" value="%{@com.abbcc.util.constant.layout.Piece@MAIN_CERT}"/>
	<div class="clr"></div>
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName" >
				<s:property value="layout.jsonContent['title'][@com.abbcc.util.constant.layout.Piece@MAIN_CERT.name()]||
					#request['moduleMap'][@com.abbcc.util.constant.layout.Piece@MAIN_CERT.name()]"/>
			</span>
	
	</div>
	<div class="bodyContContent rel rightConteWidth">
		<div class="tal mainTextColor break cert">
			<ul class="glitzColor certUl"><li class="pic">${lan['cert.pic']}</li><li class="name">${lan['cert.name']}</li><li class="organize">${lan['cert.organize']}</li></ul>
			<s:iterator value="#request['cert'].items">
				<ul class="glitzColor certUl">
					<li class="pic"><a href="<s:property value="picUrl()"/>" class="showCertPic"><img border="0" src="<s:property value="picUrl(5)"/>"/></a></li>
					<li class="name mid"><a href="<s:property value="picUrl()"/>" class="showCertPic topicLink">${name }</a></li>
					<li class="organize mid">${organize }</li>
				</ul>
			</s:iterator>
		</div>
		<div class="clr"></div>
	</div>
	<script type="text/javascript">
		$(".cert .mid").each(function(){
			var $this=$(this);
			$this.css("marginTop",($this.parent().height()-$this.height())/2);
		});
		if($(".showCertPic").length)$(".showCertPic").fancybox();
	</script>
</div>