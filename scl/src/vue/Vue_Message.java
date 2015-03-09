package vue;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


import controleur.Controleur_Message;


public class Vue_Message extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	Controleur_Message controleurMessage;
// Composants de l Interface graphique

	JLabel labelNom;
	JLabel labelPrenom;
	JLabel labelTitre;
	JLabel labelDate;
	JLabel labelHeure;
	JLabel labelLu;
	
	JButton boutonSupprimer;
	JButton boutonRepondre;
	
	JTextArea texteMessage;
	JScrollPane scrollPaneArea; 
	GridBagConstraints gbc = new GridBagConstraints();
	
	static Color marron = new Color(207,168,80);
	static Font f6 = new Font ("Helvetica",Font.ITALIC, 12);
	static Font f7 = new Font ("Helvetica",Font.BOLD, 12);
	
	public Vue_Message (Controleur_Message controleurMessage){
		this.controleurMessage = controleurMessage;
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		
		panel.add(titreMessageRecu("Marion", "Peral", "Les courses de ce soir", "09/03/2015", "14:02:34", false));
		panel.add(Box.createRigidArea(new Dimension(0,10)));
		panel.add(titreMessageRecu("Jean", "qule", "Bonjour toi ... ;)", "07/02/2015", "23:38:18", false));
		panel.add(Box.createRigidArea(new Dimension(0,10)));
		panel.add(titreMessageRecu("Marion", "Peral", "Tu fais quoi? <3", "05/02/2015", "11:15:56", true));
		panel.add(Box.createRigidArea(new Dimension(0,10)));
				
		
		JScrollPane jsp = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(jsp);	//le panel est déjà dans un scrollbar, il ne reste que mettre le scrollbar dans la fenêtre!!
		
		
		
		// affichage de la fenetre
		this.setTitle("Boite de messagerie");
		this.getContentPane().setBackground(Vue_Utilisateur.marron);
		this.pack();
		this.setPreferredSize(new Dimension (500, 600));


		
		}
		
		
	public JButton titreMessageRecu(String prenom, String nom, String titre, String date, String heure, boolean lu){
		
			JButton bouton = new JButton();
			
			bouton.setLayout(new GridBagLayout());
			
			labelNom = new JLabel (nom);
				labelNom.setFont(f7);
			labelPrenom = new JLabel (prenom);
				labelPrenom.setFont(f7);
				
			labelTitre = new JLabel(titre);
				labelTitre.setFont(f6);
				
			labelDate = new JLabel(date);
				labelDate.setFont(f7);
			labelHeure = new JLabel(heure);
				labelHeure.setFont(f7);
		if (lu)	//télécharger les images
				labelLu = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("images/lu.png")));
			else
				labelLu = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("images/non_lu.png")));
	
			
				//mise en place sur la page
	
				
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridheight = gbc.gridwidth = 1;
			gbc.anchor = GridBagConstraints.BELOW_BASELINE_LEADING;
			gbc.insets = new Insets(0, 0, 0, 0);
			bouton.add(labelPrenom, gbc);
			
			gbc.gridx = 1;
			gbc.gridy = 0;
			gbc.gridheight = gbc.gridwidth = 1;
			gbc.anchor = GridBagConstraints.BELOW_BASELINE_LEADING;
			gbc.insets = new Insets(0, 10, 0, 0);
			bouton.add(labelNom, gbc);
			
			gbc.gridx = 2;
			gbc.gridy = 0;
			gbc.gridheight = gbc.gridwidth = 1;
			gbc.anchor = GridBagConstraints.BELOW_BASELINE_LEADING;
			gbc.insets = new Insets(0, 10, 0, 0);
			bouton.add(labelTitre, gbc);
			
			gbc.gridx = 3;
			gbc.gridy = 0;
			gbc.gridheight = gbc.gridwidth = 1;
			gbc.anchor = GridBagConstraints.BELOW_BASELINE_LEADING;
			gbc.insets = new Insets(0, 10, 0, 0);
			bouton.add(labelDate, gbc);
			
			gbc.gridx = 4;
			gbc.gridy = 0;
			gbc.gridheight = gbc.gridwidth = 1;
			gbc.anchor = GridBagConstraints.BELOW_BASELINE_LEADING;
			gbc.insets = new Insets(0, 10, 0, 0);
			bouton.add(labelHeure, gbc);
			
			gbc.gridx = 5;
			gbc.gridy = 0;
			gbc.gridheight = gbc.gridwidth = 1;
			gbc.anchor = GridBagConstraints.BELOW_BASELINE_LEADING;
			gbc.insets = new Insets(0, 10, 0, 0);
			bouton.add(labelLu, gbc);
		
		
				//Action de chaque composant
			bouton.setActionCommand(Controleur_Message.ACTION_LIRE_MESSAGE);
			bouton.addActionListener(controleurMessage);

			bouton.setBackground(Vue_Utilisateur.marronclair);
			return bouton;
		
		}	
		
	public static void message(String prenom, String nom, String titre, String texte, String date, String heure) {
		
		JLabel labelNom;
		JLabel labelPrenom;
		JLabel labelTitre;
		JLabel labelDate;
		JLabel labelHeure;
		
		JButton boutonSupprimer;
		JButton boutonRepondre;
		
		JTextArea texteMessage;
		JScrollPane scrollPaneArea; 
		
		
		JFrame frame = new JFrame();
		
		labelNom = new JLabel (nom);
			labelNom.setFont(f7);
		labelPrenom = new JLabel (prenom);
			labelPrenom.setFont(f7);
			
		labelTitre = new JLabel(titre);
			labelTitre.setFont(f6);
			
		labelDate = new JLabel(date);
			labelDate.setFont(f7);
		labelHeure = new JLabel(heure);
			labelHeure.setFont(f7);
			
		boutonSupprimer = new JButton ("Supprimer");
		boutonRepondre = new JButton ("Repondre");
			
			//Messages
		JPanel panneauMessage = new JPanel();
	    panneauMessage.setLayout(new FlowLayout());
	    texteMessage = new JTextArea("Exprimez-vous");
		    scrollPaneArea = new JScrollPane(texteMessage,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		    texteMessage.setCaretPosition(0); //set scrollPane to the top  
			texteMessage.setFont(new Font("Helvetica", Font.BOLD, 14));
			texteMessage.setLineWrap(true);
			texteMessage.setWrapStyleWord(true);
			scrollPaneArea.setPreferredSize(new Dimension(300, 120)); 
		panneauMessage.add(scrollPaneArea);
		
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BELOW_BASELINE_LEADING;
		gbc.insets = new Insets(0, 0, 0, 0);
		frame.add(labelPrenom, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BELOW_BASELINE_LEADING;
		gbc.insets = new Insets(0, 10, 0, 0);
		frame.add(labelNom, gbc);
		
		
		
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BELOW_BASELINE_LEADING;
		gbc.insets = new Insets(0, 10, 0, 0);
		frame.add(labelDate, gbc);
		
		gbc.gridx = 4;
		gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BELOW_BASELINE_LEADING;
		gbc.insets = new Insets(0, 10, 0, 0);
		frame.add(labelHeure, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridheight = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.BELOW_BASELINE_LEADING;
		gbc.insets = new Insets(0, 10, 0, 0);
		frame.add(labelTitre, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridheight = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.BELOW_BASELINE_LEADING;
		gbc.insets = new Insets(10, 0, 0, 0);
		frame.add(panneauMessage, gbc);
		
	
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridheight = 1;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.BASELINE;
		gbc.insets = new Insets(10, 0, 0, 0);
		frame.add(boutonRepondre, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.gridheight = 1;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.BASELINE;
		gbc.insets = new Insets(10, 0, 0, 0);
		frame.add(boutonSupprimer, gbc);		
			
			//Action de chaque composant
/*		boutonRepondre.setActionCommand(Controleur_Message.ACTION_REPONDRE);
		boutonRepondre.addActionListener(controleurMessage);
		boutonSupprimer.setActionCommand(Controleur_Message.ACTION_SUPPRIMER);
		boutonSupprimer.addActionListener(controleurMessage);
*/	
		//frame.setBackground(Vue_Utilisateur.marronclair);
		
		frame.setTitle("Boite de messagerie");
		frame.pack();
		//frame.setPreferredSize(new Dimension (500, 600));
		frame.setVisible(true);
	}
	
	
}
	
	
	
	
