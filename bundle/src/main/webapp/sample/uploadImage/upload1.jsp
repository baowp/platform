<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">@import url(css/plupload.queue.css);</style>
	<script type="text/javascript" src="js/jsapi.js"></script>
	<script type="text/javascript">
	    google.load("jquery", "1.3");
	</script>
	 
	<!-- Thirdparty intialization scripts, needed for the Google Gears and BrowserPlus runtimes -->
	<script type="text/javascript" src="js/gears_init.js"></script>
	<script type="text/javascript" src="js/browserplus-min.js"></script>
	 
	<!-- Load plupload and all it's runtimes and finally the jQuery queue widget -->
	<script type="text/javascript" src="js/plupload.full.min.js"></script>
	<script type="text/javascript" src="js/jquery.plupload.queue.min.js"></script>
	 
	<script type="text/javascript">
	// Convert divs to queue widgets when the DOM is ready
	$(function() {
	    $("#uploader").pluploadQueue({
	        // General settings
	        runtimes : 'gears,flash,silverlight,browserplus,html5',
	        url : '/fileUpload/uploadImage',
	        max_file_size : '10mb',
	        chunk_size : '1mb',
	        unique_names : 'files',
	 
	        // Resize images on clientside if we can
	        resize : {width : 320, height : 240, quality : 90},
	 
	        // Specify what files to browse for
	        filters : [
	            {title : "Image files", extensions : "jpg,gif,png"},
	            {title : "Zip files", extensions : "zip"}
	        ],
	 
	        // Flash settings
	        flash_swf_url : 'flash/plupload.flash.swf',
	 
	        // Silverlight settings
	        silverlight_xap_url : 'http://www.plupload.com/plupload/js/plupload.silverlight.xap'
	    });
	 
	    // Client side form validation
	    $('form').submit(function(e) {
	        var uploader = $('#uploader').pluploadQueue();
	 
	        // Validate number of uploaded files
	        if (uploader.total.uploaded == 0) {
	            // Files in queue upload them first
	            if (uploader.files.length > 0) {
	                // When all files are uploaded submit form
	                uploader.bind('UploadProgress', function() {
	                    if (uploader.total.uploaded == uploader.files.length)
	                        $('form').submit();
	                });
	 
	                uploader.start();
	            } else
	                alert('You must at least upload one file.');
	 
	            e.preventDefault();
	        }
	    });
	});
	</script>
</head>
<body>
	<form>
	    <div id="uploader">
	        <p>You browser doesn't have Flash, Silverlight, Gears, BrowserPlus or HTML5 support.</p>
	    </div>
	</form>
</body>
</html>