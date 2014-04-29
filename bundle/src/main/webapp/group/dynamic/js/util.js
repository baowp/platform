function msie6qqTop() {
}
function msie6qqLeft() {
}
(function() {
	if (!$.browser.msie) {
		var link = $("<link>", {
			type : "text/css",
			rel : "stylesheet",
			href : p.compatibleCss
		}).insertAfter("head link:last");
		$(function() {
			link.appendTo("link:last");
		})
	}

	var style = '<style type="text/css">';
	if ($.cookie("username")) {
		style += ".isNotLoged{display:none}";
	} else {
		style += ".isLoged{display:none}";
	}
	document.writeln(style + "</style>");
})();

function busyOverlay(parentNode, overlay, busy) {
	if (typeof parentNode == "string")
		parentNode = $(parentNode).get(0);
	try {
		busylay = getBusyOverlay(
				parentNode || document.body,
				$
						.extend(
								{
									color : 'black',
									opacity : 0.4,
									text : '',
									style : 'text-decoration:blink;font-weight:bold;font-size:12px;color:white;'
								}, overlay), $.extend({
					color : '#fff',
					size : 128,
					type : 'o'
				}, busy));
	} catch (e) {
	}
	if (!msie6
			&& (parentNode == null || parentNode == document.body || parentNode == document)) {
		$("#cvi_busy_canvas").css("position", "fixed");
		$("#cvi_busy_text").css("position", "fixed");
	}
}

function addFav() {
	var href = "http://" + location.host;
	if (document.all) {
		window.external.addFavorite(href, enterpriseName);
	} else if (window.sidebar) {
		window.sidebar.addPanel(enterpriseName, href, "");
	}
}

function setHomepage() {
	var href = "http://" + location.host;
	if (document.all) {
		document.body.style.behavior = 'url(#default#homepage)';
		document.body.setHomePage(href);

	} else if (window.sidebar) {
		if (window.netscape) {
			try {
				netscape.security.PrivilegeManager
						.enablePrivilege("UniversalXPConnect");
				var prefs = Components.classes['@mozilla.org/preferences-service;1']
						.getService(Components.interfaces.nsIPrefBranch);
				prefs.setCharPref('browser.startup.homepage', href);
			} catch (e) {
				alert(lan['var.reject']);
			}
		}
	}
}

function removeBusy() {
	if (window.busylay)
		try {
			busylay.remove();
		} catch (e) {
		}
}

$(function() {
	$.cookie("redirectDomain", location.host);
	$("a,:button").live("focus", function() {
		this.blur()
	});
	if (!msie6)
		$("a[href=#]").attr("href", "javascript:");

	setTimeout(function() {
		$.getScript("http://51archetype.com/js/util/ip.jsp", function() {
			if (window.hostIP)
				$.ajax({
					url : "h51archetype.comcc.net/user/viewlog/addViewLog",
					cache : false,
					dataType : "script",
					data : {
						beviewId : enterpriseId,
						ip : hostIP
					}
				});
		});
	}, 2000)

	var $qq = $("#gaim-qq");
	if ($qq.css("position") != "static") {
		$qq.appendTo("#content").draggable({
			stop : window.qqDragStop
		});
	}
})
