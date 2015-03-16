package vue;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class IdButton extends JButton {

	private static final long serialVersionUID = 1L;
	
	private int id;

	public IdButton(int id) {
		this.id = id;
	}
	
	public IdButton(String string, int id) {
		super(string);
		this.id = id;
	}

	public IdButton(Icon iconResized, int i) {
		super(iconResized);
		id = i;
	}

	public IdButton(ImageIcon imageIcon) {
		super(imageIcon);
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
}
