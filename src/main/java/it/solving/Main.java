package it.solving;

import java.util.HashSet;
import java.util.ListResourceBundle;
import java.util.Set;


import it.solving.dao.EntityManagerUtil;
import it.solving.model.autore.Autore;
import it.solving.model.libro.Genere;
import it.solving.model.libro.Libro;
import it.solving.model.ruolo.Ruolo;
import it.solving.model.utente.StatoUtente;
import it.solving.model.utente.Utente;
import it.solving.service.MyServiceFactory;
import it.solving.service.autore.AutoreService;
import it.solving.service.libro.LibroService;
import it.solving.service.ruolo.RuoloService;
import it.solving.service.utente.UtenteService;

public class Main {
	
	public static void main(String[] args) {
		UtenteService utenteService = MyServiceFactory.getUtenteServiceInstance();
		RuoloService ruoloService = MyServiceFactory.getRuoloServiceInstance();
		AutoreService autoreService = MyServiceFactory.getAutoreServiceInstance();
		LibroService libroService = MyServiceFactory.getLibroServiceInstance();
		
		
		try {
			
			// ------------------ CREO GLI AUTORI -------------------------
			Autore autore1 = new Autore("mario", "rossi", "1975-03-12");
			Autore autore2 = new Autore("federica", "verdi", "1980-09-02");
			Autore autore3 = new Autore("Julio", "Farias", "1981-08-04");
			Autore autore4 = new Autore("Julio", "Farias", "1981-08-04");
			
			
			// ------------------ CREO I LIBRI ----------------------------
			Libro libro1 = new Libro("titolo1", Genere.Avventura, "trama1", autore1);
			Libro libro2 = new Libro("titolo2", Genere.Azione, "trama1", autore1);
			Libro libro3 = new Libro("titolo3", Genere.Fantascienza, "trama1", autore1);
			Libro libro4 = new Libro("titolo4", Genere.Storico, "trama1", autore2);
			Libro libro5 = new Libro("titolo5", Genere.Storico, "trama1", autore2);
			Libro libro6 = new Libro("titolo6", Genere.Thriller, "trama1", autore3);
			
			
			// ------------------- CREO I RUOLI --------------------------
			
			/*
			 * I RUOLI SONO STATI CREATI DIRETTAMENTE DA DB
			 */
			
			// mi ricavo i ruoli dal DB
			Ruolo ruoloDaDB1 = ruoloService.findByID(1L);
			Ruolo ruoloDaDB2 = ruoloService.findByID(2L);
			Ruolo ruoloDaDB3 = ruoloService.findByID(3L);
			
			
			// ------------------ CREAZIONE UTENTI ----------------------------------
			Utente utente1 = new Utente("Paolo", "Bosi", "paolo.bosi", "pass1", StatoUtente.ATTIVO);
			Utente utente2 = new Utente("Maria", "Forte", "maria.forte", "pass2", StatoUtente.ATTIVO);
			Utente utente3 = new Utente("Mario", "Foti", "mario.foti", "pass3", StatoUtente.ATTIVO);
			
			
			// ---------------- ASSOCIAZIONE RUOLI AGLI UTENTI ----------------------
			
			Set<Ruolo> listaRuoliUtente1 = new HashSet<Ruolo>();
			Set<Ruolo> listaRuoliUtente2 = new HashSet<Ruolo>();
			Set<Ruolo> listaRuoliUtente3 = new HashSet<Ruolo>();
			
			listaRuoliUtente1.add(ruoloDaDB1);
			listaRuoliUtente1.add(ruoloDaDB2);
			listaRuoliUtente2.add(ruoloDaDB2);
			listaRuoliUtente2.add(ruoloDaDB1);
			listaRuoliUtente3.add(ruoloDaDB1);
			
			utente1.setRuoli(listaRuoliUtente1);
			utente2.setRuoli(listaRuoliUtente2);
			utente3.setRuoli(listaRuoliUtente3);
			
			
			// ------------------ ASSOCIO I LIBRI AGLI AUTORI ---------------------
			Set<Libro> listaLibriAutore1 = new HashSet<Libro>();
			Set<Libro> listaLibriAutore2 = new HashSet<Libro>();
			Set<Libro> listaLibriAutore3 = new HashSet<Libro>();
			
			listaLibriAutore1.add(libro1);
			listaLibriAutore1.add(libro2);
			listaLibriAutore1.add(libro3);
			listaLibriAutore2.add(libro4);
			listaLibriAutore2.add(libro5);
			listaLibriAutore3.add(libro6);
			
			autore1.setLibri(listaLibriAutore1);
			autore2.setLibri(listaLibriAutore2);
			autore3.setLibri(listaLibriAutore3);
			
			
			// ---------------------INSERISCO GLI AUTORI, I LIBRI E GLI UTENTI NEL DB ----------------------
			/*
			autoreService.inserisciNuovo(autore1);
			autoreService.inserisciNuovo(autore2);
			autoreService.inserisciNuovo(autore3);
			*/
			
			/*
			libroService.inserisciNuovo(libro1);
			libroService.inserisciNuovo(libro2);
			libroService.inserisciNuovo(libro3);
			libroService.inserisciNuovo(libro4);
			libroService.inserisciNuovo(libro5);
			libroService.inserisciNuovo(libro6);
			*/
			
			/*
			utenteService.inserisciNuovo(utente1);
			utenteService.inserisciNuovo(utente2);
			utenteService.inserisciNuovo(utente3);
			*/
			
			// --------------------- RICERCO GLI AUTORI CON GLI ID ------------------------
			Autore autoreDaDB1 = autoreService.findByID(1L);
			Autore autoreDaDB2 = autoreService.findByID(2L);
			Autore autoreDaDB3 = autoreService.findByID(3L);
			
			Libro libroDaDB = libroService.findByID(1L);
			
			for(Libro l : libroService.findByExample(libroDaDB)) {
				System.out.println(l);
			}
			
			
			
			
			
			
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			EntityManagerUtil.shutdown();
		}
		
	}
	

}
