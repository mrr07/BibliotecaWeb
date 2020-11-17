package it.solving.service.autore;

import java.util.Set;

import it.solving.dao.autore.AutoreDAO;
import it.solving.model.autore.Autore;


public interface AutoreService {
	
	public Set<Autore> listAll() throws Exception;
	
	public Autore findByID(Long id) throws Exception;
	
	public boolean aggiorna(Autore autore) throws Exception;

	public boolean inserisciNuovo(Autore autore) throws Exception;

	public boolean rimuovi(Autore autore) throws Exception;
	
	public void setAutoreDAO(AutoreDAO autoreDAO);

}
