<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>${requestScope.examTopic.name}</title>
<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath }/css/exam_show.css" />
</head>

<body>
	<div id="list">
			<div class="top"><jsp:include page="/top.jsp"></jsp:include></div>
			<div class="nav">
				<a href="#">当前位置：</a>
				<a href="homeAction_list.action">首页<img
					src="${pageContext.request.contextPath }/images/arrow_close.gif" />
				</a><a href="examTopicAction_list.action">在线测试<img
				src="${pageContext.request.contextPath }/images/arrow_close.gif" />
			</a><a>${requestScope.examTopic.name}</a>
			<s:if test="#session.user.userType==1||#session.user.userType==2">
				<a style="float:right;margin-right: 10px;" href="examTopicAction_delete.action?id=${requestScope.examTopic.id }">删除</a>
				<a style="float:right;margin-right: 10px;" href="examTopicAction_editUI.action?id=${requestScope.examTopic.id }">修改</a>
			</s:if>
			</div>
	
			<div class="content">
				<div id="examOption" >
				<p id="name">${examTopic.name}</p>
					<s:iterator value="examOptions" status="status">
						<div class="examOptions">
							<p>${num}:${content}<span class="spans">${answer}</span><p>
							<p><label><input type="radio" value="A" name="radios_${status.index+1 }"/>&nbsp;A:${A}</label> </p>
							<p><label><input type="radio" value="B" name="radios_${status.index+1 }"/>&nbsp;B:${B} </label></p>
							<p><label><input type="radio" value="C" name="radios_${status.index+1 }"/>&nbsp;C:${C}</label></p>
							<p><label><input type="radio" value="D" name="radios_${status.index+1 }"/>&nbsp;D:${D}</label></p><br/>
						</div>
					</s:iterator>
					<p id="ok"><button class="okbtn" onclick="check()">提交</button>&nbsp;<button class="okbtn" onclick="again()">重做</button></p>
				</div>
			</div>
	</div>
</body>
<script type="text/javascript">
function check(){
	var j=0;
	var grade=0;
	var error=0;
	var ok=0;
	var radios=document.getElementsByTagName("input");
	//先判断是否全部题目都已经做完，也就是判断选中的个数够不够
	for(var i=0;i<radios.length;i++){
		if(radios[i].checked){
			ok++;
		}
	}
	if(ok!=radios.length/4){
		alert("请答完全部题目再提交");
		return;
	}
	/* var len=radios.length();  */
	for(var i=0;i<radios.length;i++){
		if(radios[i].checked){
			/* alert(radios[i].value); */
			var span=$("span").eq(j);
			if(radios[i].value==span.html()){
				span.css("color","blue");
				grade++;
			}else{
				span.css("color","red");
				error++;
			}
			j++;
		}
	}
	$("span").css("display","block");
	if(error==0){
		alert("恭喜您，全部答对");
	}else{
		alert("您答对"+grade+"题,答错"+error+"题，各题答案已显示题目后面!");
	}
}
function again(){
	window.location.href="examOptionAction_show.action?examTopicId="+'${requestScope.examTopic.id}';
}
</script>
</html>
