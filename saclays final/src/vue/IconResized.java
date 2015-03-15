package vue;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class IconResized extends ImageIcon {

	private static final long serialVersionUID = 1L;

	public IconResized(InputStream photo, int w, int h) {
		if (photo == null) {
			URL url = getClass().getClassLoader().getResource("images/profil_petit.png");
			
			try {
				this.setImage(ImageIO.read(url));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			BufferedImage imgResized = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = imgResized.createGraphics();
		    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		    try {
				g.drawImage(ImageIO.read(photo), 0, 0, w, h, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
		    g.dispose();
		    
			this.setImage(imgResized);
		}
		
		
	}
	
}
