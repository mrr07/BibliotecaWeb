package it.solving.service.libro;

import java.util.Set;

import it.solving.dao.libro.LibroDAO;
import it.solving.model.autore.Autore;
import it.solving.model.libro.Libro;

public interface LibroService {
	
public Set<Libro> listAll() throws Exception;
	
	public Libro findByID(Long id) throws Exception;
	
	public boolean aggiorna(Libro libro) throws Exception;

	public boolean inserisciNuovo(Libro libro) throws Exception;

	public boolean rimuovi(Libro libro) throws Exception;
	
	public Libro cercaLibroConAutoreETitolo(String titolo, Autore autore) throws Exception;
	
	public void setLibroDAO(LibroDAO libroDAO);
	
	public Set<Libro> findByExample(Libro libro) throws Exception;
	
}
