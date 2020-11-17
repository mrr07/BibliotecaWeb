package it.solving.dao.autore;

import it.solving.dao.IBaseDAO;
import it.solving.model.autore.Autore;

public interface AutoreDAO extends IBaseDAO<Autore> {
	
	public boolean esistonoLibriDiAutoreX(Autore autore) throws Exception;

}
