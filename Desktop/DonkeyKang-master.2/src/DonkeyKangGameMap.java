import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

import javax.swing.JOptionPane;

public class DonkeyKangGameMap extends GameMap {
	protected List<Step> steps = new ArrayList<Step>();
	protected List<Latter> latters = new ArrayList<Latter>();
	protected List<Barrel> barrels = new ArrayList<Barrel>();
	private JumpMan hero = new JumpMan(300, 750);
	private DonkeyKang villan = new DonkeyKang(0, 8);
	protected boolean isMovingRight = false;
	protected boolean isMovingLeft = false;
	protected boolean isClimbingDown = false;
	protected static boolean isClimbing = false;
	protected int counter;
	protected int test = 0;
	public static int level = 1;
	public static boolean isMovingUp = false;
	public boolean remakeMap;
	
	public void setRemake(boolean input){
		remakeMap = input;
	}
	
	public boolean getRemake(){
		return remakeMap;
	}
	
	public DonkeyKangGameMap(){
		setUpSteps();
		setUpLatters();
	}
	private void setUpLatters() {
		for(int i =0; i<3; i++){
			latters.add(new Latter(steps.get(26).getX(), steps.get(10+28*i).getY()));
			latters.add(new Latter(steps.get(29).getX()+30, steps.get(16+28*i).getY()));
		}
		latters.add(new Latter(steps.get(26).getX(), steps.get(26+28*3).getY()-86));
		latters.add(new Latter(steps.get(29).getX()+30, steps.get(29+28*2).getY()+98));
		
	}
	public static int getLevel(){
		return level;
	}
	@Override
	public void openBackgroundImage(){
		
	}
	
	public void setAllEqualToFalse(){
		isMovingUp =false;
		isClimbing = false;
		isClimbingDown = false;
		isMovingLeft = false;
		isMovingRight = false;
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
						steps.add(new Step(xCol*60, yRow*90 + temp -23));
					}
					else{
						steps.add(new Step(xCol*60, yRow*90 + temp -23));
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
	private boolean checkJumpManCollisionsWithBarrel(JumpMan hero) {
		int x = hero.getX();
		int y = hero.getY();
		Rectangle testBounds = new Rectangle(x+10, y, hero.getWidth()-10, hero.getHeight());
		for(Barrel b: barrels){
			if (b.getBoundingRect().intersects(testBounds)){
				return true;
			}
		}
		return false;
	}
	private boolean checkJumpManCollisionsWithDonkeyKong(JumpMan hero) {
		int x = hero.getX();
		int y = hero.getY();
		Rectangle testBounds = new Rectangle(x, y, hero.getWidth(), hero.getHeight());
		if (villan.getBoundingRect().intersects(testBounds)){
			return true;
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
	public void tick(Timer t, Timer t2) {
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
			for(int x=0; x<10-test; x++){
				hero.moveUp();
				counter +=1;
			}
			test ++;
		}
		else if(!checkJumpManCollisionsWithLatter(hero)){ 
			hero.moveDown();
			while(checkJumpManCollisionsWithSteps(hero)){ 
					hero.setY(hero.getY()-1);
					counter = 0;
					test = 0;
			}
		}
		for(int i=0; i<barrels.size(); i++){
			barrelMove(barrels.get(i));
		}
		if(checkJumpManCollisionsWithDonkeyKong(hero)){
			t.stop();
			t2.stop();
			JOptionPane.showMessageDialog(null, "Level " + level + " passed.", "Level Passed", JOptionPane.INFORMATION_MESSAGE);
			hero.setX(300);
			hero.setY(750);
			setAllEqualToFalse();
			barrels = new ArrayList<Barrel>();
			remakeMap = true;
			level++;
			t.start();
			t2.start();
		}
		if(checkJumpManCollisionsWithBarrel(hero)){
			t.stop();
			t2.stop();
			//openEndGameImage();
			JOptionPane.showMessageDialog(null, "You suck at Donkey Kang.", "You lose!", JOptionPane.INFORMATION_MESSAGE, null);
//			level++;
//			t.start();
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
		if(checkJumpManCollisionsWithBarrel(hero)){
		}
	}
	private void barrelMove(Barrel b) {
		if(b.getIsBarrelFalling()){
			if(checkBarrelCollisionsWithSteps(b)){
				b.setIsBarrelFalling(false);
				if(b.getIsRightDown()){
					b.setIsRightDown(false);
				}
				else{
					b.setIsRightDown(true);
				}
			}
				b.moveDown();
		}
		else{
		if(b.getIsRightDown()){
			b.moveRight();
		}
		else{
			b.moveLeft();
		}
		b.moveDown();
		if(!checkBarrelCollisionsWithSteps(b)){
			b.moveDown();
			b.setIsBarrelFalling(true);
		}
		else{
			while(checkBarrelCollisionsWithSteps(b)){
				b.setY(b.getY()-1);
			}
		}
		}
	}
	private boolean checkBarrelCollisionsWithSteps(Barrel b) {
		int x = b.getX();
		int y = b.getY();
		Rectangle testBounds = new Rectangle(x, y, b.getWidth(), b.getHeight());
		for(Step s: steps){
			if (s.getBoundingRect().intersects(testBounds)){
				return true;
			}
		}
		return false;
	}
}
