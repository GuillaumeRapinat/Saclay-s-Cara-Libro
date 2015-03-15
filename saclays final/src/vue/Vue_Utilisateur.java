package vue;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;


import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import modele.Modele_Utilisateur;
import controleur.Controleur_Utilisateur;

public class Vue_Utilisateur extends JFrame {

	private static final long serialVersionUID = 1L;
	
	// polices
	public static final Font FONT = new Font("Helvetica", Font.BOLD + Font.ITALIC, 14);
	//couleurs
	static Color MARRONCLAIR = new Color(255, 231, 136);
	
	// composants de l'interface graphique
	private GridBagConstraints gbc;
	private JComboBox<String> choixSexe;
	private JLabel sexe;
	private JTextField age;
	private JTextField prenom;
	private JTextField nom;
	private JTextField barreRechercher;
	private JTextField objet;
	private JButton boutonProfil;
	private JButton boutonDesactiver;
	private JButton boutonModifier;
	private JButton photoProfil;
	private JButton boutonAmis;
	private JButton boutonPublier;
	private JButton boutonEnvoyer;
	private JButton boutonAjouter;
	private JButton boutonRechercher;
	private JButton boutonChat;
	private JButton boutonMessage;
	private JButton titreImage;
	private JTextArea textePublier;
	//private JPanel panneau;
	
	private Modele_Utilisateur modeleUtilisateur;

	
		
