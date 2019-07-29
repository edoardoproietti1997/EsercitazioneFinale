package it.dstech.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.dstech.MoglieMiglia.Attivita;
import it.dstech.connection.ConnessioneDB;
import it.dstech.model.GestioneMoglieMiglia;


public class HomepageServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{

			String username = req.getParameter("username");
			System.out.println(username);
			GestioneMoglieMiglia gmm = null;
			ConnessioneDB conn = new ConnessioneDB();
			int vecchioSaldo = 0;
			PrintWriter out= resp.getWriter();
			try
			{
				vecchioSaldo = conn.prendiPunti(username);
			}
			catch (ClassNotFoundException | SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("saldo", vecchioSaldo);
			try
			{
				gmm = new GestioneMoglieMiglia();
			}
			catch (URISyntaxException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
<<<<<<< HEAD
			List<Attivita> attivitaMarito = gmm.getListaAzioniMarito();
			List<Attivita> attivitaMoglie = gmm.getListaAzioniMoglie();
=======
		List<Attivita> attivitaMarito = gmm.getListaAzioniMarito();
		List<Attivita> attivitaMoglie = gmm.getListaAzioniMoglie();
>>>>>>> branch 'Test' of https://github.com/edoardoproietti1997/EsercitazioneFinale.git
		
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
			req.setAttribute("moglie", realAMoglie);
			req.setAttribute("marito", realAMarito);

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
				getServletContext().getRequestDispatcher("/homepage.jsp").forward(req, resp);
				out.println("<h3>spiacente, non hai abbastanza punti per compiere questa azione <h3>");
			}
			else
			{
				int nuovoSaldo = vecchioSaldo+puntiAzione;
				int differenza ;
				if (vecchioSaldo>nuovoSaldo)
				{
						differenza = vecchioSaldo-nuovoSaldo;
						out.println("<h3>hai perso <b>"+differenza+"punti</b></h3>");
				}
				else if (vecchioSaldo<nuovoSaldo)
				{
						differenza = nuovoSaldo-vecchioSaldo;
						out.println("<h3>hai guadagnato <b>"+differenza+"punti</b></h3>");
				}
				String id;
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
				req.setAttribute("username", username);
				getServletContext().getRequestDispatcher("/homepage.jsp").forward(req, resp);
			}
	}
}