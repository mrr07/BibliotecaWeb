package it.solving.dao.autore;

import java.util.ArrayList;
import java.util.Date;
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

	@Override
	public Set<Autore> findByExample(Autore autore) throws Exception {
		
		
		int valoriNull = 0;
		int valoriAnd = 0;
		String query = "";
		int[] array= {0,0,0,0};
		
		if(autore.getNome() == "") {
			valoriNull++;
		}
		if(autore.getCognome() == "") {
			valoriNull++;
		}
		if(autore.getData_nascita() == null) {
			valoriNull++;
		}
		if(valoriNull == 3) {
			query = "SELECT a FROM Autore a";
		}
		if(valoriNull == 0) {
			query = "SELECT a FROM Autore a WHERE a.nome like :nome "
					+ "AND a.cognome like :cognome "
					+ "AND a.data_nascita = :data_nascita ";
			for(int i=1; i<array.length; i++) {
				array[i] = 1;
			}
		}
		else if(valoriNull != 3 && valoriNull != 0){
			query = "SELECT a FROM Autore a WHERE ";
			valoriAnd=2-valoriNull;
			if(autore.getNome() != "" && valoriNull != 0) {
				query = query.concat("a.nome like :nome ");
				if(valoriAnd != 0) {
					query = query.concat("and ");
					valoriAnd--;
				}
				array[1]=1;
				
			}
			if(autore.getCognome() != "" && valoriNull != 0) {
				query = query.concat("a.cognome like :cognome ");
				if(valoriAnd != 0) {
					query = query.concat("and ");
					valoriAnd--;
				}
				array[2]=1;
			}
			if(autore.getData_nascita() != null && valoriNull != 0) {
				query = query.concat("a.data_nascita = :data_nascita ");
				if(valoriAnd != 0) {
					query = query.concat("and ");
					valoriAnd--;
				}
				array[3]=1;
			}
		}
		
		/*
		Date dataDaCercare = new Date();
		dataDaCercare = autore.getData_nascita();
		int anno = dataDaCercare.getYear()+1900;
		int mese = dataDaCercare.getMonth()+1;
		int giorno = dataDaCercare.getDate();
		
		String data = anno+"-"+mese+"-"+giorno;
		System.out.println(dataDaCercare);
		*/
		
		List<Autore> listaAutori = new ArrayList<Autore>();
		TypedQuery<Autore> query1 = entityManager.createQuery(query, Autore.class);
		
				int k=1;
				if(array[1] == 1) {
					if(autore.getNome() != "") {
						query1.setParameter("nome", "%"+autore.getNome()+"%");
					}
					array[1]=0;
					k++;
				}
				
				if(array[2] == 1) {
					if(autore.getCognome() != "") {
						query1.setParameter("cognome", "%"+autore.getCognome()+"%");
					}
					array[2]=0;
					k++;
				}
				
				if(array[3] == 1) {
					if(autore.getData_nascita() != null) {
						query1.setParameter("data_nascita", autore.getData_nascita());
					}
					k++;
					array[3]=0;
				}
				
			
			
			
			
				return query1.getResultList().stream().collect(Collectors.toSet());
		
		
	}
		
}


