import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class DonkeyKangGameMap extends GameMap {
	protected List<Step> steps = new ArrayList<Step>();
	protected List<Latter> latters = new ArrayList<Latter>();
	protected List<Barrel> barrels = new ArrayList<Barrel>();
	private JumpMan hero = new JumpMan(300, 750);
	private DonkeyKang villan = new DonkeyKang(0, 20);
	protected boolean isMovingRight = false;
	protected boolean isMovingLeft = false;
	protected boolean isClimbingDown = false;
	protected static boolean isClimbing = false;
	protected int counter;
	public static boolean isMovingUp = false;
	public DonkeyKangGameMap(){
		setUpSteps();
		setUpLatters();
	}
	private void setUpLatters() {
		for(int i =0; i<3; i++){
			latters.add(new Latter(steps.get(26).getX(), steps.get(10+28*i).getY()));
			latters.add(new Latter(steps.get(29).getX()+30, steps.get(16+28*i).getY()));
		}
		latters.add(new Latter(steps.get(26).getX(), steps.get(26+28*3).getY()-82));
		latters.add(new Latter(steps.get(29).getX()+30, steps.get(29+28*2).getY()+95));
		
	}
	@Override
	public void openBackgroundImage(){
		
	}

	private void setUpSteps() {
		int temp = 0;
		for(int yRow = 1; yRow < 9; yRow++){
			for(int xCol = 0; xCol <14; xCol++){
				if(yRow%2 == 0){
					if(xCol%2 == 0){
						temp --;
						steps.add(new Step(xCol*60+120, yRow*90 + temp -13));
					}
					else{
						steps.add(new Step(xCol*60+120, yRow*90 + temp -13));
					}
				}
				else{
					if(xCol%2 == 0){
						temp ++;
						steps.add(new Step(xCol*60, yRow*90 + temp -19));
					}
					else{
						steps.add(new Step(xCol*60, yRow*90 + temp -19));
					}
				}
			}
			if(temp == -6){
				temp = 0;
			}
			else{
				temp = -7;
			}
		}
		for(int i=0; i<16; i++){
		steps.add(new Step(i*60, 780));
	}
		
	}
	public void draw(Graphics g) {
		for(int i=0; i<latters.size(); i++){
			latters.get(i).draw(g);
		}
		for(int i=0; i<steps.size(); i++){
			steps.get(i).draw(g);
		}
		for(int i=0; i<barrels.size(); i++){
			barrels.get(i).draw(g);
		}
		hero.draw(g);
		villan.draw(g);
	}
	private boolean checkJumpManCollisionsWithSteps(JumpMan hero) {
		int x = hero.getX();
		int y = hero.getY();
		Rectangle testBounds = new Rectangle(x, y, hero.getWidth(), hero.getHeight());
		for(Step s: steps){
			if (s.getBoundingRect().intersects(testBounds)){
				return true;
			}
		}
		return false;
	}
	private boolean checkJumpManCollisionsWithLatter(JumpMan hero) {
		int x = hero.getX();
		int y = hero.getY();
		Rectangle testBounds = new Rectangle(x, y, hero.getWidth(), hero.getHeight());
		for(Latter l: latters){
			if (l.getBoundingRect().intersects(testBounds)){
				return true;
			}
		}
		return false;
	}
	public int checkAllSpecialYValues(JumpMan hero){
		for(int i=0; i<latters.size(); i++){
			if(hero.getY()==latters.get(i).getY()){
				System.out.println("working");
				return i;
			}
		}
		return -1;
	}
	
	public void beginMoveRight() {
		isMovingRight = true;
	}
	public void endMoveRight() {
		isMovingRight = false;
	}
	public void beginMoveLeft() {
		isMovingLeft = true;
		
	}
	public void endMoveLeft() {
		isMovingLeft = false;
		
	}
	public void tick() {
		if(isClimbing && checkJumpManCollisionsWithLatter(hero)){
			hero.climb();
		}
		if(isClimbingDown && checkJumpManCollisionsWithLatter(hero) && !checkJumpManCollisionsWithSteps(hero)){
			hero.climbDown();
		}
		if(isMovingRight && !isClimbing){
			hero.moveRight();
			if(!checkJumpManCollisionsWithLatter(hero) &&!isMovingUp){
				hero.moveDown();
			}
			while(checkJumpManCollisionsWithSteps(hero)){
				hero.setY(hero.getY()-1);
			}
		}
		else if(isMovingLeft && !isClimbing){
			hero.moveLeft();
			if(!checkJumpManCollisionsWithLatter(hero) && !isMovingUp){
				hero.moveDown();
			}
			while(checkJumpManCollisionsWithSteps(hero)){
				hero.setY(hero.getY()-1);
			}
		}
		if(isMovingUp && counter!=40){
			hero.moveUp();
			counter +=1;
		}
		else if(!checkJumpManCollisionsWithLatter(hero)){ 
			hero.moveDown();
			while(checkJumpManCollisionsWithSteps(hero)){ 
					hero.setY(hero.getY()-1);
					counter = 0;
			}
		}
	}
	public void beginJump() {
		isMovingUp = true;
	}
	public void endJump() {
		isMovingUp = false;
	}
	public void beginClimb() {
		isClimbing=true;
		
	}
	public void endClimb() {
		isClimbing = false;
		
	}
	public void beginClimbDown() {
		isClimbingDown=true;
	}
	public void endClimbDown() {
		isClimbingDown = false;
	}
	public void tick2() {
		barrels.add(new Barrel(70, 60));
		for(Barrel b: barrels){
			b.move();
		}
	}
}
