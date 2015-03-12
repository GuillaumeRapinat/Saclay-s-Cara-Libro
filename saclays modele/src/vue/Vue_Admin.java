package vue;

import javax.swing.JButton;
import javax.swing.JFrame;

import controleur.Controleur_Utilisateur;

public class Vue_Admin extends JFrame {

	private static final long serialVersionUID = 1L;

	// composants de l'interface graphique
	JButton boutonDeconnection;
	
	public Vue_Admin(Controleur_Utilisateur controleurUtilisateur) {
		boutonDeconnection = new JButton("Se Déconnecter");
		this.add(boutonDeconnection);
		boutonDeconnection.setActionCommand(Controleur_Utilisateur.ACTION_DECONNEXION);
		boutonDeconnection.addActionListener(controleurUtilisateur);
		
		// affichage de la fenêtre
		this.setTitle("Panneau d'administration");
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
}
