package view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import controller.JogadorController;
import controller.PersonagemController;
import model.*;

@ManagedBean(name = "chaveView")
@ViewScoped
public class ChaveView {

	public List<Jogador> jogadores;
	public List<Jogador> jogadoresA;
	public List<Jogador> jogadoresB;
	public List<Partida> tableClassificacao;
	public List<Personagem> personagensA;
	public List<Personagem> personagensB;
	
	public List<Jogador> getJogadores() {
		return jogadores;
	}
	
	public List<Jogador> getJogadoresA() {
		return jogadoresA;
	}
	public List<Jogador> getJogadoresB() {
		return jogadoresB;
	}
    public List<Partida> getTableClassificacao() {
    	System.out.println("Passou pelo getClassificacao");
		return tableClassificacao;
	}

	public void setTableClassificacao(List<Partida> tableClassificacao) {
		System.out.println("Passou pelo setClassificacao");
		this.tableClassificacao = tableClassificacao;
	}
	public void set() {
		List<Partida> temp = loadClassificacao();
		setTableClassificacao(temp);
	}

	public List<Personagem> getPersonagensA() {
		return personagensA;
	}

	public void setPersonagensA(List<Personagem> personagensA) {
		this.personagensA = personagensA;
	}

	public List<Personagem> getPersonagensB() {
		return personagensB;
	}

	public void setPersonagensB(List<Personagem> personagensB) {
		this.personagensB = personagensB;
	}

	@PostConstruct
    public void init() {
    	try {
    		
			jogadores = new JogadorController().Listar();
			
			jogadoresA = new JogadorController().ListarA();
			jogadoresB = new JogadorController().ListarB();
			
			personagensA = new PersonagemController().ListarA();
			personagensB = new PersonagemController().ListarB();
			
			tableClassificacao = loadClassificacao();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	public List<Partida> loadClassificacao() {
		List<Partida> classificacao = new ArrayList<Partida>();
		
		for (int i = 0; i < 4; i++) {
			
			Partida p = new Partida(getJogadoresA().get(i),getPersonagensA().get(i),getJogadoresB().get(i),getPersonagensB().get(i));
			classificacao.add(p);
		}
		
		return classificacao;
	}
 
    public void setjogadores(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }   
    
    public void setjogadoresA(List<Jogador> jogadoresA) {
        this.jogadoresA = jogadoresA;
    }  
    
    public void setjogadoresB(List<Jogador> jogadoresB) {
        this.jogadoresB = jogadoresB;
    }  
     
    public void onSelect(SelectEvent event) {
    	System.out.println(tableClassificacao);
    	System.out.println(tableClassificacao.get(0).getJogadorA().getNome());
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", event.getObject().toString()));
    }
     
    public void onUnselect(UnselectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Unselected", event.getObject().toString()));
    }
     
    public void onReorder() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "List Reordered", null));
    }
    
    
}
