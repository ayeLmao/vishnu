import java.awt.Rectangle;


public class Latter extends GameObject {

	public Latter(int x, int y) {
		super(x, y, 30, 74);
		openImage("fixedLatter");
	}
	@Override
	public Rectangle getBoundingRect() {
		return new Rectangle(x+7, y, width-20, height);
	}
}
