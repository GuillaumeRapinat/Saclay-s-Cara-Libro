package vue;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;


import modele.ButtonEditor;
import modele.ButtonRenderer;
import modele.Modele_Admin;




import controleur.Controleur_Utilisateur;

public class Vue_Admin extends JFrame{

	private static final long serialVersionUID = 1L;

	// composants de l'interface graphique
	JPanel panelSignalement;
	
	JButton boutonDeconnection;
	JButton boutonSignalement;
	
	JLabel labelIdUtilisateur;
	JLabel labelPrenom;
	JLabel labelNom;
	JLabel labelDate;
	JLabel labelTitreSignalement;
	JLabel labelUtilisateurs;
	JLabel labelNbrCompte;
	JLabel labelNbrConnect;
	JLabel labelNbrHo_Fe;
	JLabel labelNbrHo_Fe2;
	JLabel labelNbrHo_Fe3;
	
	Controleur_Utilisateur controleurUtilisateur;

	static Font f1 = new Font("Helvetica", Font.BOLD+Font.ITALIC, 16);	
	static Font f2 = new Font("Helvetica", Font.BOLD+Font.ITALIC, 12);
	static Font f4 = new Font("Helvetica", Font.BOLD + Font.ITALIC, 14);

	
	public Vue_Admin(Controleur_Utilisateur controleurUtilisateur, Vector<Modele_Admin> lSignalements, Vector<Modele_Admin> lutilisateur) {
		
		this.controleurUtilisateur = controleurUtilisateur;
		
		this.setLayout(new GridBagLayout());
				
		labelTitreSignalement = new JLabel("Liste de Signalements");
			labelTitreSignalement.setFont(f1);
		JPanel panelSignalement = new JPanel();
		panelSignalement.setLayout(new BoxLayout(panelSignalement, BoxLayout.Y_AXIS)); 
		
		for (Modele_Admin s : lSignalements) {
			panelSignalement.add(signalement(s));
		}
		JScrollPane jsp = new JScrollPane(panelSignalement,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(jsp);	//le panel est déjà dans un scrollbar, il ne reste que mettre le scrollbar dans la fenêtre!!

		
		labelUtilisateurs = new JLabel("Liste des utilisateurs");
			labelUtilisateurs.setFont(f1);
		 Vector <String> title = new Vector<String>();
		    title.add("ID");
		    title.add("Prénom");
		    title.add("Nom");
		    title.add("Mail");
		    title.add("Age");
		    title.add("Sexe");
		    title.add("Bloque");
		    title.add("Actif");
		    
		
		   
		JTable table = new JTable(lutilisateur, title);
			table.setAutoCreateRowSorter(true);
			table.getColumn("Bloque").setCellRenderer(new ButtonRenderer());
			table.getColumn("Bloque").setCellEditor(new ButtonEditor(new JCheckBox(), new Modele_Admin(), controleurUtilisateur, table));
		JScrollPane scrollpan = new JScrollPane(table);
		scrollpan.setPreferredSize(new Dimension(500, 300));
		this.add(scrollpan);

		JPanel panneauDeconnection = new JPanel();
		panneauDeconnection.setBackground(Vue_Accueil.MARRON);
		panneauDeconnection.setLayout(new FlowLayout());
		boutonDeconnection = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/boutons_deconnect.png")));
			boutonDeconnection.setBackground(new Color(0, 0, 0));
			boutonDeconnection.setOpaque(false);
			boutonDeconnection.setBorderPainted(false);
			boutonDeconnection.setPreferredSize(new Dimension(177, 33));
			boutonDeconnection.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panneauDeconnection.add(boutonDeconnection);

		final JTextField barreRechercher = new JTextField ("Rechercher quelqu'un");
			barreRechercher.setFont(f1);
			barreRechercher.setBackground(new Color(255,248,192));
			barreRechercher.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			barreRechercher.setCursor(new Cursor(Cursor.TEXT_CURSOR));
			barreRechercher.addMouseListener(new  MouseAdapter(){
				 public void mousePressed(MouseEvent e) {
					 	barreRechercher.setText("");
				 }
			});
		JButton boutonRechercher = new JButton("Rechercher");
		boutonRechercher.setFont(f4);
		boutonRechercher.setBackground(Vue_Utilisateur.MARRONCLAIR);
		boutonRechercher.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE;
		gbc.insets = new Insets(10, 0, 0, 0);
		this.add(labelTitreSignalement, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridheight = 7;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(panelSignalement, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(labelUtilisateurs, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridheight = 5;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE;
		gbc.insets = new Insets(0, 10, 0, 0);
		this.add(scrollpan, gbc);
				
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 3;
		gbc.anchor = GridBagConstraints.BASELINE;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(panneauDeconnection, gbc);

		gbc.gridx = 4;
		gbc.gridy = 6;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(barreRechercher, gbc);

		gbc.gridx = 4;
		gbc.gridy = 7;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(boutonRechercher, gbc);
		
		boutonDeconnection.setActionCommand(Controleur_Utilisateur.ACTION_DECONNEXION);
		boutonDeconnection.addActionListener(controleurUtilisateur);
		boutonRechercher.setActionCommand(Controleur_Utilisateur.ACTION_RECHERCHER);
		boutonRechercher.addActionListener(controleurUtilisateur);

	
		// affichage de la fen�tre
		this.setTitle("Panneau d'administration");
		this.getContentPane().setBackground(Vue_Accueil.MARRON);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public IdButton signalement(Modele_Admin signalement){
		
		IdButton bouton = new IdButton(signalement.getIdS());
		bouton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		bouton.setLayout(new GridBagLayout());
	
		labelIdUtilisateur = new JLabel(""+ signalement.getId_utilisateur());
			labelIdUtilisateur.setFont(f2);
		labelPrenom = new JLabel(signalement.getPrenom());
			labelPrenom.setFont(f2);
		labelNom = new JLabel(signalement.getNom());
			labelNom.setFont(f2);
		labelDate = new JLabel("date");
			labelDate.setFont(f2);
			
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, 0, 0, 0);
		bouton.add(labelIdUtilisateur, gbc);
		
		gbc.gridx = gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, 0, 0, 0);
		bouton.add(labelIdUtilisateur, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, 10, 0, 0);
		bouton.add(labelPrenom, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, 10, 0, 0);
		bouton.add(labelNom, gbc);	
		
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, 10, 0, 0);
		bouton.add(labelDate, gbc);	
		
		bouton.setBackground(Vue_Utilisateur.MARRONCLAIR);
		
		bouton.setActionCommand(Controleur_Utilisateur.ACTION_VOIR_PUBLICATION);
		bouton.addActionListener(controleurUtilisateur);
		
		bouton.setSize(300, 400);
		return bouton;
		
	}

	public static void vuePublication(Modele_Admin publication){
		
		
		JLabel labelPrenom;
		JLabel labelNom;
		JLabel labelDate;
		JLabel labelPhotoProfil;
		JLabel labelHeure;
		IdButton labelnbrCom;
		JTextArea textePublier;
		FriendButton boutonSupprimer;
		
		
		JFrame frame = new JFrame();
		
		
	
		frame.setBackground(Vue_Utilisateur.MARRONCLAIR);
		frame.setLayout(new GridBagLayout());

		labelPhotoProfil = new JLabel (new IconResized(publication.getPhotopro(), 170, 170));
		//getClass().getClassLoader().getResource("images/profil_petit.png")

		labelPrenom = new JLabel(publication.getPrenom());
			labelPrenom.setFont(f2);
		labelNom = new JLabel(publication.getNom());
			labelNom.setFont(f2);
		labelDate = new JLabel("date");
			labelDate.setFont(f4);
			labelDate.setForeground(Color.GRAY);
		labelHeure = new JLabel("Heure");
			labelHeure.setFont(f4);
			labelHeure.setForeground(Color.GRAY);
		labelnbrCom = new IdButton(publication.getNbc() + " commentaire(s)", publication.getId_publi());
			labelnbrCom.setFont(f4);
			labelnbrCom.setBackground(new Color(0, 0, 0));
			labelnbrCom.setOpaque(false);
			labelnbrCom.setBorderPainted(false);
			labelnbrCom.setCursor(new Cursor(Cursor.HAND_CURSOR));
			
		JPanel panneauPublier = new JPanel();
		panneauPublier.setBackground(Vue_Accueil.MARRON);
		panneauPublier.setLayout(new FlowLayout());
		textePublier = new JTextArea(publication.getMessage());
			JScrollPane scrollPaneArea = new JScrollPane(textePublier,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			textePublier.setCaretPosition(0); // set scrollPane to the top
			textePublier.setLineWrap(true);
			textePublier.setWrapStyleWord(true);
			textePublier.setEditable(false);
			textePublier.setFont(f4);
			textePublier.setBackground(new Color(255,248,192));
			scrollPaneArea.setPreferredSize(new Dimension(500, 60));
		panneauPublier.add(scrollPaneArea);

		boutonSupprimer = new FriendButton("Supprimer", publication.getId_publi(), frame);
			boutonSupprimer.setBackground(Vue_Accueil.MARRON);
			boutonSupprimer.setCursor(new Cursor(Cursor.HAND_CURSOR));

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, -20, 0, 0);
		frame.add(labelPhotoProfil, gbc);

		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, 0, 0, 0);
		frame.add(labelPrenom, gbc);

		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
		gbc.insets = new Insets(0, -30, 0, 0);
		frame.add(labelNom, gbc);

		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, 0, 0, 0);
		frame.add(labelDate, gbc);

		gbc.gridx = 3;
		gbc.gridy = 1;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
		gbc.insets = new Insets(0, 20, 0, 0);
		frame.add(labelHeure, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridheight = 2;
		gbc.gridwidth = 6;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, -20, 0, 0);
		frame.add(panneauPublier, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, -20, 0, 0);
		frame.add(boutonSupprimer, gbc);
		
		gbc.gridx = 5;
		gbc.gridy = 1;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
		gbc.insets = new Insets(0, 0, 0, 0);
		frame.add(labelnbrCom, gbc);

		
		
		labelnbrCom.setActionCommand(Controleur_Utilisateur.ACTION_LISTE_COMMENTAIRES_ADMIN);
		labelnbrCom.addActionListener(new Controleur_Utilisateur());
		boutonSupprimer.setActionCommand(Controleur_Utilisateur.ACTION_SUPPRIMER_PUBLI);
		boutonSupprimer.addActionListener(new Controleur_Utilisateur());

		
		

			
	}
	
	
}
