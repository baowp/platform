<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="sign" value="${spel['T(com.abbcc.util.constant.group.GroupPiece).wide_cert'].name()}"/>
<c:if test="${empty cert}">
	${command.pieceCert() }
</c:if>

<div id="cert" class="bodyCont moveChild" data-piece="${sign }">
	<div class="clr"></div>
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName" >
			${moduleMap[sign] }
		</span>
		<c:if test="${belongPage ne 'cert' }">
			<a class="fr fs12px nb titleLinkColor draft_no_link" href="cert">${lan['more']}&gt;&gt;</a>
		</c:if>
	</div>
	<div class="bodyContContent rel rightConteWidth" data-meta='{"currentPage":${cert.currentPage},"pageCount":${cert.pageCount},"url":"${sign }"}'>
		<div class="tal mainTextColor break cert">
			<ul class="glitzColor certUl"><li class="pic">${lan['cert.pic']}</li><li class="name">${lan['cert.name']}</li><li class="organize">${lan['cert.organize']}</li></ul>
			<c:forEach items="${cert.items }" var="c">
				<c:set var="pic" value="${c.picUrl() }"></c:set>
				<ul class="glitzColor certUl">
					<li class="pic"><a href="${pic }" class="showCertPic"><img border="0" src="${c.picUrl(5)}"/></a></li>
					<li class="name mid"><a href="${pic }" class="showCertPic topicLink">${c.name }</a></li>
					<li class="organize mid">${c.organize }</li>
				</ul>
			</c:forEach>
		</div>
		<div class="clr"></div>
		<div class="wangpuBorderBottom3 mt10px mb5px mainTextColor">
				<c:set var="pageList" value="${cert }" scope="request"/>
				<jsp:include page="../../pagination.jsp"></jsp:include>
		</div>
	</div>
	<script type="text/javascript">
		$(".cert .mid").each(function(){
			var $this=$(this);
			$this.css("marginTop",($this.parent().height()-$this.height())/2);
		});
		if($(".showCertPic").length)$(".showCertPic").fancybox();
	</script>
</div>