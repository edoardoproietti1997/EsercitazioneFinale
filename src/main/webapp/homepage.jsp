<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="java.util.List"%>
	<%@page import="it.dstech.servlets.HomepageServlet"%>
	<%@page import= "it.dstech.mogliemiglia.Attivita"%>
	<%@page import= "it.dstech.mogliemiglia.GestioneMoglieMiglia"%>
<!DOCTYPE html>
<html>
<body>

	<h2>Home Page</h2>
	<h3>benvenuto utente</h3>
	<h3>ecco i tuoi punti ---> <%=request.getAttribute ("punti") %>></h3>
	<%List<Attivita> moglie = (List<Attivita>) request.getAttribute("moglie"); %>
	<%List<Attivita> marito = (List<Attivita>) request.getAttribute("marito"); %>
	<form action="/homepage" method="GET">
	<h3>happy wife, happy life ! get your points doing:</h3>
	<% for (Attivita attivita : moglie)%>
	<%{ %>
	<input type="radio" name="attivita" value="<%=attivita%>"> <%=attivita%><br>
	<%} %>
	<h3>do it for yourself</h3>
	<% for (Attivita attivita : marito)%>
	<%{ %>
	<input type="radio" name="attivita" value="<%=attivita%>"> <%=attivita%><br>
	<%} %>
	</form>
	
	<form action="/homepage" >
		<a href="login.jsp">esci</a>
	</form>
</body>
</html>