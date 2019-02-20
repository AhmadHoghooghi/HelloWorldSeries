<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<html dir="${direction}">
<head>
	<title>${msg['404.title']}</title>
</head>
<%String contextPath = request.getContextPath(); %>
<body>
	<font face="B Titr,Titr,Tahoma">
	<div id="content">
	<table width="100%">
		<tr>
			<td>
				<div align="middle">
					<img border="0" src="<%=contextPath%>/images/errors/404.jpg" alt="404 Error" align="middle">
					<br/>
					<div style="background-color:#CFCFCF">
						<font face="B Titr,Titr,Tahoma">
							<h3>${msg['404.message']}</h3>
						</font>
					</div>
				</div>
			</td>
		</tr>
	</table>
	</div>
	</font>	
</body>
</html>
