package view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import controller.PersonagemController;
import model.Personagem;

@ManagedBean(name = "PersonagemView")
@ViewScoped
public class PersonagemView {
	private List<Personagem> personagens;

	public List<Personagem> getPersonagem() {

		return personagens;

	}
	
	@PostConstruct
	public void init() {

		try {
			personagens = new PersonagemController().Listar();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
