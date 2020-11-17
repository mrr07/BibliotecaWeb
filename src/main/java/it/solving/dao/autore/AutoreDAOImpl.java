package it.solving.dao.autore;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.solving.model.autore.Autore;
import it.solving.model.libro.Libro;

public class AutoreDAOImpl implements AutoreDAO {

	private EntityManager entityManager;
	
	@Override
	public Set<Autore> list() throws Exception {
		return entityManager.createQuery("from Autore",Autore.class).getResultList().stream().collect(Collectors.toSet());
	}

	@Override
	public Autore get(Long id) throws Exception {
		return entityManager.find(Autore.class, id);
	}

	@Override
	public boolean update(Autore autore) throws Exception {
		if (autore == null) {
			throw new Exception("Problema valore in input");
		}
		autore = entityManager.merge(autore);
		return true;
	}

	@Override
	public boolean insert(Autore autore) throws Exception {
		if (autore == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(autore);
		return true;

	}

	@Override
	public boolean delete(Autore autore) throws Exception {
		if (autore == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(autore));
		return true;

	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;

	}

	@Override
	public boolean esistonoLibriDiAutoreX(Autore autore) throws Exception {
		
		TypedQuery<Libro> query = entityManager.createQuery("select l FROM Libro l join l.autore a where a = ?1", Libro.class);
		query.setParameter(1, autore);
		
		List<Libro> listaLibri = query.getResultList();
		
		if(listaLibri.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

}
