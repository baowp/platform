<%@ page language="java" import="java.util.*,com.abbcc.models.*"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>基本信息管理</title>
<link id="artDialogSkin" href="/js/artDialog3.0.5/skin/default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/artDialog3.0.5/artDialog.min.js"></script>	
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="/js/jquery/dialog/jquery.jmodal.js"></script>
<link rel="stylesheet" type="text/css" href="/css/jquery/dialog/jquery.jmodal.css">
<script type="text/javascript" src="/js/common.js"></script>
<script src="/js/jquery/tips/jquery.qtip-1.0.0-rc3.min.js" type="text/javascript"></script>
<script type="text/javascript" src="/user/enterprise/js/enterprise.js"></script>
<script type="text/javascript">
function openUpdatename(){

	art.dialog.prompt('请输入公司名字', function(data, topWin){
	    // data 代表输入数据;
	    
	    if(data.trim()==''){
	    	art.dialog.alert('公司名字不能为空！');
	    	return false;
	    }
	    $.ajax( {
			url : "/user/enterprise/company/updateName",
			dataType:"json",
			async:false,  	//不进行异步操作
			data : {
			id:'${enterpriseId}',
			name:data
			},
			success : function() {
				art.dialog.tips('修改成功！', 1);
				$("#enterpriseName").html(data);
				top.$("#headEntName").html(data);
			}
	})
	}, $("#enterpriseName").html());
}
$(function(){
	$(".massges_center01 ul li a[title]").qtip({
		position: {
	         corner: {
	            target: 'bottomMiddle',
	            tooltip: 'topMiddle'
	         }
	      }, style: {
	          name: 'cream',
	          padding: '7px 13px',
	          width: {
	             max: 310,
	             min: 0
	          },
	          tip: true
	       }
	   });
})


    </script>
<link href="/user/css/css.css" rel="stylesheet" type="text/css" />
<link href="/user/css/div.css" rel="stylesheet" type="text/css" />
<link href="/user/css/operation.css" rel="stylesheet" type="text/css" />
</head>

