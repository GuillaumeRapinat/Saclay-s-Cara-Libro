package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import modele.Modele_Message;
import modele.Modele_Utilisateur;

import vue.FriendButton;
import vue.IdButton;
import vue.Vue_Accueil;
import vue.Vue_Liste_Amis;
import vue.Vue_Message;
import vue.Vue_Utilisateur;

public class Controleur_Message implements ActionListener {

	public static final String ACTION_REPONDRE = "REPONDRE";
	public static final String ACTION_JOINDRE = "JOINDRE";
	public static final String ACTION_SUPPRIMER = "SUPPRIMER";
	public static final String ACTION_LIRE_MESSAGE = "LIRE MESSAGE";
	public static final String ACTION_ENVOYER = "ENVOYER";
	public static final String ACTION_ANNULER = "ANNULER";
	private Modele_Message modeleMessage;
	private JFrame vueM;
	
	public Controleur_Message() {
		modeleMessage = new Modele_Message();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch(e.getActionCommand()) {
			
		case ACTION_REPONDRE:
			repondre();
			break;
		case ACTION_SUPPRIMER:
			Object[] options2 = {"Oui", "Non"};
			int n2 = JOptionPane.showOptionDialog(new JDialog(), "Êtes-vous sûrs de vouloir supprimer ce message", "Supprimer message",
					JOptionPane.YES_NO_OPTION,
				    JOptionPane.QUESTION_MESSAGE,
				    new ImageIcon(getClass().getClassLoader().getResource("images/notifications.png")),
				    options2, options2[0]);
			if (n2 == JOptionPane.YES_OPTION){
				supprimerMessage((IdButton) e.getSource());
				vueM.dispose();
				JOptionPane.showMessageDialog(new JDialog(), "Le message vient d'être supprimé.", "Ami supprimé de votre liste", JOptionPane.NO_OPTION, new ImageIcon(getClass().getClassLoader().getResource("images/notifications.png")));
			}
			break;
		case ACTION_LIRE_MESSAGE:
			lireMessage((IdButton) e.getSource());
			break;
		case ACTION_ENVOYER:
			envoyer();
			break;
		case ACTION_ANNULER:
			annuler();
			break;
		}
	}

	private void lireMessage(IdButton bouton) {
		int id_message = bouton.getId();
		Modele_Message contenuMessage = Modele_Message.recupMessage(id_message);
		((Vue_Message) vueM).message(contenuMessage); //automatiquement, le message est "lu"
		vueM.setAlwaysOnTop(true);
		
		
	}	
	private void annuler() {
		// TODO Auto-generated method stub
		
	}

	private void envoyer() {

		JOptionPane.showMessageDialog(new JDialog(), "Le message vient d'être envoyé.", "Message Envoyé", JOptionPane.DEFAULT_OPTION);

	}

	private void supprimerMessage(IdButton button) {
		int id_message = button.getId();  // recupere l'id de l'ami que l'on veut supprimer
		//int id_message = ((Modele_Message) modeleMessage).getIdM();  
		
		switch(Modele_Message.supprimer(id_message)) {
///////////////////////////////////////continuer/////////////////////////////////		
		case "SUCCES":
			Vue_Message vueLM = (Vue_Message) SwingUtilities.getRoot(button);
			button.getParent().setVisible(false);
			vueLM.pack();
			vueM.setVisible(false);
			vueM.setAlwaysOnTop(false);
			break;
		case "ERREUR SUPPRIMER":
			JOptionPane.showMessageDialog(new JDialog(),"Impossible de supprimer le message.", "Erreur", JOptionPane.ERROR_MESSAGE);
    		break;
		case "ERREUR SERVEUR":
			JOptionPane.showMessageDialog(new JDialog(),"Perte de connection au serveur Cara Libro.", "Erreur", JOptionPane.ERROR_MESSAGE);
			break;
		}
		
	}

	private void repondre() {
		Vue_Message.repondre("Marion", "Peral");		
	}
	

	
}
