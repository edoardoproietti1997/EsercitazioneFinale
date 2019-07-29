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
		String errore = " ";
		req.setAttribute("error", errore);
		getServletContext().getRequestDispatcher("/register.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Marito marito = new Marito();
		PrintWriter out = resp.getWriter();
		ConnessioneDB conn = new ConnessioneDB();
		String user = req.getParameter("username");
		String psw = req.getParameter("password");
		String errore = " ";
		req.setAttribute("error", errore);
		if (conn.controlloUsername(user) == false) {
			marito.setUsername(user);
			marito.setPassword(psw);
			conn.inserisciMarito(marito);
			getServletContext().getRequestDispatcher("/homepage.jsp").forward(req, resp);
		}
		else
		{
			errore = "username gia' esistente, per favore scegline un'altro";
			req.setAttribute("error", errore);
			getServletContext().getRequestDispatcher("/register.jsp").forward(req, resp);
			req.setAttribute("username", user);
		}
	}

}
