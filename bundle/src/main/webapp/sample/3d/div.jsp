<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head> 
 <link href="/css/jquery-ui.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript" src="/js/jquery.js"></script> 
 <script type="text/javascript" src="/js/jquery-ui.min.js"></script>
   <script>  
   $(document).ready(function(){  
 		//run the currently selected effect
 		function runEffect(){
 			//get effect type from 
 			var selectedEffect = "explode";
 			
 			//most effect types need no options passed by default
 			var options = {};
 			//check if it's scale, transfer, or size - they need options explicitly set
 			if(selectedEffect == 'scale'){ options = {percent: 0}; }
 			else if(selectedEffect == 'transfer'){ options = { to: "#button", className: 'ui-effects-transfer' }; }
 			else if(selectedEffect == 'size'){ options = { to: {width: 200,height: 60} }; }
 			
 			//run the effect
 			$("#effect").effect(selectedEffect,options,500,callback);
 		};
 		
 		//callback function to bring a hidden box back
 		function callback(){
 			setTimeout(function(){
 				$("#effect:hidden").removeAttr('style').hide();
 			}, 1000);
 		};
 		
 		//set effect from select menu value
 		$("#button").click(function() {
 			runEffect();
 			return false;
 		});
   
   });  
   </script>  
   	<style type="text/css">
		.toggler { width: 500px; height: 200px; position: relative;}
		#button { padding: .5em 1em; text-decoration: none; }
		#effect { width: 240px; height: 135px; padding: 0.4em; position: relative; }
		#effect h3 { margin: 0; padding: 0.4em; text-align: center; }
		.ui-effects-transfer { border: 2px dotted gray; } 
	</style>
 </head>  
 <body>  
 <div class="toggler">
	<div id="effect" class="ui-widget-content ui-corner-all">
		<h3 class="ui-widget-header ui-corner-all">Effect</h3>

		<p>
			Etiam libero neque, luctus a, eleifend nec, semper at, lorem. Sed pede. Nulla lorem metus, adipiscing ut, luctus sed, hendrerit vitae, mi.
		</p>
	</div>
</div>

   <button id="button">Toggle</button>  
  
 </body> 