<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link id="artDialogSkin" href="/js/artDialog3.0.5/skin/default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/artDialog3.0.5/artDialog.min.js"></script>
<title>查看消息</title>
<s:include value="../product/product_style.jsp" />
<script type="text/javascript" src="../platform/js/artCommon.js"></script>
<script type="text/javascript">
function showSend(){
	art.dialog.open('/user/message/messagesendPage',{
		id : 'messageIframe',
		skin: 'aero',
		title: '留言(按esc可关闭)',
		left:200,
		top:30,
		fixed:true,
		width:530,
		height:450
	});
}
$(function(){
	$("#checkbox").click(function(){
		if(this.checked){
			$(".listTable").find("#"+$(this).attr("name")).each(function(){
				$(this).attr("checked", true); 
			})
			}else{
				$(".listTable").find("#"+$(this).attr("name")).each(function(){
					$(this).attr("checked", false); 
				})
			} 
	})
})
function all_del(ch)
{
	var checkboxs="";
	var result=del();
	if(result==false)
		return false;
	$(".listTable").find("#"+ch).each(function(){
		if(this.checked){
			checkboxs=checkboxs+$(this).attr("fieldValue")+","
		}
	})
	if(checkboxs==""){
		alert('您还未选中你要操作的消息！');
		return;
	}
	window.location.href="${pageType=='send'?'messagesenddelAll':'messagerecvdelAll'}?allId="+checkboxs+"&&pageType=${requestScope.pageType}&&userType=${userType}";
	
    }
</script>
</head>
<body>
<s:if test="result!=''&&result!=null">
<input type="hidden" value="${result}" id="re"/>
		<script>
		if(jQuery("#re").val()!='')
			art.dialog.tips(jQuery("#re").val(), 1);
		</script>
</s:if>
<div class="listTab">
	<dl class=${pageType=='recv'?'focus':'' }>
		<a href="${pageType!='recv'?'messagesearchRecv':'#' }">收件箱<span class="errorSpan">(${recvCount})</span></a>
	</dl>
	<dl class="${pageType=='send'?'focus':'' }">
		<a href="${pageType!='send'?'messagesearchSend?send=01':'#' }">发件箱<span class="errorSpan">(${sendCount})</span></a>
	</dl>
	<dl class="${pageType=='notRead'?'focus':'' }">
		<a href="${pageType!='notRead'?'messagesearchNotRead':'#' }">未读信息<span class="errorSpan">(${notReadCount})</span></a>
	</dl>
<input type="button" onclick="showSend()" value="添加留言"/>&nbsp<input type="button" onclick="all_del('ch')" value="删除留言"/>&nbsp<a href="javascript:searchable()">筛选功能</a>
</div>
<div class="searchArea">
<s:form action="messageentry" namespace="/user/message"><s:textfield  name="title" label="主题" theme="xhtml"/> <s:submit value="查询"/></s:form>
</div>
<table  class="listTable">
<thead>
		<tr>
          <th width="5%" height="24"><div class="f01">
              <input type="checkbox" name="ch" id="checkbox"/>
          </div></th>
          <th width="4%" height="24" valign="middle"><img src="images/fs.png" width="18" height="13" /></th>
          <th width="15%" height="24"><div class="f02"><s:if test="%{#request.send==01}">接收人</s:if><s:else>发送人</s:else></div></th>
          <th width="37%" height="24"><div class="f02">主题</div></th>
          <th width="23%" height="24"><div class="f02">时间</div></th>
          <th width="16%"><div class="f02">操作</div></th>
        </tr>
</thead>
<tbody>
<s:if test="pageList.totalCount==0">
	<tr><td align="center" colspan="6">没有相关数据!</td></tr>
</s:if>
	<s:iterator var="user" value="pageList.items" status="st">

		<tr>
			<td  valign="middle" align="center"><input type="checkbox" fieldValue="${messageId}" name="ch" id="ch"/></td>
			<td valign="middle" align="center">${recvState=='01'?'<img src="../images/myali_icon02.gif"/>':'<img src="../images/icon-mail2.gif"/>'}</td>
			<td  valign="middle" align="center"><s:if
				test="%{#request.send==01}">
				<s:property value="recvName()" />
			</s:if><s:else>
				<s:property value="userName().name" />
			</s:else></td>
			<td valign="middle" align="center" ><s:if
				test="%{#request.send==01}">
				<a href="messageshowInfo?id=${messageId}&&pageType=${pageType}" id="<s:property value='#st.index+1'/>"
					onmouseover="show(this,'<s:property value='#st.index+100'/>')"
					onmouseout="hide('<s:property value='#st.index+100'/>')">${title}</a>
			</s:if><s:else><a href="messageshowInfo?id=${messageId}&&pageType=${pageType}">${title}</a></s:else>
			<div id="<s:property value='#st.index+100'/>"
				style="BORDER-RIGHT: black 1px solid; PADDING-RIGHT: 20px; BORDER-TOP: black 1px solid; PADDING-center: 20px; Z-INDEX: 100; VISIBILITY: hidden; PADDING-BOTTOM: 20px; BORDER-center: black 1px solid; WIDTH: 180px; PADDING-TOP: 20px; BORDER-BOTTOM: black 1px solid; POSITION: absolute; background-color: #F0FFFF"
				onmouseover="show(i,'<s:property value='#st.index+100'/>')"
				onmouseout="hide('<s:property value='#st.index+100'/>')"><nobr>详细信息</nobr><br>
			<table>
				<tr>
					<td>接收人:</td>
					<td><s:property value="recvName()" /></td>
				</tr>
				<tr>
					<td>标题:</td>
					<td><a href="#" id="<s:property value='#st.index+1'/>"
						onmouseover="show(this,'<s:property value='#st.index+100'/>')"
						onmouseout="hide('<s:property value='#st.index+100'/>')">${title}</a></td>
				</tr>
				<tr>
					<td>内容:</td>
					<td>${content}</td>
				</tr>

				<tr>
					<td>发送时间:</td>
					<td><s:date name="addTime" format="yyyy-MM-dd hh:mm:ss" /></td>
				</tr>
			</table>
			</div>
			</td>
			<td  valign="middle" align="center"><s:date name="addTime"
				format="yyyy-MM-dd hh:mm:ss" /></td>
			
			<td  valign="middle" align="center"><s:if
				test="%{#request.send!=01}">
				<a href="messageshowInfo?id=${messageId}&&pageType=${pageType}">查看</a>|</s:if><a
				href="${pageType=='send'?'messagesenddel':'messagerecvdel'}?id=${messageId}&&pageType=${requestScope.pageType}&&userType=${userType}"
				onclick="javascript:return del()">删除</a></td>


		</tr>

	</s:iterator>
	</tbody>
	<tfoot>
	<tr>


		<th colspan="8"><s:include
			value="../../common/page.jsp">
			<s:param name="pageList" value="pageList" />
		</s:include></th>

	</tr>
	</tfoot>
</table>
</body>
</html>