package it.solving.service.utente;


import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import it.solving.dao.EntityManagerUtil;
import it.solving.dao.utente.UtenteDAO;
import it.solving.model.ruolo.Ruolo;
import it.solving.model.utente.StatoUtente;
import it.solving.model.utente.Utente;



public class UtenteServiceImpl implements UtenteService {

	private UtenteDAO utenteDAO;

	@Override
	public void setUtenteDAO(UtenteDAO utenteDAO) {
		this.utenteDAO = utenteDAO;
	}

	@Override
	public Set<Utente> listAll() throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return utenteDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public Utente findByID(Long id) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return utenteDAO.get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public boolean aggiorna(Utente utenteInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			utenteDAO.update(utenteInstance);

			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean inserisciNuovo(Utente utenteInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);
			
			
			if(utenteInstance.getRuoli() == null || utenteInstance.getRuoli().size() == 0) {
				System.out.println("IMPOSSIBILE INSERIRE L'UTENTE, NESSUN RUOLO SETTATO PER L'UTENTE");
				return false;
			} else {
				utenteDAO.insert(utenteInstance);
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
	public boolean rimuovi(Utente utenteInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			utenteDAO.delete(utenteInstance);

			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean aggiungiRuolo(Utente utenteEsistente, Ruolo ruoloInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);

			// 'attacco' alla sessione di hibernate i due oggetti
			// così jpa capisce che se è già presente quel ruolo non deve essere inserito
			utenteEsistente = entityManager.merge(utenteEsistente);
			ruoloInstance = entityManager.merge(ruoloInstance);
			
			utenteEsistente.getRuoli().add(ruoloInstance);
			//l'update non viene richiamato a mano in quanto 
			//risulta automatico, infatti il contesto di persistenza
			//rileva che utenteEsistente ora è dirty vale a dire che una sua
			//proprieta ha subito una modifica (vale anche per i Set ovviamente)

			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}

	}
	
	@Override
	public boolean rimuoviRuolo(Utente utenteEsistente, Ruolo ruoloInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);

			// 'attacco' alla sessione di hibernate i due oggetti
			// così jpa capisce che se è già presente quel ruolo non deve essere inserito
			utenteEsistente = entityManager.merge(utenteEsistente);
			ruoloInstance = entityManager.merge(ruoloInstance);
			
			utenteEsistente.getRuoli().remove(ruoloInstance);
			//l'update non viene richiamato a mano in quanto 
			//risulta automatico, infatti il contesto di persistenza
			//rileva che utenteEsistente ora è dirty vale a dire che una sua
			//proprieta ha subito una modifica (vale anche per i Set ovviamente)

			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}

	}
	
	@Override
	public boolean esisteUtenteConUsername(Utente utente) throws Exception{
		
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		
		try {
			utenteDAO.setEntityManager(entityManager);
			
			if(utenteDAO.existByUsername(utente)) {
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public Set<Utente> listAllByRuolo(Ruolo ruolo) throws Exception{
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return utenteDAO.findAllByRuolo(ruolo).stream().collect(Collectors.toSet());

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}
	
	@Override
	public Utente findByUsername(Utente utente) throws Exception{
		
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);
			
			// eseguo quello che realmente devo fare
			return utenteDAO.findByUsername(utente);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public int setStatoUtente(Utente utente, StatoUtente stato) throws Exception {
			// questo è come una connection
			EntityManager entityManager = EntityManagerUtil.getEntityManager();

			try {
				// questo è come il MyConnection.getConnection()
				entityManager.getTransaction().begin();

				// uso l'injection per il dao
				utenteDAO.setEntityManager(entityManager);

				// eseguo quello che realmente devo fare
				utenteDAO.setStatoUtente(utente, stato);

				entityManager.getTransaction().commit();
				return 1;
			} catch (Exception e) {
				entityManager.getTransaction().rollback();
				e.printStackTrace();
				throw e;
			}

		}
	}

