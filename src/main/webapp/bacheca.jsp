<%@page import="javax.swing.text.StyledEditorKit.ForegroundAction"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="java.util.List"%>
	<%@page import="it.dstech.servlets.HomepageServlet"%>
	<%@page import= "it.dstech.mogliemiglia.Attivita"%>
	<%@page import= "it.dstech.mogliemiglia.GestioneMoglieMiglia"%>
	<%@page import= "java.util.ArrayList"%>
	<%@page import= "it.dstech.connection.ConnessioneDB" %>		
		
	<%{String marito = request.getParameter("username");
	ConnessioneDB conn = new ConnessioneDB();
	int idUser = conn.prendiIdMarito(marito);
	List<String> moglie =  conn.trovaAzioneStorico(idUser); 
	for (int i = 0 ; i<moglie.size() ; i++){%>
	<%=moglie.get(i) %>
	<%} }%>
<a href="homepage.jsp">torna alla home</a>