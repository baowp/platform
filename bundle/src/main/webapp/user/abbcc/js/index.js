var headers = [];
jQuery("#topmenu li a").each(function(i, node) {
	if (node.id.match(/^header_/))
		headers.push(node.id.replace(/^header_/, ''));
})
var admincpfilename = '../right';
function toggleMenu(key, url) {
	for ( var k in headers) {
		if ($('menu_' + headers[k])) {
			$('menu_' + headers[k]).style.display = headers[k] == key ? ''
					: 'none';
		}
	}
	var lis = $('topmenu').getElementsByTagName('li');
	for ( var i = 0; i < lis.length; i++) {
		if (lis[i].className == 'navon')
			lis[i].className = '';
	}
	$('header_' + key).parentNode.parentNode.className = 'navon';
	if (url) {
		// parent.main.location = admincpfilename + '?action=' + url;
		var hrefs = $('menu_' + key).getElementsByTagName('a');
		for ( var j = 0; j < hrefs.length; j++) {
			hrefs[j].className = hrefs[j].href.substr(hrefs[j].href
					.indexOf(admincpfilename + '?action=') + 19) == url ? 'tabon'
					: (hrefs[j].className == 'tabon' ? '' : hrefs[j].className);
		}
	}
	return false;
}

function initCpMenus(menuContainerid) {
	var key = '';
	var hrefs = $(menuContainerid).getElementsByTagName('a');
	for ( var i = 0; i < hrefs.length; i++) {
		if (menuContainerid == 'leftmenu'
				&& !key
				&& 'action=home'.indexOf(hrefs[i].href.substr(hrefs[i].href
						.indexOf(admincpfilename + '?action=') + 12)) != -1) {
			key = hrefs[i].parentNode.parentNode.id.substr(5);
			hrefs[i].className = 'tabon';
		}
		if (!hrefs[i].getAttribute('ajaxtarget')) {
			hrefs[i].onclick = function() {
				if (menuContainerid != 'custommenu') {
					var lis = $(menuContainerid).getElementsByTagName('li');
					for ( var k = 0; k < lis.length; k++) {
						if (lis[k].firstChild.className != 'menulink')
							lis[k].firstChild.className = '';
					}
					if (this.className == '')
						this.className = menuContainerid == 'leftmenu' ? 'tabon'
								: 'bold';
				}
				if (menuContainerid != 'leftmenu') {
					var hk, currentkey;
					var leftmenus = $('leftmenu').getElementsByTagName('a');
					for ( var j = 0; j < leftmenus.length; j++) {
						hk = leftmenus[j].parentNode.parentNode.id.substr(5);
						if (this.href.indexOf(leftmenus[j].href) != -1) {
							leftmenus[j].className = 'tabon';
							if (hk != 'index')
								currentkey = hk;
						} else {
							leftmenus[j].className = '';
						}
					}
					if (currentkey)
						toggleMenu(currentkey);
					hideMenu();
				}

			}
		}
	}
	return key;
}
var header_key = initCpMenus('leftmenu');
toggleMenu(header_key ? header_key : 'index');
function initCpMap() {
	var ul, hrefs, s;
	s = '<ul class="cnote"><li><img src="/user/abbcc/images/admincp/btn_map.gif" /></li><li> 按 “ ESC ” 键展开 / 关闭此菜单</li></ul><table class="cmlist" id="mapmenu"><tr>';

	for ( var k in headers) {
		if (headers[k] != 'index' && headers[k] != 'uc'
				&& $('header_' + headers[k]) && $('menu_' + headers[k])) {
			s += '<td valign="top"><ul class="cmblock"><li><h4>'
					+ $('header_' + headers[k]).innerHTML + '</h4></li>';
			ul = $('menu_' + headers[k]);
			hrefs = ul.getElementsByTagName('a');
			for ( var i = 0; i < hrefs.length; i++) {
				s += '<li><a href="' + hrefs[i].href + '" target="'
						+ hrefs[i].target + '" k="' + headers[k] + '">'
						+ hrefs[i].innerHTML + '</a></li>';
			}
			s += '</ul></td>';
		}
	}
	s += '</tr></table>';
	return s;
}
$('cmain').innerHTML = initCpMap();
initCpMenus('mapmenu');
var cmcache = false;
function showMap() {
	showMenu('cpmap', true, 3, 3);
	if (!cmcache)
		ajaxget(admincpfilename + '?action=misc&operation=custommenu&'
				+ Math.random(), 'custommenu', '');
}
function resetEscAndF5(e) {
	e = e ? e : window.event;
	actualCode = e.keyCode ? e.keyCode : e.charCode;
	if (actualCode == 27) {
		if ($('cpmap_menu').style.display == 'none') {
			showMap();
		} else {
			hideMenu();
		}
	}
	if (actualCode == 116 && parent.main) {
		parent.main.location.reload();
		if (document.all) {
			e.keyCode = 0;
			e.returnValue = false;
		} else {
			e.cancelBubble = true;
			e.preventDefault();
		}
	}
}
_attachEvent(document.documentElement, 'keydown', resetEscAndF5);

function uc_left_menu(uc_menu_data) {
	var leftmenu = $('menu_uc');
	leftmenu.innerHTML = '';
	var html_str = '';
	for ( var i = 0; i < uc_menu_data.length; i += 2) {
		html_str += '<li><a href="'
				+ uc_menu_data[(i + 1)]
				+ '" hidefocus="true" onclick="uc_left_switch(this)" target="main">'
				+ uc_menu_data[i] + '</a></li>';
	}
	leftmenu.innerHTML = html_str;
	toggleMenu('uc', '');
	$('admincpnav').innerHTML = 'UCenter';
}

var uc_left_last = null;
function uc_left_switch(obj) {
	if (uc_left_last) {
		uc_left_last.className = '';
	}
	obj.className = 'tabon';
	uc_left_last = obj;
}

function uc_modify_sid(sid) {
	$('header_uc').href = 'http://www.oujiangchao.com/ucenter/admin.php?m=frame&a=main&iframe=1&sid='
			+ sid;
}

(function($) {
	$(function() {
		$("a[sitedesign=]").click(function() {
			setTimeout(function() {
				$("a[sitestatic=]").click();
				main.location = $("a[sitestatic=]").attr("href");
			}, 1000)
		})
	})
})(jQuery)
