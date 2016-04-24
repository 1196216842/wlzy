<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
  <head>
    <title>${file.trueName}</title>
	<style type="text/css">
		html,body{height:100%}
		body{margin:0;padding:0;overflow:auto;}
		#flashContent{display:none;}
	</style>
	<script type="text/javascript" src="js/flexpaper_flash.js"></script>
  </head>
  
  <body>
  <div style="position:absolute;left:200px;top:10px;">
  	<div>
	 <h3>${file.user.username}</h3>
	  <h4>${file.trueName}</h4>
	  <h5>${file.uploadTime}</h5>
	</div>
  <a id="viewerPlaceHolder" style="width:780px;height:603px;display:block"></a>
  <%-- SwfFile:escape('<%=basePath%>swf/<%=(String)session.getAttribute("fileName")%>'),  --%>
  <script type="text/javascript">
  	var fp=new FlexPaperViewer(
  			'FlexPaperViewer',
  			'viewerPlaceHolder',{config:{
  			SwfFile:'upload/documents/swf/<%=(String)request.getAttribute("fileName")%>', 
  			Scale:0.6,
  			ZoomTransition:'easeOut',
  			ZoomTime:0.5,
  			ZoomInterval:0.2,
  			FitWidthOnLoad:true,
  			PrintEnabled:false,
  			FullScreenAsMaxWindow:false,
  			ProgressiveLoading:true,
  			MinZoomSize:0.2,
  			MaxZoomSize:5,
  			SearchMatchAll:false,
  			InitViewMode:'Portrait',
  			ViewModeToolsVisible:true,
  			ZoomToolsVisible:true,
  			NavToolsVisible:true,
  			CursorToolsVisible:true,
  			SearchToolsVisible:true,
  			
  			localeChain:"zh_CN"
  			}});
  </script>
   </div>
  
  </body>
</html>
