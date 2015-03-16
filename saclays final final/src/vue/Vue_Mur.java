package vue;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controleur.Controleur_Commentaire;
import controleur.Controleur_Mur;
import modele.Modele_Publication;

public class Vue_Mur extends JScrollPane {

	private static final long serialVersionUID = 1L;

	//private static final Font FONT = new Font("Helvetica", Font.BOLD+Font.ITALIC, 18);

	private JPanel panneauMur;
	private Controleur_Mur controleurMur;
	
	public Vue_Mur(Controleur_Mur controleurMur) {
		this.controleurMur = controleurMur;
		
		panneauMur = new JPanel();
		panneauMur.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.BLACK));
		panneauMur.setBackground(Vue_Accueil.MARRON);
		panneauMur.setLayout(new BoxLayout(panneauMur, BoxLayout.Y_AXIS));
		panneauMur.setSize(500, 200);
		
		this.setViewportView(panneauMur);
		this.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
		this.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
		this.setPreferredSize(new Dimension(600, 350));
	}

	public void redessiner() {
		panneauMur.removeAll();
		panneauMur.validate();
		
		int i = 0;  // indice dans le vecteur de publications
		for(Modele_Publication publication : Modele_Publication.getPublications()) {
			JPanel panel = new JPanel();
			panel.setAlignmentX(LEFT_ALIGNMENT);
			panel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.BLACK));
			panel.setBackground(Vue_Recherche.MARRONCLAIR);
			panel.setLayout(new GridBagLayout());
			
			JPanel panneauTexte = new JPanel();
			panneauTexte.setBackground(Vue_Accueil.MARRON);
			panneauTexte.setLayout(new FlowLayout());
			JTextArea texte = new JTextArea(publication.getMessage());
				JScrollPane jsp = new JScrollPane(texte, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				texte.setCaretPosition(0);
				texte.setLineWrap(true);
				texte.setWrapStyleWord(true);
				texte.setEditable(false);
				texte.setFont(Vue_Utilisateur.FONT);
				texte.setBackground(new Color(255, 248, 192));
				jsp.setPreferredSize(new Dimension(500, 60));
			panneauTexte.setOpaque(false);
			panneauTexte.add(jsp);
			
			JLabel labelDate = new JLabel(publication.getDate().toString());
				labelDate.setFont(Vue_Utilisateur.FONT);
				labelDate.setForeground(Color.GRAY);
			JLabel labelHeure = new JLabel(publication.getHeure().toString());
				labelHeure.setFont(Vue_Utilisateur.FONT);
				labelHeure.setForeground(Color.GRAY);
			
			IdButton boutonSignaler = new IdButton("Signaler", publication.getIdPubli());
				boutonSignaler.setBackground(Vue_Accueil.MARRON);
				boutonSignaler.setCursor(new Cursor(Cursor.HAND_CURSOR));
				boutonSignaler.setActionCommand(Controleur_Mur.ACTION_SIGNALER_PUBLI);
				boutonSignaler.addActionListener(controleurMur);
			
			JButton boutonAimer = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/boutons_jaime.png")));
				boutonAimer.setBackground(null);
				boutonAimer.setBorderPainted(false);
				boutonAimer.setCursor(new Cursor(Cursor.HAND_CURSOR));
				boutonAimer.setPreferredSize(new Dimension(94,44));
				
			Controleur_Commentaire controleurCommentaire = new Controleur_Commentaire(publication.getIdPubli());
				
			JButton boutonCommenter = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/boutons_commenter_petit.png")));
				boutonCommenter.setBackground(null);
				boutonCommenter.setBorderPainted(false);
				boutonCommenter.setCursor(new Cursor(Cursor.HAND_CURSOR));
				boutonCommenter.setPreferredSize(new Dimension(94, 44));
				boutonCommenter.setActionCommand(Controleur_Commentaire.ACTION_VOIR_COMS);
				boutonCommenter.addActionListener(controleurCommentaire);
			
			GridBagConstraints gbc = new GridBagConstraints();
			
			if (publication.getPhoto() != null) {
				JLabel photo = new JLabel(new IconResized(publication.getPhoto(), 90, 90), i);
				
				gbc.gridx = 7;
				gbc.gridy = 2;
				gbc.gridheight = 3;
				gbc.gridwidth = 2;
				gbc.anchor = GridBagConstraints.BASELINE_LEADING;
				gbc.insets = new Insets(-15, 30, 0, 0);
				panel.add(photo, gbc);
			}
			
			gbc.gridx = 2;
			gbc.gridy = 1;
			gbc.gridheight = gbc.gridwidth = 1;
			gbc.anchor = GridBagConstraints.BASELINE_LEADING;
			gbc.insets = new Insets(0, 0, 0, 0);
			panel.add(labelDate, gbc);

			gbc.gridx = 3;
			gbc.gridy = 1;
			gbc.gridheight = gbc.gridwidth = 1;
			gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
			gbc.insets = new Insets(0, 20, 0, 0);
			panel.add(labelHeure, gbc);
			
			gbc.gridx = 0;
			gbc.gridy = 2;
			gbc.gridheight = 2;
			gbc.gridwidth = 6;
			gbc.anchor = GridBagConstraints.BASELINE_LEADING;
			gbc.insets = new Insets(0, -20, 0, 0);
			panel.add(panneauTexte, gbc);
			
			gbc.gridx = 0;
			gbc.gridy = 4;
			gbc.gridheight = 1;
			gbc.gridwidth = 1;
			gbc.anchor = GridBagConstraints.BASELINE_LEADING;
			gbc.insets = new Insets(0, -20, 0, 0);
			panel.add(boutonSignaler, gbc);
			
			gbc.gridx = 4;
			gbc.gridy = 4;
			gbc.gridheight = 1;
			gbc.gridwidth = 1;
			gbc.anchor = GridBagConstraints.BASELINE_LEADING;
			gbc.insets = new Insets(0, 30, 0, 0);
			panel.add(boutonAimer, gbc);
			
			gbc.gridx = 6;
			gbc.gridy = 4;
			gbc.gridheight = 1;
			gbc.gridwidth = 2;
			gbc.anchor = GridBagConstraints.BASELINE_LEADING;
			gbc.insets = new Insets(0, -90, 0, 0);
			panel.add(boutonCommenter, gbc);
			
			panneauMur.add(panel);
			panneauMur.add(Box.createRigidArea(new Dimension(0, 20)));
			
			i++;
		}
		
		panneauMur.revalidate();
		panneauMur.repaint();
	}
	
}
