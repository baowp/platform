		
		function goto(page){
			$("#page").val(page);
			document.form1.submit();
		}
		//mouse over function      
		function suggestOver(div_value){      
		    div_value.className="suggest_link_over";      
		}      
		//mouse out function      
		function suggestOut(div_value){      
		    div_value.className="suggest_link";      
		}      
		//click function      
		
		function reset(){
			$("#entId").val("");
			$("#entName").val("");
		}  
		function showAdd(obj){
			var $row=$(obj).parents("tr[payuserId]");
			$("#payuserId").attr("value",$row.attr('payuserId'));
			$("#dialog").dialog('open');
			$("#dialog").dialog({
				title:"添加帮助",
				width:680
			});
		}  
		var checkboxs="";
		function delall(ch){
			var result=del();
			if(result==false)
				return false;
		    var value= document.getElementsByName(ch);
		    for(var i=0;i<value.length;i++){
				if(value[i].checked==true){
					checkboxs=checkboxs+"dh="+value[i].defaultValue+"&"
				}
			}
			if(checkboxs==""){
				alert('您还未选中你要操作的主题！');
				return;
			}
			window.location.href="delHelpCenter.action?"+checkboxs;
		}