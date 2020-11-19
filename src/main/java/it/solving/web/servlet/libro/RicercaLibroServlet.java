package it.solving.web.servlet.libro;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

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
 * Servlet implementation class RicercaLibroServlet
 */
@WebServlet("/RicercaLibroServlet")
public class RicercaLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RicercaLibroServlet() {
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
		
		AutoreService autoreService = MyServiceFactory.getAutoreServiceInstance();
		LibroService libroService = MyServiceFactory.getLibroServiceInstance();
		
		String titolo = request.getParameter("titolo");
		String genere = request.getParameter("genere");
		String trama = request.getParameter("trama");
		String autore = request.getParameter("autore");
		Genere genereDaPassare;
		if(genere == null || genere == ""){
			genereDaPassare = Genere.EMPTY;
		} else {
			genereDaPassare = Genere.valueOf(genere);
		}
		
//		System.out.println(titolo);
//		System.out.println(genereDaPassare);
//		System.out.println(trama);
//		System.out.println(autore);
		
		Long idAutore = null;
		if(autore == null || autore == "") {
			idAutore = null;
		} else {
			idAutore = Long.parseLong(autore);
		}
		Libro libroDaCercare = new Libro();
		Set<Libro> risultato = new HashSet<>();
		Autore autoreDaCercare = new Autore();
		try {
			if(autore == null || autore == "") {
				autoreDaCercare.setId(null);
			} else {
				autoreDaCercare = autoreService.findByID(idAutore);
			}
			libroDaCercare.setTitolo(titolo);
			libroDaCercare.setGenere(genereDaPassare);
			libroDaCercare.setTrama(trama);
			libroDaCercare.setAutore(autoreDaCercare);
			
			risultato = libroService.findByExample(libroDaCercare);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// parametri di ricerca da passare
		request.setAttribute("titoloCercato", titolo);
		request.setAttribute("tramaCercata", trama);
		request.setAttribute("genereCercato", genere);
		request.setAttribute("autoreCercato", autore);
		
		
		request.setAttribute("listaLibri", risultato);
		request.getRequestDispatcher("risultatoRicercaLibro.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AutoreService autoreService = MyServiceFactory.getAutoreServiceInstance();
		LibroService libroService = MyServiceFactory.getLibroServiceInstance();
		
		String titolo = request.getParameter("titolo");
		String genere = request.getParameter("genere");
		String trama = request.getParameter("trama");
		String autore = request.getParameter("autore");
		Genere genereDaPassare;
		if(genere == null || genere == ""){
			genereDaPassare = Genere.EMPTY;
		} else {
			genereDaPassare = Genere.valueOf(genere);
		}
		
//		System.out.println(titolo);
//		System.out.println(genereDaPassare);
//		System.out.println(trama);
//		System.out.println(autore);
		
		Long idAutore = null;
		if(autore == null || autore == "") {
			idAutore = null;
		} else {
			idAutore = Long.parseLong(autore);
		}
		Libro libroDaCercare = new Libro();
		Set<Libro> risultato = new HashSet<>();
		Autore autoreDaCercare = new Autore();
		try {
			if(autore == null || autore == "") {
				autoreDaCercare.setId(null);
			} else {
				autoreDaCercare = autoreService.findByID(idAutore);
			}
			libroDaCercare.setTitolo(titolo);
			libroDaCercare.setGenere(genereDaPassare);
			libroDaCercare.setTrama(trama);
			libroDaCercare.setAutore(autoreDaCercare);
			
			risultato = libroService.findByExample(libroDaCercare);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// parametri di ricerca da passare
		request.setAttribute("titoloCercato", titolo);
		request.setAttribute("tramaCercata", trama);
		request.setAttribute("genereCercato", genere);
		request.setAttribute("autoreCercato", autore);
		
		
		request.setAttribute("listaLibri", risultato);
		request.getRequestDispatcher("risultatoRicercaLibro.jsp").forward(request, response);
		
		
	}

}
