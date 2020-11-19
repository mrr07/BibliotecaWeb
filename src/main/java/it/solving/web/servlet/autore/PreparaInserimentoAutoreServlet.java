package it.solving.web.servlet.autore;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.solving.model.utente.Utente;
import it.solving.service.MyServiceFactory;
import it.solving.service.autore.AutoreService;

/**
 * Servlet implementation class PreparaInserimentoAutoreServlet
 */
@WebServlet("/PreparaInserimentoAutoreServlet")
public class PreparaInserimentoAutoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreparaInserimentoAutoreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		AutoreService autoreService = MyServiceFactory.getAutoreServiceInstance();
		Utente utente = (Utente)session.getAttribute("utenteInSessione");
		if(utente == null) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else {
			
			String nome = request.getParameter("nomeAutoreCercato");
			String cognome = request.getParameter("cognomeAutoreCercato");
			String dataNascita = request.getParameter("dataNascitaAutoreCercato");
			
			request.setAttribute("nomeAutoreCercato", nome);
			request.setAttribute("cognomeAutoreCercato", cognome);
			request.setAttribute("dataNascitaAutoreCercato", dataNascita);
			request.getRequestDispatcher("inserisciAutore.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
