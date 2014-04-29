/**
 * soaObj 
 */
var SoaObj = function () {
    var self = this;
    //服务器action目录地址
    this.ActionPath = "http://51archetype.com/soa/";
    //服务器状态值
    this.NetState = false;
    //cookie 名
    this.CookieName = "abbcc_name";
    this.ProductCookieName = "abbcc_product";
    //登录状态显示div
    this.LoginStateDiv = "";
    //验证是否已登录
    this.IsLogin = false;
    //数据json格式
    this.LoginData = { "loginName": "", "loginPwd": "" };
    this.RegisterData = { "regName": "", "regPwd": "", "regEmail": "", "regPwdQue": "", "regPwdAns": "",
        "nameInfoDiv": "", "emailInfoDiv": "", "nameInfo": "用户名不能为空！", "emailInfo": "Email不能为空"
    };
    this.MsgData = { "msgTitle": "", "msgName": "", "msgPhone": "", "msgEnt": "", "msgCon": "" };
    this.OrderData = { "content": "", "proPrice": "", "orderDesc": "" };
    this.SearchData = { "searchIptDiv": "", "newsDiv": "", "proDiv": "" };

    //更改服务器状态值
    this.changeNetState = function () {
        self.NetState = true;
        soaObj.isLogin();
    }

    //判断服务器是否正常
    this.testNetState = function () {
        if (!self.NetState) {
            alert("服务器暂时无响应，请稍后再试！");
            return false;
        }
        else
            return true;
    }

    //设置服务器action目录地址
    this.setMainPath = function (mainPath) {
        self.ActionPath = mainPath + "/soa/";
    }

    //设置cookie
    this.setCookies = function (cookieName, cookieValue) {
        var Days = 30; //此 cookie 将被保存 30 天
        var exp = new Date();    //new Date("December 31, 9998");
        exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
        document.cookie = cookieName + "=" + escape(cookieValue) + ";expires=" + exp.toGMTString();
    }

    //获取cookie
    this.getCookies = function (cookieName) {
        var arr = document.cookie.match(new RegExp("(^| )" + cookieName + "=([^;]*)(;|$)"));
        if (arr != null) {
            return unescape(arr[2]);
        }
        return "";
    }

    //删除cookie
    this.delCookies = function (cookieName) {
        var exp = new Date();
        exp.setTime(exp.getTime() - 1000);
        document.cookie = cookieName + "=0; expires=" + exp.toGMTString();
    }

    //获取主域名domain
    this.getDomain = function () {
        var domain = window.location.href;
        var domainItems = domain.split("//");
        domain = domainItems[1];
        domainItems = domain.split("/");
        domain = domainItems[0];
        domainItems = domain.split(".");
        if (domainItems[0] == "www") {
            var domainResult = "";
            for (var i = 1; i < domainItems.length; i++) {
                domainResult += domainItems[i] + ".";
            }
            return domainResult;
        }
        else {
            return domain;
        }
    }

    //加载url
    this.loadData = function (url) {
        if (!self.testNetState()) return;
        url += "&domain=" + self.getDomain();
        url = encodeURI(encodeURI(url));
        var s = document.createElement("script");
        s.type = "text/javascript";
        s.src = url;
        document.body.appendChild(s);
    }

    //输入框focus清空初始内容功能
    this.enterInfo = function (loginDiv, info) {
        var loginName = $("#" + loginDiv).val();
        if (loginName == info) {
            $("#" + loginDiv).val("");
        }
    }



    //验证用户名唯一性
    this.checkRegName = function () {
        if ($.checkUserName(self.RegisterData.regName) != 0) {
            self._checkRegName("error");
            return;
        }
        else
            self.RegisterData.nameInfo = "";
        var url = self.ActionPath + "checkRegName.action?username=" + self.RegisterData.regName + "&f=soaObj._checkRegName";
        self.loadData(url);
    }
    this._checkRegName = function (result) {
        if (result == "success") {
            if (self.RegisterData.nameInfoDiv != "")
                $("#" + self.RegisterData.nameInfoDiv).css("color", "#444").html("可用");
            self.RegisterData.nameInfo = "";
        }
        else if (result == "failure") {
            if (self.RegisterData.nameInfoDiv != "")
                $("#" + self.RegisterData.nameInfoDiv).css("color", "red").html("对不起，您输入的账号已经被使用！");
            self.RegisterData.nameInfo = "对不起，您输入的账号已经被使用！";
        }
        else if (result == "error") {
            if (self.RegisterData.nameInfoDiv != "")
                $("#" + self.RegisterData.nameInfoDiv).css("color", "red").html("用户名输入不规范！");
            self.RegisterData.nameInfo = "用户名输入不规范！";
        }
        else {
            alert(result);
        }
    }

    //验证邮箱唯一性
    this.checkRegEmail = function () {
        if ($.checkUserMail(self.RegisterData.regEmail) != 0) {
            self._checkRegEmail("none");
            return;
        }
        else
            self.RegisterData.nameInfo = "";
        var url = self.ActionPath + "checkRegEmail.action?email=" + self.RegisterData.regEmail + "&f=soaObj._checkRegEmail";
        self.loadData(url);
    }
    this._checkRegEmail = function (result) {
        if (result == "success") {
            if (self.RegisterData.emailInfoDiv != "")
                $("#" + self.RegisterData.emailInfoDiv).css("color", "#444").html("可用");
            self.RegisterData.emailInfo = "";
        }
        else if (result == "failure") {
            if (self.RegisterData.emailInfoDiv != "")
                $("#" + self.RegisterData.emailInfoDiv).css("color", "red").html("对不起，您输入的Email已经被使用！");
            self.RegisterData.emailInfo = "对不起，您输入的Email已经被使用！";
        }
        else if (result == "error") {
            if (self.RegisterData.emailInfoDiv != "")
                $("#" + self.RegisterData.emailInfoDiv).css("color", "red").html("Email输入不规范！");
            self.RegisterData.emailInfo = "Email输入不规范！";
        }
        else {
            alert(result);
        }
    }

    //注册
    this.register = function () {
        if (!self.testNetState()) return;
        var url = self.ActionPath + "register.action?username=" + self.RegisterData.regName + "&password="
			+ hex_md5(self.RegisterData.regPwd) + "&email=" + self.RegisterData.regEmail + "&f=soaObj._register";
        self.loadData(url);
    }
    this._register = function (result, msg) {
        if (result == "success") {
            self.setCookies(self.CookieName, msg);
            window.location.href = "login.html";
        }
        else
            alert(msg);
    }

    //登录
    this.login = function () {
        if (!self.testNetState()) return;
        if (self.LoginData.loginName == "") {
            alert("用户名不能为空！");
            return;
        }
        if (self.LoginData.loginPwd == "") {
            alert("密码不能为空！");
            return;
        }
        var url = self.ActionPath + "loginUser.action?username=" + self.LoginData.loginName + "&password="
			+ self.LoginData.loginPwd + "&f=soaObj._login";
        self.loadData(url);
    }
    this._login = function (result, msg) {
        if (result != "success") {
            alert(msg);
        }
        else {
        	alert(msg)
        	window.location.href="h51archetype.comcc.net/user/login?username="+msg+"&&password="+$.trim($("#loginPwd").val())+"&&page51archetype.com://51archetype.com/user/platform/index.jsp";
        	//$("#loginForm").submit();
            //self.setCookies(self.CookieName, msg);
           // window.location.href = window.location.href;
        }
    }

    //判断是否已登录
    this.isLogin = function () {
        var userName = self.getCookies(self.CookieName);
        if (userName == "") {
            self.IsLogin = false;
            self._isLogin("failure");
            return;
        }
        var url = self.ActionPath + "isLogin.action?username=" + userName + "&f=soaObj._isLogin";
        self.loadData(url);
    }
    this._isLogin = function (result) {
        if (result == "success") {
            self.IsLogin = true;
            if (self.LoginStateDiv != "") {
                var htmlStr = "<td>"+self.getCookies(self.CookieName) + "您好， 您已登录！<a href=\"#\" onclick=\"soaObj.loginOut()\" style=\"margin-left:30px\" title=\"注销\">注销</a></td> />";
                $("#" + self.LoginStateDiv).html(htmlStr);
            }
        }
    }
	    //进入用户中心
    this.intoUserCenter = function () {
        if (!self.testNetState()) return;
        var userName = self.getCookies(self.CookieName);
        var url = self.ActionPath +"intoUserCenter.action?username=" + self.LoginData.loginName+ "&password="
			+ self.LoginData.loginPwd;
        window.open(url);
    }


    //注销
    this.loginOut = function () {
        if (!self.testNetState()) return;
        var userName = self.getCookies(self.CookieName);
        var url = self.ActionPath + "loginOut.action?username=" + userName + "&f=soaObj._loginOut";
        self.loadData(url);
    }
    this._loginOut = function (result) {
        if (result != "success") {
            alert("注销不成功，请稍后再试！");
        }
        else {
            self.IsLogin = false;
            self.delCookies(self.CookieName);
            window.location.reload();;
        }
    }

    //留言
    this.takeMsg = function () {
        if (!self.testNetState()) return;
        if (self.MsgData.msgCon == "" || self.MsgData.msgEmail == "" || self.MsgData.msgName == "" || self.MsgData.msgPhone == "" || self.MsgData.msgTitle == "") {
            alert("每一项都是必填项，请完成表单！");
            return;
        }
        self.MsgData.msgCon += "|" + self.MsgData.msgPhone;
        var url = self.ActionPath + "doMsg.action?title=" + self.MsgData.msgTitle + "&content=" + self.MsgData.msgCon
            + "&addUser=" + self.MsgData.msgName + "&addEnt=" + self.MsgData.msgEnt + "&f=soaObj._takeMsg";
        self.loadData(url);
    }
    this._takeMsg = function (result, msg) {
        if (result != "success") {
            alert(msg);
        }
        else {
            alert("留言成功！");
            window.location.href = window.location.href;
        }
    }

    //订单
    this.takeProduct = function (PId, PName, PPrice, PNum, PAmount) {
        var PContent = self.getCookies(self.ProductCookieName);
        var PDetail = new Array();
        PDetail.push(PId);
        PDetail.push(PName);
        PDetail.push(PPrice);
        PDetail.push(PNum);
        PDetail.push(PAmount);
        PContent += "|" + PDetail.join(",");
        self.setCookies(self.ProductCookieName, PContent);
    }
    this.delProduct = function (_PId) {
        var PContent = self.getCookies(self.ProductCookieName);
        var _PList = PContent.split("|");
        var PList = new Array();
        for (var i = 0; i < _PList.length; i++) {
            var PId = _PList[i].split(",")[0];
            if (PId != _PId) {
                PList.push(_PList[i]);
            }
        }
        PContent = PList.join("|");
        self.setCookies(self.ProductCookieName, PContent);
    }
    this.getProduct = function () {
        var PContent = self.getCookies(self.ProductCookieName);
        var result = new Array();
        if (PContent == "") {
            return result;
        }
        var PList = PContent.split("|");
        for (var i = 1; i < PList.length; i++) {
            var PDetail = PList[i].split(",");
            result.push(PDetail);
        }
        return result;
    }
    this.takeOrder = function () {
        if (!self.testNetState()) return;
        if (soaObj.OrderData.proPrice == "") {
            alert("订单为空！");
            return;
        }
        self.OrderData.content = self.getCookies(self.ProductCookieName);
        var url = self.ActionPath + "doOrder.action?amount=" + self.OrderData.proPrice + "&content=" + self.OrderData.content
            + "&odesc=" + self.OrderData.orderDesc + "&userName=" + self.getCookies(self.CookieName) + "&f=soaObj._takeOrder";
        self.loadData(url);
    }
    this._takeOrder = function (result, msg) {
        if (result != "success") {
            alert(msg);
        }
        else {
            alert("留言成功！");
            window.location.href = window.location.href;
        }
    }

    //搜索
    this.isSearchPage = function () {
        var url = window.location.href;
        var urlParts = url.split("/");
        var pageStr = urlParts[(urlParts.length - 1)].substring(0, 6);
        if (pageStr == "search")
            return true;
        else
            return false;
    }
    this.search = function (searchURL) {
        if (searchURL != "local") {
            if (!self.testNetState()) return;
            var searchKey = $.trim($("#" + self.SearchData.searchIptDiv).val());
            if (searchKey == "") {
                alert("请输入搜索关键字！");
                return;
            }
            if (!self.isSearchPage()) {
                window.location.href = "http://" + searchURL + "?searchKey=" + encodeURI(searchKey);
                return;
            }
        }
        var url = self.ActionPath + "search?searchKey=" + $.trim($("#" + self.SearchData.searchIptDiv).val()) + "&f=soaObj._search";
        self.loadData(url);
    }
    this._search = function (result, news_data, pro_data) {
        if (result != "success")
            alert(news_data);
        else {
            var domain = self.getDomain();
            $("#" + self.SearchData.newsDiv).empty();
            $("#" + self.SearchData.proDiv).empty();
            if (news_data.length == 0) {
                $("#" + self.SearchData.newsDiv).append("<li>暂无匹配新闻！</li>");
            }
            else {
                for (var i = 0; i < news_data.length; i++) {
                    var newsObj = news_data[i];
                    var url = "http://" + domain + "/" + newsObj[1];
                    $("#" + self.SearchData.newsDiv).append("<li><a href=\"" + url + "\" title=\"" + newsObj[0] + "\" target=\"_blank\">" + newsObj[0] + "</a>"
                        + "<div>" + newsObj[2].substring(0, 20) + "...</div></li>");
                }
            }
            if (pro_data.length == 0) {
                $("#" + self.SearchData.proDiv).append("<li>暂无匹配产品！</li>");
            }
            else {
                for (var i = 0; i < pro_data.length; i++) {
                    var proObj = pro_data[i];
                    var url = "http://" + domain + "/" + proObj[1];
                    var src = "http://" + domain + "/" + proObj[2];
                    $("#" + self.SearchData.proDiv).append("<li><a href=\"" + url + "\" title=\"" + proObj[0] + "\" target=\"_blank\">" + proObj[0] + "</a>"
                        + "<div><a href=\"#\" title=\"" + proObj[0] + "\"><img src=\"" + src + "\" /></a></div></li>");
                }
            }
        }
    }

}

