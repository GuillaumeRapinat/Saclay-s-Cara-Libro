package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vue.Vue_Message;

public class Controleur_Message implements ActionListener {

	public static final String ACTION_REPONDRE = "REPONDRE";
	public static final String ACTION_JOINDRE = "JOINDRE";
	public static final String ACTION_SUPPRIMER = "SUPPRIMER";
	public static final String ACTION_LIRE_MESSAGE = "LIRE MESSAGE";
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		switch(ae.getActionCommand()) {
			
		case ACTION_REPONDRE:
			repondre();
			break;
		case ACTION_JOINDRE:
			joindre();
			break;
		case ACTION_SUPPRIMER:
			supprimer();
			break;
		case ACTION_LIRE_MESSAGE:
			Vue_Message.message("Marion", "Peral", "Les courses de ce soir", "N'oublies pas d'acheter du lait stp, on en a plus\nAvant de partir tu peux regarder s'il reste du dentifrice?\n ah et prends peut être des bières pour demain soir!\nbisous <3", "09/03/2015", "14:02:34");
			break;
		}
	}

	private void supprimer() {
		// TODO Auto-generated method stub
		
	}

	private void joindre() {
		// TODO Auto-generated method stub
		
	}

	private void repondre() {
		// TODO Auto-generated method stub
		
	}
	

	
}
