/**
 * 
 */
// Create a Deferred and return its Promise
function asyncEvent() {
	var dfd = jQuery.Deferred();
	dfd.done(function() {
		console.info(1)
	}).done([ function() {
		console.info(2)
	}, function() {
		console.info(3)
	} ]).fail(function() {
		console.info(-1)
	}).fail(function() {
		console.info(-2)
	});
	setTimeout(function() {
		dfd.resolve("hurray");
	}, 1100);
	setTimeout(function() {
		dfd.reject("sorry");
	}, 1200);
	return dfd.promise();
}

// Attach a done and fail handler for the asyncEvent
$.when(asyncEvent()).then(function(status) {
	console.info(status + ', things are going well');
}, function(status) {
	console.info(status + ', you fail this time');
});
