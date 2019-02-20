<%@ page isErrorPage="true" language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ page import="org.springframework.dao.*"%>
<%@ page import="org.springframework.orm.ObjectRetrievalFailureException"%>
<%@ page import="org.springframework.jdbc.UncategorizedSQLException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_rt" %>

<html dir="${direction}" >
<head>
	<title>${msg['database_error.title']}</title>
</head>
<body>


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
							<h3><font color="blue">${msg['database_error.title']} : </font>${msg['database_connect_error']}
							<%
								if (exception != null) {
								Throwable t = exception;
								while (t!=null) { 
										if (t instanceof DataAccessResourceFailureException) {
										%> ${msg['database_connect_error']} <%break;}
										if (t instanceof InvalidDataAccessResourceUsageException) {
										%> ${msg['database_read_error']} <%break;}
										if (t instanceof ObjectRetrievalFailureException) {
										%> ${msg['database_read_error']} <%break;}
										if (t instanceof PermissionDeniedDataAccessException) {
										%> ${msg['database_write_error']} <%break;}
										if (t instanceof DataIntegrityViolationException) {
										%> ${msg['database_write_error']} <%break;}
										if (t instanceof UncategorizedDataAccessException) {
										%> ${msg['database_connect_error']} <%break;}
										if (t instanceof UncategorizedSQLException) {
										%> ${msg['database_connect_error']} <%break;}
										if (t instanceof org.springframework.transaction.CannotCreateTransactionException) {
										%> ${msg['database_connect_error']} <%break;}
									t = t.getCause();
								}
							}%> 
							
							</h3>
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