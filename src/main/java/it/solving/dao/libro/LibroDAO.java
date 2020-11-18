package it.solving.dao.libro;

import java.util.Set;

import it.solving.dao.IBaseDAO;
import it.solving.model.autore.Autore;
import it.solving.model.libro.Libro;

public interface LibroDAO extends IBaseDAO<Libro> {

	public Libro findByTitoloEAutore(String titolo, Autore autore) throws Exception;
	
	public Set<Libro> findByExample(Libro libro) throws Exception;
}
