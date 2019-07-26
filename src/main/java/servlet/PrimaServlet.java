package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.dstech.connection.ConnessioneDB;
import it.dstech.model.Marito;

public class PrimaServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Marito marito = new Marito();
		ConnessioneDB conn = new ConnessioneDB();
		String parameter = req.getParameter("Username");
		if (conn.controlloUsername(parameter) == false) {
			marito.setUsername(req.getParameter("Username"));
		} else {
			getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
		}
		marito.setPassword(req.getParameter("password"));
		marito.setSaldo(0);
		boolean controllo = conn.inserisciMarito(marito);
		if (controllo != true) {
			getServletContext().getRequestDispatcher("/error.jps").forward(req, resp);
		}

	}

}
