package it.solving.web.servlet.libro;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.solving.model.autore.Autore;
import it.solving.model.libro.Genere;
import it.solving.model.libro.Libro;
import it.solving.service.MyServiceFactory;
import it.solving.service.autore.AutoreService;
import it.solving.service.libro.LibroService;

/**
 * Servlet implementation class PreparaAggiornaLibroServlet
 */
@WebServlet("/PreparaAggiornaLibroServlet")
public class PreparaAggiornaLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreparaAggiornaLibroServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LibroService libroService = MyServiceFactory.getLibroServiceInstance();
		AutoreService autoreService = MyServiceFactory.getAutoreServiceInstance();
		
		String titoloCercato = request.getParameter("titoloCercato");
		String genereCercato = request.getParameter("genereCercato");
		String tramaCercata = request.getParameter("tramaCercata");
		String autoreCercato = request.getParameter("autoreCercato");
		
		String idLibro = request.getParameter("LibroDaAggiornare");
		Long idLibroDaAggionare = Long.parseLong(idLibro);
		
		List<String> listaGeneri = Stream.of(Genere.values()).map(Enum::name).collect(Collectors.toList());
		
		Set<Autore> listaAutori;
		try {
			listaAutori = autoreService.listAll();
			request.setAttribute("listaAutori", listaAutori);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Libro libroDaAggiornare = new Libro();
		try {
			
			libroDaAggiornare = libroService.findByID(idLibroDaAggionare);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// parametri di ricerca da passare
		request.setAttribute("titoloCercato", titoloCercato);
		request.setAttribute("tramaCercata", tramaCercata);
		request.setAttribute("genereCercato", genereCercato);
		request.setAttribute("autoreCercato", autoreCercato);

		
		request.setAttribute("LibroDaAggiornare", idLibro);
		request.setAttribute("listaGeneri", listaGeneri);
		request.setAttribute("titoloDaAggiornare", libroDaAggiornare.getTitolo());
		request.setAttribute("tramaDaAggiornare", libroDaAggiornare.getTrama());
		request.setAttribute("genereDaAggiornare", Genere.valueOf(libroDaAggiornare.getGenere()));
		request.setAttribute("autoreDaAggiornare", libroDaAggiornare.getAutore());
		
		request.getRequestDispatcher("aggiornaLibro.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
