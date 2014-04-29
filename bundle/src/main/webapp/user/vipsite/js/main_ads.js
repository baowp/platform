/**
 * 
 */

(function() {
	$('#carousel').jcarousel({
		auto : 2,
		scroll : 2,
		wrap : 'circular',
		initCallback : function(carousel) {
			carousel.clip.hover(function() {
				carousel.stopAuto();
			}, function() {
				carousel.startAuto();
			});
		}
	});
})();