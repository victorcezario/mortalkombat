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
	
	public List<Chave> chaves = new ArrayList<>();
	
	public List<Jogador> jogadores = new ArrayList<>();

	public List<Personagem> personagens = new ArrayList<>();

	
	public List<Partida> partidas = new ArrayList<>();
	public List<Partida> partidasSemi = new ArrayList<>();
	public List<Partida> partidasFinal = new ArrayList<>();
	
	public Jogador selectedJogador = new Jogador();
	public Jogador selectedJogador2 = new Jogador();
	
	public List<Jogador> droppedJogadores = new ArrayList<>();
	public List<Jogador> droppedJogadores2 = new ArrayList<>();
	
	public Personagem selectedPersonagem = new Personagem();
	public Personagem selectedPersonagem2  = new Personagem();
	
	public List<Personagem> droppedPersonagens = new ArrayList<>();
	public List<Personagem> droppedPersonagens2 = new ArrayList<>();
	 
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

	public List<Jogador> getJogadores() {
		return jogadores;
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
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	public String createPartida() {
			if(droppedJogadores.size() == 4 && droppedJogadores2.size() == 4 && droppedPersonagens.size() == 4 && droppedPersonagens2.size() == 4) {
				 
				for(int i=0;i<4;i++) {
					double randomVencedor = getRandomIntegerBetweenRange(0,1);
					if(randomVencedor == 0) {
						setSelectedJogador(getDroppedJogadores().get(i));
						setSelectedPersonagem(getDroppedPersonagens().get(i));
					} else {
						setSelectedJogador(getDroppedJogadores2().get(i));
						setSelectedPersonagem(getDroppedPersonagens2().get(i));
					}
					Partida partida = new Partida(getDroppedJogadores().get(i),getDroppedPersonagens().get(i),getDroppedJogadores2().get(i),getDroppedPersonagens2().get(i),getSelectedJogador(),getSelectedPersonagem());
					partidas.add(partida);
					partida.Cadastrar();
				}
				Chave chave = new Chave(1, partidas);
				chave.Cadastrar();
				chaves.add(chave);
				createSemiFinal(partidas);

				//saveMessage("Partida criada com sucesso!", "Successful");
				return "score.xhtml";
			} else {
				saveMessage("Por gentileza, complete a formação das equipes e personagens!", "Warn");
				return null;
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
		
		Torneio torneio = new Torneio();
		torneio.setChaves(chaves);
		torneio.setJogadorVencedor(jogador);
		torneio.Cadastrar();
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
	        //personagens.remove(personagem);
        }
    }
    
    public void onCarDrop4(DragDropEvent ddEvent) {
        Personagem personagem = ((Personagem) ddEvent.getData());
        if(droppedPersonagens2.size() < 4) {
	        droppedPersonagens2.add(personagem);
	        //personagens.remove(personagem);
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

	public List<Personagem> getDroppedPersonagens() {
		return droppedPersonagens;
	}

	public void setDroppedPersonagens(List<Personagem> droppedPersonagens) {
		this.droppedPersonagens = droppedPersonagens;
	}

	public Personagem getSelectedPersonagem() {
		return selectedPersonagem;
	}

	public void setSelectedPersonagem(Personagem selectedPersonagem) {
		this.selectedPersonagem = selectedPersonagem;
	}

	public List<Personagem> getDroppedPersonagens2() {
		return droppedPersonagens2;
	}

	public void setDroppedPersonagens2(List<Personagem> droppedPersonagens2) {
		this.droppedPersonagens2 = droppedPersonagens2;
	}

	public Personagem getSelectedPersonagem2() {
		return selectedPersonagem2;
	}

	public void setSelectedPersonagem2(Personagem selectedPersonagem2) {
		this.selectedPersonagem2 = selectedPersonagem2;
	}

	public List<Chave> getChaves() {
		return chaves;
	}

	public void setChaves(List<Chave> chaves) {
		this.chaves = chaves;
	} 
    
}