package modele;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import controleur.Controleur_Utilisateur;

import vue.IdButton;
import vue.Vue_Admin;
import vue.Vue_Utilisateur;

public class ButtonEditor extends DefaultCellEditor {
	  protected IdButton button;

	  private String label;

	  private boolean isPushed;
	  
	  Controleur_Utilisateur controleurUtilisateur;
	  Modele_Admin admin;
	  public ButtonEditor(JCheckBox checkBox, Modele_Admin admin, Controleur_Utilisateur controleurUtilisateur, final JTable table
) {
		 
	    super(checkBox);
	    this.controleurUtilisateur = controleurUtilisateur;
	    button = new IdButton(admin.getId_utilisateur());
	    System.out.println(""+admin.getId_utilisateur());
	    button.setOpaque(true);
	    button.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    button.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	int id = Integer.parseInt((String) table.getValueAt(table.getSelectedRow(),0)); 
		    	 System.out.println("getValueAt donne " + id);
		    	 if((String) table.getValueAt(table.getSelectedRow(),table.getSelectedColumn()) == "TRUE"){
		    		 debloquerUtilisateur(id);
		    	 }
		    	 else{	 
		    		 bloquerUtilisateur(id);
			    }
		    	 fireEditingStopped();
		        
		      }
		    });
	  }

	  public Component getTableCellEditorComponent(JTable table, Object value,
	      boolean isSelected, int row, int column) {
	    if (isSelected) {
	      button.setForeground(table.getSelectionForeground());
	      button.setBackground(table.getSelectionBackground());
	    } else {
	      button.setForeground(table.getForeground());
	      button.setBackground(table.getBackground());
	    }
	    label = (value == null) ? "" : value.toString();
	    button.setText(label);
	    isPushed = true;
	    return button;
	  }

	  @SuppressWarnings("deprecation")
	public Object getCellEditorValue() {
	    if (isPushed) {
	    //	button.setActionCommand(Controleur_Utilisateur.ACTION_BLOQUER_UTILISATEUR);
		//	button.addActionListener(controleurUtilisateur);
	    //	button.setLabel("TRUE");
	    //   	JOptionPane.showMessageDialog(button, new JLabel() + ": Ouch!");
	      // System.out.println(label + ": Ouch!");
	    }
	    isPushed = false;
	    return new String(label);
	  }

	  public boolean stopCellEditing() {
	    isPushed = false;
	    return super.stopCellEditing();
	  }

	  protected void fireEditingStopped() {
	    super.fireEditingStopped();
	  }

	  private void bloquerUtilisateur(int id) {
			//int id_u = id.getId();
			switch(Modele_Admin.bloquerU(id)) {
			case "SUCCES":
				JOptionPane.showMessageDialog(new JDialog(),"Utilisateur bloqué", "Bloqué", JOptionPane.ERROR_MESSAGE);
				break;
			case "ERREUR BLOQUAGE":
				JOptionPane.showMessageDialog(new JDialog(),"Impossible de bloquer l'utilisateur.", "Erreur", JOptionPane.ERROR_MESSAGE);
	    		break;
			}
			
		}
	  private void debloquerUtilisateur(int id) {
			//int id_u = id.getId();
			switch(Modele_Admin.debloquerU(id)) {
			case "SUCCES":
				JOptionPane.showMessageDialog(new JDialog(),"Utilisateur débloqué", "Débloqué", JOptionPane.ERROR_MESSAGE);
				break;
			case "ERREUR BLOQUAGE":
				JOptionPane.showMessageDialog(new JDialog(),"Impossible de bloquer l'utilisateur.", "Erreur", JOptionPane.ERROR_MESSAGE);
	    		break;
			}
			
		}
}
