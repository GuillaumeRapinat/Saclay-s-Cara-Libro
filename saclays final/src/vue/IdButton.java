package vue;

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

	public int getId() {
		return id;
	}
	
}
