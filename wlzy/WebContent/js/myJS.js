function openwindow(chat,username)
{	
	if(username!=""){
   		 return window.open(chat,'newindow','height=670,width=600,top=20,left=400,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no');
	}else{
		alert("请先登录");
	}
    
}