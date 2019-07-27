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
			//bisogna mettere una pagina collegata al bottone
			String username = req.getParameter("username");
			String vecchioSaldo = (String)(req.getAttribute("saldo"));
			GestioneMoglieMiglia gmm = null;
			ConnessioneDB conn = new ConnessioneDB();
			int nuovosaldo = 0;
			try
			{
				nuovosaldo = conn.prendiPunti(req.getParameter("username"));
			}
			catch (ClassNotFoundException | SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("punti", nuovosaldo);
			try
			{
				gmm = new GestioneMoglieMiglia();
			}
			if (vecchioSaldo < nuovoSaldo)
			catch (URISyntaxException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<Attivita> aMarito = gmm.getListaAzioniMarito();
			List<Attivita> aMoglie = gmm.getListaAzioniMoglie();
			List<String> realAMoglie = new ArrayList<String>();
			List<String> realAMarito = new ArrayList<String>();
			int livello = 0;
			try
			{
				livello = conn.calcolaLivello(username);
			}
			catch (ClassNotFoundException | SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (Attivita attivita : aMoglie)
			{
				if(attivita.getLivello()<=livello)
				{
					
					realAMoglie.add(attivita.getAzione());
				}
			}
			for (Attivita attivita : aMarito)
			{
				if(attivita.getLivello()<=livello)
				{
					realAMarito.add(attivita.getAzione());
				}
			}
			req.setAttribute("moglie", realAMoglie);
			req.setAttribute("marito", realAMarito);
	}
}
