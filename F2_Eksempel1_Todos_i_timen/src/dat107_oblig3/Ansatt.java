package dat107_oblig3;

import DAO.AnsattDAO;
import DAO.ProsjektDAO;
import dat107_oblig3.Ansatt;
import dat107_oblig3.Prosjekt;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(schema = "forelesning5a")
public class Ansatt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String fornavn;
    private String etternavn;
    
    @ManyToMany(mappedBy="ansatte")
    private List<Prosjekt> prosjekter;
    
    public int getId() {
		return id;
	}
    
	public void skrivUt(String innrykk) {
        System.out.printf("%sAnsatt nr %d: %s %s", innrykk, id, fornavn, etternavn);
    }
    
    public void skrivUtMedProsjekter() {
        System.out.println();
        skrivUt("");
        prosjekter.forEach(p -> p.skrivUt("\n   "));
    }

    public void leggTilProsjekt(Prosjekt p) {
        prosjekter.add(p);
    }

    public void fjernProsjekt(Prosjekt p) {
        prosjekter.remove(p);
    }

	public void setStilling(String nyStilling) {
		// TODO Auto-generated method stub
		
	}
	
	public void setLoenn(int nyLoenn) {
		// TODO Auto-generated method stub
		
	}
 

}
