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
import java.net.URL;

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

public class Vue_Ami extends JFrame {

	private static final long serialVersionUID = 1L;
	
	// composants de l'interface graphique
	String s;
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
	JButton boutonEnvoyer;
	JButton boutonChat;
	JButton boutonSupprimer;
	JButton boutonProfil;
	JButton boutonActualite;
	JTextArea texteMessage;
	public static JFileChooser fc;
	Controleur_Mur controleurMur;
	Controleur_Utilisateur controleurUtilisateur;
	
	public static Color marron = new Color(207,168,80);
	static Color marronclair = new Color(255, 231, 136);
	static Font f2 = new Font("Helvetica", Font.BOLD+Font.ITALIC, 16);

	GridBagConstraints gbc = new GridBagConstraints();
	
	public Vue_Ami(Controleur_Utilisateur controleurUtilisateur, int id_utilisateur) {
		
		this.controleurUtilisateur = controleurUtilisateur;
		
		this.setLayout(new GridBagLayout());
		
		ajouterProfil(getClass().getClassLoader().getResource("images/marion.jpg"), "Marion", "Peral", false, 24);
		
		ajouterMur(getClass().getClassLoader().getResource("images/marion_petit.jpg"), "Marion", "Peral");
		
		// affichage de la fen�tre
		this.setTitle("Saclay's Cara Libro");
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(900, 500);
		this.setVisible(true);
	}

