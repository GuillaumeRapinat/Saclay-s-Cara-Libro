package vue;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controleur.Controleur_Mur;
import controleur.Controleur_Utilisateur;
import controleur.Controleur_Message;

public class Vue_Utilisateur extends JFrame {

	private static final long serialVersionUID = 1L;
	
	// composants de l'interface graphique
	JPanel panneauMur;
	JLabel photoProfil;
	JLabel labelPrenom;
	JLabel labelNom;
	JLabel labelAge;
	JLabel labelSexe;
	JButton boutonModifier;
	JButton boutonMessage;
	JButton boutonDeconnection;
	JButton boutonAmis;
	JButton boutonPublier;
	JButton boutonChat;
	JButton boutonProfil;
	JButton boutonActualite;
	JTextArea textePublier;
	public static JFileChooser fc;
	Controleur_Mur controleurMur;
	Controleur_Utilisateur controleurUtilisateur;
	
	public static Color marron = new Color(207,168,80);
	static Color marronclair = new Color(255, 231, 136);
	static Font f2 = new Font("Helvetica", Font.BOLD+Font.ITALIC, 16);

	GridBagConstraints gbc = new GridBagConstraints();
	
	public Vue_Utilisateur(Controleur_Utilisateur controleurUtilisateur) {
		
		this.controleurUtilisateur = controleurUtilisateur;
		
		this.setLayout(new GridBagLayout());
		
		ajouterProfil();
		
		ajouterMur();
		
		// affichage de la fen�tre
		this.setTitle("Saclay's Cara Libro");
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(900, 500);
		this.setVisible(true);
	}

	private void ajouterMur() {
		panneauMur = new JPanel();
		panneauMur.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
		panneauMur.setLayout(new BoxLayout(panneauMur, BoxLayout.Y_AXIS));
		panneauMur.setSize(500, 200);
		controleurMur = new Controleur_Mur();
		Vue_Mur vue2 = new Vue_Mur(controleurMur);

		vue2.publication(getClass().getClassLoader().getResource("images/profil_petit.png"), "Guillaume", "Rapinat", "Ah oui, je suis bien arrivé en retard, et sous la pluie en plus...\n Bad day begins...", "04/03/2015", "10:05:32", 5, 12, panneauMur);

			panneauMur.add(Box.createRigidArea(new Dimension(0,20)));
		vue2.publication(getClass().getClassLoader().getResource("images/profil_petit.png"), "Wilfried", "Rabouin", "Coucou, tu veux voir ma bite?", "04/03/2015", "10:02:07", 52, 7, getClass().getClassLoader().getResource("images/penis.jpg"), panneauMur);
			panneauMur.add(Box.createRigidArea(new Dimension(0,20)));
		vue2.publication(getClass().getClassLoader().getResource("images/profil_petit.png"), "Guillaume", "Rapinat", "Et merde, je vais encore être en retard!!\n Vive les vacances putain!!!!!", "04/03/2015", "09:45:52", 2, 0, panneauMur);

		JScrollPane jsp = new JScrollPane(panneauMur, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setPreferredSize(new Dimension(600, 350));
		this.add(jsp); // le panel est déjà dans un scrollbar, il ne reste que
						// mettre le scrollbar dans la fenêtre!!
		
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.gridheight = GridBagConstraints.REMAINDER;
		gbc.gridwidth = 8;
		gbc.anchor = GridBagConstraints.BASELINE;
		gbc.insets = new Insets(0, 0, 0, -60);
		this.add(jsp, gbc);
		
		panneauMur.setBackground(marron);
		
/*		vue2.boutonCommenter.setActionCommand(Controleur_Mur.ACTION_LISTE_COMMENTAIRES);
		vue2.boutonCommenter.addActionListener(controleurMur);
		vue2.nbrCom.setActionCommand(Controleur_Mur.ACTION_LISTE_COMMENTAIRES);
		vue2.nbrCom.addActionListener(controleurMur);
*/		
	}

	private void ajouterProfil() {
		photoProfil = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("images/profil.png")));

		labelAge = new JLabel("Age");
			labelAge.setFont(f2);
		labelSexe = new JLabel("Sexe");
			labelSexe.setFont(f2);
		
			
			
		JPanel panneauModifier = new JPanel();
		panneauModifier.setLayout(new FlowLayout());
		panneauModifier.setBackground(Vue_Utilisateur.marron);	
		boutonModifier = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/boutons_modifier.png")));
			boutonModifier.setBackground(new Color(0, 0, 0));
			boutonModifier.setOpaque(false);
			boutonModifier.setBorderPainted(false);
			boutonModifier.setCursor(new Cursor(Cursor.HAND_CURSOR));
			boutonModifier.setPreferredSize(new Dimension(118, 55));
		panneauModifier.add(boutonModifier);

