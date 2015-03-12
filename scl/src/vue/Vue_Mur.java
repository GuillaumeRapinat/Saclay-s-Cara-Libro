package vue;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


import controleur.Controleur_Mur;
import controleur.Controleur_Utilisateur;

public class Vue_Mur { 

	private static final long serialVersionUID = 1L;

	JLabel photoProfil;
	JButton petitLogo;
	JLabel labelPrenom;
	JLabel labelNom;
	JLabel labelDate;
	JLabel labelHeure;
	JButton nbrAime;
	JButton nbrCom;
	JLabel photoPublier;
	JTextArea textePublier;
	JButton boutonAimer;
	JButton boutonCommenter;
	
	
	Controleur_Mur controleurMur;
	


	static Font f2 = new Font("Helvetica", Font.BOLD + Font.ITALIC, 18);
	static Font f3 = new Font("Helvetica", Font.BOLD + Font.ITALIC, 16);
	static Font f4 = new Font("Helvetica", Font.BOLD + Font.ITALIC, 14);
	

	public Vue_Mur(Controleur_Mur controleurMur) {

		this.controleurMur = controleurMur;
	
	}

	
		
	public JPanel publication(URL photo, String prenom, String nom, String texte, String Date, String Heure, int nbA, int nbC, Container container) {
		
		JPanel panel = new JPanel();
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		container.add(panel);
		panel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.BLACK));
		panel.setBackground(Vue_Utilisateur.marronclair);
		panel.setLayout(new GridBagLayout());

		photoProfil = new JLabel(new ImageIcon(photo));
		//getClass().getClassLoader().getResource("images/profil_petit.png")

		labelPrenom = new JLabel(prenom);
			labelPrenom.setFont(f2);
		labelNom = new JLabel(nom);
			labelNom.setFont(f2);
		labelDate = new JLabel(Date);
			labelDate.setFont(f4);
			labelDate.setForeground(Color.GRAY);
		labelHeure = new JLabel(Heure);
			labelHeure.setFont(f4);
			labelHeure.setForeground(Color.GRAY);
		nbrAime = new JButton(nbA + " j'aime(s)");
			nbrAime.setFont(f3);
			nbrAime.setBackground(new Color(0, 0, 0));
			nbrAime.setOpaque(false);
			nbrAime.setBorderPainted(false);
			nbrAime.setCursor(new Cursor(Cursor.HAND_CURSOR));
		nbrCom = new JButton(nbC + " commentaire(s)");
			nbrCom.setFont(f3);
			nbrCom.setBackground(new Color(0, 0, 0));
			nbrCom.setOpaque(false);
			nbrCom.setBorderPainted(false);
			nbrCom.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		JPanel panneauPublier = new JPanel();
		panneauPublier.setBackground(Vue_Utilisateur.marron);
		panneauPublier.setLayout(new FlowLayout());
		textePublier = new JTextArea(texte);
			JScrollPane scrollPaneArea = new JScrollPane(textePublier,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			textePublier.setCaretPosition(0); // set scrollPane to the top
			textePublier.setLineWrap(true);
			textePublier.setWrapStyleWord(true);
			textePublier.setEditable(false);
			textePublier.setFont(f4);
			textePublier.setBackground(new Color(255,248,192));
			scrollPaneArea.setPreferredSize(new Dimension(500, 60));
		panneauPublier.add(scrollPaneArea);

		JButton boutonSignaler = new JButton("Signaler");
			boutonSignaler.setBackground(Vue_Utilisateur.marron);
			boutonSignaler.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boutonAimer = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/boutons_jaime.png")));
			boutonAimer.setBackground(new Color(0, 0, 0));
			boutonAimer.setOpaque(false);
			boutonAimer.setBorderPainted(false);
			boutonAimer.setCursor(new Cursor(Cursor.HAND_CURSOR));
			boutonAimer.setPreferredSize(new Dimension(94,44));
		boutonCommenter = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/boutons_commenter_petit.png")));
			boutonCommenter.setBackground(new Color(0, 0, 0));
			boutonCommenter.setOpaque(false);
			boutonCommenter.setBorderPainted(false);
			boutonCommenter.setCursor(new Cursor(Cursor.HAND_CURSOR));
			boutonCommenter.setPreferredSize(new Dimension(94,44));

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, -20, 0, 0);
		panel.add(photoProfil, gbc);

		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, 0, 0, 0);
		panel.add(labelPrenom, gbc);

		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
		gbc.insets = new Insets(0, -30, 0, 0);
		panel.add(labelNom, gbc);

		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, 0, 0, 0);
		panel.add(labelDate, gbc);

		gbc.gridx = 3;
		gbc.gridy = 1;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
		gbc.insets = new Insets(0, 20, 0, 0);
		panel.add(labelHeure, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridheight = 2;
		gbc.gridwidth = 6;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, -20, 0, 0);
		panel.add(panneauPublier, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, -20, 0, 0);
		panel.add(boutonSignaler, gbc);
		
		gbc.gridx = 8;
		gbc.gridy = 4;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, 30, 0, 0);
		panel.add(boutonAimer, gbc);

		gbc.gridx = 6;
		gbc.gridy = 4;
		gbc.gridheight = 1;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, -90, 0, 0);
		panel.add(boutonCommenter, gbc);

		gbc.gridx = 5;
		gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
		gbc.insets = new Insets(0, 0, 0, 0);
		panel.add(nbrAime, gbc);

		gbc.gridx = 5;
		gbc.gridy = 1;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
		gbc.insets = new Insets(0, 0, 0, 0);
		panel.add(nbrCom, gbc);

		
		
		nbrAime.setActionCommand(Controleur_Mur.ACTION_LISTE_AIME);
		nbrAime.addActionListener(controleurMur);
		boutonCommenter.setActionCommand(Controleur_Mur.ACTION_LISTE_COMMENTAIRES);
		boutonCommenter.addActionListener(controleurMur);
		nbrCom.setActionCommand(Controleur_Mur.ACTION_LISTE_COMMENTAIRES);
		nbrCom.addActionListener(controleurMur);
		boutonSignaler.setActionCommand(Controleur_Mur.ACTION_SIGNALER);
		boutonSignaler.addActionListener(controleurMur);
		
		
		

	return panel;
		
	}



	public JPanel publication(URL photo, String prenom, String nom, String texte, String Date, String Heure, int nbA, int nbC, URL photoPubli, Container container) {
	
	JPanel panel = new JPanel();
	panel.setAlignmentX(Component.LEFT_ALIGNMENT);
	container.add(panel);
	panel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.BLACK));
	panel.setBackground(Vue_Utilisateur.marronclair);
	panel.setLayout(new GridBagLayout());

	photoProfil = new JLabel(new ImageIcon(photo));
	//getClass().getClassLoader().getResource("images/profil_petit.png")

	labelPrenom = new JLabel(prenom);
		labelPrenom.setFont(f2);
	labelNom = new JLabel(nom);
		labelNom.setFont(f2);
	labelDate = new JLabel(Date);
		labelDate.setFont(f4);
		labelDate.setForeground(Color.GRAY);
	labelHeure = new JLabel(Heure);
		labelHeure.setFont(f4);
		labelHeure.setForeground(Color.GRAY);
	nbrAime = new JButton(nbA + " j'aime(s)");
		nbrAime.setFont(f3);
		nbrAime.setBackground(new Color(0, 0, 0));
		nbrAime.setOpaque(false);
		nbrAime.setBorderPainted(false);
		nbrAime.setCursor(new Cursor(Cursor.HAND_CURSOR));
	nbrCom = new JButton(nbC + " commentaire(s)");
		nbrCom.setFont(f3);
		nbrCom.setBackground(new Color(0, 0, 0));
		nbrCom.setOpaque(false);
		nbrCom.setBorderPainted(false);
		nbrCom.setCursor(new Cursor(Cursor.HAND_CURSOR));
	photoPublier = new JLabel(new ImageIcon(photoPubli));
		photoPublier.setFont(f2);
