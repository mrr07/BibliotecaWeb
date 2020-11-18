package it.solving.dao.libro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.solving.model.autore.Autore;
import it.solving.model.libro.Genere;
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

	@Override
	public Set<Libro> findByExample(Libro libro) throws Exception {
		
//		Autore autore = libro.getAutore();
//		Genere genere = Genere.valueOf(libro.getGenere());
//		String query = "SELECT l FROM Libro l join fetch l.autore a WHERE l.titolo like ?1 "
//				+ "AND l.trama like ?2 "
//				+ "AND l.genere like ?3 "
//				+ "AND l.autore = :autore ";
//		
//		
//		
//		TypedQuery<Libro> query1 = entityManager.createQuery(query, Libro.class);
//		
//		
//		
//		query1.setParameter(1, "%"+libro.getTitolo()+"%");
//		query1.setParameter(2, "%"+libro.getTrama()+"%");
//		query1.setParameter(3, "%"+genere.toString()+"%");
//		//if(autore.getId() == null) {
//			query1.setParameter("autore", libro.getAutore());
//		//} 
//		//else {
//			//query1.setParameter(4, "= "+autore.getId());
//		//}
//		
//		
//		return query1.getResultList().stream().collect(Collectors.toSet());
		
		
		
		
		int valoriNull = 0;
		int valoriAnd = 0;
		String query = "";
		int[] array= {0,0,0,0,0};
		
		if(libro.getTitolo() == "") {
			valoriNull++;
		}
		if(libro.getTrama() == "") {
			valoriNull++;
		}
		if(libro.getGenere() == "") {
			valoriNull++;
		}
		if(libro.getAutore().getId() == null) {
			valoriNull++;
		}
		if(valoriNull == 4) {
			query = "SELECT l FROM Libro l";
		}
		if(valoriNull == 0) {
			query = "SELECT l FROM Libro l WHERE l.titolo like :titolo "
					+ "AND l.trama like :trama "
					+ "AND l.genere like :genere "
					+ "AND l.autore = :autore ";
		}
		else if(valoriNull != 4 && valoriNull != 0){
			query = "SELECT l FROM Libro l WHERE ";
			valoriAnd=3-valoriNull;
			if(libro.getTitolo() != "" && valoriNull != 0) {
				query = query.concat("l.titolo like :titolo ");
				if(valoriAnd != 0) {
					query = query.concat("and ");
					valoriAnd--;
				}
				array[1]=1;
				
			}
			if(libro.getTrama() != "" && valoriNull != 0) {
				query = query.concat("l.trama like :trama ");
				if(valoriAnd != 0) {
					query = query.concat("and ");
					valoriAnd--;
				}
				array[2]=1;
			}
			if(libro.getGenere() != "" && valoriNull != 0) {
				query = query.concat("l.genere like :genere ");
				if(valoriAnd != 0) {
					query = query.concat("and ");
					valoriAnd--;
				}
				array[3]=1;
			}
			if(libro.getAutore().getId() != null && valoriNull != 0) {
				query = query.concat("l.autore = :autore ");
				array[4]=1;
			}
		}
		
		//System.out.println(query);
		
		List<Libro> listaLibri = new ArrayList<Libro>();
		TypedQuery<Libro> query1 = entityManager.createQuery(query, Libro.class);
		Genere genere = Genere.valueOf(libro.getGenere());
		
			int k=1;
			
				if(array[1] == 1) {
					if(libro.getTitolo() != "") {
						query1.setParameter("titolo", "%"+libro.getTitolo()+"%");
					}
					array[1]=0;
					k++;
				}
				
				if(array[2] == 1) {
					if(libro.getTrama() != "") {
						query1.setParameter("trama", "%"+libro.getTrama()+"%");
					}
					array[2]=0;
					k++;
				}
				
				if(array[3] == 1) {
					if(libro.getGenere() != "") {
						query1.setParameter("genere", "%"+genere.toString()+"%");
					}
					k++;
					array[3]=0;
				}
				
				if(array[4] == 1) {
					if(libro.getAutore() != null) {
						query1.setParameter("autore", libro.getAutore());
					}
					k++;
					array[4]=0;
				}
			
			
			
			
				return query1.getResultList().stream().collect(Collectors.toSet());
		
		
	}

}
