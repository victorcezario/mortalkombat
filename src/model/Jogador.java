package model;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.Table;

@ManagedBean(name = "jogador")
@RequestScoped

@Entity
@Table(name = "Jogador")
public class Jogador implements Serializable {
	private static EntityManagerFactory manager = 
			Persistence.createEntityManagerFactory("MortalKombat"); 
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idJogador;
	private String nome;
	private String apelido;
	private String pais;
	

	public int getIdJogador() {
		return idJogador;
	}
	
	public void setIdJogador(int idJogador) {
		this.idJogador = idJogador;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getApelido() {
		return apelido;
	}
	
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	
	public String getPais() {
		return pais;
	}
	
	public void setPais(String pais) {
		this.pais = pais;
	}

	public Jogador(String nome, String apelido, String pais) {
		this.nome = nome;
		this.apelido = apelido;
		this.pais = pais;
	}
	public Jogador() { }
	
	public void Cadastrar() {
		EntityManager entitymanager = manager.createEntityManager();
		entitymanager.getTransaction().begin();
		entitymanager.persist(this);
		entitymanager.getTransaction().commit();
	}
	
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        return hash;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Jogador other = (Jogador) obj;
        if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome)) {
            return false;
        }
        return true;
    }
}