	private void ajouterMur(URL url, String prenom, String nom) {
		panneauMur = new JPanel();
		panneauMur.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
		panneauMur.setLayout(new BoxLayout(panneauMur, BoxLayout.Y_AXIS));
		panneauMur.setSize(500, 200);
		Vue_Mur vue2 = new Vue_Mur(controleurMur);

		vue2.publication(url, prenom, nom, "...ZZzzzZzZZZzzzZz...", "07/03/2015", "18:30:32", 1, 1, panneauMur);

			panneauMur.add(Box.createRigidArea(new Dimension(0,20)));
		vue2.publication(url, prenom, nom, "Bon, je vais dormir un petit peu, je suis fatigué...", "07/03/2015", "15:30:07", 1, 3, panneauMur);
			panneauMur.add(Box.createRigidArea(new Dimension(0,20)));
		vue2.publication(url, prenom, nom, "Vive les foraminifères!!", "06/03/2015", "17:12:29", 320, 78, getClass().getClassLoader().getResource("images/neogloquadrinaPD.png"), panneauMur);

		JScrollPane jsp = new JScrollPane(panneauMur, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setPreferredSize(new Dimension(600, 375));
		this.add(jsp); // le panel est déjà dans un scrollbar, il ne reste que
						// mettre le scrollbar dans la fenêtre!!
		
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.gridheight = GridBagConstraints.REMAINDER;
		gbc.gridwidth = 8;
		gbc.anchor = GridBagConstraints.BASELINE;
		gbc.insets = new Insets(-10, 0, 0, -60);
		this.add(jsp, gbc);
		
		panneauMur.setBackground(marron);
		
		
	}

	private void ajouterProfil(URL url, String prenom, String nom, boolean sexe, int age) {
		if (sexe == true) 
			s = "Homme";
		else
			s = "Femme";
		
		photoProfil = new JLabel(new ImageIcon(url));

		labelAge = new JLabel(age + " ans");
			labelAge.setFont(f2);
		labelSexe = new JLabel(s);
			labelSexe.setFont(f2);
		labelPrenom = new JLabel(prenom);
			labelPrenom.setFont(Vue_Accueil.f);
		labelNom = new JLabel(nom);
			labelNom.setFont(Vue_Accueil.f);
		
		JPanel panneauMessage = new JPanel();
		panneauMessage.setLayout(new FlowLayout());
		panneauMessage.setBackground(new Color(0,0,0));
		panneauMessage.setOpaque(false);
		texteMessage = new JTextArea("Ecrire un message");
			JScrollPane scrollPaneArea = new JScrollPane(texteMessage,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);   
			texteMessage.setCaretPosition(0); //set scrollPane to the top  
			texteMessage.setLineWrap(true);
			texteMessage.setWrapStyleWord(true);
			texteMessage.setFont(Vue_Mur.f4);
			texteMessage.setCursor(new Cursor(Cursor.TEXT_CURSOR));
			texteMessage.setBackground(new Color(255,248,192));
			scrollPaneArea.setPreferredSize(new Dimension(370, 90)); 
			panneauMessage.add(scrollPaneArea);  
			
		boutonEnvoyer = new JButton (new ImageIcon(getClass().getClassLoader().getResource("images/boutons_envoyer.png")));
			boutonEnvoyer.setBackground(new Color(0, 0, 0));
			boutonEnvoyer.setOpaque(false);
			boutonEnvoyer.setBorderPainted(false);
			boutonEnvoyer.setCursor(new Cursor(Cursor.HAND_CURSOR));			
			boutonEnvoyer.setPreferredSize(new Dimension(118, 55));
			
		boutonSupprimer = new JButton ("Supprimer");
		
		JPanel panneauDeconnection = new JPanel();
		panneauDeconnection.setLayout(new FlowLayout());
		panneauDeconnection.setBackground(marron);
		boutonDeconnection = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/boutons_deconnect.png")));
			boutonDeconnection.setBackground(new Color(0, 0, 0));
			boutonDeconnection.setOpaque(false);
			boutonDeconnection.setBorderPainted(false);
			boutonDeconnection.setCursor(new Cursor(Cursor.HAND_CURSOR));
			boutonDeconnection.setPreferredSize(new Dimension(177, 33));
		panneauDeconnection.add(boutonDeconnection);
			
			
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
		boutonRechercher.setCursor(new Cursor(Cursor.HAND_CURSOR));
			
		boutonChat = new JButton("Chat");
			
		this.getContentPane().setBackground(Vue_Utilisateur.marron);
		
				//mise en place sur la page
		
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 3;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(-20, 0, 0, 0);
		this.add(photoProfil, gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 1;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING;
		gbc.insets = new Insets(-35, 40, 0, 50);
		this.add(labelAge, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BELOW_BASELINE_LEADING;
		gbc.insets = new Insets(-35, 10, 0, 0);
		this.add(labelSexe, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, 10, 0, 0);
		this.add(labelPrenom, gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, 40, 0, 0);
		this.add(labelNom, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.gridheight = 2;
		gbc.gridwidth = 4;
		gbc.anchor = GridBagConstraints.ABOVE_BASELINE;
		gbc.insets = new Insets(-55, 0, 0, 0);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(panneauMessage, gbc);
		
		gbc.gridx = 6;
		gbc.gridy = 2;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(boutonEnvoyer, gbc);
	
		gbc.gridx = 6;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(boutonSupprimer, gbc);
		
		gbc.gridx = 8;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 3;
		gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(panneauDeconnection, gbc);
		
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
		gbc.gridwidth = GridBagConstraints.REMAINDER;
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
		boutonEnvoyer.setActionCommand(Controleur_Utilisateur.ACTION_ENVOYER_MESSAGE);
		boutonEnvoyer.addActionListener(controleurUtilisateur);
		boutonSupprimer.setActionCommand(Controleur_Utilisateur.ACTION_SUPPRIMER_AMI);
		boutonSupprimer.addActionListener(controleurUtilisateur);
		//boutonMessage.setActionCommand(Controleur_Utilisateur.ACTION_LISTER_MESSAGE);
		//boutonMessage.addActionListener(controleurUtilisateur);
		boutonDeconnection.setActionCommand(Controleur_Utilisateur.ACTION_DECONNECTION);
		boutonDeconnection.addActionListener(controleurUtilisateur);
		boutonAmis.setActionCommand(Controleur_Utilisateur.ACTION_LISTER_AMIS);
		boutonAmis.addActionListener(controleurUtilisateur);
		boutonMessage.setActionCommand(Controleur_Utilisateur.ACTION_LISTER_MESSAGES);
		boutonMessage.addActionListener(controleurUtilisateur);
		boutonRechercher.setActionCommand(Controleur_Utilisateur.ACTION_RECHERCHER_AMIS);
		boutonRechercher.addActionListener(controleurUtilisateur);
		boutonProfil.setActionCommand(Controleur_Utilisateur.ACTION_PROFIL_PERSONNEL);
		boutonProfil.addActionListener(controleurUtilisateur);
		boutonActualite.setActionCommand(Controleur_Utilisateur.ACTION_PROFIL_PERSONNEL);
		boutonActualite.addActionListener(controleurUtilisateur);
		
		
	}
	
	
}