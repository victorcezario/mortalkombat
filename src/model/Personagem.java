package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Personagem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPersonagem;
	@Column
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
	
	
}
