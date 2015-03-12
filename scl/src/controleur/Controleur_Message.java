package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import vue.Vue_Message;

public class Controleur_Message implements ActionListener {

	public static final String ACTION_REPONDRE = "REPONDRE";
	public static final String ACTION_JOINDRE = "JOINDRE";
	public static final String ACTION_SUPPRIMER = "SUPPRIMER";
	public static final String ACTION_LIRE_MESSAGE = "LIRE MESSAGE";
	public static final String ACTION_ENVOYER = "ENVOYER";
	public static final String ACTION_ANNULER = "ANNULER";
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		switch(ae.getActionCommand()) {
			
		case ACTION_REPONDRE:
			repondre();
			break;
		case ACTION_SUPPRIMER:
			supprimer();
			break;
		case ACTION_LIRE_MESSAGE:
			Vue_Message.message("Marion", "Peral", "Les courses de ce soir", "N'oublies pas d'acheter du lait stp, on en a plus\nAvant de partir tu peux regarder s'il reste du dentifrice?\n ah et prends peut être des bières pour demain soir!\nbisous <3", "09/03/2015", "14:02:34");
			break;
		case ACTION_ENVOYER:
			envoyer();
			break;
		case ACTION_ANNULER:
			annuler();
			break;
		}
	}

	private void annuler() {
		// TODO Auto-generated method stub
		
	}

	private void envoyer() {

		JOptionPane.showMessageDialog(new JDialog(), "Le message vient d'être envoyé.", "Message Envoyé", JOptionPane.DEFAULT_OPTION);

	}

	private void supprimer() {

		Object[] options2 = {"Oui", "Non"};
		int n2 = JOptionPane.showOptionDialog(new JDialog(), "Êtes-vous sûrs de vouloir supprimer ce message", "Supprimer message",
				JOptionPane.YES_NO_OPTION,
			    JOptionPane.QUESTION_MESSAGE,
			    new ImageIcon(getClass().getClassLoader().getResource("images/notifications.png")),
			    options2, options2[0]);
		if (n2 == JOptionPane.YES_OPTION){
			JOptionPane.showMessageDialog(new JDialog(), "Le message vient d'être supprimé.", "Ami supprimé de votre liste", JOptionPane.NO_OPTION, new ImageIcon(getClass().getClassLoader().getResource("images/notifications.png")));
		}
		
	}

	private void repondre() {
		Vue_Message.repondre("Marion", "Peral");		
	}
	

	
}
