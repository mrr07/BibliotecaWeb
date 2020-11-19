package it.solving.web.servlet.libro;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.solving.model.libro.Libro;
import it.solving.service.MyServiceFactory;
import it.solving.service.libro.LibroService;

/**
 * Servlet implementation class EliminazioneLibroServlet
 */
@WebServlet("/EliminazioneLibroServlet")
public class EliminazioneLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminazioneLibroServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idLibro = request.getParameter("LibroDaEliminare");
		Long id = Long.parseLong(idLibro);
		LibroService libroService = MyServiceFactory.getLibroServiceInstance();
		Libro libroDaEliminare = new Libro();
		
		try {
			libroDaEliminare = libroService.findByID(id);
			libroService.rimuovi(libroDaEliminare);
			request.setAttribute("listaLibri", libroService.listAll() );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("successMessage", "Operazione effettuata con successo");
		request.getRequestDispatcher("VisualizzaTuttiLibri.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
