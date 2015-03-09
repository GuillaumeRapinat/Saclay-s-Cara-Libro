package vue;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

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

	
	
	// composants de l'interface graphique
	
	private JButton boutonConnection;
	private JButton boutonCreerCompte;
	private JButton boutonReactiver;
	private JLabel labelLogin;
	private JLabel labelMotDePasse;
	private JLabel labelSCL;
	private JTextField texteLogin;
	private JPasswordField champMotDePasse;
	static Font f = new Font("Helvetica", Font.BOLD+Font.ITALIC, 26);
	
	public Vue_Accueil(Controleur_Utilisateur controleurUtilisateur) {	
		this.setLayout(new GridBagLayout());
		// cr�ation du panneau pour les informations de login
		labelSCL = new JLabel("Saclay's Cara Libro");
			labelSCL.setFont(f);
		labelLogin = new JLabel("Login");
			labelLogin.setFont(f); 
		texteLogin = new JTextField();
			texteLogin.setFont(Vue_Utilisateur.f2);
			texteLogin.setBackground(new Color(255,248,192));
			texteLogin.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			texteLogin.setCursor(new Cursor(Cursor.TEXT_CURSOR));
			
		labelMotDePasse = new JLabel("Mot de passe");
			labelMotDePasse.setFont(f);
		champMotDePasse = new JPasswordField();
			champMotDePasse.setFont(Vue_Utilisateur.f2);
			champMotDePasse.setEchoChar('*');
			champMotDePasse.setBackground(new Color(255,248,192));
			champMotDePasse.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			champMotDePasse.setCursor(new Cursor(Cursor.TEXT_CURSOR));
			
		// cr�ation du panneau pour les boutons
		JPanel panneauConnection = new JPanel();
		panneauConnection.setLayout(new FlowLayout());
		panneauConnection.setBackground(Vue_Utilisateur.marron);
		boutonConnection = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/logo_SCL_court.png")));
			boutonConnection.setBackground(new Color(0, 0, 0, 100));
			boutonConnection.setOpaque(false);
			boutonConnection.setBorderPainted(false);
			boutonConnection.setPreferredSize(new Dimension(146, 174));
			boutonConnection.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boutonConnection.setActionCommand(Controleur_Utilisateur.ACTION_CONNECTION);
		boutonConnection.addActionListener(controleurUtilisateur);
		panneauConnection.add(boutonConnection);
		
//juste pour visualiser admin
		JButton boutonAdmin = new JButton (">Admin<");
		boutonAdmin.setActionCommand(Controleur_Utilisateur.ACTION_ADMIN);
		boutonAdmin.addActionListener(controleurUtilisateur);
//////////
		
		
		JPanel panneauCreerCompte = new JPanel();
		panneauCreerCompte.setLayout(new FlowLayout());
		panneauCreerCompte.setBackground(Vue_Utilisateur.marron);
		boutonCreerCompte = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/bouton_creer.png")));
			boutonCreerCompte.setBackground(new Color(0, 0, 0, 100));
			boutonCreerCompte.setOpaque(false);
			boutonCreerCompte.setBorderPainted(false);
			boutonCreerCompte.setPreferredSize(new Dimension(155, 81));
			boutonCreerCompte.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panneauCreerCompte.add(boutonCreerCompte);
		boutonCreerCompte.setActionCommand(Controleur_Utilisateur.ACTION_CREER_COMPTE);
		boutonCreerCompte.addActionListener(controleurUtilisateur);
		boutonReactiver = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/boutons_reactiver.png")));
			boutonReactiver.setBackground(new Color(0, 0, 0, 100));
			boutonReactiver.setOpaque(false);
			boutonReactiver.setBorderPainted(false);
			boutonReactiver.setPreferredSize(new Dimension(165, 69));
			boutonReactiver.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boutonReactiver.setActionCommand(Controleur_Utilisateur.ACTION_REACTIVER_COMPTE);
		boutonReactiver.addActionListener(controleurUtilisateur);
		

// ajout des composants avec leur coordonnée
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
        this.add(labelLogin, gbc);

        gbc.gridx = gbc.gridy = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
        gbc.insets = new Insets(0, 200, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(texteLogin, gbc);

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
        this.add(panneauConnection, gbc);
        
//juste pour visualiser admin
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridheight = gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
        gbc.insets = new Insets(0, 0, 0, 0);
        this.add(boutonAdmin, gbc);

        gbc.gridx = 0; 
        gbc.gridy = 4;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.BASELINE_LEADING;
        gbc.insets = new Insets(0, 0, 0, 0);
        this.add(boutonCreerCompte, gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridheight = gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
        gbc.insets = new Insets(0, 0, 0, 0);
        this.add(boutonReactiver, gbc);



		// affichage de la fenêtre
		this.setTitle("Accueil");
		
		this.pack();
		//JFrame.setDefaultLookAndFeelDecorated(true);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.getContentPane().setBackground(Vue_Utilisateur.marron);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(650, 450);
		this.setVisible(true);
	}
	
	public String getLogin() {
		return texteLogin.getText();
	}
	
	public String getMotDePasse() {
		return champMotDePasse.getPassword().toString();
	}

}

