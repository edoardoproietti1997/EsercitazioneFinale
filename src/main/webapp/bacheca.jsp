<%@page import="javax.swing.text.StyledEditorKit.ForegroundAction"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="java.util.List"%>
	<%@page import="it.dstech.servlets.HomepageServlet"%>
	<%@page import= "it.dstech.mogliemiglia.Attivita"%>
	<%@page import= "it.dstech.mogliemiglia.GestioneMoglieMiglia"%>
	<%@page import= "java.util.ArrayList"%>
	<%@page import= "it.dstech.connection.ConnessioneDB" %>		
	<html>
	<body>
	<%String username = (String)request.getAttribute("username");
	ConnessioneDB conn = new ConnessioneDB();
	int idUser = conn.prendiIdMarito(username);
	List<String> moglie =  conn.trovaAzioneStorico(idUser); %>
	<ul type = "square">
	<%for (int i = 0 ; i<moglie.size() ; i++){%>
	<li><%=moglie.get(i)%></li>
	<%}%>
	</ul>
	<form action="homepage" method ="GET" >
	<input type = "hidden" id = "username" name = "username" value = "<%=username%>"> 
	<input type = "submit" value ="torna alla home"> 
	</form>
	</body>
	</html>