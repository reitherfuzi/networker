package at.pwimmer.test.net.view.panels;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class StatusPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private static final Color GREEN = new Color(0, 255, 0);
	private static final Color ORANGE = new Color(125, 125, 125);
	private static final Color RED = new Color(255, 0, 0);
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	///
	/// ENUM - The Enum-Cass "Status", which holds the colors depending on the Status.
	///
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public enum Status {
		RUNNING(GREEN), PAUSED(ORANGE), STOPPED(RED);
		
		private final Color color;
		
		private Status(Color color) {
			this.color = color;
		}
		
		public Color getColor() {
			return color;
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	///
	/// CONSTRUCTOR - Initialize the Status-Panel with the DEFAULT values or with the passed ones.
	///
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private volatile Status currentStatus;
	
	public StatusPanel() {
		setStatus(Status.STOPPED);
	}
	
	public StatusPanel(Status status) {
		setStatus(status);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	///
	/// PAINT - The paint-method which fills this Panel with the "width" & "height" and the Color depending on the Status.
	///
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(currentStatus.getColor());
		g.fillRect(0, 0, getSize().width, getSize().height);
		
		super.paintComponent(g);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	///
	/// PUBLIC - The Setter and Getter methods for the Current-Status, the Height and Width.
	///
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void setStatus(Status nextStatus) {
		if(nextStatus != null) {
			this.currentStatus = nextStatus;
		}
	}
	
	public Status getStatus() {
		return currentStatus;
	}
}