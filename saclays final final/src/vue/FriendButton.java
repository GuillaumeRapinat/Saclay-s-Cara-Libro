package vue;

import javax.swing.JButton;
import javax.swing.JFrame;

public class FriendButton extends JButton {

	private static final long serialVersionUID = 1L;
	
	private int id; 
	private JButton parent;
	private JFrame parentF;
	
	public FriendButton(String string, int id, JButton parent) {
		super(string);
		this.id = id;
		this.parent = parent;
	}
	public FriendButton(String string, int id, JFrame parentF) {
		super(string);
		this.id = id;
		this.parentF= parentF;
	}
	
	public int getId() {
		return id;
	}
	
	public JButton getParent() {
		return parent;
	}
		
}
