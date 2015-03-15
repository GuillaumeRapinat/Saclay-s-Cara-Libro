package vue;

import javax.swing.JButton;

public class FriendButton extends JButton {

	private static final long serialVersionUID = 1L;
	
	private int id; 
	private JButton parent;
	
	public FriendButton(String string, int id, JButton parent) {
		super(string);
		this.id = id;
		this.parent = parent;
	}
	
	public int getId() {
		return id;
	}
	
	public JButton getParent() {
		return parent;
	}
		
}
