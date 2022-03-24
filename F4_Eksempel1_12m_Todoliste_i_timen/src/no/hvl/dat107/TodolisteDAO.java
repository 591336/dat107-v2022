package no.hvl.dat107;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

public class TodolisteDAO {

    private EntityManagerFactory emf;

    public TodolisteDAO() {
        emf = Persistence.createEntityManagerFactory("kjell");
    }

    public /*TODO*/void hentListe(/*TODO*/) {
        EntityManager em = emf.createEntityManager();
        try {
        	
        	return em.find(Todoliste.class, id);
        	
        } finally {
            em.close();
        }
    }

    public /*TODO*/void lagreListe(/*TODO*/) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            
            /*TODO*/
            
            tx.commit();
        } catch (Throwable e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
    }

    public /*TODO*/void slettListe(/*TODO*/) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            
            /*TODO*/
            
            tx.commit();
        } catch (Throwable e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
    }

    public /*TODO*/void oppdaterListe(/*TODO*/) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            
            /*TODO*/
            
            tx.commit();
        } catch (Throwable e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
    }

    public /*TODO*/void hentListeMedNavn(/*TODO*/) {

        EntityManager em = emf.createEntityManager();
        try {
        	
        	/*TODO*/
            
        } catch (NoResultException e) {
            // catcher denne og returnerer null :)
        } finally {
            em.close();
        }
    }

    public /*TODO*/void hentTodosIListe(/*TODO*/) {

        EntityManager em = emf.createEntityManager();
        try {
        	
        	/*TODO*/
        	
        } finally {
            em.close();
        }
    }

}
