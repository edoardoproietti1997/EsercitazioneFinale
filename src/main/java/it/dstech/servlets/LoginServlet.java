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

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//perche' c'e' un doget che riporta a login e che nessuno chiama??
		getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
	}

	@Override

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ConnessioneDB conn = new ConnessioneDB();
		String errore = null ;
		String username= req.getParameter("username");
		String passworld = req.getParameter("password");
		if (conn.controlloUsername(username)&& conn.controlloPassword(passworld, username))
		{
			int vecchioSaldo = 0;
			GestioneMoglieMiglia gmm = null;
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
			req.setAttribute("saldo", vecchioSaldo);
			req.setAttribute("username", username);
			req.setAttribute("errore", errore);
			req.setAttribute("moglie", realAMoglie);
			req.setAttribute("marito", realAMarito);
			getServletContext().getRequestDispatcher("/homepage.jsp").forward(req, resp);
		}
		else if (!conn.controlloUsername(username))
		{
			errore = "username non esistente, rimetti bene il campo o crea un nuovo account (tasto in basso)";
			req.setAttribute("errore", errore);
			getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
		}
		else if (!conn.controlloPassword(passworld,username))
		{
				errore = "passwod non corretta, reinseriscila per favore";
				req.setAttribute("errore", errore);
				getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
		}
	}
}
