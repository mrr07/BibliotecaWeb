package it.solving.model.libro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import it.solving.model.autore.Autore;


@Entity
@Table(name = "libro")
public class Libro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_libro")
	private Long id;
	@Column(name = "titolo")
	private String titolo;
	@Column(name = "genere")
	private String genere;
	@Column(name = "trama")
	private String trama;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "autore_fk")
	private Autore autore;
	
	public Libro() {
	}

	public Libro(String titolo, Genere genere, String trama, Autore autore) {
		this.titolo = titolo;
		this.genere = genere.name();
		this.trama = trama;
		this.autore = autore;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(Genere genere) {
		this.genere = genere.name();
	}

	public String getTrama() {
		return trama;
	}

	public void setTrama(String trama) {
		this.trama = trama;
	}

	public Autore getAutore() {
		return autore;
	}

	public void setAutore(Autore autore) {
		this.autore = autore;
	}

	@Override
	public String toString() {
		return "Libro [id=" + id + ", titolo=" + titolo + ", genere=" + genere + ", trama=" + trama + ", autore="
				+ autore.getNome() + " " + autore.getCognome() + "]";
	}
	
	
	
	
}
