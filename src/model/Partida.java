package model;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Persistence;
import javax.persistence.Table;

@Entity
@Table(name = "Partida")
public class Partida {
	private static EntityManagerFactory manager = 
			Persistence.createEntityManagerFactory("MortalKombat"); 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPartida;
	@OneToOne
	private Jogador jogadorA;
	@OneToOne
	private Personagem personagemA;
	@OneToOne
	private Jogador jogadorB;
	@OneToOne
	private Personagem personagemB;
	@OneToOne
	private Jogador vencedor;
	
	public int getIdPartida() {
		return idPartida;
	}
	public void setIdPartida(int idPartida) {
		this.idPartida = idPartida;
	}
	public Jogador getJogadorA() {
		return jogadorA;
	}
	public void setJogadorA(Jogador jogadorA) {
		this.jogadorA = jogadorA;
	}
	public Personagem getPersonagemA() {
		return personagemA;
	}
	public void setPersonagemA(Personagem personagemA) {
		this.personagemA = personagemA;
	}
	public Jogador getJogadorB() {
		return jogadorB;
	}
	public void setJogadorB(Jogador jogadorB) {
		this.jogadorB = jogadorB;
	}
	public Personagem getPersonagemB() {
		return personagemB;
	}
	public void setPersonagemB(Personagem personagemB) {
		this.personagemB = personagemB;
	}
	public Jogador getVencedor() {
		return vencedor;
	}
	public void setVencedor(Jogador vencedor) {
		this.vencedor = vencedor;
	}
	
	public Partida() {
		
	}
	
	public Partida(Jogador jogadorA, Personagem personagemA, Jogador jogadorB, Personagem personagemB) {
		super();
		this.jogadorA = jogadorA;
		this.personagemA = personagemA;
		this.jogadorB = jogadorB;
		this.personagemB = personagemB;
	}
	public void Cadastrar() {
		EntityManager entitymanager = manager.createEntityManager();
		entitymanager.getTransaction().begin();
		entitymanager.persist(this);
		entitymanager.getTransaction().commit();
	}
	
}
