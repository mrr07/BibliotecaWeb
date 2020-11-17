package it.solving.dao;

import it.solving.dao.autore.AutoreDAO;
import it.solving.dao.autore.AutoreDAOImpl;
import it.solving.dao.libro.LibroDAO;
import it.solving.dao.libro.LibroDAOImpl;
import it.solving.dao.ruolo.RuoloDAO;
import it.solving.dao.ruolo.RuoloDAOImpl;
import it.solving.dao.utente.UtenteDAO;
import it.solving.dao.utente.UtenteDAOImpl;

public class MyDAOFactory {

	// rendiamo questo factory SINGLETON
	private static UtenteDAO UTENTE_DAO_INSTANCE = null;
	private static RuoloDAO RUOLO_DAO_INSTANCE = null;
	private static AutoreDAO AUTORE_DAO_INSTANCE = null;
	private static LibroDAO LIBRO_DAO_INSTANCE = null;

	public static UtenteDAO getUtenteDAOInstance() {
		if (UTENTE_DAO_INSTANCE == null)
			UTENTE_DAO_INSTANCE = new UtenteDAOImpl();
		return UTENTE_DAO_INSTANCE;
	}

	public static RuoloDAO getRuoloDAOInstance() {
		if (RUOLO_DAO_INSTANCE == null)
			RUOLO_DAO_INSTANCE = new RuoloDAOImpl();
		return RUOLO_DAO_INSTANCE;
	}
	
	public static AutoreDAO getAutoreDAOInstance() {
		if (AUTORE_DAO_INSTANCE == null)
			AUTORE_DAO_INSTANCE = new AutoreDAOImpl();
		return AUTORE_DAO_INSTANCE;
	}
	
	public static LibroDAO getLibroDAOInstance() {
		if (LIBRO_DAO_INSTANCE == null)
			LIBRO_DAO_INSTANCE = new LibroDAOImpl();
		return LIBRO_DAO_INSTANCE;
	}

}
