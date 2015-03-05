package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import vue.Vue_Accueil;
import vue.Vue_Liste_Jaime;
import vue.Vue_Mur;

public class Controleur_Mur implements ActionListener {
	private JFrame vuePubli;
	public static final String ACTION_LISTE_AIME = "LISTE_AIME";
	public static final String ACTION_PUBLIER = "PUBLIER";
	

	// constantes pour les actionCommand
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch(e.getActionCommand()) {
		
		case ACTION_LISTE_AIME:
			listeAime();
			break;
		}
	}
	public Controleur_Mur() {
		
	}
		
	private void listeAime() {
		Vue_Liste_Jaime vue4 = new Vue_Liste_Jaime();
		vue4.setVisible(true);
		
	}
	
	
}
