package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Chave;

public class ChaveController {

	private static EntityManagerFactory manager = 
			Persistence.createEntityManagerFactory("MortalKombat");
	public List<Chave> Listar() {
		EntityManager entitymanager = manager.createEntityManager();
		
		TypedQuery<Chave> query = null;
		
		//query = entitymanager.createQuery("select p from Partida p", Partida.class);
		//query = entitymanager.createQuery("select p from Partida p inner join Chave c ON c.idChave = :idChave where p.idPartida = c.idPartida", Partida.class).setParameter("idChave", idChave);
		query = entitymanager.createQuery("select c from Chave c", Chave.class);
		return query.getResultList();
	}
	
}
