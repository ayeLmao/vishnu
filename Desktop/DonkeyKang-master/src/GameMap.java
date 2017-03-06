import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;


public abstract class GameMap {
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	Image backgroundImage;
	
	public GameMap() {
		
	}
	
	public abstract void openBackgroundImage();

	public void draw(Graphics g, int x, int y,int w, int h) {
		g.drawImage(backgroundImage, x, y,w,h,null);
	}

	public void jump() {
		// TODO Auto-generated method stub
		
	}

}
