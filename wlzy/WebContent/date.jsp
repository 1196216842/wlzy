<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>物理天地</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/dateJS.js"></script>
</head>
<body onload=initial()>
	<!-- 日历开始 -->
	<form name=CLD>
		<table>
			<TR bgcolor="#006600">
				<TD colSpan=7><FONT color=#ffffff style="FONT-SIZE: 9pt">公历
						<SELECT name=SY onchange=changeCld() style="FONT-SIZE: 9pt">
							<SCRIPT>
														for (i = 1900; i < 2050; i++)document.write('<option>'+i);
													</SCRIPT>
					</SELECT> 年 <SELECT name=SM onchange=changeCld() style="FONT-SIZE: 9pt">
							<script>
														for (i = 1; i < 13; i++)document.write('<option>'+i);
													</script>
					</SELECT> 月 </FONT><FONT color=#ffffff face=宋体 id=GZ style="FONT-SIZE: 10pt"></FONT><BR>
				</TD>
			</TR>
			<tr bgColor=#e0e0e0 style="border:1px solid #666;text-align: center;">
				<TD width=54 style="font-size:9pt ">日</TD>
				<TD width=54 style="font-size:9pt ">一</TD>
				<TD width=54 style="font-size:9pt ">二</TD>
				<TD width=54 style="font-size:9pt ">三</TD>
				<TD width=54 style="font-size:9pt ">四</TD>
				<TD width=54 style="font-size:9pt ">五</TD>
				<TD width=54 style="font-size:9pt ">六</TD>
			</tr>
			<SCRIPT>
								var gNum;
								for (i = 0; i < 6; i++) {
								document.write('<tr align=center>');
										for (j = 0; j < 7; j++) {
											gNum = i * 7 + j;
											document.write('<td id="GD' + gNum +'"><font id="SD'+ gNum
													+ '" size=2 face="Arial Black"');
												if (j == 0)document.write(' color=red');
												if (j == 6)document.write(' color=#000080');
											document.write(' TITLE=""> </font><br><font id="LD' + gNum + '" size=2 style="font-size:9pt"> </font></td>');
											}
											document.write('</tr>');
										}
							</SCRIPT>
		</table>
	</FORM>
	<!-- ---------------日历结束--------------------- -->
</body>
</html>
