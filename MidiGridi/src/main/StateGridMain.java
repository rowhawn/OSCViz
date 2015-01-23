package main;
import oscP5.OscMessage;
import oscP5.OscP5;
import processing.core.PApplet;
import processing.serial.Serial;
import processing.video.Capture;
import processing.video.Movie;
import themidibus.MidiBus;
import util.Constants;
import util.Properties;
import vid.VideoInput;
import controlP5.ControlEvent;
import display.Display;
import display.Grid;
import display.LedArray;
import display.Particles;
import display.Settings;

public class StateGridMain extends PApplet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MidiBus myBus;
	OscP5 oscP5;
	public Grid grid;
	public Settings settings;
	public LedArray ledArray;
	public Display screen;
	public Properties properties;
	Serial myPort;
	public Particles particles;
	public VideoInput vid;
	
	public static void main(String args[]) {
	    PApplet.main(new String[] { "--present", "StateGridMain" });
	  }

	@Override
	public boolean sketchFullScreen() {
		  return true;
		}
	
	@Override
	public void setup() {
//		size(1280, 720, P2D);
		size(displayWidth, displayHeight, P2D);
		if (frame != null) {
		    frame.setResizable(true);
		  }
		background(255);
		frameRate(30);
		colorMode(RGB, 255);
		stroke(0, 0);
		rectMode(CENTER);
		properties = new Properties();
		
		vid = new VideoInput(this, new Movie(this, "C:\\Users\\Rohan\\Dropbox\\mobileWorkspace\\MidiGridi\\data\\faceshot.mov"));
//		vid = new VideoInput(this, new Capture(this, Capture.list()[9]));
		
		particles = new Particles(this);
		
//		myBus = new MidiBus(this, -1, -1); // set output to -1 to avoid having to use ports
		oscP5 = initalizeOsc();
//		grid = new Grid(this);
		settings = new Settings(this);
//		ledArray = new LedArray(this, new Arduino(this, Arduino.list()[0], 57600));
		screen = particles;
	}

	@Override
	public void draw() {
		vid.processVideo();
		if (vid.isReady()){
			screen.draw();
		}
//		ledArray.draw();
	}
	
	public void noteOn(int channel, int pitch, int velocity) {
		screen.handleNoteOn(channel, pitch, velocity);
	}

	public void noteOff(int channel, int pitch, int velocity) {
		screen.handleNoteOff(channel, pitch, velocity);
	}

	public void controllerChange(int channel, int number, int value) {
		screen.handleControllerChange(channel, number, value);
	}

	public void keyPressed() {
		screen.handleKeyPressed(key, keyCode);
	}

	public void keyReleased() {
		screen.handleKeyRelease(key, keyCode);
	}

	public void controlEvent(ControlEvent theEvent) {
		screen.handleControlEvent(theEvent);
	}

	public void oscEvent(OscMessage message) {
		if(message.isPlugged()==false) {
			screen.handleOscEvent(message);
		}
	}
	
	public void receiveChannel1(float value){
		this.properties.setChannelValue(value, Constants.CHANNEL1);
	}

	public void receiveChannel2(float value){
		this.properties.setChannelValue(value, Constants.CHANNEL2);
	}

	public void receiveChannel3(float value){
		this.properties.setChannelValue(value, Constants.CHANNEL3);
	}
	
	public void receiveChannel4(float value){
		this.properties.setChannelValue(value, Constants.CHANNEL4);
	}
	
	public void receiveChannel5(float value){
		this.properties.setChannelValue(value, Constants.CHANNEL5);
	}
	
	public void receiveChannel6(float value){
		this.properties.setChannelValue(value, Constants.CHANNEL6);
	}
	
	public void receiveChannel7(float value){
		this.properties.setChannelValue(value, Constants.CHANNEL7);
	}
	
	public void receiveChannel8(float value){
		this.properties.setChannelValue(value, Constants.CHANNEL8);
	}
	
	public void receiveLotrig(int value){
		try{
			particles.applyRepeller();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private OscP5 initalizeOsc() {
		OscP5 oscP5 = new OscP5(this, 1337);
		oscP5.plug(this,"receiveChannel1","/channel1");
		oscP5.plug(this,"receiveChannel2","/channel2");
		oscP5.plug(this,"receiveChannel3","/channel3");
		oscP5.plug(this,"receiveChannel4","/channel4");
		oscP5.plug(this,"receiveChannel5","/channel5");
		oscP5.plug(this,"receiveChannel6","/channel6");
		oscP5.plug(this,"receiveChannel7","/channel7");
		oscP5.plug(this,"receiveChannel8","/channel8");
		oscP5.plug(this,"receiveLotrig","/lotrig");
		return oscP5;
	}
}
