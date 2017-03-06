import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.net.URL;

import javax.imageio.ImageIO;


public class GameObject {
	protected int x;
	protected int y;
	private int width;
	private int height;
	private double direction;
	protected Image img;
	protected String type;
	protected String position;
	
	public GameObject(int x, int y, int wid, int ht) {
		this.x = x;
		this.y = y;
		this.width = wid;
		this.height = ht;
		
	}
	
	public void openImage(String src) {
		try {
			URL url = getClass().getResource("Images/" + src + ".png");
			img = ImageIO.read(url);
		} catch (Exception e) {
			System.out.println("Image could not be opened: " + "images/" + src + ".png");
			e.printStackTrace();
		}
	}
	public void draw(Graphics g){
		g.drawImage(img, x, y, null);
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	public int getX() {
		return this.x;
	}
	
	public Rectangle getBoundingRect() {
		return new Rectangle(x, y, width, height);
	}
	
	public int getY() {
		return this.y;
	}
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}

}
