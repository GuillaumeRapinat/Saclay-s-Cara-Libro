package vue;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controleur.Controleur_Utilisateur;

public class Vue_Recherche_Nouvel_Ami extends JFrame {

	private static final long serialVersionUID = 1L;
	
	JLabel photoProfil;
	JLabel labelPrenom;
	JLabel labelNom;
	String recherche;
	JButton demanderAmi;
	JLabel dejaAmi;
	
	static Font f2 = new Font("Helvetica", Font.BOLD+Font.ITALIC, 16);
	Controleur_Utilisateur controleurUtilisateur;
	
//	public static final int CUSTOM_CURSOR = cursor: url("images/cursor.png"), pointer;;
	
	public Vue_Recherche_Nouvel_Ami(Controleur_Utilisateur controleurUtilisateur){
		
		this.controleurUtilisateur = controleurUtilisateur;
		
		JPanel panel = new JPanel();
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); 

		recherche = "Marion";
		panel.add(amis(getClass().getClassLoader().getResource("images/profil_petit.png"), "Marion", "Peral", true));
		panel.add(amis(getClass().getClassLoader().getResource("images/profil_petit.png"), "Marion", "Johns", false));
		panel.add(amis(getClass().getClassLoader().getResource("images/profil_petit.png"), "Marion", "Petit", false));
		panel.add(amis(getClass().getClassLoader().getResource("images/profil_petit.png"), "Marion", "Corbé", true));
		panel.add(amis(getClass().getClassLoader().getResource("images/profil_petit.png"), "Marion", "Papanopoulos", false));
		panel.add(amis(getClass().getClassLoader().getResource("images/profil_petit.png"), "Marion", "Peral", false));

		
		JScrollPane jsp = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(jsp);	//le panel est déjà dans un scrollbar, il ne reste que mettre le scrollbar dans la fenêtre!!
		
		
	
		
		
		this.setTitle("Recherche de << " + recherche + " >>");
		this.pack();
		this.setLocation(800, 200);
		//this.setSize(300, 400);
		
		
	}
	
	public JButton amis(URL photo, String prenom, String nom, Boolean ami){
		
		JButton bouton = new JButton();
//		bouton.setAlignmentX(Component.LEFT_ALIGNMENT);
	    
		
		bouton.setLayout(new GridBagLayout());
	
		photoProfil = new JLabel(new ImageIcon(photo));
		

		labelPrenom = new JLabel(prenom);
			labelPrenom.setFont(f2);
		labelNom = new JLabel(nom);
			labelNom.setFont(f2);
			
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = gbc.gridy = 0;
		gbc.gridheight = 2;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, 0, 0, 0);
		bouton.add(photoProfil, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BELOW_BASELINE_LEADING;
		gbc.insets = new Insets(0, 0, 0, 0);
		bouton.add(labelPrenom, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
		gbc.insets = new Insets(0, 0, 0, 0);
		bouton.add(labelNom, gbc);	
		if (ami) {
			dejaAmi = new JLabel ("Ami(e)");
			dejaAmi.setPreferredSize(new Dimension (60, 10));
			dejaAmi.setFont(Vue_Mur.f4);
			gbc.gridx = 3;
			gbc.gridy = 1;
			gbc.gridheight = gbc.gridwidth = 1;
			gbc.anchor = GridBagConstraints.ABOVE_BASELINE_TRAILING;
			gbc.insets = new Insets(-30, 0, 0, 0);
			bouton.add(dejaAmi, gbc);	
		}
		else {
			demanderAmi = new JButton("+ ami(e)");
			demanderAmi.setFont(Vue_Mur.f4);
			demanderAmi.setCursor(new Cursor(Cursor.HAND_CURSOR));
			
			demanderAmi.setActionCommand(Controleur_Utilisateur.ACTION_DEMANDER_AMI);
			demanderAmi.addActionListener(controleurUtilisateur);
			
			gbc.gridx = 3;
			gbc.gridy = 1;
			gbc.gridheight = gbc.gridwidth = 2;
			gbc.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
			gbc.insets = new Insets(-30, 0, 0, 0);
			bouton.add(demanderAmi, gbc);	
		}

		
		return bouton;
		
	}
	

}
