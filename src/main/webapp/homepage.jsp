
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="java.util.List"%>
	<%@page import="it.dstech.servlets.HomepageServlet"%>
	<%@page import= "it.dstech.mogliemiglia.Attivita"%>
	<%@page import= "it.dstech.mogliemiglia.GestioneMoglieMiglia"%>
	<%@page import= "java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<body>
	<%String username = (String)request.getAttribute("username"); %>
	<h2>Home Page</h2>
	<%if (request.getAttribute("errore")!=null) { %>
	<%String error = (String)request.getAttribute("errore") ;%>
	<h2><%=error %></h2>
	<%}%>
	<h3>benvenuto <%=username%></h3>

	<h3>ecco i tuoi punti ---> <%=request.getAttribute("saldo") %>></h3>
	<%List<String> moglie = (List<String>) request.getAttribute("moglie");
	List<String> marito = (List<String>) request.getAttribute("marito"); 
	request.setAttribute("moglie", moglie);
	request.setAttribute("marito", marito);%>
	
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
	
	<form action="/homepage" method ="POST" >
	<input type = "submit" value ="guarda la tua bacheca"> 
	</form>
		

</body>
</html>