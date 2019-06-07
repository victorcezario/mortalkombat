package model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Table;

@Entity
@Table(name = "Chave")
public class Chave {
	private static EntityManagerFactory manager = 
			Persistence.createEntityManagerFactory("MortalKombat"); 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idChave;
	private int tipoChave;
	@OneToMany
	private List<Partida> partidas = new ArrayList<>();
	
	public int getIdChave() {
		return idChave;
	}
	public void setIdChave(int idChave) {
		this.idChave = idChave;
	}
	public int getTipoChave() {
		return tipoChave;
	}
	public void setTipoChave(int tipoChave) {
		this.tipoChave = tipoChave;
	}
	
	public List<Partida> getPartidas() {
		return partidas;
	}
	public void setPartidas(List<Partida> partidas) {
		this.partidas = partidas;
	}
	public Chave(int tipoChave, List<Partida> partidas) {
		super();
		this.tipoChave = tipoChave;
		this.partidas = partidas;
	}
	public Chave() {}
	
	public void Cadastrar() {
		EntityManager entitymanager = manager.createEntityManager();
		entitymanager.getTransaction().begin();
		entitymanager.persist(this);
		entitymanager.getTransaction().commit();
	}
	
}
