package it.solving.dao.libro;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.solving.model.autore.Autore;
import it.solving.model.libro.Libro;

public class LibroDAOImpl implements LibroDAO {

	private EntityManager entityManager;
	
	@Override
	public Set<Libro> list() throws Exception {
		return entityManager.createQuery("from Libro",Libro.class).getResultList().stream().collect(Collectors.toSet());
	}

	@Override
	public Libro get(Long id) throws Exception {
		return entityManager.find(Libro.class, id);
	}

	@Override
	public boolean update(Libro libro) throws Exception {
		if (libro == null) {
			throw new Exception("Problema valore in input");
		}
		libro = entityManager.merge(libro);
		return true;

	}

	@Override
	public boolean insert(Libro libro) throws Exception {
		if (libro == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(libro);
		return true;

	}

	@Override
	public boolean delete(Libro libro) throws Exception {
		if (libro == null) {
			throw new Exception("Problema valore in input");
		}
		
		entityManager.remove(entityManager.merge(libro));
		return true;

	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;

	}

	@Override
	public Libro findByTitoloEAutore(String titolo, Autore autore) throws Exception {
		
		TypedQuery<Libro> query = entityManager.createQuery("select l FROM Libro l join l.autore a where a = ?1 and l.titolo = ?2", Libro.class);
		query.setParameter(1, autore);
		query.setParameter(2, titolo);
		
		return query.getSingleResult();
	}

}
