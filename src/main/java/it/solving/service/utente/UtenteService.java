package it.solving.service.utente;

import java.util.Set;

import it.solving.dao.utente.UtenteDAO;
import it.solving.model.ruolo.Ruolo;
import it.solving.model.utente.StatoUtente;
import it.solving.model.utente.Utente;



public interface UtenteService  {
	
	public Set<Utente> listAll() throws Exception;

	public Utente findByID(Long id) throws Exception;

	public boolean aggiorna(Utente utenteInstance) throws Exception;

	public boolean inserisciNuovo(Utente utenteInstance) throws Exception;

	public boolean rimuovi(Utente utenteInstance) throws Exception;
	
	public boolean aggiungiRuolo(Utente utenteEsistente, Ruolo ruoloInstance) throws Exception;
	
	public boolean rimuoviRuolo(Utente utenteEsistente, Ruolo ruoloInstance) throws Exception;
	
	public boolean esisteUtenteConUsername(Utente utente) throws Exception;
	
	public Set<Utente> listAllByRuolo(Ruolo ruolo) throws Exception;
 
	public Utente findByUsername(Utente utente) throws Exception;
	
	public int setStatoUtente(Utente utente, StatoUtente stato) throws Exception;
	
	//per injection
	public void setUtenteDAO(UtenteDAO utenteDAO);

	public Utente findByUserPass(String user, String pass) throws Exception;

}
