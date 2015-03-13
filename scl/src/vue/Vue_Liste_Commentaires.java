package vue;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


import controleur.Controleur_Mur;


public class Vue_Liste_Commentaires extends JFrame{ 

	private static final long serialVersionUID = 1L;
	
	protected static int tempmouse = 0;

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
	static Font f6 = new Font("Helvetica", Font.BOLD + Font.ITALIC, 12);

	public Vue_Liste_Commentaires() {

	//	this.controleurMur = controleurMur;
		JPanel grandPanel = new JPanel();		
		JPanel panelC = new JPanel();
		grandPanel.setLayout(new BoxLayout(grandPanel, BoxLayout.Y_AXIS));
		
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
		jsp.setMaximumSize(new Dimension(400,150));
		grandPanel.add(jsp);	//le panel est déjà dans un scrollbar, il ne reste que mettre le scrollbar dans la fenêtre!!
		grandPanel.add(ecireCommentaire(getClass().getClassLoader().getResource("images/profil_tout_petit.png"), "Quelqu'un,", "mais qui??"));
		

		this.add(grandPanel);
	
		
		this.setTitle("Liste de commentaires");
		this.pack();
		this.setSize(350, 500);
		
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
			labelPrenom.setPreferredSize(new Dimension(80,20));
		labelNom = new JLabel(nom);
			labelNom.setFont(f4);
			labelNom.setPreferredSize(new Dimension(80,20));
		labelDate = new JLabel(Date);
			labelDate.setFont(f6);
			labelDate.setForeground(Color.GRAY);
		labelHeure = new JLabel(Heure);
			labelHeure.setFont(f6);
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

		boutonAimer = new JButton("J'aime");
		boutonAimer.setFont(new Font("Helvetica", Font.BOLD + Font.ITALIC, 16));
		boutonAimer.setBackground(Vue_Utilisateur.marron);
			
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 2;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, 0, 0, 0);
		panel.add(photoProfil, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, 0, 0, 0);
		panel.add(labelPrenom, gbc);

		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
		gbc.insets = new Insets(0, 10, 0, 0);
		panel.add(labelNom, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(-10, 0, 0, 0);
		panel.add(labelDate, gbc);

		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
		gbc.insets = new Insets(-10, 10, 0, 0);
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
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, 0, 0, 0);
		panel.add(panneauCommentaires, gbc);
		
		
		nbrAime.setActionCommand(Controleur_Mur.ACTION_LISTE_AIME);
		nbrAime.addActionListener(controleurMur);
		
		
		

	return panel;
		
	}
	
	
	public JPanel ecireCommentaire(URL url, String prenom, String nom) {
		

		//super.Modele_Utilisateur(url, prenom, nom);

		JPanel panel = new JPanel();
		controleurMur = new Controleur_Mur();
		panel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.BLACK));
		panel.setBackground(Vue_Utilisateur.marronclair);
		panel.setLayout(new GridBagLayout());

		photoProfil = new JLabel(new ImageIcon(url));

		labelPrenom = new JLabel(prenom);
			labelPrenom.setFont(f4);
			labelPrenom.setPreferredSize(new Dimension(90,20));
		labelNom = new JLabel(nom);
			labelNom.setFont(f4);
			labelNom.setPreferredSize(new Dimension(90,20));
		JPanel panneauCommentaires = new JPanel();
		final JTextArea texteCommentaire = new JTextArea("Commentaire");
			JScrollPane scrollPaneArea2 = new JScrollPane(texteCommentaire,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			texteCommentaire.setFont(Vue_Message.f6);
			texteCommentaire.setLineWrap(true);
			texteCommentaire.setWrapStyleWord(true);
			texteCommentaire.setBackground(Vue_Utilisateur.marronclair);
			scrollPaneArea2.setBorder(null);
			scrollPaneArea2.setPreferredSize(new Dimension(330, 40));
			panneauCommentaires.setOpaque(false);
			texteCommentaire.addMouseListener( new  MouseAdapter(){
				 public void mousePressed(MouseEvent e) {
					 if (Vue_Liste_Commentaires.tempmouse == 0){
						 tempmouse = 1;
						 texteCommentaire.setText("");
					 }
				 }
			});
		panneauCommentaires.add(scrollPaneArea2);

		boutonCommenter = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/boutons_commenter_petit.png")));
			boutonCommenter.setBackground(new Color(0, 0, 0));
			boutonCommenter.setOpaque(false);
			boutonCommenter.setBorderPainted(false);
			boutonCommenter.setCursor(new Cursor(Cursor.HAND_CURSOR));
			boutonCommenter.setPreferredSize(new Dimension(94,44));


		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, 0, 0, 0);
		panel.add(photoProfil, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, 0, 0, 0);
		panel.add(labelPrenom, gbc);

		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
		gbc.insets = new Insets(0, 10, 0, 0);
		panel.add(labelNom, gbc);

		
		gbc.gridx = 5;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, 0, 0, 0);
		panel.add(boutonCommenter, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridheight = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(0, -20, 0, 0);
		panel.add(panneauCommentaires, gbc);
		
		

		panel.setMinimumSize(new Dimension(300,150));
	return panel;
}
}