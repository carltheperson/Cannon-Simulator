package cannonsimulator;

import java.awt.Color;

public class Ball {
	private double x;
	private double y;
	private double diameter;
	
	private double speedX;
	private double speedY;
	
	private double velocity;
	private Color color;
	
	//----------------------------------------------------------//
	
	public Ball(int x, int y, int diameter, int speedX, int speedY, Color color) {
		this.x = (double) x;
		this.y = (double) y;
		this.diameter = (double) diameter;
		this.speedX = (double) speedX/4.5;
		this.speedY = (double) ((speedY/4.5) * -1);
		this.color = color;
		
		this.velocity = this.speedY;
		
	}
	
	public int getX() {
		return (int) x;
	}
	public int getY() {
		return (int) y;
	}
	public int getDiameter() {
		return (int) diameter;
	}
	public Color getColor() {
		return color;
	}
	
	public void update() {
		
		//-------- X ----------//
		
		x = x + speedX;
		speedX = speedX * 0.996; //Air resistance

		//If it hits the walls reverse
		if (x > GamePanel.WIDTH - diameter || x < 0) {
			speedX = speedX * -1;
		}
		
		//If it is slow enough stop
		if (speedX < 0 && speedX > -0.1 || speedX > 0 && speedX < 0.1) {
			speedX = 0;
		}
		
		//If the ball is done bouncing, add more resistance
		if (velocity < 0.1 && y == GamePanel.HEIGHT - diameter) {
			speedX = speedX * 0.996;
		}
		
		//-------- Y ----------//
		
		velocity = velocity * 0.999; //Air resistance
		velocity = velocity + 0.4; //Gravity
		y = y + velocity;
		
		// if it hits the bottom
		if (y + diameter >= GamePanel.HEIGHT) {
			velocity = velocity *-1 + 0.55;
			//If it won't be able to bounce back
			if (velocity + 0.4 + y + diameter >= GamePanel.HEIGHT) {
				y = GamePanel.HEIGHT - diameter;
			}
		}
		
	}
	

}
