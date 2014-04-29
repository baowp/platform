/**
 * 
 */
function Menu() {
	for ( var key in this.onload)
		$(this.onload[key])
}
Menu.prototype = {
	onload : {
		initMenu : function() {
			$(".moveMenu").each(menu.generate);
			$(".submenu li").each(menu.generate);
		},
		superfish : function() {
			$("#list_nav").superfish({
				speed : 300,
				delay : 200,
				animation : {
					height : "show"
				}
			});
		}
	},
	generate : function() {
		var $menu = $(this)
		var position = $menu.position();
		var x = $menu.data("x") || "top";
		var y = $menu.data("y") || "height";
		position[x] += $menu[y]();
		var bg = $menu.hasClass("headerMenuLiCheckBg")
				|| $menu.parent().hasClass("headerMenuLiCheckBg") ? "headerMenuLiCheckBg"
				: "";

		var subs = $("<ul>", {
			className : "submenu headerMenuBg " + bg,
			css : position
		});
		var submenu = $menu.data("submenu");
		if (submenu.length) {
			$.each(submenu, function(i, nav) {
				subs.append($("<li>", {
					id : "menu-" + nav.page,
					className : "headerMenuLi",
					"data-submenu" : $.toJSON(nav.subJson),
					"data-x" : "left",
					"data-y" : "width"
				}).append($("<span>").append($("<a>", {
					href : p.href(nav.page),
					innerHTML : nav.name
				}))))
			})
			$menu.append(subs);
			$menu.addClass("branch");
		} else {
			$menu.addClass("leaf")
		}
		$menu.removeAttr("data-submenu");
	}
}

var menu = new Menu();
