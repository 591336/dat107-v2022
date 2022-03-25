package dat107_oblig3;

import DAO.AnsattDAO;
import DAO.ProsjektDAO;
import dat107_oblig3.Ansatt;
import dat107_oblig3.Prosjekt;

import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

@Entity
@Table(schema = "forelesning5a")
public class Prosjekt {

	private EntityManagerFactory emf;

	public Prosjekt() {
		emf = Persistence.createEntityManagerFactory("personPersistenceUnit",
				Map.of("javax.persistence.jdbc.password", "pass"));
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String navn;

	// Vi velger Prosjekt som owning-side. Vi kunne også valgt Ansatt.
	// Hipp-som-happ.
	@ManyToMany
	@JoinTable(name = "forelesning5a.Prosjektdeltagelse", // NB! Må ha med schema !!!
			joinColumns = @JoinColumn(name = "Prosjekt_Id"), inverseJoinColumns = @JoinColumn(name = "Ansatt_Id"))
	private List<Ansatt> ansatte;

	public int getId() {
		return id;
	}

	public void skrivUt(String innrykk) {
		System.out.printf("%sProsjekt nr %d: %s", innrykk, id, navn);
	}

	public void skrivUtMedAnsatte() {
		System.out.println();
		skrivUt("");
		ansatte.forEach(a -> a.skrivUt("\n   "));
	}

	public void leggTilAnsatt(Ansatt a) {
		ansatte.add(a);
	}

	public void fjernAnsatt(Ansatt a) {
		ansatte.remove(a);
	}

}
