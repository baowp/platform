/**
 * 
 */
(function($) {
	$.extend($.ui.dialog.prototype.options, {
		resize : fn,
		open : fn
	})
	function fn() {
		var dia = $(this).parent();
		dia.find(".ui-resizable-e,.ui-resizable-w").height(function() {
			var h1 = dia.find(".ui-dialog-content").height();
			var h2 = dia.find(".ui-dialog-buttonpane").height();
			return h1 + h2;
		})
		dia.find(".ui-resizable-s").width(function() {
			return dia.find(".ui-dialog-content").width() - 2;
		})
	}
})(jQuery)