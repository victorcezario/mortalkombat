package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Jogador;

public class JogadorController {
	
	private static EntityManagerFactory manager = 
			Persistence.createEntityManagerFactory("MortalKombat"); 
	
	
	public static void Cadastrar(Jogador obj) {
		EntityManager entitymanager = manager.createEntityManager();
		entitymanager.getTransaction().begin();
		entitymanager.persist(obj);
		entitymanager.getTransaction().commit();
	}
	public List<Jogador> Listar() {
		EntityManager entitymanager = manager.createEntityManager();
		
		TypedQuery<Jogador> query = null;
		
		query = entitymanager.createQuery("select c from Jogador c", Jogador.class);
		
		return query.getResultList();
	}
}
