package it.solving.dao.ruolo;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.solving.model.ruolo.Ruolo;


public class RuoloDAOImpl implements RuoloDAO {

	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Set<Ruolo> list() throws Exception {
		return entityManager.createQuery("from Ruolo",Ruolo.class).getResultList().stream().collect(Collectors.toSet());
	}

	@Override
	public Ruolo get(Long id) throws Exception {
		return entityManager.find(Ruolo.class, id);
	}

	@Override
	public boolean update(Ruolo ruoloInstance) throws Exception {
		if (ruoloInstance == null) {
			throw new Exception("Problema valore in input");
		}
		ruoloInstance = entityManager.merge(ruoloInstance);
		return true;

	}

	@Override
	public boolean insert(Ruolo ruoloInstance) throws Exception {
		if (ruoloInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(ruoloInstance);
		return true;
	

	}

	@Override
	public boolean delete(Ruolo ruoloInstance) throws Exception {
		if (ruoloInstance == null) {
			throw new Exception("Problema valore in input");
		}
		
		entityManager.remove(entityManager.merge(ruoloInstance));
		return true;
		
	}

	@Override
	public Ruolo findByDescrizioneAndCodice(String descrizione, String codice) throws Exception {
		TypedQuery<Ruolo> query = entityManager
				.createQuery("select r from Ruolo r where r.descrizione=?1 and r.codice=?2", Ruolo.class)
				.setParameter(1, descrizione)
				.setParameter(2, codice);
		
		return query.getResultStream().findFirst().orElse(null);
	}
	

}
