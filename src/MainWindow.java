import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class MainWindow {
	public static void runGUI(String skillName) {
		JFrame frame = new JFrame("Skill Shot Window");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(new LeagueCanvas());
        frame.setVisible(true);
        frame.addMouseMotionListener(new MouseComponent());
        frame.addKeyListener(new KeyComponent());
        LeagueCanvas.skillName = skillName;
	}
}

class LeagueCanvas extends JComponent {
	
	private int lastX = 0;
	private int lastY = 0;
	static String skillName;
	
	static ArrayList<LeagueProjectile> projectiles;
	
	public LeagueCanvas() {
		Thread animThread = new Thread(new Runnable() {
			public void run() {
				projectiles = new ArrayList<LeagueProjectile>();
				
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
		
		twodg.setColor(Color.BLACK);
		twodg.fillOval(w/2 - 12, h/2 - 12, 50, 50);
		
		for (int i = 0 ; i < projectiles.size() ; i++) {
			LeagueProjectile p = projectiles.get(i);
			
			p.xPos += p.xSpeed;
			p.yPos += p.ySpeed;
			twodg.setColor(p.color);
			twodg.fillOval((int)(p.xPos - (p.radiusOfHitbox / 2)), (int)(p.yPos - (p.radiusOfHitbox / 2)), p.radiusOfHitbox*2, p.radiusOfHitbox*2);
			
			if (p.isOffScreen()) {
				projectiles.remove(i);
				i--;
			}
		}
		
	}

	public static void addProjectile(LeagueProjectile leagueProjectile) {
		projectiles.add(leagueProjectile);
	}
	
}

@SuppressWarnings("serial")
class MouseComponent extends JComponent implements MouseMotionListener {

	static int lastX = 0;
	static int lastY = 0;
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		lastX = arg0.getX();
		lastY = arg0.getY()-40;
	}
}

class KeyComponent extends JComponent implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {
		//TODO: Instead of "q", make it the selected ability's key
		if (e.getKeyChar() == 'q') {
			
			double dir = 0;			
			double chX = MouseComponent.lastX - 500;
			double chY = 500 - MouseComponent.lastY;
			
			dir = Math.toDegrees(Math.atan(chY/chX));
			
			//Allows projectile to spawn in correct direction
			if (chX < 0) {
				dir += 180;
			}
						
			Skill skill = new Skill(LeagueCanvas.skillName);
			LeagueCanvas.addProjectile(new LeagueProjectile(skill.name, dir, 500, 500));
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
