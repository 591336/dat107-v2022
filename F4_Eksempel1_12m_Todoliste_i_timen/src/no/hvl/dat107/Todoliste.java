package no.hvl.dat107;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Todoliste", schema = "forelesning4")
public class Todoliste {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int listeId;
	
	private String navn;
	
	public Todoliste(String navn) {
		this.navn = navn;
	}
	
	public int getListeId() {
		return listeId;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}
	
	public void leggTil(Todo todo) {
		todos.add
	}

	@Override
	public String toString() {
		return "Todoliste [listeId=" + listeId + ", navn=" + navn + "]";
	}

}




