package it.solving.model.libro;

public enum Genere {
	Avventura,
	Azione,
	Fantasy,
	Fantascienza,
	Storico,
	Thriller,
	Horror,
	EMPTY;
	
	@Override
	public String toString() {
        return this == EMPTY ? "" : this.name();
    }

}
