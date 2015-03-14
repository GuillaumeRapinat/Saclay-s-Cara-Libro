package vue;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import controleur.Controleur_Utilisateur;

public class Vue_Liste_Amis extends JFrame {

	private static final long serialVersionUID = 1L;
	
	JLabel photoProfil;
	JLabel labelPrenom;
	JLabel labelNom;
	JButton boutonAmiCommun;
	
	static Font f2 = new Font("Helvetica", Font.BOLD+Font.ITALIC, 16);
	
	Controleur_Utilisateur controleurUtilisateur;
	
	public Vue_Liste_Amis(Controleur_Utilisateur controleurUtilisateur){
		
		this.controleurUtilisateur = controleurUtilisateur;
		
		JPanel panel = new JPanel();
		
	    
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); 

		
		panel.add(amis(getClass().getClassLoader().getResource("images/profil_petit.png"), "Puce", "D'amour"));
		panel.add(amis(getClass().getClassLoader().getResource("images/profil_petit.png"), "Nounours", "Vieux"));
		panel.add(amis(getClass().getClassLoader().getResource("images/profil_petit.png"), "Nounours", "Petit"));
		panel.add(amis(getClass().getClassLoader().getResource("images/profil_petit.png"), "Puce", "D'amour"));
		panel.add(amis(getClass().getClassLoader().getResource("images/profil_petit.png"), "Nounours", "Vieux"));
		panel.add(amis(getClass().getClassLoader().getResource("images/profil_petit.png"), "Nounours", "Petit"));

		
		JScrollPane jsp = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(jsp);	//le panel est déjà dans un scrollbar, il ne reste que mettre le scrollbar dans la fenêtre!!
		
		
	
		
		this.setTitle("Liste d'amis");
		this.pack();
		this.setLocation(800, 200);
		this.setSize(400, 400);
		
		
	}
	
	public JButton amis(URL photo, String prenom, String nom){
		
		JButton bouton = new JButton();
		
		bouton.setLayout(new GridBagLayout());
	
		photoProfil = new JLabel(new ImageIcon(photo));
		

		labelPrenom = new JLabel(prenom);
			labelPrenom.setFont(f2);
		labelNom = new JLabel(nom);
			labelNom.setFont(f2);
		boutonAmiCommun = new JButton("Amis en commun");
			boutonAmiCommun.setFont(Vue_Mur.f4);
			boutonAmiCommun.setCursor(new Cursor(Cursor.HAND_CURSOR));	
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = gbc.gridy = 0;
		gbc.gridheight = 2;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, 0, 0, 0);
		bouton.add(photoProfil, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BELOW_BASELINE_LEADING;
		gbc.insets = new Insets(0, 0, 0, 0);
		bouton.add(labelPrenom, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
		gbc.insets = new Insets(0, 0, 0, 0);
		bouton.add(labelNom, gbc);	
		
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridheight = gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.BASELINE;
		gbc.insets = new Insets(20, 10, 0, 0);
		boutonAmiCommun.setBackground(Vue_Utilisateur.marron);
		bouton.add(boutonAmiCommun, gbc);
		
		boutonAmiCommun.setActionCommand(Controleur_Utilisateur.ACTION_AMI_COMMUN);
		boutonAmiCommun.addActionListener(controleurUtilisateur);
		
		bouton.setBackground(Vue_Utilisateur.marronclair);
		return bouton;
		
	}
	
}
