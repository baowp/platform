<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table width="95%" border="0" cellpadding="0" class="tb tb2 fixpadding">
        <tbody>

          <tr class="hover">
            <td width="15%" align="left" class="header">&nbsp;&nbsp;主营产品:</td>
            <td width="32%" align="left"><span class="vtop rowform">
             ${ent.mainBusiness }
            </span></td>
            <td width="15%" align="left"><span class="header">主营行业:</span></td>
            <td width="32%" colspan="2" align="left"><span class="vtop rowform">
             ${ent.industry }
            </span></td>
          </tr>
          <tr class="hover">
            <td align="left" class="header">&nbsp;&nbsp;企业类型</td>
            <td align="left"><span class="vtop rowform">
            ${ent.type=='00'?'个人用户':ent.type=='01'?'有限责任公司':ent.type=='02'?'个体经营':ent.type=='03'?'事业单位或团体':'其它' }
            </span></td>
            <td align="left" class="header">公司注册地</td>
            <td colspan="2" align="left"><span class="vtop rowform">
            ${ent.dist }
            </span></td>
          </tr>
          <tr class="hover">
            <td align="left" class="header">&nbsp;&nbsp;主要经营地点</td>
            <td align="left"><span class="vtop rowform">
              ${ent.entAddress}
            </span></td>
            <td align="left" class="header">经营模式</td>
            <td colspan="2" align="left"><span class="vtop rowform">
            	${ent.businessType }
            </span></td>
          </tr>
          <tr class="hover">
            <td align="left" class="header">&nbsp;&nbsp;公司成立时间</td>
            <td align="left"><span class="vtop rowform">
              ${ent.regTime}
            </span></td>
            <td align="left" class="header">法定代表人/负责人</td>
            <td colspan="2" align="left"><span class="vtop rowform">
              ${ent.legalPre }
            </span></td>
          </tr>
          <tr class="hover">
            <td align="left" class="header">&nbsp;&nbsp;年营业额</td>
            <td align="left"><span class="vtop rowform">
            ${ent.annualTurnover}
            </span></td>
            <td align="left" class="header">员工人数</td>
            <td colspan="2" align="left"><span class="vtop rowform">
              ${ent.staffSum }
            </span></td>
          </tr>
          <tr class="hover">
            <td align="left" class="header">&nbsp;&nbsp;经营品牌</td>
            <td align="left"><span class="vtop rowform">
              ${ent.brand }
            </span></td>
            <td align="left" class="header">注册资本</td>
            <td colspan="2" align="left"><span class="vtop rowform">
              ${ent.registeredCapital}
            </span></td>
          </tr>
          <tr class="hover">
            <td align="left" class="header">&nbsp;&nbsp;主要客户群</td>
            <td align="left"><span class="vtop rowform">
              ${ent.customer }
            </span></td>
            <td align="left" class="header">主要市场</td>
            <td colspan="2" align="left"><span class="vtop rowform">
             ${ent.market }
            </span></td>
          </tr>
          <tr class="hover">
            <td align="left">&nbsp;&nbsp;<span class="header">年出口额</span></td>
            <td align="left"><span class="vtop rowform">
              ${ent.annualExport }
            </span></td>
            <td align="left" class="header">年进口额</td>
            <td colspan="2" align="left"><span class="vtop rowform">
              ${ent.annualImport }
            </span></td>
          </tr>
          <tr>
            <td align="left" class="hover">&nbsp;<span class="header">&nbsp;开户银行</span></td>
            <td align="left" class="hover"><span class="vtop rowform">
              ${ent.bank }
            </span></td>
            <td align="left" class="header">帐&nbsp;号</td>
            <td colspan="2" align="left" class="hover"><span class="vtop rowform">
              ${ent.bankAccount }
            </span></td>
          </tr>
          <tr>
            <td align="left" class="hover">&nbsp;<span class="header">&nbsp;是否提供OEM服务</span></td>
            <td align="left" class="hover"><span class="vtop rowform">
            ${ent.oem=='01'?'是':'否' }
            </span></td>
            <td align="left" class="header">研发部门人数</td>
            <td colspan="2" align="left" class="hover"><span class="vtop rowform">
              ${ent.rdSum }
            </span></td>
          </tr>
          <tr>
            <td align="left" class="hover">&nbsp;<span class="header">&nbsp;月产量</span></td>
            <td align="left" class="hover"><span class="vtop rowform">
              ${ent.monthProd }
            </span></td>
            <td align="left" class="header">厂房面积</td>
            <td colspan="2" align="left" class="hover"><span class="vtop rowform">
              ${ent.factoryArea }平方米
            </span></td>
          </tr>
          <tr>
            <td align="left" class="hover">&nbsp;&nbsp;<span class="header">质量控制</span></td>
            <td align="left" class="hover"><span class="vtop rowform">
              ${ent.qualityControl }
            </span></td>
            <td align="left" class="header">管理体系认证</td>
            <td colspan="2" align="left" class="hover"><span class="vtop rowform">
              ${ent.qasyscert }
            </span></td>
          </tr>
          <tr>
            <td align="left" class="header">&nbsp;&nbsp;icp备案号</td>
            <td align="left" class="hover"><span class="vtop rowform">
              ${ent.icp }
            </span></td>
            <td class="header">&nbsp;</td>
            <td width="26%" class="hover"><a href="admin.php?action=usergroups&amp;operation=edit&amp;id=19&amp;return=admin"></a></td>
            <td width="16%" class="hover">&nbsp;</td>
          </tr>
          <tr><td><input type="button" onclick="javascript:history.go(-1)" value="返回"/></td></tr>
        </tbody>
      </table>
</body>
</html>