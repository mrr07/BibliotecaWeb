package it.solving.dao.ruolo;

import it.solving.dao.IBaseDAO;
import it.solving.model.ruolo.Ruolo;

public interface RuoloDAO extends IBaseDAO<Ruolo> {
	
	public Ruolo findByDescrizioneAndCodice(String descrizione, String codice) throws Exception;
	

}
