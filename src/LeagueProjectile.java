

import java.awt.Color;

public class LeagueProjectile {
	String skillName;
	int totalSpeed;
	double xSpeed;
	double ySpeed;
	double dir; //0 - 359, follows unit circle directionals
	double xPos;
	double yPos;
	Color color;
	int radiusOfHitbox;
	
	public LeagueProjectile (int totalSpeed, double dir, int startingX, int startingY, Color color, int radiusOfHitbox) {
		double dirDegrees = Math.toRadians(dir);
		
		this.totalSpeed = totalSpeed;
		this.dir = dirDegrees;
		
		xSpeed = totalSpeed * Math.cos(dirDegrees);
		ySpeed = -1 * totalSpeed * Math.sin(dirDegrees);
		
		xPos = startingX;
		yPos = startingY;
		
		this.color = color;
		this.radiusOfHitbox = radiusOfHitbox;
	}
	
	public boolean isOffScreen() {
		if ((xPos < (0 - radiusOfHitbox)) || xPos > (1000 + radiusOfHitbox)) {
			return true;
		}
		
		if ((yPos < (0 - radiusOfHitbox)) || yPos > (1000 + radiusOfHitbox)) {
			return true;
		}
		
		return false;
	}
}
