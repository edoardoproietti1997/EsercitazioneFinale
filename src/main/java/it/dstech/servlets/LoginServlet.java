package it.dstech.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.dstech.connection.ConnessioneDB;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
		//perche' c'e' un doget che riporta a login e che nessuno chiama??
	}

	@Override
<<<<<<< HEAD
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
=======
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter print = resp.getWriter();
>>>>>>> branch 'Test' of https://github.com/edoardoproietti1997/EsercitazioneFinale.git
		ConnessioneDB conn = new ConnessioneDB();
<<<<<<< HEAD
		String errore = null ;
		String username= req.getParameter("username");
		String passworld = req.getParameter("password");
		if (conn.controlloUsername(username)==true && conn.controlloPassword(passworld, username)==true)
		{
			getServletContext().getRequestDispatcher("/homepage.jsp").forward(req, resp);
			req.setAttribute("username", username);
		}
		else if (conn.controlloUsername(username)==false)
		{
			errore = "username non esistente, riimetti bene il campo o crea un nuovo account (tasto in basso)";
			req.setAttribute("errore", errore);
			getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
		}
		else if (conn.controlloPassword(passworld,username)==false)
		{
				errore = "passwod non corretta, reinseriscila per favore";
				req.setAttribute("errore", errore);
				getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
		}
=======
		String parameter= req.getParameter("username");
		String parameter2 = req.getParameter("password");
		boolean controllo= false;
		try {
			controllo = conn.controlloLogin(parameter, parameter2);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
			if(controllo == true) {
				req.setAttribute(parameter, parameter2);
				getServletContext().getRequestDispatcher("/homepage.jsp").forward(req, resp);
		}
			getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
			print.println("<h3>USERNAME O PASSWORD NON CORRETTI</h3>");
>>>>>>> branch 'Test' of https://github.com/edoardoproietti1997/EsercitazioneFinale.git
	}
}
