<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>物理天地</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/style.css" />
<script type="text/javascript">
	//首页
	function firstPage(){
		var _currentPage=document.pageRollForm.currentPage.value;
		if(_currentPage==1){
				alert("亲，已经是第一页了");
				return;
		}
			document.pageRollForm.currentPage.value=1;
			document.pageRollForm.submit(); 
	}

	//上一页
	function previousPage(){
			var _currentPage=document.pageRollForm.currentPage.value;
			if(_currentPage==1){
				alert("亲，已经是第一页了");
				return;
			}
			document.pageRollForm.currentPage.value=--_currentPage;
			document.pageRollForm.submit(); 
		}
	//下一页
	function nextPage(){
		var _currentPage=document.pageRollForm.currentPage.value;
		var _pageCount=document.pageRollForm.pageCount.value;
		if(_currentPage==_pageCount){
				alert("亲，已经是最后一页了");
				return;
		}
		document.pageRollForm.currentPage.value=++_currentPage;
		document.pageRollForm.submit(); 
	}
		//尾页
		function lastPage(){
			var _currentPage=document.pageRollForm.currentPage.value;
			var _pageCount=document.pageRollForm.pageCount.value;
			if(_currentPage==_pageCount){
				alert("亲，已经是最后一页了");
				return;
			}
			document.pageRollForm.currentPage.value=_pageCount;
			document.pageRollForm.submit(); 
		}
		//跳转到指定页面
		function gotoPage(){
			var _selectValue =document.pageRollForm.goto_page.value;
			document.pageRollForm.currentPage.value=_selectValue ;
			document.pageRollForm.submit();
		}
</script>
<style>
.input{background:white;font-size: 13px;color:black;border: 0px solid #ddd;}
.input:hover{cursor:pointer;}
.select{background:#eee;font-size: 13px;;color:black;border: 1px solid #ddd;}
.select:hover{cursor:pointer;}
</style>
</head>
<body>
	<s:form action="%{param.action}" name="pageRollForm">
		当前是第${pageRoll.currentPage}页|
		共${pageRoll.pageCount}页|
		共${pageRoll.totalCount}条记录|
		<input type="hidden" name="currentPage" value="${pageRoll.currentPage}"/>
		<input type="hidden" name="pageCount" value="${pageRoll.pageCount}"/>
		<input class="input" type="button" value="首页" onclick="firstPage()"/> 
		<input class="input" type="button" value="上一页" onclick="previousPage()"/> 
		<select class="select" name="goto_page" onchange="gotoPage()">
			<c:forEach begin="1"  end="${pageRoll.pageCount}" var="i">
				<c:choose>
					<c:when test="${pageRoll.currentPage==i}">
						<option value="${i}" selected="selected">${i}</option>
					</c:when>
					<c:otherwise>
						<option value="${i}">${i}</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
		<input class="input" type="button" value="下一页" onclick="nextPage()"/> 
		<input class="input" type="button" value="尾页" onclick="lastPage()"/> 
	</s:form>
</body>
</html>
