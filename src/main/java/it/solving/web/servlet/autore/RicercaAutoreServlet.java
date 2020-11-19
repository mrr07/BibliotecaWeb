package it.solving.web.servlet.autore;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.solving.model.autore.Autore;
import it.solving.model.utente.Utente;
import it.solving.service.MyServiceFactory;
import it.solving.service.autore.AutoreService;

/**
 * Servlet implementation class RicercaAutoreServlet
 */
@WebServlet("/RicercaAutoreServlet")
public class RicercaAutoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RicercaAutoreServlet() {
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
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String dataNascita = request.getParameter("dataNascita");
		
		System.out.println(nome);
		System.out.println(cognome);
		System.out.println(dataNascita);
		
		Autore autore = new Autore();
		autore.setNome(nome);
		autore.setCognome(cognome);
		autore.setData_nascita(dataNascita);
		
		Set<Autore> listaAutori = new HashSet<>();
		try {
			
			listaAutori = autoreService.findByExample(autore);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		request.setAttribute("listaAutori", listaAutori);
		request.setAttribute("nomeAutoreCercato", nome);
		request.setAttribute("cognomeAutoreCercato", cognome);
		request.setAttribute("dataNascitaAutoreCercato", dataNascita);
		request.getRequestDispatcher("risultatoRicercaAutore.jsp").forward(request, response);
		
		
			
		
		
	}

}
