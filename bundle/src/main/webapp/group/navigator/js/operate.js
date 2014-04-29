function edit(obj) {
	var name = $(obj).parents("tr").find("input[name='name']").val();
	var id = $(obj).parents("tr").attr("navigatorId");
	var url = "/rest/group/navigator/update";
	$.ajax({
		type : "post",
		url : url,
		data : {
			id : id,
			name : name
		},
		success : function() {
			info("修改成功 ");
		}
	});

}
function deleteNav(obj) {
	var msg = "您真的确定要删除吗？";
	if (confirm(msg) == true) {
		var temp = false;
		var id = $(obj).parents("tr").attr("navigatorId");
		var url = "/rest/group/navigator/deleteNav";
		$.ajax({
			type : "post",
			async : false,
			url : url,
			data : {
				id : id
			},
			success : function(flag) {
				temp = flag;
			}
		});
		if (temp) {
			info("该分类存在下属类别，不能删除 ");
			return false;
		} else {
			window.location = "/rest/group/navigator/delete?id=" + id;
		}
	} else {
		return false;
	}
}
function setDisplay(obj) {
	var id = $(obj).parents("tr").attr("navigatorId");
	var isDisplay = $(obj).attr("value");
	var grade=$("input[name=grade]").val();
	var url = "/rest/group/navigator/setDisplay";
	$.ajax({
		type : "post",
		url : url,
		data : {
			id : id,
			display : isDisplay,
			grade:grade
		},
		success : function(flag) {
			if(!flag)
			{
				info("设置失败,一级菜单最多只能显示8个菜单");
				return;
			}
			if (isDisplay == 0) {
				$(obj).html('已隐藏').attr("value", "1");
			} else {
				$(obj).html('已显示').attr("value", "0");
			}
		}
	});
}

function back(grade) {
	if (grade == 0 || grade == "") {
		grade += 1;
	}
	window.location.replace("/rest/group/navigator/list?grade=" + grade);
}

function check() {
	var page = $.trim($("input[name='page']").val());
	var name = $.trim($("input[name='name']").val());
	if (page == "" || name == "") {
		alert("页面或导航名称不能为空！");
		return false;
	}
}

function stepSort(obj, type) {
	var $row = $(obj).parents("tr[navigatorId]");
	$("input[name=sourceCate]").val($row.attr("navigatorId"));
	$("input[name=sourceSort]").val($row.attr("sort"));
	$("input[name=stepType]").val(type);
	$("input[name=navigatorId]").val();
	$("#form1").submit();
}