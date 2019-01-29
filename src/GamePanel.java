package cannonsimulator;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable, MouseListener, MouseMotionListener {
	
	public static int WIDTH = 1200;
	public static int HEIGHT = 800;
	
	private Thread thread;
	private boolean running;
	private BufferedImage image;
	private Graphics2D g;
	private int fps = 60;
	
	private Cannon cannon = new Cannon();
	private SliderInput angleSlider = new SliderInput(50, 155, 157, 0, "Angle");
	private SliderInput sizeSlider = new SliderInput(50, 225, 0, 75, "Size");
	private SliderInput powerSlider = new SliderInput(50, 295, 150, 0, "Power");
	private Cloud cloud1 = new Cloud();
	private Cloud cloud2 = new Cloud();
	private Cloud cloud3 = new Cloud();
	
	//----------------------------------------------------------//
	
	public GamePanel() {
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
		addMouseMotionListener(this);
		addMouseListener(this);
		
	}
	
	public void addNotify() {
		super.addNotify();
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}
	
	public void run() {
		running = true;
		
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		//fps
		long startTime;
		long URDTimeMillis;
		long waitTime;
		long targetTime = 1000 / fps;
		
		//-------------------- GAME LOOP -----------------//
		while (running) {
			startTime = System.nanoTime();
			
			gameRender();
			gameDraw();
			
			
			//fps
			URDTimeMillis = (System.nanoTime() - startTime) / 1000000;
			waitTime = targetTime - URDTimeMillis;
			try {
				Thread.sleep(waitTime);
			}
			catch (Exception e) {}
			
		}
	}
	

	private void gameRender() {
		
		//Background and grass
		g.setColor(new Color(197, 234, 243));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(new Color(28, 232, 119));
		g.fillRect(0, HEIGHT - 50, WIDTH, 50);
		
		//Clouds
		g = cloud1.draw(g);
		g = cloud2.draw(g);
		g = cloud3.draw(g);
		
		//Cannon
		g = cannon.draw(g, angleSlider.getValue(), sizeSlider.getValue(), powerSlider.getValue());
		
		//Sliders
		g = angleSlider.draw(g);
		g = sizeSlider.draw(g);
		g = powerSlider.draw(g);
		
		//Game text
		g.setColor(Color.BLACK);
		Font f = new Font("Calibri", Font.BOLD, 72);
		g.setFont(f);
		g.drawString("Cannon Simulator", 460, 85);
		f = new Font("Helvetica", Font.BOLD, 24);
		g.setFont(f);
		g.drawString("A totally accurate simulation of using a cannon!", 453, 115);
		
		
	}
	
	private void gameDraw() {
		Graphics g2 = this.getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}
	
	
	//-------------------- MOUSE LISTENING -----------------//
	static boolean dragging = false;
	static boolean click = false;
	static int cursorX = 0;
	static int cursorY = 0;
	
	@Override
	public void mouseClicked(MouseEvent e) {
		click = true;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		dragging = true;
		cursorX = e.getX();
		cursorY = e.getY();
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		dragging = false;
		click = false;
		cursorX = e.getX();
		cursorY = e.getY();
	}
	
	//EMPTY FUNCTIONS
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
}



