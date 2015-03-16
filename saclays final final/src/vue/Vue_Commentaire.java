package vue;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import modele.Modele_Commentaire;
import controleur.Controleur_Commentaire;

public class Vue_Commentaire extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public static final Font FONT = new Font("Helvetica", Font.BOLD + Font.ITALIC, 12);
	public static final Font FONT2 = new Font("Helvetica", Font.ITALIC, 12);
	public static final Font FONT3 = new Font("Helvetica", Font.BOLD + Font.ITALIC, 14);
	
	private JTextArea texteCommentaire;
	private JPanel panelC;
	
	public Vue_Commentaire(Controleur_Commentaire controleurCommentaire) {
		panelC = new JPanel();
		panelC.setLayout(new BoxLayout(panelC, BoxLayout.Y_AXIS));
		panelC.setBackground(Vue_Accueil.MARRON);
		
		JScrollPane jsp = new JScrollPane(panelC, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setMaximumSize(new Dimension(400, 150));
		
		// partie pour commenter
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.BLACK));
		panel.setBackground(Vue_Recherche.MARRONCLAIR);
		panel.setLayout(new GridBagLayout());
		panel.setMinimumSize(new Dimension(300, 150));
		
		JPanel panneauCommentaire = new JPanel();
		texteCommentaire = new JTextArea("Commentaire");
			JScrollPane jsp2 = new JScrollPane(texteCommentaire, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			texteCommentaire.setFont(FONT2);
			texteCommentaire.setLineWrap(true);
			texteCommentaire.setWrapStyleWord(true);
			//texteCommentaire.setBackground(Vue_Recherche.MARRONCLAIR);
			jsp2.setBorder(null);
			jsp2.setPreferredSize(new Dimension(330, 40));
			panneauCommentaire.setOpaque(false);
			texteCommentaire.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					texteCommentaire.setText("");
				}
			});
		panneauCommentaire.add(jsp2);
			
		JButton boutonCommenter = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/boutons_commenter_petit.png")));
			boutonCommenter.setBackground(null);
			boutonCommenter.setBorderPainted(false);
			boutonCommenter.setCursor(new Cursor(Cursor.HAND_CURSOR));
			boutonCommenter.setPreferredSize(new Dimension(94, 44));
			boutonCommenter.setActionCommand(Controleur_Commentaire.ACTION_COMMENTER);
			boutonCommenter.addActionListener(controleurCommentaire);
		
		GridBagConstraints gbc = new GridBagConstraints();
			
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(0, -20, 0, 0);
		panel.add(panneauCommentaire, gbc);
		
		gbc.gridx = 5;
		gbc.gridy = 1;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(0, 0, 0, 0);
		panel.add(boutonCommenter, gbc);
		
		// on affiche la vue
		JPanel grandPanel = new JPanel();
		grandPanel.setLayout(new BoxLayout(grandPanel, BoxLayout.Y_AXIS));
		grandPanel.add(jsp);
		grandPanel.add(panel);
		this.add(grandPanel);
		URL url = getClass().getResource("/images/logo_SCL_court.png");
	    Image icone = Toolkit.getDefaultToolkit().getImage(url);
	    this.setIconImage(icone);
		this.setTitle("Liste de commentaires");
		this.pack();
		this.setSize(350, 500);
		this.setResizable(false);
		this.setVisible(true);
	}

	public void redessiner(Vector<Modele_Commentaire> commentaires) {
		panelC.removeAll();
		panelC.validate();
		
		for(Modele_Commentaire com : commentaires) {
			JPanel panel = new JPanel();
			panel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.BLACK));
			panel.setBackground(Vue_Recherche.MARRONCLAIR);
			panel.setLayout(new GridBagLayout());
			
			JLabel photoProfil = new JLabel(new IconResized(com.getPhoto(), 50, 45));
				
			JLabel labelPrenom = new JLabel(com.getPrenom());
				labelPrenom.setFont(FONT3);
				labelPrenom.setPreferredSize(new Dimension(80,20));
			JLabel labelNom = new JLabel(com.getNom());
				labelNom.setFont(FONT3);
				labelNom.setPreferredSize(new Dimension(80,20));
				
			JLabel labelDate = new JLabel(com.getDate().toString());
				labelDate.setFont(FONT);
				labelDate.setForeground(Color.GRAY);
			JLabel labelHeure = new JLabel(com.getHeure().toString());
				labelHeure.setFont(FONT);
				labelHeure.setForeground(Color.GRAY);
			
			JPanel panneauCommentaires = new JPanel();
			JTextArea texteCom = new JTextArea(com.getMessage());
			texteCom.setBackground(Vue_Accueil.MARRON);
				JScrollPane jsp = new JScrollPane(texteCom, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				texteCom.setCaretPosition(MAXIMIZED_VERT);
				texteCom.setFont(FONT2);
				texteCom.setLineWrap(true);
				texteCom.setWrapStyleWord(true);
				texteCom.setEditable(false);
				texteCom.setBackground(Vue_Recherche.MARRONCLAIR);
				jsp.setBorder(null);
				jsp.setPreferredSize(new Dimension(315, 40));
				panneauCommentaires.setOpaque(false);
			panneauCommentaires.add(jsp);
			
			JButton boutonAimer = new JButton("J'aime");
			boutonAimer.setFont(new Font("Helvetica", Font.BOLD + Font.ITALIC, 16));
			boutonAimer.setBackground(Vue_Accueil.MARRON);
			
			GridBagConstraints gbc = new GridBagConstraints();
			
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridheight = 2;
			gbc.gridwidth = 1;
			gbc.anchor = GridBagConstraints.BASELINE_LEADING;
			gbc.insets = new Insets(0, 0, 0, 0);
			panel.add(photoProfil, gbc);
			
			gbc.gridx = 1;
			gbc.gridy = 0;
			gbc.gridheight = 1;
			gbc.gridwidth = 1;
			gbc.anchor = GridBagConstraints.BASELINE_LEADING;
			gbc.insets = new Insets(0, 0, 0, 0);
			panel.add(labelPrenom, gbc);
			
			gbc.gridx = 2;
			gbc.gridy = 0;
			gbc.gridheight = 1;
			gbc.gridwidth = 1;
			gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
			gbc.insets = new Insets(0, 10, 0, 0);
			panel.add(labelNom, gbc);
			
			gbc.gridx = 1;
			gbc.gridy = 1;
			gbc.gridheight = gbc.gridwidth = 1;
			gbc.anchor = GridBagConstraints.BASELINE_LEADING;
			gbc.insets = new Insets(-10, 0, 0, 0);
			panel.add(labelDate, gbc);
			
			gbc.gridx = 2;
			gbc.gridy = 1;
			gbc.gridheight = gbc.gridwidth = 1;
			gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
			gbc.insets = new Insets(-10, 10, 0, 0);
			panel.add(labelHeure, gbc);
			
			gbc.gridx = 3;
			gbc.gridy = 1;
			gbc.gridheight = gbc.gridwidth = 1;
			gbc.anchor = GridBagConstraints.BASELINE;
			gbc.insets = new Insets(0, 0, 0, 0);
			panel.add(boutonAimer, gbc);
			
			gbc.gridx = 0;
			gbc.gridy = 2;
			gbc.gridheight = 1;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.anchor = GridBagConstraints.BASELINE_LEADING;
			gbc.insets = new Insets(0, 0, 0, 0);
			panel.add(panneauCommentaires, gbc);
			
			panelC.add(panel);
			panelC.add(Box.createRigidArea(new Dimension(0, 10)));
		}
		
		panelC.revalidate();
		panelC.repaint();
		this.pack();
		this.setSize(350, 500);
	}
	
	public String getMessage() {
		return texteCommentaire.getText();
	}

}
