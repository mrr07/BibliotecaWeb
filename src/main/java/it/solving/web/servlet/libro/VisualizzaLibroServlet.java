package it.solving.web.servlet.libro;

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
 * Servlet implementation class VisualizzaLibroServlet
 */
@WebServlet("/VisualizzaLibroServlet")
public class VisualizzaLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaLibroServlet() {
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
			String titoloCercato = request.getParameter("titoloCercato");
			String genereCercato = request.getParameter("genereCercato");
			String tramaCercata = request.getParameter("tramaCercata");
			String autoreCercato = request.getParameter("autoreCercato");
			
			String titoloDaVisualizzare = request.getParameter("titoloDaVisualizzare");
			String autoreDaVisualizzare = request.getParameter("autoreDaVisualizzare");
			String genereDaVisualizzare = request.getParameter("genereDaVisualizzare");
			String tramaDaVisualizzare = request.getParameter("tramaDaVisualizzare");
			
			String idLibro = request.getParameter("LibroDaVisualizzare");
			
			
			
			request.setAttribute("LibroDaVisualizzare", idLibro);
			request.setAttribute("titoloDaVisualizzare", titoloDaVisualizzare);
			request.setAttribute("genereDaVisualizzare", genereDaVisualizzare);
			request.setAttribute("tramaDaVisualizzare", tramaDaVisualizzare);
			request.setAttribute("autoreDaVisualizzare", autoreDaVisualizzare);
			
			
			request.setAttribute("titoloCercato", titoloCercato);
			request.setAttribute("tramaCercata", tramaCercata);
			request.setAttribute("genereCercato", genereCercato);
			request.setAttribute("autoreCercato", autoreCercato);
			
			request.getRequestDispatcher("visualizzaLibro.jsp").forward(request, response);
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
