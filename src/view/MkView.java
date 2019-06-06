package view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import controller.JogadorController;
import controller.PersonagemController;
import model.Jogador;
import model.Personagem;

@ManagedBean(name = "MkView")
@ViewScoped

public class MkView {

	private List<Personagem> personagens;

	public List<Personagem> getPersonagem() {

		return personagens;

	}

	private List<Jogador> jogadores;

	public List<Jogador> getJogadores() {

		return jogadores;

	}
	
	@PostConstruct
	public void init() {

		try {
			personagens = new PersonagemController().ListarA();
			jogadores = new JogadorController().ListarA();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
