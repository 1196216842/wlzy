<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>物理天地</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	var ws;
	var target="ws://127.0.0.1:8080/dxwlC/chatSocket?username=${sessionScope.user.username}";
	window.onload=connect;
	function connect(){
		if('WebSocket' in window){
			ws=new WebSocket(target);
		}else if('MozWebSocket' in window){
			ws=new MozWebSocket(target);
		}else{
			alert('WebSocket is not supported by this browser.');
			return;
		}
		
		//在打开连接后开启消息监听
           ws.onmessage=function(event){
            	eval("var result="+event.data);
            	//加入离去的时候
            	if(result.come!=undefined){
            		$("#content").append(result.come+"<BR/><BR/>");
            	}
            	if(result.leave!=undefined){
            		$("#content").append(result.leave+"<BR/><BR/>");
            	}
            	if(undefined!=result.usernames){
            		$("option").remove();
            		$("#select1").append("<option selected='selected' value='all'>所有人</option>");
            		$(result.usernames).each(function (){
            			$("#select1").append("<option  value="+this+">"+this+"</option>");
            		});
            	}
            	
            	//发送消息的时候
            	if(result.content!=undefined){
            		$("#content").append("<div id='msgDiv'>"+result.content+"<BR/><BR/></div>");
            	}
            	$("#content").scrollTop( $('#content')[0].scrollHeight );
            };
        } 
        //发送消息
		function send(){
			var json=null;
			var msgSend=$("#msg");
			var select=$("#select1");
			if(select.val()=="all"){
				json={
					to:"所有人",
					msg:msgSend.val(),
					type:1//1广播，2私聊
				};
			}else{
				json={
					to:select.val(),
					msg:msgSend.val(),
					type:2//1广播，2私聊
				};
			
			}
			ws.send(JSON.stringify(json));
			msgSend.attr("value","");
		}
		
	</script>
<style type="text/css">

#content{
	/*position:absolute; */
	border: 1px solid black; 
	width: 400px; 
	height: 400px;
	overflow:auto;
	word-wrap:break-word;
	word-break:break-all;
		
}
#msg{
	border: 1px solid black;
	margin-left: 20px;
	float: left;
}
#div2{
	width:400px;
}
#msgDiv{
	position:relative;
	width:250px;
	background:#F8C301;
	border-radius:5px;
	margin:20px 50px 10px 0;
	padding:10px;
	font-size:12px;
	float:left;
	}


#span1{
	width:100;
	float: left;
}
	
</style>
  </head>
  <body>
	<div id="div1">
		<h3>欢迎 ${sessionScope.user.username } 使用本系统！！</h3>
		<div id="content"></div>
		<div id="div2"  style="clear: both;" >
			<input type="text" id="msg"/><button onclick="send();">发送</button>
			<span id="span1"><select id="select1"></select></span>
		</div>
	</div>
  </body>
</html>