package cannonsimulator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class SliderInput {

	private int min;
	private int max;
	private int x;
	private int y;
	
	private int width = 250;
	private int height = 10;
	
	private String label;
	
	private int sliderX;
	private int sliderWidth = 10;
	private int sliderHeight = 30;
	private boolean sliderGrapped = false;
	
	//----------------------------------------------------------//
	
	public SliderInput(int x, int y, int min, int max, String label) {
		this.min = min;
		this.max = max;
		this.x = x;
		this.y = y;
		this.label = label;
		
		this.sliderX = (width / 2) - (sliderWidth / 2);
	}
	
	public Graphics2D draw(Graphics2D g) {
		
		if (GamePanel.dragging && GamePanel.cursorX > (sliderX - 10) + x && GamePanel.cursorX < sliderX + (sliderWidth + 10) + x && GamePanel.cursorY > y && GamePanel.cursorY < y + height) {
			sliderGrapped = true;
		}
		if (!GamePanel.dragging) {
			sliderGrapped = false;
		}
		if (sliderGrapped && GamePanel.cursorX > x + (sliderWidth / 2) && GamePanel.cursorX < x + width - 1) {
			sliderX = GamePanel.cursorX - x - (sliderWidth / 2);
		}

		g.setColor(Color.GRAY);
		g.fillRect(x, y, width, height);
		
		g.setColor(Color.BLACK);
		g.fillRect(sliderX + x, y - (sliderHeight / 3), sliderWidth, sliderHeight);
		
		g.setColor(Color.BLACK);
		Font f = new Font("Calibri", Font.BOLD, 24);
		g.setFont(f);
		g.drawString(label, (width / 2) - (sliderWidth / 2), y - 12);
		
		
		return g;
	}
	
	public int getValue() {
		
		return (int) ((double) (sliderX + (sliderWidth / 2)) / (double) width * (double) (max - min));
		
	}


}
