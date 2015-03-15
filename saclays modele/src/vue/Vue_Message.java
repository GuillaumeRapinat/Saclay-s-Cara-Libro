package vue;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import modele.Modele_Message;
import modele.Modele_Utilisateur;


import controleur.Controleur_Message;



public class Vue_Message extends JFrame {
	
	private static final long serialVersionUID = 1L;
	protected static int tempmouse = 0;
	
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
	
	public Vue_Message (Controleur_Message controleurMessage, Vector<Modele_Message> resultatMessages){
		this.controleurMessage = controleurMessage;
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		
		for(Modele_Message message : resultatMessages) {
		
		panel.add(titreMessageRecu(message));
		panel.add(titreMessageRecu(message));
		panel.add(titreMessageRecu(message));
				
		}
		JScrollPane jsp = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(jsp);	//le panel est déjà dans un scrollbar, il ne reste que mettre le scrollbar dans la fenêtre!!
		
		
		
		// affichage de la fenetre
		this.setTitle("Boite de messagerie");
		this.getContentPane().setBackground(Vue_Utilisateur.marron);
		this.pack();
		this.setPreferredSize(new Dimension (500, 600));
		this.setLocation(550, 180);
		this.setVisible(true);

		
		}
		
		
	public JButton titreMessageRecu(Modele_Message message){
		
			IdButton bouton = new IdButton(message.getIdM());
			
			bouton.setLayout(new GridBagLayout());
			
			labelNom = new JLabel (message.getNom());
				labelNom.setFont(f7);
			labelPrenom = new JLabel (message.getPrenom());
				labelPrenom.setFont(f7);
				
			labelTitre = new JLabel(message.getObjet());
				labelTitre.setFont(f6);
				
			labelDate = new JLabel("Date");//message.getDate());
				labelDate.setFont(f7);
			labelHeure = new JLabel("Heure");//message.getHeure());
				labelHeure.setFont(f7);
		if (message.getLu())	//télécharger les images
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
		
	public static void message(Modele_Message contenuMessage) {
		
		JLabel labelNom;
		JLabel labelPrenom;
		JLabel labelTitre;
		JLabel labelDate;
		JLabel labelHeure;
		
		JButton boutonSupprimer;
		JButton boutonRepondre;
		
		final JTextArea texteMessage;
		JScrollPane scrollPaneArea; 
		
		Controleur_Message controleurMessage = new Controleur_Message();
		
		
		JFrame frame = new JFrame();
		frame.setLayout(new GridBagLayout());
		
		
		labelNom = new JLabel (contenuMessage.getNom());
			labelNom.setFont(f7);
		labelPrenom = new JLabel (contenuMessage.getPrenom());
			labelPrenom.setFont(f7);
			
		labelTitre = new JLabel(contenuMessage.getObjet());
			labelTitre.setFont(f6);
			
		labelDate = new JLabel("Date");
			labelDate.setFont(f7);
		labelHeure = new JLabel("heure");
			labelHeure.setFont(f7);
			
		boutonSupprimer = new JButton ("Supprimer");
			boutonSupprimer.setBackground(Vue_Utilisateur.marronclair);
		boutonRepondre = new JButton ("Repondre");
			boutonRepondre.setBackground(Vue_Utilisateur.marronclair);
		
		
			//Messages
		JPanel panneauMessage = new JPanel();
	    panneauMessage.setLayout(new FlowLayout());
	    texteMessage = new JTextArea(contenuMessage.getTexte());
		    scrollPaneArea = new JScrollPane(texteMessage,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		    texteMessage.setCaretPosition(0); //set scrollPane to the top  
			texteMessage.setFont(new Font("Helvetica", Font.BOLD + Font.ITALIC, 12));
			texteMessage.setLineWrap(true);
			texteMessage.setWrapStyleWord(true);
			texteMessage.setEditable(false);
			texteMessage.setBackground(Vue_Accueil.beige);
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
		
		gbc.gridx = 3;
		gbc.gridy = 3;
		gbc.gridheight = 1;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
		gbc.insets = new Insets(10, 0, 0, 0);
		frame.add(boutonSupprimer, gbc);		
			
			//Action de chaque composant
		boutonRepondre.setActionCommand(Controleur_Message.ACTION_REPONDRE);
		boutonRepondre.addActionListener(controleurMessage);
		boutonSupprimer.setActionCommand(Controleur_Message.ACTION_SUPPRIMER);
		boutonSupprimer.addActionListener(controleurMessage);
	
		//frame.setBackground(Vue_Utilisateur.marronclair);
		
		frame.setTitle("Boite de messagerie");
		frame.pack();
		frame.setLocation(250, 180);
		frame.setVisible(true);
	}


	public static void repondre(String prenom, String nom) {
		
		JLabel labelNom;
		JLabel labelPrenom;
		JLabel labelTitre;		
		JButton boutonAnnuler;
		JButton boutonEnvoyer;
		
		final JTextArea texteMessage;
		JScrollPane scrollPaneArea; 
		Controleur_Message controleurMessage = new Controleur_Message();

		
		JFrame frame = new JFrame();
		frame.setLayout(new GridBagLayout());
		
		
		labelNom = new JLabel (nom);
			labelNom.setFont(f7);
		labelPrenom = new JLabel (prenom);
			labelPrenom.setFont(f7);
			
		labelTitre = new JLabel("titre");
			labelTitre.setFont(f6);
				
		boutonAnnuler = new JButton ("Annuler");
			boutonAnnuler.setBackground(Vue_Utilisateur.marronclair);
		boutonEnvoyer = new JButton ("Envoyer");
			boutonEnvoyer.setBackground(Vue_Utilisateur.marronclair);
		
		
			//Messages
		JPanel panneauMessage = new JPanel();
	    panneauMessage.setLayout(new FlowLayout());
	    texteMessage = new JTextArea("Ecriver votre message");
		    scrollPaneArea = new JScrollPane(texteMessage,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		    texteMessage.setCaretPosition(0); //set scrollPane to the top  
			texteMessage.setFont(new Font("Helvetica", Font.BOLD + Font.ITALIC, 12));
			texteMessage.setLineWrap(true);
			texteMessage.setWrapStyleWord(true);
			texteMessage.setBackground(Vue_Accueil.beige);
			texteMessage.addMouseListener( new  MouseAdapter(){
				 public void mousePressed(MouseEvent e) {
					 if (Vue_Message.tempmouse == 0){
						 Vue_Message.tempmouse = 1;
					 	texteMessage.setText("");
					 }
				 }
			});
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
		frame.add(boutonEnvoyer, gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 3;
		gbc.gridheight = 1;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
		gbc.insets = new Insets(10, 0, 0, 0);
		frame.add(boutonAnnuler, gbc);		
			
			//Action de chaque composant
		boutonEnvoyer.setActionCommand(Controleur_Message.ACTION_ENVOYER);
		boutonEnvoyer.addActionListener(controleurMessage);
/*		boutonSupprimer.setActionCommand(Controleur_Message.ACTION_SUPPRIMER);
		boutonSupprimer.addActionListener(controleurMessage);
*/	
		//frame.setBackground(Vue_Utilisateur.marronclair);
		
		frame.setTitle("Nouveau Message");
		frame.pack();
		frame.setLocation(560, 180);
		frame.setVisible(true);
		
	}


	
	
	
}
	
	
	
	

