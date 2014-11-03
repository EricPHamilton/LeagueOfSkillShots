import java.awt.Color;


public class Skill {
	int speed;
	String name;
	Color color;
	int radiusOfHitbox;
	
	public Skill (String name) {
		if (name.equalsIgnoreCase("Kennen Q")) { 
			this.name = name;
			this.speed = 40;
			this.color = new Color(0, 102, 255); //Light-ish blue
			this.radiusOfHitbox = 15;
		} else if (name.equalsIgnoreCase("Ezreal Q")) {
			this.name = name;
			this.speed = 50;
			this.color = new Color(102, 204, 255); //Very white, blue tint.
			this.radiusOfHitbox = 20;
		} else /*if (name.equalsIgnoreCase("Blitzcrank Q"))*/ {
			this.name = name;
			this.speed = 30;
			this.color = new Color(204, 204, 51); //Gold-ish
			this.radiusOfHitbox = 35;
		}
	}
}
