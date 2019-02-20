<%@ page isErrorPage="true" language="java" import="ir.asta.wise.core.util.*" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%
	if (request.getAttribute(ir.asta.wise.core.Constants.ACCESS_DENIED) != null ) {
%>
	<jsp:forward page="/error/403.jsp" />
<%
	}
	if (exception != null) {
		Throwable t = exception;
		while (t!=null) { 
			if (t instanceof org.springframework.security.access.AccessDeniedException) {
%>
	<jsp:forward page="/error/403.jsp" />
<% 
				break;
			} else if (t instanceof org.springframework.dao.DataAccessException) {
%>
	<jsp:forward page="/error/database_error.jsp" /> 
<%
				break;
			} else if (t instanceof org.springframework.transaction.TransactionException) {
%>
	<jsp:forward page="/error/database_error.jsp" /> 
<%
				break;
			}
			t = t.getCause();
		}
	}
%>
 
<html dir="${direction}" >
<head>
	<title>${msg['errorPage.title']}</title>
</head>

<body >
	<font face="B Titr,Titr,Tahoma">
	<div id="content">
	<table width="100%">
		<tr>
			<td>
				<div align="middle">
					<img border="0" src="/portal/images/errors/500.jpg" alt="500 Error" align="middle">
					<br/>
					<div style="background-color:#CFCFCF">
						<font face="B Titr,Titr,Tahoma">
							<h3>${msg['errorPage.message']}</h3>
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

