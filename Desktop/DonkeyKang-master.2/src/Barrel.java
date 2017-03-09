import java.awt.Rectangle;


public class Barrel extends GameObject {
	protected boolean isRightDown = true;
	protected String picture = "barrelUpright";
	protected boolean isBarrelFalling;
	public Barrel(int x, int y) {
		super(x, y, 15, 15);
		openImage(picture);
	}

	public void moveRight() {
		x += 1*DonkeyKangGameMap.getLevel();
		if(picture.equals("barrelUpright")){
			picture = "barrelTilted";
		}
		else{
			picture = "barrelUpright";
		}
		openImage(picture);
	}

	public boolean getIsRightDown() {
		return isRightDown;
	}
	
	public void setIsRightDown(boolean b){
		isRightDown = b;
	}
	public void setIsBarrelFalling(boolean b){
		isBarrelFalling = b;
	}
	public boolean getIsBarrelFalling(){
		return isBarrelFalling;
	}
	public void moveDown() {
		y +=2;
		
	}
	@Override
	public Rectangle getBoundingRect() {
		return new Rectangle(x+5, y, width-5, height);
	}

	public void moveLeft() {
		x-= 1*DonkeyKangGameMap.getLevel();
		if(picture.equals("barrelUpright")){
			picture = "barrelTilted";
		}
		else{
			picture = "barrelUpright";
		}
		openImage(picture);
	}
}
