package it.solving.service.libro;

import java.util.Set;

import javax.persistence.EntityManager;

import it.solving.dao.EntityManagerUtil;
import it.solving.dao.libro.LibroDAO;
import it.solving.model.autore.Autore;
import it.solving.model.libro.Libro;

public class LibroServiceImpl implements LibroService {
	
	private LibroDAO libroDAO;

	@Override
	public Set<Libro> listAll() throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			libroDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return libroDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 

	}

	@Override
	public Libro findByID(Long id) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			libroDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return libroDAO.get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 
	}

	@Override
	public boolean aggiorna(Libro libro) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			libroDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			libroDAO.update(libro);

			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean inserisciNuovo(Libro libro) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			libroDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			if(libro.getAutore() == null) {
				System.out.println("Impossibile Inserire il Libro, Autore non presente");
				return false;
			} else {
				libroDAO.insert(libro);
			}
			

			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean rimuovi(Libro libro) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			libroDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			libroDAO.delete(libro);

			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}


	}

	@Override
	public void setLibroDAO(LibroDAO libroDAO) {
		this.libroDAO = libroDAO;

	}

	@Override
	public Libro cercaLibroConAutoreETitolo(String titolo, Autore autore) throws Exception {
		
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			libroDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return libroDAO.findByTitoloEAutore(titolo, autore);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}

	}

}
