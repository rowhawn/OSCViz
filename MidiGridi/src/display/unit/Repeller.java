package display.unit;

import main.StateGridMain;
import processing.core.PApplet;
import processing.core.PVector;

public class Repeller {
	StateGridMain parent;
	PVector location;
	
	public Repeller (StateGridMain pparent, float x, float y){
		parent = pparent;
		location = new PVector(x, y);
	}

	public PVector repel(Particle p) {
		PVector dir = PVector.sub(location,p.location);
		float d = dir.mag();
		d = PApplet.constrain(d,5,100);
		dir.normalize();
		float force = -1 * 5F;
		dir.mult(force);
		return dir;
	}
	
	public void setLocation(float x, float y){
		location.set(x, y);
	}
	
	public void draw(){
		parent.fill(255);
		parent.ellipse(location.x, location.y, 20, 20);
	}
}
