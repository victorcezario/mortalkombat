package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import controller.ChaveController;
import controller.TorneioController;
import model.Chave;
import model.Partida;
import model.Torneio;

@ManagedBean(name="scoreView")
@ViewScoped
public class ScoreView implements Serializable {
     
    private TreeNode root;
    List<Partida> partidas = new ArrayList<>();
	List<Partida> partidasSemi = new ArrayList<>();
	List<Partida> partidasFinal = new ArrayList<>();
    @PostConstruct
    public void init() {
    	List<Torneio> torneio = new TorneioController().lastResult();
    	System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n inicio TORNEIOS");
    	System.out.println("Setor1: "+torneio.get(0).getChaves().get(0));
    	System.out.println("Setor2: "+torneio.get(0).getChaves());
    	System.out.println("Setor3: "+torneio.get(0));
    	System.out.println("Setor4: "+torneio);
    	System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n fim TORNEIOS");
    	List<Chave> chaves = torneio.get(0).getChaves();
    	for (Chave chave : chaves) {
    		if(chave.getTipoChave() == 3) {
    			partidasFinal.add(chave.getPartidas().get(0));
    		}
    		if(chave.getTipoChave() == 2) {
        		partidasSemi.add(chave.getPartidas().get(0));
        		partidasSemi.add(chave.getPartidas().get(1));
    		}
    		if(chave.getTipoChave() == 1) {
        		partidas.add(chave.getPartidas().get(0));
        		partidas.add(chave.getPartidas().get(1));
        		partidas.add(chave.getPartidas().get(2));
        		partidas.add(chave.getPartidas().get(3));
    		}
		}
    	root = new DefaultTreeNode(partidasFinal.get(0).getJogadorVencedor().getNome(), null);
    	//Final
        root.setExpanded(true);
        TreeNode node0 = new DefaultTreeNode(partidasFinal.get(0).getJogadorA().getNome(), root);
        TreeNode node1 = new DefaultTreeNode(partidasFinal.get(0).getJogadorB().getNome(), root);
        node0.setExpanded(true);
        node1.setExpanded(true);
        //SemiFinal
        TreeNode node00 = new DefaultTreeNode(partidasSemi.get(0).getJogadorA().getNome(), node0);
        TreeNode node01 = new DefaultTreeNode(partidasSemi.get(0).getJogadorB().getNome(), node0);
        node00.setExpanded(true);
        node01.setExpanded(true);
        TreeNode node10 = new DefaultTreeNode(partidasSemi.get(1).getJogadorA().getNome(), node1);
        TreeNode node11 = new DefaultTreeNode(partidasSemi.get(1).getJogadorB().getNome(), node1);
        node10.setExpanded(true);
        node11.setExpanded(true);
        //Quartas de Final
        //Time Vermelho
        TreeNode node001 = new DefaultTreeNode(partidas.get(0).getJogadorA().getNome(), node00);
        TreeNode node011 = new DefaultTreeNode(partidas.get(0).getJogadorB().getNome(), node00);
        node001.setExpanded(true);
        node011.setExpanded(true);
        TreeNode node101 = new DefaultTreeNode(partidas.get(1).getJogadorA().getNome(), node01);
        TreeNode node111 = new DefaultTreeNode(partidas.get(1).getJogadorB().getNome(), node01);
        node101.setExpanded(true);
        node111.setExpanded(true);
        //Time Azul
        TreeNode node002 = new DefaultTreeNode(partidas.get(2).getJogadorA().getNome(), node10);
        TreeNode node012 = new DefaultTreeNode(partidas.get(2).getJogadorB().getNome(), node10);
        node002.setExpanded(true);
        node012.setExpanded(true);
        TreeNode node102 = new DefaultTreeNode(partidas.get(3).getJogadorA().getNome(), node11);
        TreeNode node112 = new DefaultTreeNode(partidas.get(3).getJogadorB().getNome(), node11);
        node102.setExpanded(true);
        node112.setExpanded(true);
    }
 
    public TreeNode getRoot() {
        return root;
    }
    
}