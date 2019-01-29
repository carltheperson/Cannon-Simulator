package cannonsimulator;

import java.awt.Graphics2D;
import java.awt.Color;
import java.util.Random;

public class Cloud {
	private int size;
	private double x;
	private int y;
	private double speed;
	private Random rand = new Random(); 
	
	//----------------------------------------------------------//
	
	public Cloud() {
		resetCloud();
		x = rand.nextInt(GamePanel.WIDTH);
	}
	
	public Graphics2D draw(Graphics2D g) {
		this.update();
		g.setColor(new Color(230, 255, 255));
		g.fillOval((int)x, y, size, size);
		g.fillOval((int)x + (size / 2) + (size / 6), y - (size / 3), size, size);
		g.fillOval((int)x + size + (size / 3), y, size, size);
		
		return g;
	}
	 void update() {
		x += speed;
		if (x > GamePanel.WIDTH) {
			resetCloud();
		}
		
	}
	
	private void resetCloud() {
		y = (int) rand.nextInt((400) + 1);
		size = (int) rand.nextInt(60 - 30) + 30;
		x = ((size * 2.5) + (int) rand.nextInt(400)) *-1; //x is below 0 so that the cloud dosen't immediately respawn
		speed = (Math.random() * 0.5 + 0.25);
	}
}
