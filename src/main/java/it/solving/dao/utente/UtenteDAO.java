package it.solving.dao.utente;

import java.util.List;

import it.solving.dao.IBaseDAO;
import it.solving.model.ruolo.Ruolo;
import it.solving.model.utente.StatoUtente;
import it.solving.model.utente.Utente;

public interface UtenteDAO extends IBaseDAO<Utente> {
	
	public List<Utente> findAllByRuolo(Ruolo ruoloInput) throws Exception;
	
	public boolean existByUsername(Utente utente) throws Exception;
	
	public Utente findByUsername(Utente utente) throws Exception;
	
	public int setStatoUtente(Utente utente, StatoUtente stato) throws Exception;
	
	public Utente findByUserEPass(String user, String pass) throws Exception;

}