//getClass().getClassLoader().getResource("images/profil.png")
	
	JPanel panneauPublier = new JPanel();
	
	panneauPublier.setBackground(Vue_Utilisateur.marron);
	panneauPublier.setLayout(new FlowLayout());
	textePublier = new JTextArea(texte);
		JScrollPane scrollPaneArea = new JScrollPane(textePublier,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		textePublier.setCaretPosition(0); // set scrollPane to the top
		textePublier.setLineWrap(true);
		textePublier.setWrapStyleWord(true);
		textePublier.setEditable(false);
		textePublier.setFont(f4);
		textePublier.setBackground(new Color(255,248,192));
		scrollPaneArea.setPreferredSize(new Dimension(500, 80));
	panneauPublier.add(scrollPaneArea);

	JButton boutonSignaler = new JButton("Signaler");
	boutonSignaler.setBackground(Vue_Utilisateur.marron);
	boutonSignaler.setCursor(new Cursor(Cursor.HAND_CURSOR));
	boutonAimer = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/boutons_jaime.png")));
		boutonAimer.setBackground(new Color(0, 0, 0));
		boutonAimer.setOpaque(false);
		boutonAimer.setBorderPainted(false);
		boutonAimer.setCursor(new Cursor(Cursor.HAND_CURSOR));

	boutonCommenter = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/boutons_commenter_petit.png")));
		boutonCommenter.setBackground(new Color(0, 0, 0));
		boutonCommenter.setOpaque(false);
		boutonCommenter.setBorderPainted(false);
		boutonCommenter.setCursor(new Cursor(Cursor.HAND_CURSOR));
		

	GridBagConstraints gbc = new GridBagConstraints();

	gbc.gridx = 0;
	gbc.gridy = 0;
	gbc.gridheight = gbc.gridwidth = 2;
	gbc.anchor = GridBagConstraints.BASELINE_LEADING;
	gbc.insets = new Insets(0, 0, 0, 0);
	panel.add(photoProfil, gbc);

	gbc.gridx = 2;
	gbc.gridy = 0;
	gbc.gridheight = gbc.gridwidth = 1;
	gbc.anchor = GridBagConstraints.BASELINE_LEADING;
	gbc.insets = new Insets(0, 0, 0, 0);
	panel.add(labelPrenom, gbc);

	gbc.gridx = 3;
	gbc.gridy = 0;
	gbc.gridheight = gbc.gridwidth = 1;
	gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
	gbc.insets = new Insets(0, -30, 0, 0);
	panel.add(labelNom, gbc);

	gbc.gridx = 2;
	gbc.gridy = 1;
	gbc.gridheight = gbc.gridwidth = 1;
	gbc.anchor = GridBagConstraints.BASELINE_LEADING;
	gbc.insets = new Insets(0, 0, 0, 0);
	panel.add(labelDate, gbc);

	gbc.gridx = 3;
	gbc.gridy = 1;
	gbc.gridheight = gbc.gridwidth = 1;
	gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
	gbc.insets = new Insets(0, 20, 0, 0);
	panel.add(labelHeure, gbc);

	gbc.gridx = 0;
	gbc.gridy = 2;
	gbc.gridheight = 1;
	gbc.gridwidth = 6;
	gbc.anchor = GridBagConstraints.BASELINE_LEADING;
	gbc.insets = new Insets(0, 0, 0, 0);
	panel.add(panneauPublier, gbc);

	gbc.gridx = 0;
	gbc.gridy = 4;
	gbc.gridheight = 1;
	gbc.gridwidth = 1;
	gbc.anchor = GridBagConstraints.BASELINE_LEADING;
	gbc.insets = new Insets(0, 0, 0, 0);
	panel.add(boutonSignaler, gbc);
		
	gbc.gridx = 8;
	gbc.gridy = 4;
	gbc.gridheight = 1;
	gbc.gridwidth =12;
	gbc.anchor = GridBagConstraints.BASELINE_LEADING;
	gbc.insets = new Insets(-5, -140, 0, 0);
	panel.add(boutonAimer, gbc);

	gbc.gridx = 6;
	gbc.gridy = 4;
	gbc.gridheight = 1;
	gbc.gridwidth = 2;
	gbc.anchor = GridBagConstraints.BASELINE_LEADING;
	gbc.insets = new Insets(-5, -110, 0, 0);
	panel.add(boutonCommenter, gbc);

	gbc.gridx = 5;
	gbc.gridy = 0;
	gbc.gridheight = gbc.gridwidth = 1;
	gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
	gbc.insets = new Insets(0, 0, 0, 0);
	panel.add(nbrAime, gbc);

	gbc.gridx = 5;
	gbc.gridy = 1;
	gbc.gridheight = gbc.gridwidth = 1;
	gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
	gbc.insets = new Insets(0, 0, 0, 0);
	panel.add(nbrCom, gbc);

	gbc.gridx = 6;
	gbc.gridy = 0;
	gbc.gridheight = 3;
	gbc.gridwidth = 2;
	gbc.anchor = GridBagConstraints.BASELINE_LEADING;
	gbc.insets = new Insets(-15, 0, 0, 0);
	panel.add(photoPublier, gbc);
	
	
	nbrAime.setActionCommand(Controleur_Mur.ACTION_LISTE_AIME);
	nbrAime.addActionListener(controleurMur);
	boutonCommenter.setActionCommand(Controleur_Mur.ACTION_LISTE_COMMENTAIRES);
	boutonCommenter.addActionListener(controleurMur);
	nbrCom.setActionCommand(Controleur_Mur.ACTION_LISTE_COMMENTAIRES);
	nbrCom.addActionListener(controleurMur);
	
	

return panel;
	
}
}