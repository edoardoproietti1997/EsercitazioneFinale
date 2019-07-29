package it.dstech.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.dstech.connection.ConnessioneDB;

public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
		//perche' c'e' un doget che riporta a login e che nessuno chiama??
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		ConnessioneDB conn = new ConnessioneDB();
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
	}
}
