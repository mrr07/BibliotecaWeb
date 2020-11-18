package it.solving.web.servlet.libro;

import java.io.IOException;
import java.util.ArrayList;
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
import javax.servlet.http.HttpSession;

import it.solving.model.autore.Autore;
import it.solving.model.libro.Genere;
import it.solving.model.libro.Libro;
import it.solving.model.utente.Utente;
import it.solving.service.MyServiceFactory;
import it.solving.service.autore.AutoreService;
import it.solving.service.libro.LibroService;

/**
 * Servlet implementation class InserimentoLibroServlet
 */
@WebServlet("/InserimentoLibroServlet")
public class InserimentoLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserimentoLibroServlet() {
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
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AutoreService autoreService = MyServiceFactory.getAutoreServiceInstance();
		LibroService libroService = MyServiceFactory.getLibroServiceInstance();
		
		
		String titolo = request.getParameter("titolo");
		String trama = request.getParameter("trama");
		String genere = request.getParameter("genere");
		String autore = request.getParameter("autore");
		
		if (titolo.isEmpty() || titolo.isEmpty() || genere.isEmpty() || autore == null) {
			Set<Libro> listaLibri = new HashSet<>();
			List<String> listaGeneri = Stream.of(Genere.values()).map(Enum::name).collect(Collectors.toList());
			Set<Autore> listaAutori = new HashSet<>();
				
				try {
					listaLibri = libroService.listAll();
					listaAutori = autoreService.listAll();
					request.setAttribute("listaAutori", listaAutori);
										
				} catch(Exception e) {
					e.printStackTrace();
				}
				
				
				request.setAttribute("listaLibri", listaLibri);
				request.setAttribute("listaGeneri", listaGeneri);
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("inserisciLibro.jsp").forward(request, response);
				return;
		}
		
		Long idAutore = Long.parseLong(autore);
		Genere genereDaInserire = Genere.valueOf(genere);
		
		Autore autoreDelLibro = null;
		try {
			autoreDelLibro = autoreService.findByID(idAutore);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Libro libroDaInserire = new Libro();
		
		libroDaInserire.setTitolo(titolo);
		libroDaInserire.setTrama(trama);
		libroDaInserire.setGenere(genereDaInserire);
		libroDaInserire.setAutore(autoreDelLibro);
		try {
			libroService.inserisciNuovo(libroDaInserire);
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		Set<Libro> listaLibri = new HashSet<>();
		
		try {
			listaLibri = libroService.listAll();
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
		request.setAttribute("listaLibri", listaLibri);
		request.setAttribute("successMessage", "Operazione effettuata con successo");
		request.getRequestDispatcher("VisualizzaTuttiLibri.jsp").forward(request, response);
		
		 
		 
	}

}
