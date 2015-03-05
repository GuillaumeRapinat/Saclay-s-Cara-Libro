package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controleur.Controleur_Utilisateur;

public class Vue_Admin extends JFrame {

	private static final long serialVersionUID = 1L;

	// composants de l'interface graphique
	JButton boutonDeconnection;
	JButton boutonSignalement;
	
	
	public Vue_Admin(Controleur_Utilisateur controleurUtilisateur) {
		this.setLayout(new GridBagLayout());
		
		boutonSignalement = new JButton("Signalements");
		
		JPanel panneauDeconnection = new JPanel();
		panneauDeconnection.setBackground(Vue_Utilisateur.marron);
		panneauDeconnection.setLayout(new FlowLayout());
		boutonDeconnection = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/boutons_deconnect.png")));
			boutonDeconnection.setBackground(new Color(0, 0, 0));
			boutonDeconnection.setOpaque(false);
			boutonDeconnection.setBorderPainted(false);
			boutonDeconnection.setPreferredSize(new Dimension(177, 33));
		panneauDeconnection.add(boutonDeconnection);
		
			
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BELOW_BASELINE_LEADING;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(boutonSignalement, gbc);
			
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(panneauDeconnection, gbc);

		boutonDeconnection.setActionCommand(Controleur_Utilisateur.ACTION_DECONNECTION);
		boutonDeconnection.addActionListener(controleurUtilisateur);
		
		// affichage de la fen�tre
		this.setTitle("Panneau d'administration");
		this.getContentPane().setBackground(Vue_Utilisateur.marron);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
}
