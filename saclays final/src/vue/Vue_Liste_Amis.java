package vue;

import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.net.URL;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controleur.Controleur_Utilisateur;
import modele.Modele_Utilisateur;

public class Vue_Liste_Amis extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel panel;

	private Controleur_Utilisateur controleurUtilisateur;
	
	public Vue_Liste_Amis(Controleur_Utilisateur controleurUtilisateur, Vector<Modele_Utilisateur> listeDemandes, Vector<Modele_Utilisateur> listeAmis) {
		this.controleurUtilisateur = controleurUtilisateur;
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); 
		
		for(Modele_Utilisateur demandeur : listeDemandes) afficher(demandeur, false);
		for(Modele_Utilisateur ami : listeAmis) 		  afficher(ami, true);
		
		JScrollPane jsp = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(jsp);	//le panel est deja  dans un scrollbar, il ne reste que mettre le scrollbar dans la fenetre!!
		URL url = getClass().getResource("/images/logo_SCL_court.png");
	    Image icone = Toolkit.getDefaultToolkit().getImage(url);
	    this.setIconImage(icone);
		this.setTitle("Liste d'amis");
		this.pack();
		this.setLocation(800, 200);
		this.setResizable(false);
		this.setVisible(true);
	}

	private void afficher(Modele_Utilisateur utilisateur, boolean ami) {
		IdButton bouton = new IdButton(utilisateur.getId());
		bouton.setLayout(new GridBagLayout());
		bouton.setEnabled(ami);
		bouton.setActionCommand(Controleur_Utilisateur.ACTION_VOIR_PROFIL);
		bouton.addActionListener(controleurUtilisateur);

		JLabel photoProfil = new JLabel(new IconResized(utilisateur.getPhoto(), 75, 67));
		JLabel labelPrenom = new JLabel(utilisateur.getPrenom());
			labelPrenom.setFont(Vue_Accueil.FONT2);
		JLabel labelNom = new JLabel(utilisateur.getNom());
			labelNom.setFont(Vue_Accueil.FONT2);
			
		GridBagConstraints gbc = new GridBagConstraints();
			
		gbc.gridx = gbc.gridy = 0;
		gbc.gridheight = 3;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, 0, 0, 0);
		bouton.add(photoProfil, gbc);
					
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BELOW_BASELINE_LEADING;
		gbc.insets = new Insets(-10, 10, 0, 0);
		bouton.add(labelPrenom, gbc);
					
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
		gbc.insets = new Insets(-10, 10, 0, 0);
		bouton.add(labelNom, gbc);	
		
		FriendButton boutonRefuser = null;
		
		if(ami) {
			boutonRefuser = new FriendButton("Supprimer", utilisateur.getId(), bouton);
			boutonRefuser.setActionCommand(Controleur_Utilisateur.ACTION_SUPPRIMER_AMI);
			boutonRefuser.addActionListener(controleurUtilisateur);
			gbc.gridy = 2;
		} else {
			FriendButton boutonAccepter = new FriendButton("Accepter", utilisateur.getId(), bouton);
			boutonAccepter.setFont(Vue_Utilisateur.FONT);
			boutonAccepter.setBackground(Vue_Accueil.MARRON);
			boutonAccepter.setCursor(new Cursor(Cursor.HAND_CURSOR));
			boutonAccepter.setActionCommand(Controleur_Utilisateur.ACTION_ACCEPTER_AMI);
			boutonAccepter.addActionListener(controleurUtilisateur);
			
			gbc.gridx = 3;
			gbc.gridy = 1;
			gbc.gridheight = gbc.gridwidth = 2;
			gbc.anchor = GridBagConstraints.ABOVE_BASELINE;
			gbc.insets = new Insets(-50, 60, 0, 0);
			bouton.add(boutonAccepter, gbc);		
		
			boutonRefuser = new FriendButton("Refuser", utilisateur.getId(), bouton);
			boutonRefuser.setActionCommand(Controleur_Utilisateur.ACTION_REFUSER_AMI);
			boutonRefuser.addActionListener(controleurUtilisateur);
			gbc.gridy = 3;
		}
		
		boutonRefuser.setBackground(Vue_Accueil.MARRON);
		boutonRefuser.setFont(Vue_Utilisateur.FONT);
		boutonRefuser.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		gbc.gridx = 3;
		gbc.gridheight =1;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.ABOVE_BASELINE;
		gbc.insets = new Insets(-30, 60, 0, 0);
		bouton.add(boutonRefuser, gbc);
		
		IdButton boutonAmiCommun = new IdButton("Amis en commun", utilisateur.getId());
		boutonAmiCommun.setFont(Vue_Utilisateur.FONT);
		boutonAmiCommun.setCursor(new Cursor(Cursor.HAND_CURSOR));	
		boutonAmiCommun.setBackground(Vue_Accueil.MARRON);
		boutonAmiCommun.setActionCommand(Controleur_Utilisateur.ACTION_VOIR_AMI_COMMUN);
		
		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.gridheight = gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.BASELINE;
		gbc.insets = new Insets(-30, 10, 0, 0);
		bouton.add(boutonAmiCommun, gbc);
		
		bouton.setBackground(Vue_Recherche.MARRONCLAIR);
		panel.add(bouton);
	}

	public JPanel getPanel() {
		return panel;
	}

}
