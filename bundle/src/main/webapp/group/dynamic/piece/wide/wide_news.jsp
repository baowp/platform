<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="sign"
	value="${spel['T(com.abbcc.util.constant.group.GroupPiece).wide_news'].name()}" />
${command.pieceNews() }
<div id="news" class="bodyCont moveChild" data-piece="${sign }">
	<div class="clr"></div>
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName"> ${moduleMap[sign]
			} </span>
		<c:if test="${belongPage ne 'news' }">
			<a class="fr fs12px nb titleLinkColor draft_no_link" href="news">${lan['more']}
				&gt;&gt;</a>
		</c:if>
	</div>
	<div class="bodyContContent rel rightConteWidth"
		data-meta='{"currentPage":${commonNews.currentPage },"pageCount":${commonNews.pageCount },"parentKey":"newsCategory","parentValue":"${param.newsCategory }","url":"${sign }"}'>
		<div class="tal mainTextColor break mainNews">
			<div class="topNews listNews">
				<c:forEach items="${topNews }" var="n">
					<dl class="glitzColor listNewsDl">
						<dt title="${n.title}">
							[${lan['news.top']}]<a target="_blank"
								href="news_detail?itemId=${n.newsId}" class="topicLink">${n.title}&nbsp;</a>
						</dt>
						<dd>${n.origin}&nbsp;</dd>
						<dd><fmt:formatDate value="${n.addTime }" pattern="yyyy-MM-dd hh:mm:ss"/></dd>
					</dl>
				</c:forEach>
			</div>
			<div class="commonNews listNews">
				<c:forEach items="${commonNews.items }" var="c">
					<dl class="glitzColor listNewsDl">
						<dt title="${c.title}"><a target="_blank" href="news_detail?itemId=${c.newsId}"
								class="topicLink">${c.title}&nbsp;</a>
						</dt>
						<dd>${c.origin}&nbsp;</dd>
						<dd>
							<fmt:formatDate value="${c.addTime }" pattern="yyyy-MM-dd hh:mm:ss"/>
						</dd>
					</dl>
				</c:forEach>
			</div>
		</div>
		<c:set var="pageList" value="${commonNews }" scope="request"/>
		<jsp:include page="../../pagination.jsp"></jsp:include>
		<div class="clr"></div>
	</div>
</div>