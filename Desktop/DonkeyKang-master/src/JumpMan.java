import java.awt.Image;


public class JumpMan extends GameObject {
	private int maxJump;
	private int maxClimb = 0;
	private int groundLevel = 755;
	private boolean isRightUp;
	public JumpMan(int x, int y) {
		super(x, y, 30, 30);
		openImage("heroLeft");
	}
	public void setPosition(String s){
		position = s;
	}
	public void moveRight(){
		openImage("heroRight");
		//move character right
		if (x <= 920) {
			x += 3;
		}
		if(x>870 && !isRightUp){
			groundLevel +=84;
		}
	}
	
	public void moveLeft() {
		openImage("heroLeft");
		if (x >= 3) {
			x -= 3;
		}
		if(x<60 && isRightUp){
			groundLevel +=84;
		}
	}
	public void moveUp() {
		if(y >= 1){
			y -= 1;
		}
	}
	public void moveDown() {
			y += 1;
	}
	public void climb() {
		y-=1;
		maxClimb +=1;
		groundLevel = y;
	}
	public void climbDown() {
		y+=1;
		maxClimb -=1;
		groundLevel = y;
		
	}
	public void setIsRightUp(boolean b){
		isRightUp = b;
	}
	public boolean getIsRightUp() {
		return isRightUp;
	}
	public int getMaxClimb(){
		return maxClimb;
	}

}
