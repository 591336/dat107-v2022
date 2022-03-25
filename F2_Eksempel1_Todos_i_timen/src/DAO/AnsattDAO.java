package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import DAO.AnsattDAO;
import DAO.ProsjektDAO;
import dat107_oblig3.Ansatt;
import dat107_oblig3.Prosjekt;

public class AnsattDAO {

	private EntityManagerFactory emf;

	public AnsattDAO() {
		emf = Persistence.createEntityManagerFactory("AnsattProsjektPU");
	}

	// søk etter ansatt med ansattID
	public Ansatt finnAnsattMedId(int id) {

		EntityManager em = emf.createEntityManager();

		Ansatt a = null;
		try {
			a = em.find(Ansatt.class, id); // henter ut etter pk
		} finally {
			em.close();
		}
		return a;

	}

	// søk etter ansatt på brukernavn(initialer)
	public void finnAnsattMedBN(String brukernavn) {
    	
		EntityManager em = emf.createEntityManager();
		
		Ansatt a = null;
		try {
			a = em.find(Ansatt.class, brukernavn); //henter ut etter BN
		} finally {
			em.close();
		}
    }

	// utlisting av alle ansatte
	public List<Ansatt> utlistAlle() {
		EntityManager em = emf.createEntityManager();

		List<Ansatt> ansatte = null;
		try {
			TypedQuery<Ansatt> query = em.createNamedQuery("SELECT a FROM Ansatt  as a order by a.id", Ansatt.class);
			ansatte = query.getResultList(); // Henter ut etter spørring
		} finally {
			em.close();
		}
		return ansatte;
	}

	// oppdater en ansatts stilling
	public void oppdaterStilling(int id, String nyStilling) {

		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();

			Ansatt a = em.find(Ansatt.class, id); // finner rad som skal oppdateres
			a.setStilling(nyStilling); // TODO lag setStilling

			em.getTransaction().commit();
		} catch (Throwable e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	// oppdater en ansatts lønn
	public void oppdaterLoenn(int id, int nyLoenn) {

		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();

			Ansatt a = em.find(Ansatt.class, id); // finner rad som skal oppdateres
			a.setLoenn(nyLoenn); // TODO lag setLoenn

			em.getTransaction().commit();
		} catch (Throwable e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	// legg til ansatt
	public void leggTilAnsattEgen(Ansatt a) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(a); // oppretter ny rad i tab
			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
	}

	public void registrerProsjektdeltagelse(int ansattId, int prosjektId) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			Ansatt a = em.find(Ansatt.class, ansattId);
			Prosjekt p = em.find(Prosjekt.class, prosjektId);

			a.leggTilProsjekt(p);
			p.leggTilAnsatt(a);

			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
	}

	public void slettProsjektdeltagelse(int ansattId, int prosjektId) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			Ansatt a = em.find(Ansatt.class, ansattId);
			Prosjekt p = em.find(Prosjekt.class, prosjektId);

			a.fjernProsjekt(p);
			p.fjernAnsatt(a);

			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
	}
}
