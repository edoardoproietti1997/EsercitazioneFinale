package it.dstech.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.dstech.connection.ConnessioneDB;
import it.dstech.model.Marito;

public class RegistrazioneServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/register.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Marito marito = new Marito();
		ConnessioneDB conn = new ConnessioneDB();
		String user = (String) req.getAttribute("username");
		String psw = (String) req.getAttribute("password");
		String errore = null;
		if (conn.controlloUsername(user) == false) {
			marito.setUsername(user);
			marito.setPassword(psw);
			conn.inserisciMarito(marito);
			errore = "registrazione andata a buon fine, prego accedi";
			req.setAttribute("errore", errore);
			getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
		}
		else
		{
			errore = "username gia' esistente, per favore scegline un'altro";
			req.setAttribute("errore", errore);
			getServletContext().getRequestDispatcher("/register.jsp").forward(req, resp);
		}
	}

}
