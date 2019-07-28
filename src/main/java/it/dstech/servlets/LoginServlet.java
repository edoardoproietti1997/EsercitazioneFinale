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
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter print = resp.getWriter();
		ConnessioneDB conn = new ConnessioneDB();
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
	}
}
