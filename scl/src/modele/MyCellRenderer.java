package modele;

import java.awt.Component;
import java.awt.Frame;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

import vue.Vue_Liste_Jaime;

public class MyCellRenderer implements TableCellRenderer {

	private static final long serialVersionUID = 1L;
	 
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        if (value instanceof JComboBox) {
            return (JComboBox) value;
        }
        if (value instanceof Boolean) {
            JCheckBox cb = new JCheckBox();
            cb.setSelected(((Boolean) value).booleanValue());
            return cb;
        }
        if (value instanceof JCheckBox) {
            return (JCheckBox) value;
        }
        return new JTextField(value.toString());
    }


}
