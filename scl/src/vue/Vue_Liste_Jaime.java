package vue;

import java.awt.Component;
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

public class Vue_Liste_Jaime extends JFrame {

	private static final long serialVersionUID = 1L;
	
	JLabel photoProfil;
	JLabel labelPrenom;
	JLabel labelNom;
	
	
	static Font f2 = new Font("Helvetica", Font.BOLD+Font.ITALIC, 16);
	
	public Vue_Liste_Jaime(){
		
		JPanel panel = new JPanel();
		
	    
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); 

		
		panel.add(amis(getClass().getClassLoader().getResource("images/profil_petit.png"), "Guillaume", "Rapinat"));
		panel.add(amis(getClass().getClassLoader().getResource("images/profil_petit.png"), "Estelle", "Malherbe"));
		panel.add(amis(getClass().getClassLoader().getResource("images/profil_petit.png"), "Wilfried", "Rabouin"));
		panel.add(amis(getClass().getClassLoader().getResource("images/profil_petit.png"), "Guillaume", "Rapinat"));
		panel.add(amis(getClass().getClassLoader().getResource("images/profil_petit.png"), "Estelle", "Malherbe"));
		panel.add(amis(getClass().getClassLoader().getResource("images/profil_petit.png"), "Wilfried", "Rabouin"));

		
		JScrollPane jsp = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(jsp);	//le panel est déjà dans un scrollbar, il ne reste que mettre le scrollbar dans la fenêtre!!
		
		
	
		
		this.setTitle("Liste de j'aime");
		this.pack();
		this.setLocation(800, 200);
		this.setSize(200, 400);
		
		
	}
	
	public JButton amis(URL photo, String prenom, String nom){
		
		JButton bouton = new JButton();
		
		bouton.setLayout(new GridBagLayout());
	
		photoProfil = new JLabel(new ImageIcon(photo));
		

		labelPrenom = new JLabel(prenom);
			labelPrenom.setFont(f2);
		labelNom = new JLabel(nom);
			labelNom.setFont(f2);
			
		
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
		
		return bouton;
		
	}

}
