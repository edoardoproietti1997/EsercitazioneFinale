
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="java.util.List"%>
	<%@page import="it.dstech.servlets.HomepageServlet"%>
	<%@page import= "it.dstech.MoglieMiglia.Attivita"%>
	<%@page import= "it.dstech.model.GestioneMoglieMiglia"%>
<<<<<<< HEAD
	<%@page import= "java.util.ArrayList;"%>
=======
>>>>>>> branch 'Test' of https://github.com/edoardoproietti1997/EsercitazioneFinale.git
<!DOCTYPE html>
<html>
<body>
<<<<<<< HEAD
	<%String username = (String)request.getAttribute("username"); %>
	<h2>Home Page</h2>
	<h3>benvenuto <%=username%></h3>
=======
	h2>Home Page</h2>
	<h3>benvenuto utente</h3>
>>>>>>> branch 'Test' of https://github.com/edoardoproietti1997/EsercitazioneFinale.git
	<h3>ecco i tuoi punti ---> <%=request.getAttribute("saldo") %>></h3>
	<%List<String> moglie = new ArrayList<String>();%>
	<%moglie.addAll((List<String>) request.getAttribute("moglie")); %>
	<%List<String> marito = new ArrayList<String>();%>
	<%marito.addAll((List<String>) request.getAttribute("marito")); %>
	
	<form action="/homepage" method="GET">
<<<<<<< HEAD
	
=======
>>>>>>> branch 'Test' of https://github.com/edoardoproietti1997/EsercitazioneFinale.git
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
	</form>
	<form action= method = "POST">
	<input type = "submit" value ="guarda la tua bacheca"> 
	</form>
<<<<<<< HEAD
		<a href="login.jsp">esci</a>
=======
>>>>>>> branch 'Test' of https://github.com/edoardoproietti1997/EsercitazioneFinale.git
</body>
</html>