	public Vue_Utilisateur(Controleur_Utilisateur controleurUtilisateur, Modele_Utilisateur modeleUtilisateur) {
		this.modeleUtilisateur = modeleUtilisateur;
		
		// composants de la vue
		photoProfil = new JButton();
		redessinerPhoto();
			photoProfil.setBackground(null);
			photoProfil.setBorderPainted(false);
			photoProfil.setEnabled(false);
			
		age = new JTextField(modeleUtilisateur.getAge() + " ans");
			age.setBackground(null);
			age.setBorder(null);
			age.setEditable(false);
			age.setPreferredSize(new Dimension(60,20));
			age.setFont(Vue_Accueil.FONT3);
		sexe = new JLabel();
			redessinerSexe();
			sexe.setPreferredSize(new Dimension(100,20));
			sexe.setFont(Vue_Accueil.FONT3);
			
		JPanel panneauModifier = new JPanel();
		panneauModifier.setLayout(new FlowLayout());
		panneauModifier.setBackground(null);
		boutonModifier = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/boutons_modifier.png")));
			boutonModifier.setBackground(null);
			boutonModifier.setBorderPainted(false);
			boutonModifier.setCursor(new Cursor(Cursor.HAND_CURSOR));
			boutonModifier.setPreferredSize(new Dimension(118, 55));
		panneauModifier.add(boutonModifier);
			
		prenom = new JTextField();
			redessinerPrenom();
			prenom.setBackground(null);
			prenom.setBorder(null);
			prenom.setEditable(false);
			prenom.setPreferredSize(new Dimension(120,20));
			prenom.setFont(Vue_Accueil.FONT2);
		nom = new JTextField();
			redessinerNom();
			nom.setBackground(null);
			nom.setBorder(null);
			nom.setEditable(false);
			nom.setPreferredSize(new Dimension(120,20));
			nom.setFont(Vue_Accueil.FONT2);
			
		objet = new JTextField("Objet");
			objet.setBackground(new Color(255, 248, 192));
			objet.setPreferredSize(new Dimension(500,20));
			objet.setFont(new Font("Helvetica", Font.BOLD+Font.ITALIC, 14));
			objet.setCursor(new Cursor(Cursor.TEXT_CURSOR));
			objet.setVisible(false);
			
		JPanel panneauPublier = new JPanel();
		panneauPublier.setLayout(new FlowLayout());
		panneauPublier.setBackground(null);
		textePublier = new JTextArea("Exprimez-vous");
			JScrollPane scrollPaneArea = new JScrollPane(textePublier,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);   
			textePublier.setCaretPosition(0); //set scrollPane to the top  
			textePublier.setFont(new Font("Helvetica", Font.BOLD+Font.ITALIC, 14));
			textePublier.setLineWrap(true);
			textePublier.setWrapStyleWord(true);
			textePublier.setBackground(new Color(255, 248, 192));
			textePublier.setCursor(new Cursor(Cursor.TEXT_CURSOR));
			textePublier.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					textePublier.setText("");
				}
			});
			scrollPaneArea.setPreferredSize(new Dimension(500, 100)); 
			panneauPublier.add(scrollPaneArea);  
		
		boutonPublier = new JButton (new ImageIcon(getClass().getClassLoader().getResource("images/boutons_publier.png")));
			boutonPublier.setBackground(null);
			boutonPublier.setBorderPainted(false);
			boutonPublier.setCursor(new Cursor(Cursor.HAND_CURSOR));			
			boutonPublier.setPreferredSize(new Dimension(118, 55));
			
		boutonEnvoyer = new JButton (new ImageIcon(getClass().getClassLoader().getResource("images/boutons_envoyer.png")));
			boutonEnvoyer.setBackground(new Color(0, 0, 0));
			boutonEnvoyer.setOpaque(false);
			boutonEnvoyer.setBorderPainted(false);
			boutonEnvoyer.setCursor(new Cursor(Cursor.HAND_CURSOR));
			boutonEnvoyer.setPreferredSize(new Dimension(118, 55));
			boutonEnvoyer.setVisible(false);
		
		JPanel panneauAjouter = new JPanel();
		panneauAjouter.setLayout(new FlowLayout());
		panneauAjouter.setBackground(null);
		boutonAjouter = new JButton (new ImageIcon(getClass().getClassLoader().getResource("images/bouton_photo.png")));
			boutonAjouter.setBackground(null);
			boutonAjouter.setBorderPainted(false);
			boutonAjouter.setCursor(new Cursor(Cursor.HAND_CURSOR));
			boutonAjouter.setPreferredSize(new Dimension(57, 57));
		panneauAjouter.add(boutonAjouter);
		
		titreImage = new JButton("image telechargee");
			titreImage.setBackground(null);
			titreImage.setBorderPainted(false);
			titreImage.setPreferredSize(new Dimension(80,20));
			titreImage.setCursor(new Cursor(Cursor.HAND_CURSOR));
			
		JButton boutonDeconnection = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/boutons_deconnect.png")));
			boutonDeconnection.setBackground(null);
			boutonDeconnection.setBorderPainted(false);
			boutonDeconnection.setCursor(new Cursor(Cursor.HAND_CURSOR));
			boutonDeconnection.setPreferredSize(new Dimension(177, 33));
			
		boutonMessage = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/message.png")));
			boutonMessage.setBackground(null);
			boutonMessage.setBorderPainted(false);
			boutonMessage.setCursor(new Cursor(Cursor.HAND_CURSOR));
			boutonMessage.setPreferredSize(new Dimension(59, 102));
			
		JPanel panneauAmis = new JPanel();
		panneauAmis.setLayout(new FlowLayout());
		panneauAmis.setBackground(null);
		boutonAmis = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ami.png")));
			boutonAmis.setBackground(null);
			boutonAmis.setBorderPainted(false);
			boutonAmis.setCursor(new Cursor(Cursor.HAND_CURSOR));
			boutonAmis.setPreferredSize(new Dimension(85, 64));
		panneauAmis.add(boutonAmis);
		
		boutonProfil = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/profil_tout_petit.png")));
			boutonProfil.setBackground(null);
			boutonProfil.setBorderPainted(false);
			boutonProfil.setCursor(new Cursor(Cursor.HAND_CURSOR));
			boutonProfil.setPreferredSize(new Dimension(50, 45));
			
		boutonDesactiver = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/boutons_X.png")));
			boutonDesactiver.setBackground(null);
			boutonDesactiver.setBorderPainted(false);
			boutonDesactiver.setCursor(new Cursor(Cursor.HAND_CURSOR));
			boutonDesactiver.setPreferredSize(new Dimension(50, 41));
			
		barreRechercher = new JTextField ("Rechercher quelqu'un");
			barreRechercher.setFont(Vue_Accueil.FONT2);
			barreRechercher.setBackground(new Color(255,248,192));
			barreRechercher.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			barreRechercher.setCursor(new Cursor(Cursor.TEXT_CURSOR));
			barreRechercher.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					barreRechercher.setText("");
				}
			});
			
		boutonRechercher = new JButton("Rechercher");
			boutonRechercher.setFont(FONT);
			boutonRechercher.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boutonChat = new JButton("Chat");
			
		//mise en place sur la page
		this.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
				
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 4;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
		gbc.insets = new Insets(-20, 20, 0, 0);
		this.add(photoProfil, gbc);
				
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING;
		gbc.insets = new Insets(10, -20, 0, 0);
		this.add(age, gbc);
				
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING;
		gbc.insets = new Insets(10, 40, 0, 0);
		this.add(sexe, gbc);
				
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridheight = 1;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.ABOVE_BASELINE_TRAILING;
		gbc.insets = new Insets(-10, 0, 0, 0);
		this.add(panneauModifier, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, 10, 0, 0);
		this.add(prenom, gbc);
				
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, 40, 0, 0);
		this.add(nom, gbc);
			
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridheight = 3;
		gbc.gridwidth = 6;
		gbc.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
		gbc.insets = new Insets(-20, 0, 0, 0);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(panneauPublier, gbc);
		
