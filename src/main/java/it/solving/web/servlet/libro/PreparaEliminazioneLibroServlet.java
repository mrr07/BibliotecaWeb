package it.solving.web.servlet.libro;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.solving.model.libro.Libro;
import it.solving.model.utente.Utente;
import it.solving.service.MyServiceFactory;
import it.solving.service.autore.AutoreService;
import it.solving.service.libro.LibroService;

/**
 * Servlet implementation class PreparaEliminazioneLibroServlet
 */
@WebServlet("/PreparaEliminazioneLibroServlet")
public class PreparaEliminazioneLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreparaEliminazioneLibroServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utente utente = (Utente)session.getAttribute("utenteInSessione");
		if(utente == null) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else {
			
			String titoloCercato = request.getParameter("titoloCercato");
			String genereCercato = request.getParameter("genereCercato");
			String tramaCercata = request.getParameter("tramaCercata");
			String autoreCercato = request.getParameter("autoreCercato");
			
			String idLibro = request.getParameter("LibroDaEliminare");
			Long id = Long.parseLong(idLibro);
			
			// parametri di ricerca da passare
			request.setAttribute("titoloCercato", titoloCercato);
			request.setAttribute("tramaCercata", tramaCercata);
			request.setAttribute("genereCercato", genereCercato);
			request.setAttribute("autoreCercato", autoreCercato);
			
			request.setAttribute("LibroDaEliminare", id);
			request.getRequestDispatcher("confermaEliminazioneLibro.jsp").forward(request, response);
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
