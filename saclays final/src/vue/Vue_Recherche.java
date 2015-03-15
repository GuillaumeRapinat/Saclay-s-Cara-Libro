package vue;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.net.URL;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import modele.Modele_Utilisateur;
import controleur.Controleur_Utilisateur;

public class Vue_Recherche extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public static final Color MARRONCLAIR = new Color(255, 231, 136);
	
	public Vue_Recherche(Controleur_Utilisateur controleurUtilisateur, Vector<Modele_Utilisateur> resultatRecherche) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); 
		
		for(Modele_Utilisateur utilisateur : resultatRecherche) {
			JButton bouton = new JButton();
			bouton.setLayout(new GridBagLayout());
			
			JLabel photoProfil = new JLabel(new IconResized(utilisateur.getPhoto(), 75, 67));
			JLabel labelPrenom = new JLabel(utilisateur.getPrenom());
				labelPrenom.setFont(Vue_Accueil.FONT2);
			JLabel labelNom = new JLabel(utilisateur.getNom());
				labelNom.setFont(Vue_Accueil.FONT2);
			
			IdButton boutonAjouter = new IdButton("Ajouter", utilisateur.getId());
				boutonAjouter.setFont(Vue_Utilisateur.FONT);
				boutonAjouter.setCursor(new Cursor(Cursor.HAND_CURSOR));
				boutonAjouter.setBackground(Vue_Accueil.MARRON);
				boutonAjouter.setActionCommand(Controleur_Utilisateur.ACTION_AJOUTER_AMI);
				boutonAjouter.addActionListener(controleurUtilisateur);	
				
			IdButton boutonAmiCommun = new IdButton("Amis en commun", utilisateur.getId());
				boutonAmiCommun.setFont(Vue_Utilisateur.FONT);
				boutonAmiCommun.setCursor(new Cursor(Cursor.HAND_CURSOR));
				boutonAmiCommun.setBackground(Vue_Accueil.MARRON);
				boutonAmiCommun.setActionCommand(Controleur_Utilisateur.ACTION_VOIR_AMI_COMMUN);
				
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
			gbc.insets = new Insets(0, 10, 0, 0);
			bouton.add(labelPrenom, gbc);
				
			gbc.gridx = 2;
			gbc.gridy = 1;
			gbc.gridheight = gbc.gridwidth = 1;
			gbc.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
			gbc.insets = new Insets(0, 10, 0, 0);
			bouton.add(labelNom, gbc);	
			
			gbc.gridx = 3;
			gbc.gridy = 0;
			gbc.gridheight = gbc.gridwidth = 2;
			gbc.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
			gbc.insets = new Insets(-30, 60, 0, 0);
			bouton.add(boutonAjouter, gbc);		
			
			gbc.gridx = 3;
			gbc.gridy = 1;
			gbc.gridheight = gbc.gridwidth = 2;
			gbc.anchor = GridBagConstraints.BASELINE;
			gbc.insets = new Insets(20, 0, 0, 0);
			bouton.add(boutonAmiCommun, gbc);
				
			bouton.setBackground(MARRONCLAIR);
			panel.add(bouton);
		}
		
		JScrollPane jsp = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(jsp);	//le panel est deja  dans un scrollbar, il ne reste que mettre le scrollbar dans la fenetre!!
		URL url = getClass().getResource("/images/logo_SCL_court.png");
	    Image icone = Toolkit.getDefaultToolkit().getImage(url);
	    this.setIconImage(icone);
		this.setTitle("Resultat de la recherche");
		this.pack();
		this.setLocation(800, 200);
		this.setResizable(false);
		this.setVisible(true);
	}
	
}
