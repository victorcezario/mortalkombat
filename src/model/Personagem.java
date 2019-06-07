package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.Table;

@Entity
@Table(name = "Personagem")
public class Personagem implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPersonagem;
	private String nome;
	
	public int getIdPersonagem() {
		return idPersonagem;
	}
	
	public void setIdPersonagem(int idPersonagem) {
		this.idPersonagem = idPersonagem;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public Personagem(String nome) {
		this.nome = nome;
	}
	
	public Personagem() {}
	
}
