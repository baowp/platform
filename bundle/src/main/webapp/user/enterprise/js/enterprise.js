function openUpdateuser() {
	art.dialog.open('/user/enterprise/updateuser/edit', {
		id : 'upFrame',
		skin : 'aero',
		title : '修改个人资料(按esc可关闭)',
		left : 200,
		fixed : true,
		top : 0,
		width : 500,
		height : 500
	});
}
function openCompletion() {
	art.dialog.open('/user/enterprise/company/completionList', {
		id : 'completion1',
		skin : 'aero',
		title : '补全公司资料(按esc可关闭)',
		fixed : true,
		width : 775,
		height : 550
	});
}
function openMap(){
	art.dialog.open('include/mapbar/map.jsp', {
		id : 'mapIframe',
		skin : 'aero',
		title : '地图定位(按esc可关闭)',
		left : 150,
		top : 40,
		fixed : true,
		width : 630,
		height : 470
	});
}
