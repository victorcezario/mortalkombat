package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Torneio;

public class TorneioController {

	private static EntityManagerFactory manager = 
			Persistence.createEntityManagerFactory("MortalKombat");
	
	public List<Torneio> Listar() {
		EntityManager entitymanager = manager.createEntityManager();
		
		TypedQuery<Torneio> query = null;
		
		query = entitymanager.createQuery("select c from Torneio c", Torneio.class);
		return query.getResultList();
	}
	
	public List<Torneio> lastResult() {
		EntityManager entitymanager = manager.createEntityManager();
		
		TypedQuery<Torneio> query = null;
		
		query = entitymanager.createQuery("select c from Torneio c ORDER BY idTorneio DESC", Torneio.class).setMaxResults(1);
		return query.getResultList();
	}
}
