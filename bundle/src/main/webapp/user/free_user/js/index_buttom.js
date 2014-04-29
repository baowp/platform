var id=function(o){return document.getElementById(o)}
var scroll=function (o){
	var space=0;
		space=25;
	space=space+id(o).offsetTop;
	id(o).style.top=space+'px';
	void function(){
			var goTo = 0;
			var roll=setInterval(function(){
				var height =document.documentElement.scrollTop+document.body.scrollTop+space;
				var top = parseInt(id(o).style.top);
				if(height!= top){
					goTo = height-parseInt((height - top)*0.9);
					id(o).style.top=goTo+'px';
				}
				//else{if(roll) clearInterval(roll);}
			},50);
	}()
}
scroll('box1');
scroll('box2');