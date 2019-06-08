package view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import controller.ChaveController;
import model.Chave;

@ManagedBean(name="scoreView")
@ViewScoped
public class ScoreView implements Serializable {
     
    private TreeNode root;
     
    @PostConstruct
    public void init() {
    	List<Chave> chaves = new ChaveController().Listar();
    	for (Chave chave : chaves) {
    		if(chave.getTipoChave() == 3) {
    			root = new DefaultTreeNode(chave.getPartidas().get(0).getJogadorVencedor().getNome(), null);
    		}
    		if(chave.getTipoChave() == 2) {
    			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\AAAAAAAAAAAAAAAAAAAA");
    			System.out.println(chave.getPartidas().get(0).getJogadorA().getNome());
        		System.out.println(chave.getPartidas().get(0).getJogadorB().getNome());
        		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\AAAAAAAAAAAAAAAAAAAA");
    			//System.out.println(chave.getPartidas());
    			//root.getChildren().add(new DefaultTreeNode(chave.getPartidas().get(1).getJogadorA().getNome()));
    				 //root.getChildren().add(new DefaultTreeNode(chave.getPartidas().get(s).getJogadorB().getNome()));
    			 
    		}
		}
    	
    	//if(chaves.get(0).getTipoChave() == 3) {
    		//root = new DefaultTreeNode(chaves.get(0).getPartidas().get(0).getJogadorVencedor().getNome(), null);
    	//}
    	//root = new DefaultTreeNode("Teste", null);
        //root.setExpanded(true);
        //TreeNode node0 = new DefaultTreeNode("Node 0", root);
        //TreeNode node1 = new DefaultTreeNode("Node 1", root);
        //TreeNode node00 = new DefaultTreeNode("Node 0.0", node0);
        //TreeNode node01 = new DefaultTreeNode("Node 0.1", node0);
        //TreeNode node10 = new DefaultTreeNode("Node 1.0", node1);
        //node1.getChildren().add(new DefaultTreeNode("Node 1.1"));
        //node00.getChildren().add(new DefaultTreeNode("Node 0.0.0"));
       // node00.getChildren().add(new DefaultTreeNode("Node 0.0.1"));
        //node01.getChildren().add(new DefaultTreeNode("Node 0.1.0"));
        //node10.getChildren().add(new DefaultTreeNode("Node 1.0.0"));
        //root.getChildren().add(new DefaultTreeNode("Node 2"));
    }
 
    public TreeNode getRoot() {
        return root;
    }
    
}