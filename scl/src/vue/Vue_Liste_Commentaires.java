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

public class Vue_Liste_Commentaires extends JFrame{ 

	private static final long serialVersionUID = 1L;

	JLabel photoProfil;
	JButton petitLogo;					// quand on clique sur le bouton, on peut aller sur le profil de la personne!!
	JLabel labelPrenom;
	JLabel labelNom;
	JLabel labelDate;
	JLabel labelHeure;
	JButton nbrAime;
//	JButton boutoncommenter;
	JTextArea textePublier;
	JButton boutonAimer;
	JButton boutonCommenter;
	Controleur_Mur controleurMur;
	
	static Font f4 = new Font("Helvetica", Font.BOLD + Font.ITALIC, 14);
	static Font f5 = new Font("Helvetica", Font.BOLD, 14);
	

	public Vue_Liste_Commentaires() {

	//	this.controleurMur = controleurMur;
		
		
		JPanel panelC = new JPanel();
		
	    
		panelC.setLayout(new BoxLayout(panelC, BoxLayout.Y_AXIS)); 

		//getClass().getClassLoader().getResource("images/profil_tout_petit.png")
		panelC.add(commentaire(getClass().getClassLoader().getResource("images/profil_tout_petit.png"), "Guillaume", "Rapinat", "ba be bi bo bu", "08/03/2015", "15:57:34", 3));
		panelC.add(Box.createRigidArea(new Dimension(0,10)));
		panelC.add(commentaire(getClass().getClassLoader().getResource("images/profil_tout_petit.png"), "Estelle", "Malherbe", "guillaume t'es un gamin, je vais te supprimer de maliste d'amis", "09/03/2015", "12:35:34", 7));
		panelC.add(Box.createRigidArea(new Dimension(0,10)));
		panelC.add(commentaire(getClass().getClassLoader().getResource("images/profil_tout_petit.png"), "Wilfried", "Rabouin", "personne ne veux voir ma bite alors??", "09/03/2015", "14:30:00", 0));
		panelC.add(Box.createRigidArea(new Dimension(0,10)));
		panelC.add(commentaire(getClass().getClassLoader().getResource("images/profil_tout_petit.png"), "Guillaume", "Rapinat", "blou\n bleuh\n  blu\n   blah\n    boooooooo!!!!", "09/03/2015", "15:47:39", 2));
		panelC.add(Box.createRigidArea(new Dimension(0,10)));
		panelC.add(commentaire(getClass().getClassLoader().getResource("images/profil_tout_petit.png"), "Estelle", "Malherbe", "NON WILFRIED! Et ok Guillaume je te supprime", "09/03/2015", "17:02:10", 19));
		
		
		JScrollPane jsp = new JScrollPane(panelC,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(jsp);	//le panel est déjà dans un scrollbar, il ne reste que mettre le scrollbar dans la fenêtre!!
		
		
	
		
		this.setTitle("Liste de commentaires");
		this.pack();
		this.setSize(400, 500);
		
	}

	
		
	public JPanel commentaire(URL photo, String prenom, String nom, String texte, String Date, String Heure, int nbA) {
		
		JPanel panel = new JPanel();
		controleurMur = new Controleur_Mur();
		panel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.BLACK));
		panel.setBackground(Vue_Utilisateur.marronclair);
		panel.setLayout(new GridBagLayout());

		photoProfil = new JLabel(new ImageIcon(photo));

		labelPrenom = new JLabel(prenom);
			labelPrenom.setFont(f4);
		labelNom = new JLabel(nom);
			labelNom.setFont(f4);
		labelDate = new JLabel(Date);
			labelDate.setFont(f4);
			labelDate.setForeground(Color.GRAY);
		labelHeure = new JLabel(Heure);
			labelHeure.setFont(f4);
			labelHeure.setForeground(Color.GRAY);
		nbrAime = new JButton(nbA + " j'aime(s)");
			nbrAime.setFont(f4);
			nbrAime.setBackground(new Color(0, 0, 0));
			nbrAime.setOpaque(false);
			nbrAime.setBorderPainted(false);
			nbrAime.setCursor(new Cursor(Cursor.HAND_CURSOR));
	
		JPanel panneauCommentaires = new JPanel();
		JTextArea texteCommentaire = new JTextArea(texte);
		texteCommentaire.setBackground(Vue_Utilisateur.marron);
			JScrollPane scrollPaneArea = new JScrollPane(texteCommentaire,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		    texteCommentaire.setCaretPosition(MAXIMIZED_VERT); //set scrollPane to the top
			texteCommentaire.setFont(Vue_Message.f6);
			texteCommentaire.setLineWrap(true);
			texteCommentaire.setWrapStyleWord(true);
			texteCommentaire.setEditable(false);
			texteCommentaire.setBackground(Vue_Utilisateur.marronclair);
			scrollPaneArea.setBorder(null);
			scrollPaneArea.setPreferredSize(new Dimension(315, 40));
			panneauCommentaires.setOpaque(false);
		panneauCommentaires.add(scrollPaneArea);

/*		boutonAimer = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/boutons_jaime_applatit.png")));
			boutonAimer.setBackground(new Color(0, 0, 0));
			boutonAimer.setOpaque(false);
			boutonAimer.setBorderPainted(false);
			boutonAimer.setCursor(new Cursor(Cursor.HAND_CURSOR));
			boutonAimer.setPreferredSize(new Dimension(94,44));
*/
		boutonAimer = new JButton("J'aime");
		boutonAimer.setFont(new Font("Helvetica", Font.BOLD + Font.ITALIC, 16));
		boutonAimer.setBackground(Vue_Utilisateur.marron);
			
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 2;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, -20, 0, 0);
		panel.add(photoProfil, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE;
		gbc.insets = new Insets(0, 0, 0, 0);
		panel.add(labelPrenom, gbc);

		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE;
		gbc.insets = new Insets(0, 0, 0, 0);
		panel.add(labelNom, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE;
		gbc.insets = new Insets(0, 0, 0, 0);
		panel.add(labelDate, gbc);

		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE;
		gbc.insets = new Insets(0, 0, 0, 0);
		panel.add(labelHeure, gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE;
		gbc.insets = new Insets(0, 0, 0, 0);
		panel.add(nbrAime, gbc);

		gbc.gridx = 3;
		gbc.gridy = 1;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE;
		gbc.insets = new Insets(0, 0, 0, 0);
		panel.add(boutonAimer, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridheight = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.BASELINE;
		gbc.insets = new Insets(0, -35, 0, 0);
		panel.add(panneauCommentaires, gbc);
		
		
/*		gbc.gridx = 6;
		gbc.gridy = 4;
		gbc.gridheight = 1;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, -110, 0, 0);
		panel.add(boutonCommenter, gbc);
*/
		

		
		nbrAime.setActionCommand(Controleur_Mur.ACTION_LISTE_AIME);
		nbrAime.addActionListener(controleurMur);
		
		
		

	return panel;
		
	}
}