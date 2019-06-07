package view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import controller.PartidaController;
import model.Jogador;
import model.Partida;

@ManagedBean(name = "classificacaoView")
@ViewScoped
public class ClassificacaoView implements Serializable {
	public List<Partida> partidas;
	private String vencedor1; 
	public List<Jogador> jogadores;
	public List<Jogador> vencedores;
	
	public List<Jogador> getJogadores() {
		return jogadores;
	}

	public void setJogadores(List<Jogador> jogadores) {
		this.jogadores = jogadores;
	}

	public List<Jogador> getVencedores() {
		return vencedores;
	}

	public void setVencedores(List<Jogador> vencedores) {
		this.vencedores = vencedores;
	}

	public String getVencedor1() {
		return vencedor1;
	}

	public void setVencedor1(String vencedor1) {
		this.vencedor1 = vencedor1;
	}

	@PostConstruct
    public void init() {
    	try {
    		
			partidas = new PartidaController().Listar(1);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	public List<Partida> getPartidas() {
		return partidas;
	}

	public void setPartidas(List<Partida> partidas) {
		this.partidas = partidas;
	}
}
