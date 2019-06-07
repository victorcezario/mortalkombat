package controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Partida;

@ManagedBean(name="partidaController")
@SessionScoped
public class PartidaController {
	
	private static EntityManagerFactory manager = 
			Persistence.createEntityManagerFactory("MortalKombat"); 
	
	
	public static void Register(Partida obj) {
		EntityManager entitymanager = manager.createEntityManager();
		entitymanager.getTransaction().begin();
		entitymanager.persist(obj);
		entitymanager.getTransaction().commit();
		entitymanager.close();
	}
	public List<Partida> Listar(int idChave) {
		EntityManager entitymanager = manager.createEntityManager();
		
		TypedQuery<Partida> query = null;
		
		//query = entitymanager.createQuery("select p from Partida p", Partida.class);
		query = entitymanager.createQuery("select p from Partida p inner join Chave c ON c.idChave = :idChave where p.idPartida = c.idPartida", Partida.class).setParameter("idChave", idChave);
		return query.getResultList();
	}
}
