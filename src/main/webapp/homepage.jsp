<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="java.util.List"%>
	<%@page import="it.dstech.servlets.HomepageServlet"%>
	<%@page import= "it.dstech.MoglieMiglia.Attivita"%>
	<%@page import= "it.dstech.model.GestioneMoglieMiglia"%>
<!DOCTYPE html>
<html>
<body>
	h2>Home Page</h2>
	<h3>benvenuto utente</h3>
	<h3>ecco i tuoi punti ---> <%=request.getAttribute("saldo") %>></h3>
	
	<%List<String> moglie = (List<String>) request.getAttribute("moglie"); %>
	<%List<String> marito = (List<String>) request.getAttribute("marito"); %>
	<form action="/homepage" method="GET">
	<p>happy wife, happy life ! get your points doing:</p>
	<% for (String attivita : moglie) { %>
	<input type = "radio" name ="attivita"><%=attivita%>
	<%} %>
	<p>do it for yourself:</p>
	<% for (String attivita : marito) { %>
	<input type = "radio" name ="attivita"><%=attivita%>
	<%} %>
	<input type="submit" value="Submit">
	</form>
	
	<form action="/homepage" >
		<a href="login.jsp">esci</a>
	</form>
	<form action= method = "POST">
	<input type = "submit" value ="guarda la tua bacheca"> 
	</form>
</body>
</html>