package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CloseEvent;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.event.DragDropEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

import controller.JogadorController;
import controller.PersonagemController;
import model.*;

@ManagedBean(name = "chaveView")
@ViewScoped
public class ChaveView implements Serializable {
	
	public List<Jogador> jogadores;
	public List<Jogador> jogadoresA;
	public List<Jogador> jogadoresB;
	public List<Partida> tableClassificacao;
	public List<Personagem> personagens;
	public List<Personagem> personagensA;
	public List<Personagem> personagensB;
	
	public List<Partida> partidas = new ArrayList<>();
	
	public List<Jogador> droppedJogadores = new ArrayList<>();
	
	public Jogador selectedJogador;
	
	public List<Jogador> droppedJogadores2 = new ArrayList<>();
	
	public Jogador selectedJogador2;
	
	public List<Personagem> droppedPersonagens = new ArrayList<>();
	
	public Jogador selectedPersonagem;
	
	public List<Personagem> droppedPersonagens2 = new ArrayList<>();
	
	public Jogador selectedPersonagem2;
	
	 
    private String message;
 
    public String getMessage() {
        return message;
    }
 
    public void setMessage(String message) {
        this.message = message;
    }
     
    public void saveMessage(String msg, String type) {
        FacesContext context = FacesContext.getCurrentInstance();
        if(type == "Warn") {
        	context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"",  msg) );
        }else {
        	context.addMessage(null, new FacesMessage("",  msg) );
        }
        
    }
    
	public List<Personagem> getPersonagens() {
		return personagens;
	}

	public void setPersonagens(List<Personagem> personagens) {
		this.personagens = personagens;
	}

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
		return tableClassificacao;
	}

	public void setTableClassificacao(List<Partida> tableClassificacao) {
		this.tableClassificacao = tableClassificacao;
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

	public List<Partida> getPartidas() {
		return partidas;
	}

	public void setPartidas(List<Partida> partidas) {
		this.partidas = partidas;
	}

	@PostConstruct
    public void init() {
    	try {
    		
			jogadores = new JogadorController().Listar();
			personagens = new PersonagemController().Listar();
			
			jogadoresA = new JogadorController().ListarA();
			jogadoresB = new JogadorController().ListarB();
			
			personagensA = new PersonagemController().ListarA();
			personagensB = new PersonagemController().ListarB();
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	public void createPartida() {
		System.out.println("A" + droppedJogadores.size());
		System.out.println("B" + droppedJogadores2.size());
		System.out.println("C" + droppedPersonagens.size());
		System.out.println("D" + droppedPersonagens2.size());
		if(droppedJogadores.size() == 4 && droppedJogadores2.size() == 4 && droppedPersonagens.size() == 4 && droppedPersonagens2.size() == 4) {
			for(int i=0;i<4;i++) {
				Partida partida = new Partida(getDroppedJogadores().get(i),getDroppedPersonagens().get(i),getDroppedJogadores2().get(i),getDroppedPersonagens2().get(i));
				partidas.add(partida);
				partida.Cadastrar();
			}
			Chave chave = new Chave(1, partidas);
			chave.Cadastrar();
			saveMessage("Partida Criada com sucesso !", "Successful");
		}else {
			saveMessage("Por gentileza complete a forma��o das equipes e personagens !", "Warn");
		}
		
		
	}
    public void onCarDrop(DragDropEvent ddEvent) {
        Jogador jogador = ((Jogador) ddEvent.getData());
        if(droppedJogadores.size() < 4) {
        	droppedJogadores.add(jogador);
            jogadores.remove(jogador);
        }
    }
    
    public void onCarDrop2(DragDropEvent ddEvent) {
        Jogador jogador = ((Jogador) ddEvent.getData());
        if(droppedJogadores2.size() < 4) {
	        droppedJogadores2.add(jogador);
	        jogadores.remove(jogador);
        }
    }
    
    public void onCarDrop3(DragDropEvent ddEvent) {
        Personagem personagem = ((Personagem) ddEvent.getData());
        if(droppedPersonagens.size() < 4) {
	        droppedPersonagens.add(personagem);
	        personagens.remove(personagem);
        }
    }
    
    public void onCarDrop4(DragDropEvent ddEvent) {
        Personagem personagem = ((Personagem) ddEvent.getData());
        if(droppedPersonagens2.size() < 4) {
	        droppedPersonagens2.add(personagem);
	        personagens.remove(personagem);
        }
    }
    
    public void removeJogador(Jogador j) {
    	droppedJogadores.remove(j);
        jogadores.add(j);
    }
    public void removeJogador2(Jogador j) {
    	droppedJogadores2.remove(j);
        jogadores.add(j);
    }
    public void removePersonagem(Personagem p) {
    	droppedPersonagens.remove(p);
        personagens.add(p);
    }
    public void removePersonagem2(Personagem p) {
    	droppedPersonagens2.remove(p);
        personagens.add(p);
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

	public List<Jogador> getDroppedJogadores() {
		return droppedJogadores;
	}

	public void setDroppedJogadores(List<Jogador> droppedJogadores) {
		this.droppedJogadores = droppedJogadores;
	}

	public Jogador getSelectedJogador() {
		return selectedJogador;
	}

	public void setSelectedJogador(Jogador selectedJogador) {
		this.selectedJogador = selectedJogador;
	}

	public Jogador getSelectedJogador2() {
		return selectedJogador2;
	}

	public void setSelectedJogador2(Jogador selectedJogador2) {
		this.selectedJogador2 = selectedJogador2;
	}

	public List<Jogador> getDroppedJogadores2() {
		return droppedJogadores2;
	}

	public void setDroppedJogadores2(List<Jogador> droppedJogadores2) {
		this.droppedJogadores2 = droppedJogadores2;
	}

	public void setJogadores(List<Jogador> jogadores) {
		this.jogadores = jogadores;
	}

	public void setJogadoresA(List<Jogador> jogadoresA) {
		this.jogadoresA = jogadoresA;
	}

	public void setJogadoresB(List<Jogador> jogadoresB) {
		this.jogadoresB = jogadoresB;
	}

	public List<Personagem> getDroppedPersonagens() {
		return droppedPersonagens;
	}

	public void setDroppedPersonagens(List<Personagem> droppedPersonagens) {
		this.droppedPersonagens = droppedPersonagens;
	}

	public Jogador getSelectedPersonagem() {
		return selectedPersonagem;
	}

	public void setSelectedPersonagem(Jogador selectedPersonagem) {
		this.selectedPersonagem = selectedPersonagem;
	}

	public List<Personagem> getDroppedPersonagens2() {
		return droppedPersonagens2;
	}

	public void setDroppedPersonagens2(List<Personagem> droppedPersonagens2) {
		this.droppedPersonagens2 = droppedPersonagens2;
	}

	public Jogador getSelectedPersonagem2() {
		return selectedPersonagem2;
	}

	public void setSelectedPersonagem2(Jogador selectedPersonagem2) {
		this.selectedPersonagem2 = selectedPersonagem2;
	}  
	
    
}
