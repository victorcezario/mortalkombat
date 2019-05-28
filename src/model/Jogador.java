package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Jogador")
public class Jogador {
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
	
	
}
