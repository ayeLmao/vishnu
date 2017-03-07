import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Map;

import javax.swing.JFrame;


public class MovingObjectsGameLauncher {

	public static void main(String[] args) {
		System.setProperty("sun.java2d.opengl", "true");
		JFrame gameFrame = new JFrame();
		Map<String,String> environMap= System.getenv();
		System.out.println(environMap.keySet());
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		MovingObjectsPanel mop = new MovingObjectsPanel(d);
		gameFrame.add(mop);
		gameFrame.pack();
		gameFrame.setSize(960, 825);
		mop.setBackground(Color.black);
		gameFrame.setVisible(true);
		gameFrame.setDefaultCloseOperation(gameFrame.EXIT_ON_CLOSE);
	}
}
