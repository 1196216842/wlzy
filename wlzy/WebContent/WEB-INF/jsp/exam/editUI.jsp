<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>${requestScope.examTopic.name}</title>
<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath }/css/exam_editUI.css" />
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
			</a><a href="examOptionAction_show.action?examTopicId=${requestScope.examTopic.id }">${requestScope.examTopic.name}</a>
			<img
				src="${pageContext.request.contextPath }/images/arrow_close.gif" /><a>修改</a>
			</div>
	
			<div class="content">
				<div id="examOption" >
				<p id="name">${examTopic.name}</p>
				<form action="examTopicAction_edit.action" method="post">
				<input type="hidden" name="id" value="${requestScope.examTopic.id}"/>
					<table>
						<tr>
							<td>题号</td>						
							<td>题目</td>						
							<td>选项A</td>						
							<td>选项B</td>						
							<td>选项C</td>						
							<td>选项D</td>						
							<td>答案</td>						
						</tr>
					<s:iterator value="examOptions" status="status">
						<tr>
						
							<%-- <td class="first"><input type="text" value="${num}"/></td>
							<td><input type="text" value="${content}"/></td>
							<td><input type="text" value="${A }"/></td>
							<td><input type="text" value="${B }"/></td>
							<td><input type="text" value="${C }"/></td>
							<td><input type="text" value="${D }"/></td>
							<td class="last"><input type="text" value="${answer }"/></td> --%>
						
							<td class="first">	<input type="hidden" name="ids" value="${id}"/><textarea name="nums">${num}</textarea></td>
							<td><textarea name="contents">${content}</textarea></td>
							<td><textarea name="As">${A }</textarea></td>
							<td><textarea name="Bs">${B }</textarea></td>
							<td><textarea name="Cs">${C}</textarea></td>
							<td><textarea name="Ds">${D}</textarea></td>
							<td class="last"><textarea name="answers">${answer}</textarea></td>
						</tr>
					</s:iterator>
					</table>
					<p id="ok"><input type="submit" value="提交" class="okbtn"/>
					</form>
				</div>
			</div>
	</div>
</body>

</html>