<body>
<s:include value="/common/resultMessage.jsp"></s:include>
<div id="body">
  <div id="mn">
    <div id="main_massges">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="8">&nbsp;</td>
          <td><div class="massges_left">
            <div class="massges_center01" style="display:block">
			<ul>
			<li class="${empty updateMessage?'massges_center_success':updateMessage.state=='00'?'massges_center':'massges_center'}" id="updateMessage">您的公司已经发布上网</li>
			<li class="font04">为了让客户对贵公司有一个全面了解，请补充完善您的公司内容！<a
								href="javascript:openCompletion()" id="completion" title="这里可以修改您的公司内容">补全公司内容</a></li>
			<li class="font04"><a
								href="javascript:openUpdateuser()" id="updateuser" title="这里可以修改您的基本资料及联系方式">修改基本资料和联系方式
							</a></li>
			<li class="font04"><a href="<s:url value='/user/contact/contactshow'/>" title="这里可以添加您企业的联系人">修改公司联系人
							</a></li>
			<li class="font04"><span class="font03">重要提醒：</span>贵司资料上网后会受到客户及工商质检部门等多方关注，请如实填写！<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			  若有虚假信息产生的相关责任，由贵司自行承担！</a></li>
			  </ul>
			</div>
          </div></td>
        </tr>
      </table>
    </div>
    <div class="main_bm">
      <div class="h">
        <table width="100%" height="27" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="99" align="left" background="/user/images/ji01.jpg">&nbsp;&nbsp;&nbsp;<span class="font_h">详细信息</span></td>
            <td align="left"><span class="font08">(<font id="enterpriseName">${name}</font>)</span><span class="font_h"><img id="editImage" alt="editImage" src="/images/icon_texteditor.jpg"></img><a href="javascript:openUpdatename()" class="appaly">修改公司名称</a></span></td>
          </tr> 
        </table>
      </div>
    </div>
    <div id="main_center02">
      <table width="95%" border="0" cellpadding="0" class="tb tb2 fixpadding">
        <tbody>

          <tr class="hover">
            <td width="20%" align="left" class="header">&nbsp;&nbsp;主营产品:</td>
            <td width="32%" align="left"><span class="vtop rowform">
             ${mainBusiness }
            </span></td>
            <td width="20%" align="left"><span class="header">主营行业:</span></td>
            <td width="32%" colspan="2" align="left"><span class="vtop rowform">
              <s:property value="industyString()"/>
            </span></td>
          </tr>
          <tr class="hover">
            <td align="left" class="header">&nbsp;&nbsp;企业类型:</td>
            <td align="left"><span class="vtop rowform">
            ${type=='00'?'个人用户':type=='01'?'有限责任公司':type=='02'?'个体经营':type=='03'?'事业单位或团体':'其它' }
            </span></td>
            <td align="left" class="header">公司注册地:</td>
            <td colspan="2" align="left"><span class="vtop rowform">
            ${dist }
            </span></td>
          </tr>
          <tr class="hover">
            <td align="left" class="header">&nbsp;&nbsp;主要经营地点:</td>
            <td align="left"><span class="vtop rowform">
              ${entAddress}
            </span></td>
            <td align="left" class="header">经营模式:</td>
            <td colspan="2" align="left"><span class="vtop rowform">
              
            <s:property value="businessString()"/>
            </span></td>
          </tr>
          <tr class="hover">
            <td align="left" class="header">&nbsp;&nbsp;公司成立时间:</td>
            <td align="left"><span class="vtop rowform">
              ${regTime}
            </span></td>
            <td align="left" class="header">法定代表人/负责人:</td>
            <td colspan="2" align="left"><span class="vtop rowform">
              ${legalPre }
            </span></td>
          </tr>
          <tr class="hover">
            <td align="left" class="header">&nbsp;&nbsp;年营业额:</td>
            <td align="left"><span class="vtop rowform">
            ${annualTurnover}
            </span></td>
            <td align="left" class="header">员工人数:</td>
            <td colspan="2" align="left"><span class="vtop rowform">
              ${staffSum }
            </span></td>
          </tr>
          <tr class="hover">
            <td align="left" class="header">&nbsp;&nbsp;经营品牌:</td>
            <td align="left"><span class="vtop rowform">
              ${brand }
            </span></td>
            <td align="left" class="header">注册资本:</td>
            <td colspan="2" align="left"><span class="vtop rowform">
              ${registeredCapital}
            </span></td>
          </tr>
          <tr class="hover">
            <td align="left" class="header">&nbsp;&nbsp;主要客户群:</td>
            <td align="left"><span class="vtop rowform">
              ${customer }
            </span></td>
            <td align="left" class="header">主要市场:</td>
            <td colspan="2" align="left"><span class="vtop rowform">
             ${market }
            </span></td>
          </tr>
          <tr class="hover">
            <td align="left">&nbsp;&nbsp;<span class="header">年出口额:</span></td>
            <td align="left"><span class="vtop rowform">
              ${annualExport }
            </span></td>
            <td align="left" class="header">年进口额:</td>
            <td colspan="2" align="left"><span class="vtop rowform">
              ${annualImport }
            </span></td>
          </tr>
          <tr>
            <td align="left" class="hover">&nbsp;<span class="header">&nbsp;开户银行:</span></td>
            <td align="left" class="hover"><span class="vtop rowform">
              ${bank }
            </span></td>
            <td align="left" class="header">帐&nbsp;号:</td>
            <td colspan="2" align="left" class="hover"><span class="vtop rowform">
              ${bankAccount }
            </span></td>
          </tr>
          <tr>
            <td align="left" class="hover">&nbsp;<span class="header">&nbsp;是否提供OEM服务:</span></td>
            <td align="left" class="hover"><span class="vtop rowform">
            ${oem=='01'?'是':'否' }
            </span></td>
            <td align="left" class="header">研发部门人数:</td>
            <td colspan="2" align="left" class="hover"><span class="vtop rowform">
              ${rdSum }
            </span></td>
          </tr>
          <tr>
            <td align="left" class="hover">&nbsp;<span class="header">&nbsp;月产量:</span></td>
            <td align="left" class="hover"><span class="vtop rowform">
              ${monthProd }
            </span></td>
            <td align="left" class="header">厂房面积:</td>
            <td colspan="2" align="left" class="hover"><span class="vtop rowform">
              ${factoryArea }平方米
            </span></td>
          </tr>
          <tr>
            <td align="left" class="hover">&nbsp;&nbsp;<span class="header">质量控制:</span></td>
            <td align="left" class="hover"><span class="vtop rowform">
              ${qualityControl }
            </span></td>
            <td align="left" class="header">管理体系认证:</td>
            <td colspan="2" align="left" class="hover"><span class="vtop rowform">
              ${qasyscert }
            </span></td>
          </tr>
          <tr>
            <td align="left" class="header">&nbsp;&nbsp;icp备案号:</td>
            <td align="left" class="hover"><span class="vtop rowform">
              ${icp }
            </span></td>
            <td class="header">&nbsp;</td>
            <td width="26%" class="hover"><a href="admin.php?action=usergroups&amp;operation=edit&amp;id=19&amp;return=admin"></a></td>
            <td width="16%" class="hover">&nbsp;</td>
          </tr>
          
        </tbody>
      </table>
    </div>
    <div class="main_bm">
      <div class="h">
        <table width="100%" height="27" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="99" align="left" background="/user/images/ji01.jpg">&nbsp;&nbsp;<span class="font_h">&nbsp;联系方式</span></td>
            <td>&nbsp;</td>
          </tr>
        </table>
      </div>
    </div>
    <div class="main_center03">
      <div class="center04_z" style="display:block">
	  <ul>
	  <li class="font04">
	    <table width="95%" border="0" cellpadding="0" class="tb tb2 fixpadding">
          <tbody>
            <tr class="hover">
              <td width="15%" align="left" class="header">&nbsp;<s:property value='#request.AbcUser.name' />(<s:property
												value='#request.AbcUser.position' />)</td>
              <td width="85%" align="left">&nbsp;</td>
              </tr>
            <tr class="hover">
              <td align="left" class="header">&nbsp;电 话</td>
              <td align="left"><span class="vtop rowform">
                <s:property value='#request.AbcUser.phone' />
              </span></td>
              </tr>
            <tr class="hover">
              <td align="left" class="header">&nbsp;手机</td>
              <td align="left"><span class="vtop rowform">
                <s:property value='#request.AbcUser.cellphone' />
              </span></td>
              </tr>
            <tr class="hover">
              <td align="left" class="header">&nbsp;传 真</td>
              <td align="left"><span class="vtop rowform">
                <s:property value='#request.AbcUser.fax' />
              </span></td>
              </tr>
            <tr class="hover">
              <td align="left" class="header">&nbsp;地 址</td>
              <td align="left"><span class="vtop rowform">
                <s:property value='#request.AbcUser.address' />
              </span></td>
              </tr>
            <tr class="hover">
              <td align="left">&nbsp;<span class="header">Email</span></td>
              <td align="left"><span class="vtop rowform">
                <s:property value='#request.AbcUser.email' />
              </span></td>
              </tr>
            <tr>
              <td align="left" class="hover">&nbsp;<span class="header">Q Q</span></td>
              <td align="left" class="hover"><span class="vtop rowform">
                <s:property value='#request.AbcUser.qq' />
              </span></td>
              </tr>
            <tr>
              <td align="left" class="hover">&nbsp;<span class="header">MSN</span></td>
              <td align="left" class="hover"><span class="vtop rowform">
                <s:property value='#request.AbcUser.msn' />
              </span></td>
              </tr>
            <tr>
              <td align="left" class="hover">&nbsp;<span class="header">邮 编</span></td>
              <td align="left" class="hover"><span class="vtop rowform">
                <s:property value='#request.AbcUser.zipcode' />
              </span></td>
              </tr>
            <tr>
              <td align="left" class="header">公司主页</td>
              <td align="left" class="hover"><span class="vtop rowform">
                <a href="<s:property value='#request.AbcUser.url' />"><s:property
												value='#request.AbcUser.url' /></a>
              </span></td>
            </tr>
          </tbody>
        </table>
	  </li>
	  </ul>
      </div>
    </div>
  </div>
</div>
</body>
</html>