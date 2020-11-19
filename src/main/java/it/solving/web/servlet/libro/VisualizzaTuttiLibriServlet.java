package it.solving.web.servlet.libro;

import java.io.IOException;
import java.util.HashSet;
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
import it.solving.service.libro.LibroService;

/**
 * Servlet implementation class VisualizzaTuttiLibriServlet
 */
@WebServlet("/VisualizzaTuttiLibriServlet")
public class VisualizzaTuttiLibriServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaTuttiLibriServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String messaggio = request.getParameter("successMessage");
		LibroService libroService = MyServiceFactory.getLibroServiceInstance();
		Set<Libro> listaLibri = new HashSet<>();
			
			try {
				listaLibri = libroService.listAll();
									
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			
			request.setAttribute("listaLibri", listaLibri);
			request.setAttribute("successMessage", messaggio);
			request.getRequestDispatcher("VisualizzaTuttiLibri.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
