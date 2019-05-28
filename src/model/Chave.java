package model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Chave")
public class Chave {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idChave;
	private int tipoChave;
	@OneToMany
	private List<Partida> partidas = new ArrayList<>();
	
	public int getIdChave() {
		return idChave;
	}
	public void setIdChave(int idChave) {
		this.idChave = idChave;
	}
	public int getTipoChave() {
		return tipoChave;
	}
	public void setTipoChave(int tipoChave) {
		this.tipoChave = tipoChave;
	}
	
	
}
