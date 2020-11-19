package it.solving.service.autore;

import java.util.Set;

import javax.persistence.EntityManager;

import it.solving.dao.EntityManagerUtil;
import it.solving.dao.autore.AutoreDAO;
import it.solving.model.autore.Autore;

public class AutoreServiceImpl implements AutoreService {

	private AutoreDAO autoreDAO;

	@Override
	public Set<Autore> listAll() throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			autoreDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return autoreDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 
	}

	@Override
	public Autore findByID(Long id) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			autoreDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return autoreDAO.get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 
	}

	@Override
	public boolean aggiorna(Autore autore) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			autoreDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			autoreDAO.update(autore);

			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean inserisciNuovo(Autore autore) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			autoreDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			
			autoreDAO.insert(autore);
			

			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean rimuovi(Autore autore) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			autoreDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			if(!autoreDAO.esistonoLibriDiAutoreX(autore)) {
				autoreDAO.delete(autore);
			} else {
				System.out.println("IMPOSSIBILE ELIMINARE L'AUTORE!!\n");
				return false;
			}
			

			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			System.out.println("IMPOSSIBILE ELIMINARE L'AUTORE!!\n");
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public void setAutoreDAO(AutoreDAO autoreDAO) {
		this.autoreDAO = autoreDAO;

	}

	@Override
	public Set<Autore> findByExample(Autore autore) throws Exception {
	
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			autoreDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return autoreDAO.findByExample(autore);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

}
