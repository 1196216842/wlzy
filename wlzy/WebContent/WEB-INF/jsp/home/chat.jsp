<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>物理天地</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	var ws;
	var target="ws://127.0.0.1:8080/wlzy/chatServer?username=${sessionScope.user.username}";
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
        	//消息处理
        	if(result.come!=undefined){//若消息中含有用户加入消息，则加上用户加入
        		/* alert(result.come); */
        		var div=$("#info #left #content div");
        		var span=$("<span class='tip'>"+result.come+"</span><br/><br>");
        		div.append(span);
        	}
        	if(result.usernames!=undefined){//若消息中含有用户列表，则加上用户列表
        		var div=$("#info #right #userInfo div");
        		div.html("");
        		$(result.usernames).each(function(){
        			//alert(this);
        			div.append("<span>"+this+"</span><br/>");
        		});
        	}
        	if(result.content!=undefined){//若消息中含有用户内容，则加上用户内容
        		var div=$("#info #left #content div");
        		div.append("<pre>"+result.content+"</pre><br/>");
        	}
        	$("#info #left #content div").scrollTop( $('#info #left #content div')[0].scrollHeight );
        	if(result.leave!=undefined){//若消息中含有用户离开消息，则加上用户离开
        		var div=$("#info #left #content div");
        		var span=$("<span class='tip'>"+result.leave+"</span><br/><br>");
        		div.append(span);
        	}
        }; 
  }
 /*     
     
     $(function(){
     	$("#btn").click(function(){
	     	send();
	     }); 
     });
      */
     
      //发送消息
	function send(str){
		/* var msg=$("#msg"); */
		ws.send(str);
		/* ws.send(JSON.stringify(json)); */
		/* msg.val("");  */
	}
		
</script>
<style type="text/css">
* {
	padding: 0px;
	margin: 0px;
}

#div1 {
	width: 100%;
	height: 100%;
}

#top {
	width: 100%;
	height: 25px;
	margin: 1px;
	text-align: center;
	font-size: 1.5em;
	font-family: "微软雅黑";
	margin-bottom: 5px;
}

#info {
	width: 100%;
	height: 68%;
}

#info #left {
	width: 75%;
	height: 100%;
	float: left;
}

#info #left #content {
	width: 100%;
	height: 100%;
	border: 1px solid black;
	margin: 1px;
}

#info #left #content p {
	width: 98%;
	margin: 0px auto;
	font-size: 16px;
	font-family: 微软雅黑;
	margin-left: 5px;
}

#info #left #content .tip {
	background:red;
	background: #ddd;
	font-size: 14px;
	font-family: 微软雅黑;
	text-align: center;
}

#info #left #content div {
	width: 98%;
	height: 95%;
	overflow: auto;
	margin-left: 10px;
}

#info #left #content div pre {
	 	font-size: 19px;
		font-family:"微软雅黑";
		/* text-wrap:normal; */
		display: block;
		overflow: auto;
		background: #f4f4f4;
		padding: 5px 10px;
		border: 1px solid #eee;
		word-wrap: break-word; /* Internet Explorer 5.5+ */
		white-space: pre-wrap; /* Firefox */
}


/* 
#input textarea {
	width: 80%;
	height: 100%;
	border: 1px solid red;
	float: left;
} 
 */

#info #right {
	width: 25%;
	height: 100%;
	float: right;
}

#info #right #userInfo {
	width: 100%;
	height: 100%;
	border: 1px solid blue;
	margin: 1px;
}

#info #right #userInfo p {
	width: 98%;
	margin: 0px auto;
	font-size: 16px;
	font-family: 微软雅黑;
	margin-left: 5px;
}

#info #right #userInfo div {
	width: 98%;
	height: 95%;
	margin: 0px auto;
	overflow: auto;
}

#info #right #userInfo div span {
	width: 100%;
	font-size: 16px;
	font-family: "微软雅黑";
	margin-left: 5px;
	color: orange;
	white-space:nowrap;
	overflow:hidden;
	text-overflow:ellipsis;
}

#input {
	width: 100%;
	height: 30%;
	margin: 1px;
}
#input button {
	width: 60px;
	height: 15%;
	background:black;
	color:white;
}
/* #msg {
	font-size: 16px;
	font-family: "微软雅黑";
} */
</style>
  </head>
  <body>
	<div id="div1">
		<div id="top">
			欢迎您${sessionScope.user.username}加入群聊
		</div>
		<div id="info">
			<div id="left">
				<div id="content">
					<p>聊天消息:</p>
					<hr/>
					<div></div>
				</div>
				
			</div>
			<div id="right">
				<div id="userInfo">
					<p>用户列表:</p>
					<hr/>
					<div></div>
				</div>
			</div>
		</div>
		<div id="input">
				<!-- <textarea id="msg"></textarea>  -->
				 <script id="editor" type="text/plain" style="width:100%;height:50%;"></script>
				<!-- js区域 -->
				<script type="text/javascript" src="${pageContext.request.contextPath }/ueditor/ueditor.config.js"></script>
	 			<script type="text/javascript" src="${pageContext.request.contextPath }/ueditor/ueditor.all.js"></script>
				<script type="text/javascript">
			 		$(function(){
			 			var ue = UE.getEditor('editor');
			 			$("#btn").click(function(){
			 				var str = ue.getPlainTxt();
			 				send(str);
			 				ue.setContent("");
			 			});
			 		});
					
				</script> 
					
				<button id="btn">发送</button>
		</div>
	</div>
  </body>
</html>