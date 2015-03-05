package vue;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.Controleur_Utilisateur;

public class Vue_Charger_Image extends JFrame {

	private static final long serialVersionUID = 1L;
	
	JLabel photoProfil;
	JButton boutonCharger;
	JButton boutonTerminer;
	JButton boutonAnnuler;
	
	
	static Font f2 = new Font("Helvetica", Font.BOLD+Font.ITALIC, 16);
	
	public Vue_Charger_Image(){
		
		this.setLayout(new GridBagLayout());
		
		photoProfil = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("images/profil.png")));

		boutonCharger = new JButton("Charger");
		boutonCharger.setFont(Vue_Mur.f4);
		boutonCharger.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		JPanel panneauTerminer = new JPanel();
		panneauTerminer.setLayout(new FlowLayout());
		boutonTerminer = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/boutons_ajouter.png")));
			boutonTerminer.setBackground(new Color(0, 0, 0));
			boutonTerminer.setOpaque(false);
			boutonTerminer.setBorderPainted(false);
			boutonTerminer.setPreferredSize(new Dimension(118, 55));
			boutonTerminer.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panneauTerminer.add(boutonTerminer);
			
		boutonAnnuler = new JButton("Annuler");
		boutonAnnuler.setFont(Vue_Mur.f4);
		boutonAnnuler.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = gbc.gridy = 0;
		gbc.gridheight = 3;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(photoProfil, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BELOW_BASELINE_LEADING;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(boutonCharger, gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 2;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(50, 0, 0, 0);
		this.add(panneauTerminer, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BELOW_BASELINE_LEADING;
		gbc.insets = new Insets(50, 0, 0, 0);
		this.add(boutonAnnuler, gbc);
		
		
//		boutonAnnuler.setActionCommand(Controleur_Utilisateur.ACTION_ANNULER_AJOUTER_IMAGE);
//		boutonAnnuler.addActionListener(new Controleur_Utilisateur());
		
		this.setTitle("Image à charger");
		this.getContentPane().setBackground(Vue_Utilisateur.marron);
		this.pack();
		//this.setSize(900, 600);
		
		
	}

	


}
