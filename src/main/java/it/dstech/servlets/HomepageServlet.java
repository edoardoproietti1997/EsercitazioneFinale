package it.dstech.servlets;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.dstech.connection.ConnessioneDB;
import it.dstech.mogliemiglia.Attivita;
import it.dstech.mogliemiglia.GestioneMoglieMiglia;

public class HomepageServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
			GestioneMoglieMiglia gmm = null;
			ConnessioneDB conn = new ConnessioneDB();
			try
			{
				int saldo = conn.prendiPunti(req.getParameter("username"));
			}
			catch (ClassNotFoundException | SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try
			{
				gmm = new GestioneMoglieMiglia();
			}
			catch (URISyntaxException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<Attivita> aMarito = gmm.getListaAzioniMarito();
			List<Attivita> aMoglie = gmm.getListaAzioniMoglie();
			List<Attivita> realAMoglie = new ArrayList<Attivita>();
			List<Attivita> realAMarito = new ArrayList<Attivita>();
			//mi servira' un metodo che controlla il livello della persona che e' entrata
			//sara tipo select count (id username) from db.tabellacontutteleoperazioni, in tal modo conteremo tutte le operazioni svolte
			//la parte intera del risultato della query+1 ci dira' il livello dell'utente, questa andra' messa in un int livello
			int livello = 1;//poi bisogna levare l'1 e mettere tipo metodo;
			for (Attivita attivita : aMoglie)
			{
				if(attivita.getLivello()<=livello)
				{
					realAMoglie.add(attivita);
				}
			}
			for (Attivita attivita : aMarito)
			{
				if(attivita.getLivello()<=livello)
				{
					realAMarito.add(attivita);
				}
			}
			req.setAttribute("moglie", realAMoglie);
			req.setAttribute("marito", realAMarito);
			
		
	}
}
