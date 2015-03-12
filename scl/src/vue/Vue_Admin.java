package vue;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Controleur_Utilisateur;

public class Vue_Admin extends JFrame{

	private static final long serialVersionUID = 1L;

	// composants de l'interface graphique
	JPanel panelSignalement;
	
	JButton boutonDeconnection;
	JButton boutonSignalement;
	
	JLabel labelIdUtilisateur;
	JLabel labelPrenom;
	JLabel labelNom;
	JLabel labelDate;
	JLabel labelTitreSignalement;
	JLabel labelUtilisateurs;
	JLabel labelNbrCompte;
	JLabel labelNbrConnect;
	JLabel labelNbrHo_Fe;
	JLabel labelNbrHo_Fe2;
	JLabel labelNbrHo_Fe3;
	
	Controleur_Utilisateur controleurUtilisateur;

	static Font f1 = new Font("Helvetica", Font.BOLD+Font.ITALIC, 16);	
	static Font f2 = new Font("Helvetica", Font.BOLD+Font.ITALIC, 12);

	
	public Vue_Admin(Controleur_Utilisateur controleurUtilisateur) {
		
		this.controleurUtilisateur = controleurUtilisateur;
		
		this.setLayout(new GridBagLayout());
				
		labelTitreSignalement = new JLabel("Liste de Signalements");
			labelTitreSignalement.setFont(f1);
		JPanel panelSignalement = new JPanel();
		panelSignalement.setLayout(new BoxLayout(panelSignalement, BoxLayout.Y_AXIS)); 
		
		panelSignalement.add(signalement(4, 18, true, "Guillaume", "Rapinat", "10/03/15"));
		panelSignalement.add(signalement(6, 19, true, "Marion", "Peral", "08/03/15"));
		panelSignalement.add(signalement(9, 18, true, "Wilfried", "Rabouin", "02/03/15"));
		panelSignalement.add(signalement(3, 16, true, "Estelle", "Malherbe", "27/02/15"));
		panelSignalement.add(signalement(4, 16, true, "Guillaume", "Rapinat", "23/02/15"));
		panelSignalement.add(signalement(4, 11, true, "Guillaume", "Rapinat", "14/02/15"));

		JScrollPane jsp = new JScrollPane(panelSignalement,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(jsp);	//le panel est déjà dans un scrollbar, il ne reste que mettre le scrollbar dans la fenêtre!!

		
		labelUtilisateurs = new JLabel("Liste des utilisateurs");
			labelUtilisateurs.setFont(f1);
			
			
		Vue_Table listUtilisateur = new Vue_Table();
			
		JTable table = new JTable(listUtilisateur);
		JScrollPane scrollpan = new JScrollPane(table);
		scrollpan.setPreferredSize(new Dimension(400, 100));
		this.add(scrollpan);

		JPanel panneauDeconnection = new JPanel();
		panneauDeconnection.setBackground(Vue_Utilisateur.marron);
		panneauDeconnection.setLayout(new FlowLayout());
		boutonDeconnection = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/boutons_deconnect.png")));
			boutonDeconnection.setBackground(new Color(0, 0, 0));
			boutonDeconnection.setOpaque(false);
			boutonDeconnection.setBorderPainted(false);
			boutonDeconnection.setPreferredSize(new Dimension(177, 33));
			boutonDeconnection.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panneauDeconnection.add(boutonDeconnection);

		JTextField barreRechercher = new JTextField ("Rechercher quelqu'un");
			barreRechercher.setFont(f1);
			barreRechercher.setBackground(new Color(255,248,192));
			barreRechercher.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			barreRechercher.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		JButton boutonRechercher = new JButton("Rechercher");
		boutonRechercher.setFont(Vue_Mur.f4);
		boutonRechercher.setBackground(Vue_Utilisateur.marronclair);
		boutonRechercher.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		labelNbrCompte = new JLabel("... comptes");
			labelNbrCompte.setFont(f1);
		labelNbrConnect = new JLabel("... connectés");
			labelNbrConnect.setFont(f1);
		ImageIcon image = new ImageIcon(getClass().getClassLoader().getResource("images/signe_ho_fe.png"));
		labelNbrHo_Fe = new JLabel("...");
		labelNbrHo_Fe2 = new JLabel(image);
		labelNbrHo_Fe3 = new JLabel("...");
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE;
		gbc.insets = new Insets(10, 0, 0, 0);
		this.add(labelTitreSignalement, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridheight = 7;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE;
		gbc.insets = new Insets(-80, 0, 0, 0);
		this.add(panelSignalement, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(labelUtilisateurs, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE;
		gbc.insets = new Insets(10, 0, 0, 0);
		this.add(scrollpan, gbc);
				
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 3;
		gbc.anchor = GridBagConstraints.BASELINE;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(panneauDeconnection, gbc);

		gbc.gridx = 3;
		gbc.gridy = 1;
		gbc.gridheight = 1;
		gbc.gridwidth = 3;
		gbc.anchor = GridBagConstraints.BASELINE;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(labelNbrCompte, gbc);

		gbc.gridx = 3;
		gbc.gridy = 2;
		gbc.gridheight = 1;
		gbc.gridwidth = 3;
		gbc.anchor = GridBagConstraints.BASELINE;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(labelNbrConnect, gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 4;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING;
		gbc.insets = new Insets(30, 0, 0, -30);
		this.add(labelNbrHo_Fe, gbc);

		gbc.gridx = 4;
		gbc.gridy = 3;
		gbc.gridheight = 3;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(labelNbrHo_Fe2, gbc);

		gbc.gridx = 5;
		gbc.gridy = 4;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BELOW_BASELINE_LEADING;
		gbc.insets = new Insets(30, -30, 0, 0);
		this.add(labelNbrHo_Fe3, gbc);
		
		gbc.gridx = 4;
		gbc.gridy = 6;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(barreRechercher, gbc);

		gbc.gridx = 4;
		gbc.gridy = 7;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(boutonRechercher, gbc);
		
		boutonDeconnection.setActionCommand(Controleur_Utilisateur.ACTION_DECONNECTION);
		boutonDeconnection.addActionListener(controleurUtilisateur);
	
		// affichage de la fen�tre
		this.setTitle("Panneau d'administration");
		this.getContentPane().setBackground(Vue_Utilisateur.marron);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public JButton signalement(int id_Utilisateur, int idPubliCom, boolean type, String prenom, String nom, String date){
		
		JButton bouton = new JButton();
		bouton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		bouton.setLayout(new GridBagLayout());
	
		labelIdUtilisateur = new JLabel(""+id_Utilisateur);
			labelIdUtilisateur.setFont(f2);
		labelPrenom = new JLabel(prenom);
			labelPrenom.setFont(f2);
		labelNom = new JLabel(nom);
			labelNom.setFont(f2);
		labelDate = new JLabel(date);
			labelDate.setFont(f2);
			
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, 0, 0, 0);
		bouton.add(labelIdUtilisateur, gbc);
		
		gbc.gridx = gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, 0, 0, 0);
		bouton.add(labelIdUtilisateur, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, 10, 0, 0);
		bouton.add(labelPrenom, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, 10, 0, 0);
		bouton.add(labelNom, gbc);	
		
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, 10, 0, 0);
		bouton.add(labelDate, gbc);	
		
		bouton.setBackground(Vue_Utilisateur.marronclair);
		
		bouton.setSize(300, 400);
		return bouton;
		
	}
	
	
}
