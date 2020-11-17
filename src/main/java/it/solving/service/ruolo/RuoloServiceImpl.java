package it.solving.service.ruolo;

import java.util.Set;

import javax.persistence.EntityManager;

import it.solving.dao.EntityManagerUtil;
import it.solving.dao.ruolo.RuoloDAO;
import it.solving.model.ruolo.Ruolo;


public class RuoloServiceImpl implements RuoloService {

	private RuoloDAO ruoloDAO;

	@Override
	public Set<Ruolo> listAll() throws Exception {
		try {
			// uso l'injection per il dao
			ruoloDAO.setEntityManager(EntityManagerUtil.getEntityManager());

			// eseguo quello che realmente devo fare
			return ruoloDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public Ruolo findByID(Long id) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			ruoloDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return ruoloDAO.get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public boolean aggiorna(Ruolo ruoloInstance) throws Exception {
		throw new Exception("Operazione non consentita");

	}

	@Override
	public boolean inserisciNuovo(Ruolo ruoloInstance) throws Exception {
		throw new Exception("Operazione non consentita");

	}

	@Override
	public boolean rimuovi(Ruolo ruoloInstance) throws Exception {
		throw new Exception("Operazione non consentita");

	}

	@Override
	public void setRuoloDAO(RuoloDAO ruoloDAO) {
		this.ruoloDAO = ruoloDAO;
	}

	@Override
	public Ruolo cercaPerDescrizioneECodice(String descrizione, String codice) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			ruoloDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return ruoloDAO.findByDescrizioneAndCodice(descrizione, codice);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

}