		labelPrenom = new JLabel("Prénom");
			labelPrenom.setFont(f2);
		labelNom = new JLabel("Nom");
			labelNom.setFont(f2);
		
		JPanel panneauPublier = new JPanel();
		panneauPublier.setLayout(new FlowLayout());
		panneauPublier.setBackground(Vue_Utilisateur.marron);
		textePublier = new JTextArea("Exprimez-vous");
			JScrollPane scrollPaneArea = new JScrollPane(textePublier,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);   
			textePublier.setCaretPosition(0); //set scrollPane to the top  
			textePublier.setLineWrap(true);
			textePublier.setWrapStyleWord(true);
			textePublier.setFont(Vue_Mur.f4);
			textePublier.setBackground(new Color(255,248,192));
			textePublier.setCursor(new Cursor(Cursor.TEXT_CURSOR));
			scrollPaneArea.setPreferredSize(new Dimension(500, 110)); 
			panneauPublier.add(scrollPaneArea);  
			
		boutonPublier = new JButton (new ImageIcon(getClass().getClassLoader().getResource("images/boutons_publier.png")));
			boutonPublier.setBackground(new Color(0, 0, 0));
			boutonPublier.setOpaque(false);
			boutonPublier.setBorderPainted(false);
			boutonPublier.setCursor(new Cursor(Cursor.HAND_CURSOR));			
			boutonPublier.setPreferredSize(new Dimension(118, 55));
			
			
		JPanel panneauAjouter = new JPanel();
		panneauAjouter.setLayout(new FlowLayout());
		panneauAjouter.setBackground(Vue_Utilisateur.marron);
		JButton boutonAjouter = new JButton (new ImageIcon(getClass().getClassLoader().getResource("images/bouton_photo.png")));
			boutonAjouter.setBackground(new Color(0, 0, 0));
			boutonAjouter.setOpaque(false);
			boutonAjouter.setBorderPainted(false);
			boutonAjouter.setCursor(new Cursor(Cursor.HAND_CURSOR));
			boutonAjouter.setPreferredSize(new Dimension(57, 57));
		panneauAjouter.add(boutonAjouter);
		
//titre image qui va changé/apparaitre selon le résultat de l'ajout
//c'est un bouton qui fonctionne que s'il y a une image et qui permet de supprimer l'image téléchargé
		JButton titreImage = new JButton("titre image téléchargé");
			titreImage.setBackground(null);
			titreImage.setBorderPainted(false);
			titreImage.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		boutonDeconnection = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/boutons_deconnect.png")));
			boutonDeconnection.setBackground(new Color(0, 0, 0));
			boutonDeconnection.setOpaque(false);
			boutonDeconnection.setBorderPainted(false);
			boutonDeconnection.setCursor(new Cursor(Cursor.HAND_CURSOR));
			//boutonDeconnection.setPreferredSize(new Dimension(50, 300));
		boutonMessage = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/message.png")));
			boutonMessage.setBackground(new Color(0, 0, 0));
			boutonMessage.setOpaque(false);
			boutonMessage.setBorderPainted(false);
			boutonMessage.setCursor(new Cursor(Cursor.HAND_CURSOR));
			boutonMessage.setPreferredSize(new Dimension(59, 102));
		JPanel panneauAmis = new JPanel();
		panneauAmis.setLayout(new FlowLayout());
		panneauAmis.setBackground(Vue_Utilisateur.marron);
		boutonAmis = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ami.png")));
			boutonAmis.setBackground(new Color(0, 0, 0));
			boutonAmis.setOpaque(false);
			boutonAmis.setBorderPainted(false);
			boutonAmis.setCursor(new Cursor(Cursor.HAND_CURSOR));
			boutonAmis.setPreferredSize(new Dimension(85, 64));
		panneauAmis.add(boutonAmis);
		boutonProfil = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/profil_tout_petit.png")));
			boutonProfil.setBackground(new Color(0, 0, 0));
			boutonProfil.setOpaque(false);
			boutonProfil.setBorderPainted(false);
			boutonProfil.setCursor(new Cursor(Cursor.HAND_CURSOR));
			boutonProfil.setPreferredSize(new Dimension(50, 45));
			
		boutonActualite = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/logo_SCL_tout_petit.png")));
			boutonActualite.setBackground(new Color(0, 0, 0));
			boutonActualite.setOpaque(false);
			boutonActualite.setBorderPainted(false);
			boutonActualite.setCursor(new Cursor(Cursor.HAND_CURSOR));
			boutonActualite.setPreferredSize(new Dimension(50, 45));	
			
