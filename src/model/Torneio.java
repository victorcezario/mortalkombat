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
import javax.persistence.OneToOne;
import javax.persistence.Persistence;
import javax.persistence.Table;

@Entity
@Table(name = "Torneio")
public class Torneio {
	private static EntityManagerFactory manager = 
			Persistence.createEntityManagerFactory("MortalKombat"); 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idTorneio;
	@OneToMany
	private List<Chave> chaves = new ArrayList<>();
	@OneToOne
	private Jogador jogadorVencedor;
	
	public static EntityManagerFactory getManager() {
		return manager;
	}
	public static void setManager(EntityManagerFactory manager) {
		Torneio.manager = manager;
	}
	public List<Chave> getChaves() {
		return chaves;
	}
	public void setChaves(List<Chave> chaves) {
		this.chaves = chaves;
	}
	
	public Jogador getJogadorVencedor() {
		return jogadorVencedor;
	}
	public void setJogadorVencedor(Jogador jogadorVencedor) {
		this.jogadorVencedor = jogadorVencedor;
	}
	
	public int getIdTorneio() {
		return idTorneio;
	}
	public void setIdTorneio(int idTorneio) {
		this.idTorneio = idTorneio;
	}
	public int Cadastrar() {
		EntityManager entitymanager = manager.createEntityManager();
		entitymanager.getTransaction().begin();
		entitymanager.persist(this);
		entitymanager.getTransaction().commit();
		return this.idTorneio;
	}
	
	public Torneio(List<Chave> chaves) {
		super();
		this.chaves = chaves;
	}
	public Torneio() {}
}
