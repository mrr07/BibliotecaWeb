package it.solving.web.servlet.autore;

import java.io.IOException;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.solving.model.autore.Autore;
import it.solving.service.MyServiceFactory;
import it.solving.service.autore.AutoreService;

/**
 * Servlet implementation class InserisciAutoreServlet
 */
@WebServlet("/InserisciAutoreServlet")
public class InserisciAutoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserisciAutoreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		 AutoreService autoreService = MyServiceFactory.getAutoreServiceInstance();
		 
		 // dati inseriti nella ricerca
		 String nomeAutoreCercato = request.getParameter("nomeAutoreCercato");
		 String cognomeAutorecercato = request.getParameter("cognomeAutoreCercato");
		 String dataNascitaAutoreCercato = request.getParameter("dataNascitaAutoreCercato");
		 
		 
		 // dati inseriti nella form
		 String nome = request.getParameter("nome");
		 String cognome = request.getParameter("cognome");
		 String dataNascita = request.getParameter("dataNascita");
		 Date data_nascita = Date.valueOf(dataNascita);
		 
		 Autore autoreDaAggiungere = new Autore(nome, cognome, dataNascita);
		 try {
			 autoreService.inserisciNuovo(autoreDaAggiungere);
			 request.setAttribute("successMessage", "Operazione effettuata con successo");
			 
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 
		 Autore autore = new Autore();
		 autore.setNome(nomeAutoreCercato);
		 autore.setCognome(cognomeAutorecercato);
		 autore.setData_nascita(dataNascitaAutoreCercato);

	     Set<Autore> listaAutori = new HashSet<>();
		 try {

			listaAutori = autoreService.findByExample(autore);

		 } catch (Exception e) {
		     e.printStackTrace();
		 }

		 request.setAttribute("listaAutori", listaAutori);
		 request.setAttribute("nomeAutoreCercato", nomeAutoreCercato);
		 request.setAttribute("cognomeAutoreCercato", cognomeAutorecercato);
		 request.setAttribute("dataNascitaAutoreCercato", dataNascitaAutoreCercato);
		 request.getRequestDispatcher("risultatoRicercaAutore.jsp").forward(request, response);
		 
		 
	}

}
