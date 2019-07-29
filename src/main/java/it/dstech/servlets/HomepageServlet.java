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
			String errore = null;
			String username = (String)req.getAttribute("username");
			GestioneMoglieMiglia gmm = null;
			ConnessioneDB conn = new ConnessioneDB();
			int vecchioSaldo = 0;
			try
			{
				vecchioSaldo = conn.prendiPunti(username);
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
			List<Attivita> attivitaMarito = gmm.getListaAzioniMarito();
			List<Attivita> attivitaMoglie = gmm.getListaAzioniMoglie();
			List<String> realAMoglie = new ArrayList<String>();
			List<String> realAMarito = new ArrayList<String>();
			int livello = 0;
			int idMarito = 0;
			try
			{
				idMarito = conn.prendiIdMarito(username);
				livello = conn.calcolaLivello(idMarito);
			}
			catch (ClassNotFoundException | SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (Attivita attivita : attivitaMarito)
			{
				if(attivita.getLivello()<=livello)
				{
					realAMoglie.add(attivita.getAzione());
				}
			}
			for (Attivita attivita : attivitaMarito)
			{
				if(attivita.getLivello()<=livello)
				{
					realAMarito.add(attivita.getAzione());
				}
			}
		

			String azione = req.getParameter("attivita");
			int puntiAzione =0;
			for (Attivita attivita : attivitaMoglie)
			{
				if (azione.equals(attivita.getAzione()))
				{
					puntiAzione = attivita.getPunteggio();
				}
			}
			for (Attivita attivita : attivitaMoglie)
			{
				if (azione.equals(attivita.getAzione()))
				{
					puntiAzione = attivita.getPunteggio();
				}
			}
			
			
			
			if (vecchioSaldo + puntiAzione < 0 )
			{
				errore = "non puoi compiere questa azione , non hai abbastanza punti";
			}
			else
			{
				int nuovoSaldo = vecchioSaldo+puntiAzione;
				int differenza ;
				if (vecchioSaldo>nuovoSaldo)
				{
						differenza = vecchioSaldo-nuovoSaldo;
						errore = "ben fatto! goditi il tuo tempo, (hai perso)"+differenza+"punti";
				}
				else if (vecchioSaldo<nuovoSaldo)
				{
						differenza = nuovoSaldo-vecchioSaldo;
						errore = "ben fatto! vedrai i tuoi sforzi non saranno vani (hai guadagnato)"+differenza+"punti";
				}
				int id;
				try
				{
					id = conn.prendiIdMarito(username);
					conn.inserisciAzione(id, azione);
					conn.modificaSaldo(username, nuovoSaldo);
				}
				catch (ClassNotFoundException | SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			req.setAttribute("saldo", vecchioSaldo);
			req.setAttribute("username", username);
			req.setAttribute("errore", errore);
			req.setAttribute("moglie", realAMoglie);
			req.setAttribute("marito", realAMarito);
			req.setAttribute("username", username);
			getServletContext().getRequestDispatcher("/homepage.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String username = (String) req.getAttribute("username");
		req.setAttribute("username", username);
		getServletContext().getRequestDispatcher("/bacheca.jsp");
		
	}
}