//objet du message
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridheight = 1;
		gbc.gridwidth = 6;
		gbc.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
		gbc.insets = new Insets(-50, 0, 0, 0);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(objet, gbc);
				
		gbc.gridx = 7;
		gbc.gridy = 3;
		gbc.gridheight = 2;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING;
		gbc.insets = new Insets(-20, 0, 0, 0);
		this.add(boutonPublier, gbc);
//seulement visible dans le profil ami, il permet d'être placé comme il faut		
		gbc.gridx = 7;
		gbc.gridy = 3;
		gbc.gridheight = 2;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING;
		gbc.insets = new Insets(30, 0, 0, 0);
		this.add(boutonEnvoyer, gbc);
		
		
		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.gridheight = 2;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BELOW_BASELINE_LEADING;
		gbc.insets = new Insets(-20, 0, 0, 0);
		this.add(panneauAjouter, gbc);
		
		gbc.gridx = 4;
		gbc.gridy = 4;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BELOW_BASELINE_LEADING;
		gbc.insets = new Insets(-20, -90, 0, -50);
		this.add(titreImage, gbc);
				
		gbc.gridx = 8;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 3;
		gbc.anchor = GridBagConstraints.BASELINE;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(boutonDeconnection, gbc);
			
		gbc.gridx = 8;
		gbc.gridy = 1;
		gbc.gridheight = 2;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
		gbc.insets = new Insets(0, 35, 0, -20);
		this.add(boutonMessage, gbc);
				
		gbc.gridx = 9;
		gbc.gridy = 1;
		gbc.gridheight = 2;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
		gbc.insets = new Insets(0, 20, 0, 0);
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
		this.add(boutonDesactiver, gbc);
		
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
		gbc.insets = new Insets(-40, 0, 0, 0);
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
		boutonDeconnection.setActionCommand(Controleur_Utilisateur.ACTION_DECONNEXION);
		boutonDeconnection.addActionListener(controleurUtilisateur);
		boutonRechercher.setActionCommand(Controleur_Utilisateur.ACTION_RECHERCHER);
		boutonRechercher.addActionListener(controleurUtilisateur);
		photoProfil.setActionCommand(Controleur_Utilisateur.ACTION_CHANGER_PHOTO_PROFIL);
		photoProfil.addActionListener(controleurUtilisateur);
		boutonAmis.setActionCommand(Controleur_Utilisateur.ACTION_LISTER_AMIS);
		boutonAmis.addActionListener(controleurUtilisateur);
		boutonDesactiver.setActionCommand(Controleur_Utilisateur.ACTION_DESACTIVER_COMPTE);
		boutonDesactiver.addActionListener(controleurUtilisateur);
		boutonProfil.setActionCommand(Controleur_Utilisateur.ACTION_VOIR_MON_PROFIL);
		boutonProfil.addActionListener(controleurUtilisateur);
		boutonEnvoyer.setActionCommand(Controleur_Utilisateur.ACTION_ENVOYER_MESSAGE);
		boutonEnvoyer.addActionListener(controleurUtilisateur);
		boutonMessage.setActionCommand(Controleur_Utilisateur.ACTION_LISTER_MESSAGES);
		boutonMessage.addActionListener(controleurUtilisateur);
		
		// affichage de la fenetre
		URL url = getClass().getResource("/images/logo_SCL_court.png");
        Image icone = Toolkit.getDefaultToolkit().getImage(url);
        this.setIconImage(icone);
		this.setTitle("Saclay's Cara Libro");
		this.pack();
		this.getContentPane().setBackground(Vue_Accueil.MARRON);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(900, 700);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setVisible(true);
	}

	public void modifierProfil() {
		// verrouillage de certains controleurs
		boutonAmis.setEnabled(false);
		boutonAjouter.setEnabled(false);
		boutonPublier.setEnabled(false);
		boutonRechercher.setEnabled(false);
		boutonChat.setEnabled(false);
		barreRechercher.setEnabled(false);
		boutonMessage.setEnabled(false);
		textePublier.setEnabled(false);
		boutonDesactiver.setEnabled(false);
		boutonProfil.setEnabled(false);
		
		// met � jour le bouton modifier
		boutonModifier.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/boutons_terminer.png")));
		boutonModifier.setActionCommand(Controleur_Utilisateur.ACTION_TERMINER);
		
		// permet l'edition des donn�es du profil
		photoProfil.setCursor(new Cursor(Cursor.HAND_CURSOR));
		photoProfil.setEnabled(true);
		
		prenom.setBackground(new Color(255,248,192));
		prenom.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		prenom.setEditable(true);
		
		nom.setBackground(new Color(255,248,192));
		nom.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		nom.setEditable(true);
		
		age.setText(modeleUtilisateur.getAge());
		age.setBackground(new Color(255,248,192));
		age.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		age.setEditable(true);
		
		sexe.setVisible(false);
		choixSexe = new JComboBox<String>();
		choixSexe.addItem("Aucun");
		choixSexe.addItem("Homme");
		choixSexe.addItem("Femme");
		String s = modeleUtilisateur.getSexe();
		if (s != null) choixSexe.setSelectedItem(s);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING;
		gbc.insets = new Insets(10, 40, 0, 0);
		this.add(choixSexe, gbc);
	}

	public void terminer() {
		// d�verouillage de certains controleurs
		boutonAmis.setEnabled(true);
		boutonAjouter.setEnabled(true);
		boutonPublier.setEnabled(true);
		boutonRechercher.setEnabled(true);
		boutonChat.setEnabled(true);
		barreRechercher.setEnabled(true);
		boutonMessage.setEnabled(true);
		textePublier.setEnabled(true);
		boutonDesactiver.setEnabled(true);
		boutonProfil.setEnabled(true);
		
		// met � jour le bouton modifier
		boutonModifier.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/boutons_modifier.png")));
		boutonModifier.setActionCommand(Controleur_Utilisateur.ACTION_MODIFIER_PROFIL);
		
		// reverrouille les donn�es du profil
		photoProfil.setCursor(null);
		photoProfil.setEnabled(false);
		
		String s = modeleUtilisateur.getPrenom();
		if (s.equals("")) prenom.setText("Prenom");
		else			  prenom.setText(s);
		prenom.setBackground(null);
		prenom.setBorder(null);
		prenom.setEditable(false);
		
		s = modeleUtilisateur.getNom();
		if (s.equals("")) nom.setText("Nom");
		else			  nom.setText(s);
		nom.setBackground(null);
		nom.setBorder(null);
		nom.setEditable(false);
		
		s = modeleUtilisateur.getSexe();
		if (!s.equals("Aucun")) sexe.setText(s + " de ");
		else 			  		sexe.setText(null);
		sexe.setVisible(true);
		choixSexe.setVisible(false);
		choixSexe = null;
		
		age.setText(modeleUtilisateur.getAge() + " ans");
		age.setBackground(null);
		age.setBorder(null);
		age.setEditable(false);
	}
	
	public void autreProfil() {
		boutonModifier.setVisible(false);
		boutonDesactiver.setVisible(false);
		boutonAjouter.setVisible(false);
		titreImage.setVisible(false);
		textePublier.setText("Écrire un message...");
		boutonPublier.setVisible(false);
		boutonEnvoyer.setVisible(true);
		objet.setVisible(true);
		
	}
	
	public void revenirProfil() {
		boutonModifier.setVisible(true);
		boutonDesactiver.setVisible(true);
		boutonAmis.setVisible(true);
		boutonMessage.setVisible(true);
		boutonAjouter.setVisible(true);
		titreImage.setVisible(true);
		textePublier.setText("Exprimez-vous");
		boutonPublier.setVisible(true);
		boutonEnvoyer.setVisible(false);
		objet.setVisible(false);
		}
	
	public void redessinerPhoto() {
		IconResized icon = new IconResized(modeleUtilisateur.getPhoto(),150,134);
		photoProfil.setIcon(icon);
		photoProfil.setDisabledIcon(icon);
	}
	
	public void redessinerSexe() {
		String s = modeleUtilisateur.getSexe();
		if (s != null && !s.equals("Aucun")) sexe.setText(s + " de ");
	}
	
	public void redessinerPrenom() {
		String s = modeleUtilisateur.getPrenom();
		if (s == null || s == "") prenom.setText("Prenom");
		else		   			  prenom.setText(s);
	}
	
	public void redessinerNom() {
		String s = modeleUtilisateur.getNom();
		if (s == null || s == "") nom.setText("Nom");
		else					  nom.setText(s);
	}
	
	public void redessinerTout() {
		redessinerPhoto();
		age.setText(modeleUtilisateur.getAge() + " ans");
		redessinerSexe();
		redessinerPrenom();
		redessinerNom();
	}

	///////////////////////////////////////////////////////////////////////
	// GETTERS
	
	public String getPrenom() {
		return prenom.getText();
	}
	
	public String getNom() {
		return nom.getText();
	}
	
	public int getAge() {
		int n;
		
		try {
			n = Integer.parseInt(age.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(new JDialog(),"'" + age.getText() + "' n'est pas un nombre. 0 prit par d�fault.", "Erreur", JOptionPane.ERROR_MESSAGE);
			return 0;
		}
		
		return n;
	}
	
	public String getSexe() {
		return choixSexe.getSelectedItem().toString();
	}

	public String getRecherche() {
		return barreRechercher.getText();
	}
	public String getMessage() {
		return textePublier.getText();
	}
	public String getObjet(){
		return objet.getText();
	}
}