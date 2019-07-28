<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.OutputStreamWriter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="it.dstech.servlets.HomepageServlet"%>
<%@page import="it.dstech.MoglieMiglia.Attivita"%>
<%@page import="it.dstech.model.GestioneMoglieMiglia"%>
<!DOCTYPE html>
<html>
<body>
	<h2>Home Page</h2>
	<h3>benvenuto utente</h3>
	<h3>
		ecco i tuoi punti --->
		<%=request.getAttribute("saldo")%>>
	</h3>

	<%
		List<String> moglie = (List<String>) request.getAttribute("moglie");
	%>
	<%
		List<String> marito = (List<String>) request.getAttribute("marito");
	%>
	<form action="/homepage" method="GET">
		<h3>happy wife, happy life ! get your points doing:</h3>
		<%
			for (int i = 0; i < moglie.size(); i++) {
				PrintWriter output = response.getWriter();
				output.print("<option value='" + i + "'>'" + i + "'</option>");
				output.print(i);
			}
		%>
		<h3>do it for yourself</h3>
		
		<%
			{
				for (int i = 0; i < moglie.size(); i++) {
					PrintWriter output = response.getWriter();
					output.print("<option value= '" + i + "'>'" + i + "'</option>");
					output.print(i);
				}
			}
		%>


	</form>

	<form action="/homepage">
		<a href="login.jsp">esci</a>
	</form>
</body>
</html>