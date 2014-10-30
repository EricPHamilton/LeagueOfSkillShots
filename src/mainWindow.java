import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

public class mainWindow {
	public static void runGUI(String skillName) {
		JFrame frame = new JFrame("Skill Shot Window");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLocationRelativeTo(null);
        frame.add(new LeagueCanvas());
        frame.setVisible(true);
        LeagueCanvas.skillName = skillName;
	}
}

class LeagueCanvas extends JComponent {
	
	private int lastX = 0;
	private int lastY = 0;
	static String skillName;
	
	ArrayList<LeagueProjectile> projectiles;
	
	public LeagueCanvas() {
		Thread animThread = new Thread(new Runnable() {
			public void run() {
				projectiles = new ArrayList<LeagueProjectile>();
				
				projectiles.add(new LeagueProjectile(10, 0, 100, 100, Color.GREEN, 20));
				projectiles.add(new LeagueProjectile(20, 180, 400, 400, Color.BLUE, 20));
				
				while (true) {
					repaint();
					try {
						//30 FPS
						Thread.sleep(33);
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
		
		for (int i = 0 ; i < projectiles.size() ; i++) {
			LeagueProjectile p = projectiles.get(i);
			
			p.xPos += p.xSpeed;
			p.yPos += p.ySpeed;
			twodg.setColor(p.color);
			twodg.fillOval(p.xPos, p.yPos, p.radiusOfHitbox, p.radiusOfHitbox);
			
			if (p.isOffScreen()) {
				projectiles.remove(i);
				i--;
			}
		}
		
	}
}
