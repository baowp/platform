// 导航栏配置文件
var outlookbar=new outlook();
var t;

t=outlookbar.addtitle('管理首页','管理首页',1)
outlookbar.additem('会员管理',t,'viewMember.action')
outlookbar.additem('企业招聘管理',t,'viewRecuitManage.action')
outlookbar.additem('证书管理',t,'searchCert.action')
outlookbar.additem('推广管理',t,'viewPopularize.action')
outlookbar.additem('产品管理',t,'searchProd.action')
outlookbar.additem('供求管理',t,'searchSupply.action')
outlookbar.additem('日志查询',t,'adminShowLog.action')

t=outlookbar.addtitle('个人设置','个人设置',1)
outlookbar.additem('账号信息',t,'mySettings/edit_myInfo.jsp')
outlookbar.additem('修改密码',t,'mySettings/edit_myPassword.jsp')

t=outlookbar.addtitle('网站设置','系统设置',1)
outlookbar.additem('网站基本信息',t,'viewSiteInfo.action')
outlookbar.additem('审核设置',t,'viewAuditInfo.action')

t=outlookbar.addtitle('超级管理','系统设置',1)
outlookbar.additem('管理员管理',t,'listAdmin.action')
//outlookbar.additem('权限分配',t,'adminPriv.action')

t=outlookbar.addtitle('数据字典','系统设置',1)
outlookbar.additem('地区管理',t,'viewDistrict.action')
outlookbar.additem('行业管理',t,'viewIndustry.action')
outlookbar.additem('产品分类管理',t,'viewProdcata.action')
outlookbar.additem('关键字管理',t,'viewBannedkey.action')

t=outlookbar.addtitle('会员管理','会员管理',1)
outlookbar.additem('未验证用户',t,'viewMember.action?state=00')
outlookbar.additem('已验证用户',t,'viewMember.action?state=01')
outlookbar.additem('申请付费用户',t,'showMemberAudit.action')
outlookbar.additem('已付费用户',t,'payMember.action')
outlookbar.additem('付费过期用户',t,'payEnd.action?payendType=-1')
outlookbar.additem('付费提醒',t,'payEnd.action')
outlookbar.additem('二级会员',t,'viewSubmembers.action')
//outlookbar.additem('会员分组',t,'viewMembergroup.action')
//outlookbar.additem('邮件群发',t,'addGroupMail.action')
outlookbar.additem('会员信息导入',t,'memeber/relevance/edit')
outlookbar.additem('补全用户相册',t,'/rest/admin/album/fillPage')
//outlookbar.additem('中英文信息互导',t,'engSiteManage.action')

t=outlookbar.addtitle('会员统计','会员管理',1)
//outlookbar.additem('二级会员统计',t,'showStat.action?pageType=admin')
outlookbar.additem('会员注册统计',t,'/admin/memberManage/register_stat.jsp')
outlookbar.additem('会员升级统计',t,'/admin/memberManage/upgrade_stat.jsp')
outlookbar.additem('使用率统计',t,'infoPublishStat.action')

/*t=outlookbar.addtitle('个性网站','会员管理',1)
outlookbar.additem('网站服务器管理',t,'listWebServer.action')
outlookbar.additem('会员网站管理',t,'listUserSite.action')
outlookbar.additem('会员模板管理',t,'listUserTemplate.action')
outlookbar.additem('会员信息同步',t,'listSiteSync.action')*/

/*t=outlookbar.addtitle('新闻公告','新闻留言',1)
outlookbar.additem('新闻审核',t,'viewAuditNews.action')
outlookbar.additem('新闻管理',t,'listNews.action')
outlookbar.additem('系统新闻',t,'listSysNews.action')
outlookbar.additem('新闻布局',t,'layOutNews.action')
outlookbar.additem('系统分类',t,'showcategorys.action')
outlookbar.additem('公告管理',t,'listAnnounce.action')
outlookbar.additem('新闻栏目管理',t,'listNewsCategory.action')*/

t=outlookbar.addtitle('留言管理','新闻留言',1)
outlookbar.additem('用户留言',t,'listMessage.action')
outlookbar.additem('留言群发',t,'viewAddGroupmessage.action')

t=outlookbar.addtitle('招聘管理','企业管理',1)
outlookbar.additem('企业招聘审核',t,'viewRecuitAudit.action')
outlookbar.additem('企业招聘管理',t,'viewRecuitManage.action')

t=outlookbar.addtitle('证书管理','企业管理',1)
outlookbar.additem('证书审核',t,'viewAuditCert.action')
outlookbar.additem('证书查询',t,'searchCert.action')

t=outlookbar.addtitle('产品管理','企业管理',1)
outlookbar.additem('推广管理',t,'viewPopularize.action')
outlookbar.additem('产品审核',t,'viewAuditProd.action')
//outlookbar.additem('产品管理',t,'viewProd.action')
outlookbar.additem('产品查询',t,'searchProd.action')
outlookbar.additem('供求审核',t,'viewAuditSupply.action')
outlookbar.additem('供求查询',t,'searchSupply.action')

t=outlookbar.addtitle('域名管理','企业管理',1)
outlookbar.additem('域名待审核',t,'/admin/bind/audit')
outlookbar.additem('审核已通过',t,'/admin/bind/approved')
outlookbar.additem('审核不通过',t,'/admin/bind/unapproved')
outlookbar.additem('数据备份',t,'showbackup.action')
//t=outlookbar.addtitle('网站统计','统计分析',1)
//outlookbar.additem('访问统计',t,'visitStat.action')
//outlookbar.additem('收入统计',t,'earnStat.action')

//t=outlookbar.addtitle('日志管理','统计分析',1)
//outlookbar.additem('日志查询',t,'adminShowLog.action')
//outlookbar.additem('日志分析',t,'logAnalysis.action')
t=outlookbar.addtitle('帮助中心','帮助中心',1)
outlookbar.additem('帮助中心管理',t,'adminViewHelpCenter.action')

