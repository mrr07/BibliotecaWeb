package it.solving.dao.utente;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.solving.model.ruolo.Ruolo;
import it.solving.model.utente.StatoUtente;
import it.solving.model.utente.Utente;

public class UtenteDAOImpl implements UtenteDAO {

	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Set<Utente> list() throws Exception {
		// dopo la from bisogna specificare il nome dell'oggetto (lettera maiuscola) e
		// non la tabella
		return entityManager.createQuery("from Utente",Utente.class).getResultList().stream().collect(Collectors.toSet());
	}

	@Override
	public Utente get(Long id) throws Exception {
		return entityManager.find(Utente.class, id);
	}

	@Override
	public boolean update(Utente utenteInstance) throws Exception {
		if (utenteInstance == null) {
			throw new Exception("Problema valore in input");
		}
		utenteInstance = entityManager.merge(utenteInstance);
		return true;
	}

	@Override
	public boolean insert(Utente utenteInstance) throws Exception {
		if (utenteInstance == null || existByUsername(utenteInstance)) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(utenteInstance);
		return true;
	}

	@Override
	public boolean delete(Utente utenteInstance) throws Exception {
		if (utenteInstance == null) {
			throw new Exception("Problema valore in input");
		}
		
		entityManager.remove(entityManager.merge(utenteInstance));
		return true;
	}

	// questo metodo ci torna utile per capire se possiamo rimuovere un ruolo non
	// essendo collegato ad un utente
	public List<Utente> findAllByRuolo(Ruolo ruoloInput) throws Exception{
		TypedQuery<Utente> query = entityManager.createQuery("select u FROM Utente u join u.ruoli r where r = :ruolo",Utente.class);
		query.setParameter("ruolo", ruoloInput);
		return query.getResultList();
	}
	
	
	@Override
	public boolean existByUsername(Utente utente) throws Exception {
		
		Set<Utente> listaUtenti = new HashSet<>();
		listaUtenti = list();
		
		for(Utente ut : listaUtenti) {
			if(utente.getUsername().compareTo(ut.getUsername()) == 0) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public Utente findByUsername(Utente utente) throws Exception {
		
		TypedQuery<Utente> query = entityManager.createQuery("select u FROM Utente u where u.username = ?1",Utente.class);
		query.setParameter(1, utente.getUsername());
		return query.getSingleResult();
	
		
	}

	@Override
	public int setStatoUtente(Utente utente, StatoUtente stato) throws Exception {
		
			Utente utenteDaAggiornare = findByUsername(utente);
			utenteDaAggiornare.setStato(stato);
			update(utenteDaAggiornare);
			return 1;
			
	}

	

	

}