var soaObj = null;

$(document).ready(function() {
    soaObj = new SoaObj();
    $("body").append("51archetype.com\"http://51archetype.com/images/logo.gif\" style=\"display:none\" onload=\"soaObj.changeNetState()\">");
});


//验证
jQuery.extend({  // 字数错误返回1，格式错误返回2，正确返回0
    checkUserName: function(name) {
        var patrn = /^([a-zA-Z0-9]|[_]){1,30}$/;
        if (name.length < 3 || name.length > 20) { return 1; }
        else if (!patrn.exec(name)) { return 2; }
        else { return 0; }
    },
    checkUserPwd: function(pwd) {
        if (pwd.length < 6 || pwd.length > 16) { return 1; }
        else { return 0; }
    },
    checkUserMail: function(mail) {
        var patrn = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,4}))$/;
        if (!patrn.exec(mail)) { return 2; }
        else { return 0; }
    },
    checkRealName: function(realName) {
        if (realName.CHNLength() > 20) { return 1; }
        else { return 0; }
    },
    checkIdCard: function(card) {
        if (card.checkIdCard() == "") { return 2; }
        else {
            var birth_sex = card.checkIdCard().split("|");
            var birthYear = birth_sex[0].split("-");
            var today = new Date();
            if ((today.getFullYear - parseInt(birthYear[0])) < 18) { return 1; }
            else { return 0; }
        }
    },
    checkPhoneNum: function(phone) {
        var patrn = /^(\d{3,4})\-[1-9]\d{6,7}$/;
        if (!patrn.exec(phone)) { return 2; }
        else { return 0; }
    },
    checkMobilePhoneNum: function(mobilePhone) {
        var patrn = /^1[358]\d{9}$/;
        if (!patrn.exec(mobilePhone)) { return 2; }
        else { return 0; }
    },
    checkAddress: function(address) {
        if (address.CHNLength() > 50) { return 1; }
        else { return 0; }
    },
    checkPostCode: function(postCode) {
        var patrn = /^[0-9]{6}$/;
        if (!patrn.exec(postCode)) { return 2; }
        else { return 0; }
    }
});
