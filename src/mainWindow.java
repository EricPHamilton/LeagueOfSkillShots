import javax.swing.*;
import java.awt.*;

public class mainWindow {
	public static void main (String[] args) {
		JFrame frame = new JFrame("Skill Shot Window");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLocationRelativeTo(null);
        frame.add(new LeagueCanvas());
        frame.setVisible(true);
	}
}

class LeagueCanvas extends JComponent {
	
	private int lastX = 0;
	private int lastY = 0;
	
	public LeagueCanvas() {
		Thread animThread = new Thread(new Runnable() {
			public void run() {
				while (true) {
					repaint();
					try {
						Thread.sleep(100);
					} catch (Exception ex) {
						System.out.println(ex.getStackTrace());
					}
				}
			}
		});
		
		animThread.start();
	}
		
	public void paintComponent (Graphics g) {
		Graphics2D twodg = (Graphics2D) g;
		
		int w = getWidth();
		int h = getHeight();
		
		int width = 40;
		int circleSpeed = 3;
		
		int x = lastX + circleSpeed;
		int y = lastY + circleSpeed;
		
		if (x > w +width) {
			x = -width;
		}
		
		if (y > h +width) {
			y = -width;
		}
		
		
		
		twodg.setColor(Color.CYAN);
		twodg.fillOval(x, y, width, width);
		
		lastX = x;
		lastY = y;
		
	}
}
