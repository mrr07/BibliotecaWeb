package it.solving.model.autore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import it.solving.model.libro.Libro;


@Entity
@Table(name = "autore")
public class Autore {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_autore")
	private Long id;
	@Column(name = "nome_autore")
	private String nome;
	@Column(name = "cognome_autore")
	private String cognome;
	@Column(name = "data_nascita_autore")
	private Date data_nascita;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "autore")
	private Set<Libro> libri = new HashSet<>();
	
	public Autore() {
		super();
	}

	public Autore(String nome, String cognome, String data_nascita /*, Set<Libro> libri*/) {
		this.nome = nome;
		this.cognome = cognome;
		try {
			this.data_nascita = new SimpleDateFormat("yyyy-MM-dd").parse(data_nascita); 
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		//this.libri = libri;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getData_nascita() {
		return data_nascita;
	}

	public void setData_nascita(String data_nascita) {
		try {
			this.data_nascita = new SimpleDateFormat("yyyy-MM-dd").parse(data_nascita); 
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	public Set<Libro> getLibri() {
		return libri;
	}

	public void setLibri(Set<Libro> libri) {
		this.libri = libri;
	}

	@Override
	public String toString() {
		return nome+ " "+cognome;
	}
	
	
	
	

}
