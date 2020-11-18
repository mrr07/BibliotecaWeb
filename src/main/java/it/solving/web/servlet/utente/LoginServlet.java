package it.solving.web.servlet.utente;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.solving.model.utente.Utente;
import it.solving.service.MyServiceFactory;



/**
 * Servlet implementation class ExecuteGetUtenteServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//non permetto che l'utente arrivi alla servlet scrivendo l'url sulla barra degli indirizzi del browser
		HttpSession session = request.getSession();
		if(session.getAttribute("utenteInSessione") == null) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		if (user.isEmpty() || pass.isEmpty()) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		Utente utente = new Utente();
		try {
			
			utente = MyServiceFactory.getUtenteServiceInstance().findByUserPass(user, pass);
			if(utente == null) {
				request.setAttribute("errorMessage", "Attenzione username e/o password sono errati");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
		} catch (SQLException e){
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			utente = MyServiceFactory.getUtenteServiceInstance().findByUserPass(user, pass);
			if(utente == null) {
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
			
			HttpSession session = request.getSession();	
			session.setAttribute("utenteInSessione", utente);
			
		} catch (SQLException e){
			e.printStackTrace();
		} catch (Exception e) {
			
		}
		
		request.getRequestDispatcher("index.jsp").include(request, response);
		
	}

}
