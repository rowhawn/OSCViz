package display.unit;

import main.StateGridMain;
import processing.core.PConstants;
import processing.core.PVector;
import util.Input;

public class Particle implements DisplayUnit{
	StateGridMain parent;
	PVector location;
	PVector velocity;
	PVector acceleration;
	int lifeTime;
	int channel;
	float mass;
	
	public Particle(StateGridMain pparent, float x, float y, int brightness){
		parent = pparent;
		location = new PVector(x, y);
		velocity = new PVector(0f, 0f);
		acceleration = new PVector(0f, 0f);
		lifeTime = (int) parent.random(parent.properties.particleLifetime - 5, parent.properties.particleLifetime + 5);
		channel = brightness;
		mass = 1.0f;
	}

	@Override
	public void draw() {
		update();
		display();
	}

	private void display() {
		parent.noStroke();
		parent.colorMode(PConstants.HSB, 255);
		int pointColor = parent.color(255 *  parent.noise((float)parent.millis() / 10000.0f), 255, 255 * parent.properties.getChannelValue(channel));
		parent.fill(pointColor);
		parent.ellipse(location.x, location.y, 5, 5);
	}

	private void update() {
		velocity.add(acceleration);
//		velocity.limit(2.0f);
		location.add(velocity);
		acceleration.mult(0);
		checkBounds();
		decrementLifetime();
	}
	
	private void decrementLifetime() {
		if (lifeTime > 0){
			lifeTime--;
		}
	}

	public boolean isAlive(){
		return lifeTime > 0;
	}

	private void checkBounds() {
		if ((location.x > parent.width) || (location.x < 0)){
			velocity.x = velocity.x * -1;
		}
		if ((location.y > parent.height) || (location.y < 0)){
			velocity.y = velocity.y * -1;
		}
		
		// TODO check if new position is within channel bounds
	}

	@Override
	public void handleInput(Input input, int channel) {
		// TODO Auto-generated method stub
		
	}

	public void applyForce(PVector force) {
		PVector f = force.get();
		f.div(mass);
		acceleration.add(f);
	}

}
