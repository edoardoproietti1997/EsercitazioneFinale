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
			String username = req.getParameter("username");
			//prendiamo l'username che ci ervir' per un botto di roba
			
			GestioneMoglieMiglia gmm = null;
			ConnessioneDB conn = new ConnessioneDB();
			//creiamo un nuovo oggetto di gestionemogliemiglia
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
			//creiamo una lista di attivita' con tutte le attivita' del marito e una lista di stringhe dove ci andranno solo
			//il nome delle attivita' che il marito puo' compiere (dipendera' dal lvl del marito e dell'azione
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
			for (Attivita attivita : attivitaMoglie)
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
			//fino a qui gli abbiamo detto di fare quello che abbiamo descritto prima

			String azione = req.getParameter("attivita");
			//prendiamo ora l'attivia' che ha selezionato il nostro utente e diciamo tutti i vari casi che avvengono in base a quell'azione
			//errore e' una variabile abbastanza esplicativa di quel che avviene nei vari casi
			if (azione == null)
			{
				errore = "non hai selezionato nessuna azione!!";
			}
			int puntiAzione =0;
			for (Attivita attivita : attivitaMoglie)
			{
				if (attivita.getAzione().equals(azione))
				{
					puntiAzione = attivita.getPunteggio();
				}
			}
			for (Attivita attivita : attivitaMarito)
			{
				if (attivita.getAzione().equals(azione))
				{
					puntiAzione = attivita.getPunteggio();
				}
			}
			//innanzi tutto in questa piccola parte abbiamo detto a punti azione tramite un metodo di prendere il punteggio dell'azione
			//scelta dall'utente (deve pero' fare il controllo con tutte le attivita' sia della moglie che del marito
			
			int nuovoSaldo = vecchioSaldo+puntiAzione;
			if (nuovoSaldo < 0 )
			{
				errore = "non puoi compiere questa azione , non hai abbastanza punti";
			}
			else
			{
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
				try
				{
					conn.inserisciAzione(idMarito, azione);
					conn.modificaSaldo(username, nuovoSaldo);
				}
				//ora con questi 2 metodi andiamo a inserire l'azione fatta nel db e a modificare il saldo dell'utente
				catch (ClassNotFoundException | SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			req.setAttribute("saldo", nuovoSaldo);
			req.setAttribute("username", username);
			req.setAttribute("errore", errore);
			req.setAttribute("moglie", realAMoglie);
			req.setAttribute("marito", realAMarito);
			getServletContext().getRequestDispatcher("/homepage.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String username = req.getParameter("username");
		req.setAttribute("username", username);
		getServletContext().getRequestDispatcher("/bacheca.jsp").forward(req, resp);
	}
}