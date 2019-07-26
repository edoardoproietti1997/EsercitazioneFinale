package it.dstech.servlets;

import java.io.IOException;

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
		String parameter = req.getParameter("username");
		
		if (conn.controlloUsername(parameter)) {
			marito.setUsername(req.getParameter("username"));
		} else {
			getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
		}
		marito.setPassword(req.getParameter("password"));
		boolean controllo = conn.inserisciMarito(marito);
		if (!controllo) {
			getServletContext().getRequestDispatcher("/error.jps").forward(req, resp);
		}

	}

}
