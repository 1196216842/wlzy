<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>物理资源共享网站</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/home_list.css" />
</head>
<body>
<div class="top">
	<jsp:include page="/top2.jsp"></jsp:include>
</div>
<div class="nav">
	<a href="#">当前位置：</a>
	<a href="homeAction_list.action">首页</a>
</div>



<div class="content">
	<div id="home_list_left">
		<div id="home_vedio">
			<div id="home_vedio_name">在线视频</div>
			<div id="home_vedio_list"></div>
		</div>
		<div id="home_doc">
			<div id="home_doc_name">在线文档</div>
			<div id="home_doc_list"></div>
		</div>
		<div id="home_notice">
			<div id="home_notice_name">最新公告</div>
			<div id="home_notice_list"></div>
		</div>
		<div id="home_hot">
			<div id="home_hot_name">热门主题</div>
			<div id="home_hot_list"></div>
		</div>
		<div id="home_new">
			<div id="home_new_name">最新主题</div>
			<div id="home_new_list"></div>
		</div>
		<div id="home_author">
			<div id="home_author_name">物理学家</div>
			<div id="home_author_list"></div>
		</div>
		<div id="home_exp">
			<div id="home_exp_name">物理实验</div>
			<div id="home_exp_list"></div>
		</div>
	</div>
	
	<div id="home_list_right">
		<div id="home_date">
			<div id="home_date_name">日历挂件</div>
			<div id="home_date_list"></div>
		</div>
		<div id="home_audio">
			<div id="home_audio_name">音频在线</div>
			<div id="home_audio_list"></div>
		</div>
			<div id="home_exam">
			<div id="home_exam_name">在线试题</div>
			<div id="home_exam_list"></div>
		</div>
		<div id="home_zy">
			<div id="home_zy_name">资源下载</div>
			<div id="home_zy_list"></div>
		</div>
		<div id="home_how">
			<div id="home_how_name">网站帮助</div>
			<div id="home_how_list"></div>
		</div>
	
		
	</div>
</div>



<div class="bottom">
	<jsp:include page="/bottom2.jsp"></jsp:include>
</div>
</body>
</html>