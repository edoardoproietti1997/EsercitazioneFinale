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

import it.dstech.connection.ConnessioneDB;
import it.dstech.mogliemiglia.Attivita;
import it.dstech.mogliemiglia.GestioneMoglieMiglia;

public class HomepageServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		//la pagina homepage si deve aggiornare e reindirizzare ogni volta
		//gli deve dire hai guadagnato o hai perso tot punti
		//deve aggiungere al db nella parte relativa ai movimenti il movimento fatto
			//bisogna mettere una pagina collegata al bottone vedi azioni svolte fin'ora (nella jsp)
			String username = req.getParameter("username");
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
			//a questo punto gli ho passato le liste di azioni che puo' compiere in base al livello,
			//l'utente ne scegliera' una e in base ad essa ora dobbiamo fare una query di insert sul db e una query di update per il saldo
			String azione = req.getParameter("devo decidere che nome assegnare al button e qui riprende l'azione scelta dall'utente");
			int puntiAzione =0;
			for (Attivita attivita : aMoglie)
			{
				if (azione.equals(attivita.getAzione()))
				{
					puntiAzione = attivita.getLivello();
				}
			}
			for (Attivita attivita : aMarito)
			{
				if (azione.equals(attivita.getAzione()))
				{
					puntiAzione = attivita.getLivello();
				}
			}
			if (vecchioSaldo + puntiAzione < 0 )
			{
				out.println("<h3>spiacente, non hai abbastanza punti per compiere questa azione <h3>");
				getServletContext().getRequestDispatcher("/homepage.jsp").forward(req, resp);
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
//String saldo = req.getParameter("saldo");
//int vecchioSaldo = (Integer.parseInt(saldo));
//int differenza;
//
// questa parte andra' inserita dopo e servira' a controllare se l'utente ha perso o guadagnato punti '