		JTextField barreRechercher = new JTextField ("Rechercher quelqu'un");
			barreRechercher.setFont(f2);
			barreRechercher.setBackground(new Color(255,248,192));
			barreRechercher.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			barreRechercher.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		JButton boutonRechercher = new JButton("Rechercher");
		boutonRechercher.setFont(Vue_Mur.f4);
		boutonRechercher.setBackground(marronclair);
		boutonRechercher.setCursor(new Cursor(Cursor.HAND_CURSOR));
			
		boutonChat = new JButton("Chat");
			boutonChat.setFont(Vue_Mur.f4);
			boutonChat.setBackground(marronclair);
			
		this.getContentPane().setBackground(Vue_Utilisateur.marron);
		
				//mise en place sur la page
		
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 3;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(-20, 0, 0, 0);
		this.add(photoProfil, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING;
		gbc.insets = new Insets(-35, 0, 0, 50);
		this.add(labelAge, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING;
		gbc.insets = new Insets(-35, 0, 0, 0);
		this.add(labelSexe, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridheight = 1;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.ABOVE_BASELINE;
		gbc.insets = new Insets(-20, 0, 0, 0);
		this.add(boutonModifier, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, 9, 0, 0);
		this.add(labelPrenom, gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, 40, 0, 0);
		this.add(labelNom, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridheight = 3;
		gbc.gridwidth = 6;
		gbc.anchor = GridBagConstraints.ABOVE_BASELINE;
		gbc.insets = new Insets(-60, 0, 0, 0);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(panneauPublier, gbc);
		
		gbc.gridx = 6;
		gbc.gridy = 4;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
		gbc.insets = new Insets(-20, 150, 0, 0);
		this.add(boutonPublier, gbc);
		
		gbc.gridx = 4;
		gbc.gridy = 4;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(-20, -90, 0, -50);
		this.add(titreImage, gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 4;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(-40, -215, 0, -50);
		this.add(panneauAjouter, gbc);
		
		
		
		gbc.gridx = 8;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 3;
		gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(boutonDeconnection, gbc);
		
		gbc.gridx = 8;
		gbc.gridy = 1;
		gbc.gridheight = 2;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, 35, 0, -20);
		this.add(boutonMessage, gbc);
		
		gbc.gridx = 9;
		gbc.gridy = 1;
		gbc.gridheight = 2;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(panneauAmis, gbc);
		
		gbc.gridx = 10;
		gbc.gridy = 1;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(boutonProfil, gbc);

		gbc.gridx = 10;
		gbc.gridy = 2;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(boutonActualite, gbc);

		gbc.gridx = 8;
		gbc.gridy = 3;
		gbc.gridheight = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;;
		gbc.anchor = GridBagConstraints.BELOW_BASELINE;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(barreRechercher, gbc);
		
		gbc.gridx = 9;
		gbc.gridy = 4;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.ABOVE_BASELINE;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(boutonRechercher, gbc);
		
		gbc.gridx = 9;
		gbc.gridy = 10;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.ABOVE_BASELINE;
		gbc.insets = new Insets(330, 0, 0, 0);
		this.add(boutonChat, gbc);
		
				//Action de chaque composant
		boutonModifier.setActionCommand(Controleur_Utilisateur.ACTION_MODIFIER_PROFIL);
		boutonModifier.addActionListener(controleurUtilisateur);
		//boutonMessage.setActionCommand(Controleur_Utilisateur.ACTION_LISTER_MESSAGE);
		//boutonMessage.addActionListener(controleurUtilisateur);
		boutonDeconnection.setActionCommand(Controleur_Utilisateur.ACTION_DECONNECTION);
		boutonDeconnection.addActionListener(controleurUtilisateur);
		boutonAmis.setActionCommand(Controleur_Utilisateur.ACTION_LISTER_AMIS);
		boutonAmis.addActionListener(controleurUtilisateur);
		boutonMessage.setActionCommand(Controleur_Utilisateur.ACTION_LISTER_MESSAGES);
		boutonMessage.addActionListener(controleurUtilisateur);
		boutonAjouter.setActionCommand(Controleur_Utilisateur.ACTION_AJOUTER_IMAGE);
		boutonAjouter.addActionListener(controleurUtilisateur);
		boutonRechercher.setActionCommand(Controleur_Utilisateur.ACTION_RECHERCHER_AMIS);
		boutonRechercher.addActionListener(controleurUtilisateur);
		boutonProfil.setActionCommand(Controleur_Utilisateur.ACTION_PROFIL_PERSONNEL);
		boutonProfil.addActionListener(controleurUtilisateur);
		boutonActualite.setActionCommand(Controleur_Utilisateur.ACTION_PROFIL_PERSONNEL);
		boutonActualite.addActionListener(controleurUtilisateur);
		
		
	}
	
	
}