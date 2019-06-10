package controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


import model.Personagem;

@ManagedBean(name="personagemController")
@SessionScoped
public class PersonagemController {
	
	private static EntityManagerFactory manager = 
			Persistence.createEntityManagerFactory("MortalKombat"); 
	
	
	public static void Cadastrar(Personagem obj) {
		EntityManager entitymanager = manager.createEntityManager();
		entitymanager.getTransaction().begin();
		entitymanager.persist(obj);
		entitymanager.getTransaction().commit();
	}
	public List<Personagem> Listar() {
		EntityManager entitymanager = manager.createEntityManager();
		
		TypedQuery<Personagem> query = null;
		
		query = entitymanager.createQuery("select c from Personagem c", Personagem.class);
		
		return query.getResultList();
	}

}
