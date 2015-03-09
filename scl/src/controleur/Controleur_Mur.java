package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import vue.Vue_Liste_Commentaires;
import vue.Vue_Liste_Jaime;


public class Controleur_Mur implements ActionListener {
	public static final String ACTION_LISTE_AIME = "LISTE AIME";
	public static final String ACTION_PUBLIER = "PUBLIER";
	public static final String ACTION_LISTE_COMMENTAIRES = "LISTE COMMENTAIRES";
	
public Controleur_Mur() {
		
	}
	// constantes pour les actionCommand
	

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		switch(ae.getActionCommand()) {
			
		case ACTION_LISTE_AIME:
			listeAime();
			break;
		case ACTION_LISTE_COMMENTAIRES:
			listeCommentaires();
			break;
		}
	}
	private void listeCommentaires() {
		Vue_Liste_Commentaires vueC = new Vue_Liste_Commentaires();
		vueC.setVisible(true);
		
	}
		
	private void listeAime() {
		Vue_Liste_Jaime vue4 = new Vue_Liste_Jaime();
		vue4.setVisible(true);
		
	}
	
	
}
