package display;

import java.util.ArrayList;
import java.util.List;

import main.StateGridMain;
import oscP5.OscMessage;
import processing.core.PConstants;
import processing.core.PVector;
import blobDetection.Blob;
import controlP5.ControlEvent;
import display.unit.Particle;
import display.unit.Repeller;

public class Particles implements Display{
	StateGridMain parent;
	List<Particle> particleList;
	Repeller repeller;

	public Particles(StateGridMain pparent){
		parent = pparent;
		particleList = new ArrayList<Particle>();
		repeller = new Repeller(parent, parent.width / 2, parent.height / 2);
	}
	
	@Override
	public void draw() {
//		parent.background(255 * parent.properties.getChannelValue(Constants.CHANNEL1)); // maybe make background tied to amplitude as well?
		parent.background(0);
		populateParticleList();
		for (int i = 0; i < particleList.size(); i++){
			particleList.get(i).applyForce(PVector.random2D());
			particleList.get(i).draw();
			if (!particleList.get(i).isAlive()){
				particleList.remove(i);
			}
		}
	}

	private void populateParticleList() {
		while (particleList.size() < parent.properties.maxParticles){
			particleList.add(generateParticle());
		}
	}
	
	private Particle generateParticle(){
		while (true){
			float particleX = parent.random(parent.width);
			float particleY = parent.random(parent.height);
			int brightness = parent.vid.getBrightnessAt(particleX, particleY);
			if (brightness != 0){
				return new Particle(parent, particleX, particleY, brightness - 1);
			}
		}
	}
	
	public void applyRepeller() {
		Blob blob = parent.vid.getBlob();
		if (blob != null){
			repeller.setLocation(blob.x * parent.width, blob.y * parent.height);
		}
		for (Particle p: particleList) {
			if (p != null){
				PVector force = repeller.repel(p);
				p.applyForce(force);
			}
		  }
	}

	@Override
	public void handleKeyPressed(char key, int keyCode) {
		if (key == PConstants.CODED) {
			if (keyCode == PConstants.RIGHT) {
				parent.background(0);
				parent.screen = parent.settings;
			}
		} else {
			if (key == 'r'){
				applyRepeller();
			}
		}
	}

	@Override
	public void handleKeyRelease(char key, int keyCode) {

	}

	@Override
	public void handleNoteOn(int channel, int pitch, int velocity) {

	}

	@Override
	public void handleNoteOff(int channel, int pitch, int velocity) {

	}

	@Override
	public void handleControlEvent(ControlEvent theEvent) {

	}

	@Override
	public void handleControllerChange(int channel, int number, int value) {
		
	}

	@Override
	public void handleOscEvent(OscMessage message) {
		if (message.checkAddrPattern("/lotrig")){
			applyRepeller();
		}
	}
}
