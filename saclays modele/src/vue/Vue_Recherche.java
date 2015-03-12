package vue;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import modele.Modele_Utilisateur;
import controleur.Controleur_Utilisateur;

public class Vue_Recherche extends JFrame {

	private static final long serialVersionUID = 1L;

	private JButton boutonAjouter ;
	//private JLabel dejaAmi;
	
	public Vue_Recherche(Controleur_Utilisateur controleur_Utilisateur, Vector<Modele_Utilisateur> resultatRecherche) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); 
		
		for(Modele_Utilisateur utilisateur : resultatRecherche) {
			JButton bouton = new JButton();
			bouton.setLayout(new GridBagLayout());
			
			InputStream photo = utilisateur.getPhoto();
			ImageIcon icon = null;
			if (photo == null) {
				icon = new ImageIcon(getClass().getClassLoader().getResource("images/profil_petit.png"));
			} else {
				BufferedImage imgResized = new BufferedImage(75, 67, BufferedImage.TYPE_INT_RGB);
				Graphics2D g = imgResized.createGraphics();
			    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			    try {
					g.drawImage(ImageIO.read(photo), 0, 0, 75, 67, null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			    g.dispose();
				icon = new ImageIcon(imgResized);    
			}
			
			JLabel photoProfil = new JLabel(icon);
			JLabel labelPrenom = new JLabel(utilisateur.getPrenom());
				labelPrenom.setFont(Vue_Accueil.FONT2);
			JLabel labelNom = new JLabel(utilisateur.getNom());
				labelNom.setFont(Vue_Accueil.FONT2);
			
			boutonAjouter = new JButton("Ajouter");
			boutonAjouter.setFont(Vue_Utilisateur.FONT);
			boutonAjouter.setCursor(new Cursor(Cursor.HAND_CURSOR));
			//boutonAjouter.setActionCommand(Controleur_Utilisateur.ACTION_DEMANDER_AMI);
			//boutonAjouter.addActionListener(controleurUtilisateur);
				
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
			gbc.gridy = 1;
			gbc.gridheight = gbc.gridwidth = 2;
			gbc.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
			gbc.insets = new Insets(-30, 60, 0, 0);
			bouton.add(boutonAjouter, gbc);		
				
			panel.add(bouton);
		}
		
		JScrollPane jsp = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		this.add(jsp);	//le panel est deja  dans un scrollbar, il ne reste que mettre le scrollbar dans la fenetre!!
		URL url = getClass().getResource("/images/logo_SCL_court.png");
	    Image icone = Toolkit.getDefaultToolkit().getImage(url);
	    this.setIconImage(icone);
		this.setTitle("Résultat de la recherche");
		this.pack();
		this.setLocation(800, 200);
		this.setResizable(false);
		this.setVisible(true);
	}
	
}
