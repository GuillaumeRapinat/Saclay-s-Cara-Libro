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
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controleur.Controleur_Utilisateur;

public class Vue_Accueil extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	// couleurs
	public static final Color MARRON = new Color(207,168,80);
	static Color BEIGE = new Color(255,248,192);

	// polices
	public static final Font FONT = new Font("Helvetica", Font.BOLD+Font.ITALIC, 26);
	public static final Font FONT2 = new Font("Helvetica", Font.BOLD+Font.ITALIC, 16);
	public static final Font FONT3 = new Font("Helvetica", Font.BOLD+Font.ITALIC, 14);
	
	// composants de l'interface graphique
	private JTextField texteMail;
	private JPasswordField champMotDePasse;
	
	public Vue_Accueil(Controleur_Utilisateur controleurUtilisateur) {	
		// informations de connexion
		JLabel labelSCL = new JLabel("Saclay's Cara Libro");
			labelSCL.setFont(FONT);
		JLabel labelMail = new JLabel("Adresse electronique :");
			labelMail.setFont(FONT);
		texteMail = new JTextField();
			texteMail.setFont(FONT2);
			texteMail.setBackground(new Color(255,248,192));
			texteMail.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JLabel labelMotDePasse = new JLabel("Mot de passe :");
			labelMotDePasse.setFont(FONT);
		champMotDePasse = new JPasswordField();
			champMotDePasse.setFont(FONT2);
			champMotDePasse.setEchoChar('*');
			champMotDePasse.setBackground(new Color(255,248,192));
			champMotDePasse.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		// creation des boutons
		JPanel panneauConnexion = new JPanel();
		panneauConnexion.setLayout(new FlowLayout());
		panneauConnexion.setBackground(null);
		JButton boutonConnexion = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/logo_SCL_court.png")));
			boutonConnexion.setBackground(null);
			boutonConnexion.setBorderPainted(false);
			boutonConnexion.setPreferredSize(new Dimension(146, 174));
			boutonConnexion.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boutonConnexion.setActionCommand(Controleur_Utilisateur.ACTION_CONNEXION);
		boutonConnexion.addActionListener(controleurUtilisateur);
		panneauConnexion.add(boutonConnexion);

		JPanel panneauCreerCompte = new JPanel();
		panneauCreerCompte.setLayout(new FlowLayout());
		panneauCreerCompte.setBackground(null);
		JButton boutonCreerCompte = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/bouton_creer.png")));
			boutonCreerCompte.setBackground(null);
			boutonCreerCompte.setBorderPainted(false);
			boutonCreerCompte.setPreferredSize(new Dimension(155, 81));
			boutonCreerCompte.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panneauCreerCompte.add(boutonCreerCompte);
		boutonCreerCompte.setActionCommand(Controleur_Utilisateur.ACTION_CREER_COMPTE);
		boutonCreerCompte.addActionListener(controleurUtilisateur);
		
		JButton boutonReactiver = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/boutons_reactiver.png")));
			boutonReactiver.setBackground(null);
			boutonReactiver.setBorderPainted(false);
			boutonReactiver.setPreferredSize(new Dimension(165, 69));
			boutonReactiver.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boutonReactiver.setActionCommand(Controleur_Utilisateur.ACTION_REACTIVER_COMPTE);
		boutonReactiver.addActionListener(controleurUtilisateur);
		
		// ajout des composants avec leurs coordonnees
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE;
		gbc.insets = new Insets(0, 0, 20, 0);
		this.add(labelSCL, gbc);
		 
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(labelMail, gbc);

		gbc.gridx = gbc.gridy = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
		gbc.insets = new Insets(0, 200, 0, 0);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(texteMail, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(labelMotDePasse, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
		gbc.insets = new Insets(0, 200, 30, 0);
		this.add(champMotDePasse, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE;
		gbc.insets = new Insets(0, 0, 30, 0);
		this.add(panneauConnexion, gbc);
		
		gbc.gridx = 0; 
        gbc.gridy = 4;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.BASELINE_LEADING;
        gbc.insets = new Insets(0, 0, 0, 0);
        this.add(panneauCreerCompte, gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridheight = gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
        gbc.insets = new Insets(0, 0, 0, 0);
        this.add(boutonReactiver, gbc);
		
		// affichage de la fenetre
        URL url = getClass().getResource("/images/logo_SCL_court.png");
        Image icone = Toolkit.getDefaultToolkit().getImage(url);
        this.setIconImage(icone);
		this.setTitle("Accueil");
		this.pack();
		this.getContentPane().setBackground(MARRON);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700, 500);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setVisible(true);
	}
	
	public String getMail() {
		return texteMail.getText();
	}
	
	public String getMotDePasse() {
		char[] mdp = champMotDePasse.getPassword();
		return String.valueOf(mdp);
	}
	
}
