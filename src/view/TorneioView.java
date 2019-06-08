package view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import controller.TorneioController;
import model.Torneio;

@ManagedBean(name = "torneioView")
@ViewScoped
public class TorneioView {

	public List<Torneio> torneios = new ArrayList<>();
	
	public List<Torneio> getTorneios() {
		return torneios;
	}
	public void setTorneios(List<Torneio> torneios) {
		this.torneios = torneios;
	}
	@PostConstruct
    public void init() {
    	try {
    		
			torneios = new TorneioController().Listar();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	public void redirect() {
		
	}
}
