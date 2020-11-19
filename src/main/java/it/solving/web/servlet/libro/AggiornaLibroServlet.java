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
import javax.servlet.http.HttpSession;

import it.solving.model.autore.Autore;
import it.solving.model.libro.Genere;
import it.solving.model.libro.Libro;
import it.solving.model.utente.Utente;
import it.solving.service.MyServiceFactory;
import it.solving.service.autore.AutoreService;
import it.solving.service.libro.LibroService;

/**
 * Servlet implementation class AggiornaLibroServlet
 */
@WebServlet("/AggiornaLibroServlet")
public class AggiornaLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiornaLibroServlet() {
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
		
		// parametri della ricerca effettuata in precedenza
		String titoloCercato = request.getParameter("titoloCercato");
		String genereCercato = request.getParameter("genereCercato");
		String tramaCercata = request.getParameter("tramaCercata");
		String autoreCercato = request.getParameter("autoreCercato");
		
		// valori del libro da aggiornare
		String idLibro = request.getParameter("LibroDaAggiornare");
		Long idLibroDaAggiornare = Long.parseLong(idLibro);
		
		String titolo = request.getParameter("titolo");
		String trama = request.getParameter("trama");
		String genere = request.getParameter("genere");
		String autore = request.getParameter("autore");
		Long idAutore = Long.parseLong(autore);
		
		if (titolo.isEmpty() || titolo.isEmpty() || genere.isEmpty() || autore == null) {
			List<String> listaGeneri = Stream.of(Genere.values()).map(Enum::name).collect(Collectors.toList());
			Set<Autore> listaAutori = new HashSet<>();
				
				try {
					listaAutori = autoreService.listAll();
					request.setAttribute("listaAutori", listaAutori);
										
				} catch(Exception e) {
					e.printStackTrace();
				}
				
				request.setAttribute("listaGeneri", listaGeneri);
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("inserisciLibro.jsp").forward(request, response);
				return;
		}
		
		
		Libro libroDaAggiornare = new Libro();
		libroDaAggiornare.setId(idLibroDaAggiornare);
		libroDaAggiornare.setTitolo(titolo);
		libroDaAggiornare.setTrama(trama);
		libroDaAggiornare.setGenere(Genere.valueOf(genere));
		try {
			libroDaAggiornare.setAutore(autoreService.findByID(idAutore));
			
			libroService.aggiorna(libroDaAggiornare);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		Long idAutore1 = Long.parseLong(autore);
		Genere genereDaInserire = Genere.valueOf(genere);
		
		Autore autoreDelLibro = null;
		try {
			autoreDelLibro = autoreService.findByID(idAutore1);
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
		
		Genere genereDaPassare;
		if(genereCercato == ""){
			genereDaPassare = Genere.EMPTY;
		} else {
			genereDaPassare = Genere.valueOf(genere);
		}
	
		Long idAutore2 = null;
		if(autoreCercato == "") {
			idAutore2 = null;
		} else {
			idAutore2 = Long.parseLong(autore);
		}
		Libro libroDaCercare = new Libro();
		Set<Libro> risultato = new HashSet<>();
		Autore autoreDaCercare = new Autore();
		try {
			if(autoreCercato == "") {
				autoreDaCercare.setId(null);
			} else {
				autoreDaCercare = autoreService.findByID(idAutore2);
			}
			libroDaCercare.setTitolo(titoloCercato);
			libroDaCercare.setGenere(genereDaPassare);
			libroDaCercare.setTrama(tramaCercata);
			libroDaCercare.setAutore(autoreDaCercare);
			
			risultato = libroService.findByExample(libroDaCercare);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("listaLibri", risultato);
		request.setAttribute("successMessage", "Operazione effettuata con successo");
		request.getRequestDispatcher("risultatoRicercaLibro.jsp").forward(request, response);
		
		
		
	}

}
