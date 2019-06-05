package view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import controller.JogadorController;
import model.*;

@ManagedBean(name = "chaveView")
@ViewScoped
public class ChaveView {

	public List<Jogador> jogadores;
	public List<Jogador> jogadoresA;
	public List<Jogador> jogadoresB;
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
			
			jogadoresA = new JogadorController().ListarA();
			jogadoresB = new JogadorController().ListarB();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
 
    public void setjogadores(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }     
     
    public void onSelect(SelectEvent event) {
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
