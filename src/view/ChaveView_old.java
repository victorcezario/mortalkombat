package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
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
public class ChaveView_old implements Serializable {
	
	public List<Chave> chaves;
	public List<Jogador> jogadores;
	public List<Jogador> jogadoresA;
	public List<Jogador> jogadoresB;
	public Jogador jogadorSelected;
	public Personagem personagemSelected;
	public List<Partida> tableClassificacao;
	public List<Personagem> personagens;
	public List<Personagem> personagensA;
	public List<Personagem> personagensB;
	
	public List<Partida> partidas = new ArrayList<>();
	public List<Partida> partidasSemi = new ArrayList<>();
	public List<Partida> partidasFinal = new ArrayList<>();
	
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
	

	public List<Partida> getPartidasSemi() {
		return partidasSemi;
	}

	public void setPartidasSemi(List<Partida> partidasSemi) {
		this.partidasSemi = partidasSemi;
	}

	public Personagem getPersonagemSelected() {
		return personagemSelected;
	}

	public void setPersonagemSelected(Personagem personagemSelected) {
		this.personagemSelected = personagemSelected;
	}

	public Jogador getJogadorSelected() {
		return jogadorSelected;
	}

	public void setJogadorSelected(Jogador jogadorSelected) {
		this.jogadorSelected = jogadorSelected;
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
   

	@PostConstruct
    public void init() {
    	try {
    		
			jogadores = new JogadorController().Listar();
			personagens = new PersonagemController().Listar();
			
			jogadoresA = new JogadorController().ListarA();
			jogadoresB = new JogadorController().ListarB();
			
			personagensA = new PersonagemController().ListarA();
			personagensB = new PersonagemController().ListarB();
				
			droppedJogadores.add(jogadores.get(0));
			droppedJogadores.add(jogadores.get(1));
			droppedJogadores.add(jogadores.get(2));
			droppedJogadores.add(jogadores.get(3));
			
			
			droppedJogadores2.add(jogadores.get(4));
			droppedJogadores2.add(jogadores.get(5));
			droppedJogadores2.add(jogadores.get(6));
			droppedJogadores2.add(jogadores.get(7));
			
			droppedPersonagens.add(personagens.get(0));
			droppedPersonagens.add(personagens.get(1));
			droppedPersonagens.add(personagens.get(2));
			droppedPersonagens.add(personagens.get(3));
			
			
			droppedPersonagens2.add(personagens.get(4));
			droppedPersonagens2.add(personagens.get(5));
			droppedPersonagens2.add(personagens.get(6));
			droppedPersonagens2.add(personagens.get(7));
			
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
				double randomVencedor = getRandomIntegerBetweenRange(0,1);
				if(randomVencedor == 0) {
					setJogadorSelected(getDroppedJogadores().get(i));
					setPersonagemSelected(getDroppedPersonagens().get(i));
				} else {
					setJogadorSelected(getDroppedJogadores2().get(i));
					setPersonagemSelected(getDroppedPersonagens2().get(i));
				}
				Partida partida = new Partida(getDroppedJogadores().get(i),getDroppedPersonagens().get(i),getDroppedJogadores2().get(i),getDroppedPersonagens2().get(i),getJogadorSelected(),getPersonagemSelected());
				partidas.add(partida);
				partida.Cadastrar();
			}
			Chave chave = new Chave(1, partidas);
			chave.Cadastrar();
			chaves.add(chave);
			createSemiFinal(partidas);
			saveMessage("Partida Criada com sucesso !", "Successful");
		}else {
			saveMessage("Por gentileza complete a forma��o das equipes e personagens !", "Warn");
		}
		
		
	}
	public void createSemiFinal(List<Partida> partidas) {
			Jogador jogador1;
			Jogador jogador2;
			Personagem personagem1;
			Personagem personagem2;
			
			double randomVencedor1 = getRandomIntegerBetweenRange(0,1);
			if(randomVencedor1 == 0) {
				jogador1 = partidas.get(0).getJogadorVencedor();
				personagem1 = partidas.get(0).getPersonagemVencedor();
			} else {
				jogador1 = partidas.get(1).getJogadorVencedor();
				personagem1 = partidas.get(1).getPersonagemVencedor();
			}
			
			double randomVencedor2 = getRandomIntegerBetweenRange(0,1);
			if(randomVencedor2 == 0) {
				jogador2 = partidas.get(2).getJogadorVencedor();
				personagem2 = partidas.get(2).getPersonagemVencedor();
			} else {
				jogador2 = partidas.get(3).getJogadorVencedor();
				personagem2 = partidas.get(3).getPersonagemVencedor();
			}
			Partida partida1 = new Partida(partidas.get(0).getJogadorVencedor(),partidas.get(0).getPersonagemVencedor(),partidas.get(1).getJogadorVencedor(),partidas.get(1).getPersonagemVencedor(),jogador1,personagem1);
			partidasSemi.add(partida1);
			partida1.Cadastrar();
			
			Partida partida2 = new Partida(partidas.get(2).getJogadorVencedor(),partidas.get(2).getPersonagemVencedor(),partidas.get(3).getJogadorVencedor(),partidas.get(3).getPersonagemVencedor(),jogador2,personagem2);
			partidasSemi.add(partida2);
			partida2.Cadastrar();
			
			Chave chave = new Chave(2, partidasSemi);
			chave.Cadastrar();
			chaves.add(chave);
			createFinal(partidasSemi);
	}
	public void createFinal(List<Partida> partidas) {
		Jogador jogador;
		Personagem personagem;
		
		double randomVencedor = getRandomIntegerBetweenRange(0,1);
		if(randomVencedor == 0) {
			jogador = partidas.get(0).getJogadorVencedor();
			personagem = partidas.get(0).getPersonagemVencedor();
		} else {
			jogador = partidas.get(1).getJogadorVencedor();
			personagem = partidas.get(1).getPersonagemVencedor();
		}
		Partida partida = new Partida(partidas.get(0).getJogadorVencedor(),partidas.get(0).getPersonagemVencedor(),partidas.get(1).getJogadorVencedor(),partidas.get(1).getPersonagemVencedor(),jogador,personagem);
		partidasFinal.add(partida);
		partida.Cadastrar();

		Chave chave = new Chave(3, partidasFinal);
		chaves.add(chave);
		chave.Cadastrar();
	}
	public static double getRandomIntegerBetweenRange(double min, double max){
	    double x = (int)(Math.random()*((max-min)+1))+min;
	    return x;
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
    
    public List<Partida> getPartidasFinal() {
		return partidasFinal;
	}

	public void setPartidasFinal(List<Partida> partidasFinal) {
		this.partidasFinal = partidasFinal;
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

		public List<Chave> getChave() {
			return chaves;
		}

		public void setChave(List<Chave> chave) {
			this.chaves = chave;
		}
    
}
