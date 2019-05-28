package controller;

import org.hibernate.Session;

import model.Chave;
import model.Jogador;
import model.Partida;
import model.Personagem;

public class App {
	
	//Funções
	
	public void listaChaves() {
		//tipo da Chave, partidas
	}

	public void listaPartidas() {
		//Jogador1 , Personagem 1, Jogador2 , Personagem 2, Vencedor
	}
	
	public void consultaInfoJogador() {
		//Nome, Apelido, Pais, TotalDerrotas, TotalVitórias
	}
	
	public void consultaInfoPersonagem() {
		//Nome, TotalDerrotas, TotalVitórias
	}

	public static void cadastraJogador(Session session) {
		Jogador jogador1 = new Jogador();
		jogador1.setApelido("Hugo");
		jogador1.setNome("Victor");
		jogador1.setPais("Brasil");
		
		session.save(jogador1);
	}
	
	public void editaJogador(Jogador jogador) {
		
	}
	
	public void cadastraPersonagem(Personagem personagem) {
		
	}
	
	public void editaPersonagem(Personagem personagem) {
		
	}
	
	public void cadastraPartida(Partida partida) {
		
	}
	
	public void cadastraChave(Chave chave) {
		
	}
	
}
