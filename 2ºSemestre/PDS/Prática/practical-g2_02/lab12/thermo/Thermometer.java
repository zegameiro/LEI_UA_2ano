package lab12.thermo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * 
 * This class manages the data of a thermometer, specifically the current temperature.
 */
public class Thermometer extends JPanel{
	/** The height of the mercury tube */
	private static final int TUBE_HEIGHT = 150;
	
	/** The width of the mercury tube */
	private static final int TUBE_WIDTH = 10;
	
	/** The left edge of the tube */
	private static final int LEFT = 100;
	
	/** The top of the tube */
	private static final int TOP = 50;
	
	/** The height of the component containing the tube */
	private static final int COMPONENT_HEIGHT = TUBE_HEIGHT * 2;
	
	/** The width of the component containing the tube */
	private static final int COMPONENT_WIDTH = TUBE_WIDTH * 30;
	
	/** The current temperature */
	private int current;
	
	/** The minimum temperature */
	private int minimum;
	
	/** The maximum temperature */
	private int maximum;
	
	/**
	 * Create a new thermometer.  min should be less than max.
	 * @param min the minimum temperature the thermometer should display
	 * @param max the maximum temperature the thermometer should display
	 * @param cur temperature to display
	 */
	public Thermometer (int cur, int min, int max)  {
		minimum = min;
		maximum = max;
		setCurrent(cur);
	}
	
	
	/**
	 * Displays the temperature passed in.
	 * @param cur the temperature to display.  If cur is outside the range of the thermometer,
	 * the mercury is set at its upper or lower bound.
	 */
	public void setCurrent(int cur) {
		if (cur > maximum) {
			current = maximum;
		}
		else if (cur < minimum) {
			current = minimum;
		}
		else {
			current = cur;
		}
		repaint();
	}

	/**
	 * Draws the thermometer as a mercury tube and bulb with the minimum,
	 * maximum, and current temperatures labeled.
	 * @param g the graphics context to draw on
	 */
	@Override
	public void paintComponent(Graphics g) {
		// Paint the background
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
	    
	    // Draw the tube
		g.setColor(getForeground());
		g.drawRect (LEFT, TOP, TUBE_WIDTH, TUBE_HEIGHT);
		
		// Draw the minimum and maximum labels
		g.setFont (new Font ("Arial", Font.BOLD, 12));
		int fontHeight = g.getFontMetrics().getHeight();
		int xLabel = LEFT + TUBE_WIDTH + 5;
		int yLabel = TOP + fontHeight/2;
		g.drawString (String.valueOf(maximum), xLabel, yLabel);
		g.drawString (String.valueOf(minimum), xLabel, yLabel + TUBE_HEIGHT);
		
		// Draw the mercury
		int mercuryHeight = TUBE_HEIGHT * (current - minimum) / (maximum - minimum);
		int mercuryTop = TOP + TUBE_HEIGHT - mercuryHeight;
		g.setColor (Color.blue);
		g.fillRect (LEFT, mercuryTop, TUBE_WIDTH, mercuryHeight);
		
		// Draw the bulb at the base of the thermometer
		int bulbDiameter = TUBE_WIDTH * 2;
		g.fillOval (LEFT - TUBE_WIDTH/2, TOP + TUBE_HEIGHT - bulbDiameter/2, bulbDiameter, bulbDiameter);
	
		// Label the current temperature
		g.setColor (Color.orange);
		g.drawString (String.valueOf (current), xLabel, mercuryTop + fontHeight/2);
	}

	/**
	 * Returns the minimum size for the thermometer component
	 * @return the minimum size for the thermometer component
	 */
	@Override
	public Dimension getMinimumSize() {
		return new Dimension (COMPONENT_WIDTH, COMPONENT_HEIGHT);
	}

	/**
	 * Returns the preferred size for the thermometer component
	 * @return the preferred size for the thermometer component
	 */
	@Override
	public Dimension getPreferredSize() {
		return getMinimumSize();
	}

	

}
