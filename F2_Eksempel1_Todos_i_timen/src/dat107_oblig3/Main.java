package dat107_oblig3;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import DAO.AnsattDAO;
import DAO.ProsjektDAO;
import dat107_oblig3.Ansatt;
import dat107_oblig3.Prosjekt;

public class Main {

	public static void main(String[] args) {

			EntityManagerFactory emf = Persistence.createEntityManagerFactory("AnsattProsjektPU");
			EntityManager em = emf.createEntityManager();


			AnsattDAO ansattDAO = new AnsattDAO();
			ProsjektDAO prosjektDAO = new ProsjektDAO();

			Ansatt a2 = ansattDAO.finnAnsattMedId(2);
			a2.skrivUtMedProsjekter();

			Prosjekt p2 = prosjektDAO.finnProsjektMedId(2);
			p2.skrivUtMedAnsatte();

			Prosjekt p3 = prosjektDAO.finnProsjektMedId(3);
			p3.skrivUtMedAnsatte();

			ansattDAO.registrerProsjektdeltagelse(a2.getId(), p3.getId());
			a2 = ansattDAO.finnAnsattMedId(2);
			p3 = prosjektDAO.finnProsjektMedId(3);
			a2.skrivUtMedProsjekter();
			p3.skrivUtMedAnsatte();

			ansattDAO.slettProsjektdeltagelse(a2.getId(), p3.getId());
			a2 = ansattDAO.finnAnsattMedId(2);
			p3 = prosjektDAO.finnProsjektMedId(3);
			a2.skrivUtMedProsjekter();
			p3.skrivUtMedAnsatte();

	}

}
