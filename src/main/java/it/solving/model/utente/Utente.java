package it.solving.model.utente;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import it.solving.model.ruolo.Ruolo;



@Entity
@Table(name = "utente")
public class Utente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "cognome")
	private String cognome;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	
	@Enumerated(EnumType.STRING)
	private StatoUtente stato = StatoUtente.ATTIVO;
	
	@ManyToMany
	@JoinTable(name = "utente_ruolo", joinColumns = @JoinColumn(name = "utente_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ruolo_id", referencedColumnName = "ID"))
	private Set<Ruolo> ruoli = new HashSet<>();

	public Utente() {
		super();
	}

	public Utente(String nome, String cognome, String username, String password, StatoUtente stato) {
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.stato = stato;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public StatoUtente getStato() {
		return stato;
	}

	public void setStato(StatoUtente stato) {
		this.stato = stato;
	}

	public Set<Ruolo> getRuoli() {
		return ruoli;
	}

	public void setRuoli(Set<Ruolo> ruoli) {
		this.ruoli = ruoli;
	}

	@Override
	public String toString() {
		return "Utente [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", username=" + username + ", password="
				+ password + ", stato=" + stato + ", ruoli=" + ruoli.size() + "]";
	}
	
	
	

}
