var owner = "1237534";
var sf_mess_cfg = {
	theme : "cidilife",
	color : "gray",
	title : "\u6b22\u8fce\u7ed9\u6211\u4eec\u7559\u8a00",
	send : "\u53d1\u9001",
	copyright : "ABBCC\u6280\u672f\u652f\u6301",
	mbpos : "RD"
};
var sf_mess_msg = {
	emailErr : '\u8bf7\u586b\u5199\u6b63\u786e\u7684Email',
	messErr : '\u60a8\u7684\u7559\u8a00\u5b57\u6570\u5df2\u8d85\u8fc7\u9650\u5236\uff0c\u8bf7\u4fdd\u7559\u57281000\u4e2a\u5b57\u4ee5\u5185\u3002',
	prefix : '\u8bf7\u586b\u5199',
	success : '\u6211\u4eec\u5df2\u7ecf\u6536\u5230\u60a8\u7684\u7559\u8a00,\u7a0d\u5019\u4f1a\u4e0e\u60a8\u8054\u7cfb.\u8c22\u8c22!',
	fail : '\u60a8\u7684\u7559\u8a00\u53d1\u9001\u5931\u8d25\uff0c\u8bf7\u91cd\u8bd5\u3002'
};
var sf_mess_cols = [
		{
			type : "textarea",
			mbtype : "message",
			tip : "\u7559\u8a00\u5185\u5bb9",
			innertip : "\u8bf7\u5728\u6b64\u7559\u8a00\uff0c\u6211\u4eec\u4f1a\u53ca\u65f6\u8054\u7cfb\u60a8",
			idname : "content"
		},
		{
			type : "text",
			mbtype : "tel",
			tip : "\u624b\u673a\u53f7\u7801",
			innertip : "\u8bf7\u8f93\u5165\u60a8\u7684\u624b\u673a\u53f7\u7801",
			idname : "phone"
		} ];
document
		.write('<script src="/js/feedbackB/entry.js" type="text/javascript"></script>');