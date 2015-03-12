package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import vue.Vue_Liste_Commentaires;
import vue.Vue_Liste_Jaime;


public class Controleur_Mur implements ActionListener {
	public static final String ACTION_LISTE_AIME = "LISTE AIME";
	public static final String ACTION_PUBLIER = "PUBLIER";
	public static final String ACTION_LISTE_COMMENTAIRES = "LISTE COMMENTAIRES";
	public static final String ACTION_SIGNALER = "SIGNALER";
	
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
		case ACTION_SIGNALER:
			signaler();
			break;
		}
	}
	private void signaler() {

		Object[] possibilities = {"Insultant", "Vie privée"};
		String s = (String)JOptionPane.showInputDialog(
		                    new JDialog(),
		                    "Pourquoi voulez-vous signaler cette publication?", "Signalement",
		                    JOptionPane.PLAIN_MESSAGE,
		                    new ImageIcon(getClass().getClassLoader().getResource("images/notifications.png")),
		                    possibilities,
		                    null);
		if ((s != null) && (s.length() > 0)) {
			JOptionPane.showMessageDialog(new JDialog(), "          Très bien, nous allons réprimander cet individu,\naprès avoir confirmé que la publication est de type: " + s, "Signalé", JOptionPane.DEFAULT_OPTION);
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
