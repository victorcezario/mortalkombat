package controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Jogador;

@ManagedBean(name="jogadorController")
@SessionScoped
public class JogadorController {
	
	private static EntityManagerFactory manager = 
			Persistence.createEntityManagerFactory("MortalKombat"); 
	
	
	public static void Register(Jogador obj) {
		EntityManager entitymanager = manager.createEntityManager();
		entitymanager.getTransaction().begin();
		entitymanager.persist(obj);
		entitymanager.getTransaction().commit();
		entitymanager.close();
	}
	public List<Jogador> Listar() {
		EntityManager entitymanager = manager.createEntityManager();
		
		TypedQuery<Jogador> query = null;
		
		query = entitymanager.createQuery("select c from Jogador c", Jogador.class);
		
		return query.getResultList();
	}
	
	public List<Jogador> ListarA() {
		EntityManager entitymanager = manager.createEntityManager();
		
		TypedQuery<Jogador> query = null;
		
		query = entitymanager.createQuery("select c from Jogador c", Jogador.class).setFirstResult(0).setMaxResults(4);
		
		return query.getResultList();
	}
	public List<Jogador> ListarB() {
		EntityManager entitymanager = manager.createEntityManager();
		
		TypedQuery<Jogador> query = null;
		
		query = entitymanager.createQuery("select c from Jogador c", Jogador.class).setFirstResult(4).setMaxResults(4);
		
		return query.getResultList();
	}
}
