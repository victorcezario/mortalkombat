package view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import controller.*;
import model.*;

@ManagedBean(name = "JogadorView")
@ViewScoped

public class JogadorView {
	private List<Jogador> jogadores;
	
	public List<Jogador> getJogadores(){
		
		return jogadores;
		
	}
	@PostConstruct
	public void init() {
		
		try {
			jogadores = new JogadorController().Listar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
