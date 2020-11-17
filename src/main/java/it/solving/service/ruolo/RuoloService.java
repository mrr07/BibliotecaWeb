package it.solving.service.ruolo;

import java.util.Set;

import it.solving.dao.ruolo.RuoloDAO;
import it.solving.model.ruolo.Ruolo;



public interface RuoloService {
	
	public Set<Ruolo> listAll() throws Exception;

	public Ruolo findByID(Long id) throws Exception;

	public boolean aggiorna(Ruolo ruoloInstance) throws Exception;

	public boolean inserisciNuovo(Ruolo ruoloInstance) throws Exception;

	public boolean rimuovi(Ruolo ruoloInstance) throws Exception;

	public Ruolo cercaPerDescrizioneECodice(String descrizione, String codice) throws Exception;

	// per injection
	public void setRuoloDAO(RuoloDAO ruoloDAO);
}
