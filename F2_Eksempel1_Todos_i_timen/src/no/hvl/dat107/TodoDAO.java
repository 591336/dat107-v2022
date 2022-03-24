package no.hvl.dat107;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class TodoDAO {
	
	private EntityManagerFactory emf 
			= Persistence.createEntityManagerFactory("todoPersistenceUnit", 
			Map.of("javax.persistence.jdbc.password", "Truwa123"));
	
	/* --------------------------------------------------------------------- */

	public Todo finnTodoMedPk(int id) {
		
		EntityManager em = emf.createEntityManager();

		try {
			return em.find(Todo.class, id); 

		} finally {
			em.close();
		}
	}

	/* --------------------------------------------------------------------- */

	public List<Todo> finnAlleTodos() {
		
		EntityManager em = emf.createEntityManager();
		
		try {
			TypedQuery<Todo> query = em.createQuery("SELECT t FROM Todo t", Todo.class);

			return query.getResultList();

		} finally {
			em.close();
		}
	}

	/* --------------------------------------------------------------------- */

	public Todo finnTodoMedTekst(String tekst) {
		EntityManager em = emf.createEntityManager();
		
		try {
			TypedQuery<Todo> query = em.createQuery("SELECT t FROM Todo t WHERE t.tekst LIKE :tekst", Todo.class);
			query.setParameter("tekst", tekst);

			return query.getSingleResult(); // NB! Exception hvis ingen eller flere resultater
			
		} finally {
			em.close();
		}
	}
	
	/* --------------------------------------------------------------------- */

	public List<Todo>  finnTodosMedTekst(String tekst) {
		EntityManager em = emf.createEntityManager();
		
		try {
			TypedQuery<Todo> query = em.createQuery("SELECT t FROM Todo t WHERE t.tekst LIKE :tekst", Todo.class);
			query.setParameter("tekst", tekst);

			return query.getResultList(); // NB! Tom liste hvis ingen resultat
		
		} finally {
			em.close();
		}
	}

	/* --------------------------------------------------------------------- */

	public void lagreNyTodo(int id, String tekst) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			
			Todo todo = new Todo(id, tekst);
			em.persist(todo);
			
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

	/* --------------------------------------------------------------------- */

	public void slettTodoMedPk(int id) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			
//			Todo todo = new Todo(id, "");
//			todo = em.merge(todo);
			
			Todo todo = em.find(Todo.class, id);
			em.remove(todo);
			
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

	/* --------------------------------------------------------------------- */

	public void oppdaterTodo(Todo todo) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			em.merge(todo);
			
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

	/* --------------------------------------------------------------------- */

	public void oppdaterTekst(int id, String tekst) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			Todo managedTodo = em.find(Todo.class, id);
			// NB!: Kan ikke gjøre:
			// Todo managedTodo = finnTodoMedPk(id);

			managedTodo.setTekst(tekst);
						
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
