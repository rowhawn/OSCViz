package display;
import main.StateGridMain;
import oscP5.OscMessage;
import processing.core.PConstants;
import themidibus.MidiBus;
import util.Constants;
import controlP5.Button;
import controlP5.ControlEvent;
import controlP5.ControlP5;
import controlP5.Slider;
import controlP5.Textlabel;

public class Settings implements Display {
	StateGridMain parent;
	static ControlP5 myController;
	static Slider fadeLengthSlider;
	static Button channelMonitor;
	static Textlabel channel1Label;
	static Textlabel channel2Label;
	static Textlabel channel3Label;
	static Textlabel channel4Label;
	static Textlabel channel5Label;
	static Textlabel channel6Label;
	static Textlabel channel7Label;
	static Textlabel channel8Label;

	public Settings(StateGridMain pparent) {
		parent = pparent;
		myController = new ControlP5(pparent);
		myController.setAutoDraw(false);
		fadeLengthSlider = myController.addSlider("FadeLength", 0, 30,
				parent.properties.defaultFadeSleepTime, 30, 100, 10, 100);
		channelMonitor = myController.addButton("Show Channel Info").setPosition(30, 400).setWidth(85).setSwitch(true);
		channel1Label = myController.addTextlabel("channel 1 value").setText("C1:     " + parent.properties.getChannelValue(Constants.CHANNEL1)).setPosition(30, 440).hide();
		channel2Label = myController.addTextlabel("channel 2 value").setText("C2:    " + parent.properties.getChannelValue(Constants.CHANNEL2)).setPosition(30, 460).hide();
		channel3Label = myController.addTextlabel("channel 3 value").setText("C3:    " + parent.properties.getChannelValue(Constants.CHANNEL3)).setPosition(30, 480).hide();
		channel4Label = myController.addTextlabel("channel 4 value").setText("C4:    " + parent.properties.getChannelValue(Constants.CHANNEL4)).setPosition(30, 500).hide();
		channel5Label = myController.addTextlabel("channel 5 value").setText("C5:    " + parent.properties.getChannelValue(Constants.CHANNEL5)).setPosition(130, 440).hide();
		channel6Label = myController.addTextlabel("channel 6 value").setText("C6:    " + parent.properties.getChannelValue(Constants.CHANNEL6)).setPosition(130, 460).hide();
		channel7Label = myController.addTextlabel("channel 7 value").setText("C7:    " + parent.properties.getChannelValue(Constants.CHANNEL7)).setPosition(130, 480).hide();
		channel8Label = myController.addTextlabel("channel 8 value").setText("C8:    " + parent.properties.getChannelValue(Constants.CHANNEL8)).setPosition(130, 500).hide();
	}

	@Override
	public void draw() {
		parent.fill(0);
		parent.rectMode(PConstants.CORNER);
		parent.rect(0, 0, 1360, 768);
		parent.textSize(32);
		parent.fill(255);
		parent.text("SETTINGS", 10, 30);
		myController.draw();
		channel1Label.setText("C1:     " + parent.properties.getChannelValue(Constants.CHANNEL1));
		channel2Label.setText("C2:    " + parent.properties.getChannelValue(Constants.CHANNEL2));
		channel3Label.setText("C3:    " + parent.properties.getChannelValue(Constants.CHANNEL3));
		channel4Label.setText("C4:    " + parent.properties.getChannelValue(Constants.CHANNEL4));
		channel5Label.setText("C5:    " + parent.properties.getChannelValue(Constants.CHANNEL5));
		channel6Label.setText("C6:    " + parent.properties.getChannelValue(Constants.CHANNEL6));
		channel7Label.setText("C7:    " + parent.properties.getChannelValue(Constants.CHANNEL7));
		channel8Label.setText("C8:    " + parent.properties.getChannelValue(Constants.CHANNEL8));
	}

	public static int getFadeSleepTime() {
		return (int) fadeLengthSlider.getValue();
	}

	public void resetSettings() {
		fadeLengthSlider.setValue(parent.properties.defaultFadeSleepTime);
	}

	@Override
	public void handleKeyPressed(char key, int keyCode) {
		if (key == PConstants.CODED) {
			if (keyCode == PConstants.LEFT) {
				parent.background(0);
				parent.screen = parent.particles;
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
	public void handleControllerChange(int channel, int number, int value) {
	}

	public void handleControlEvent(ControlEvent theEvent) {
		if (theEvent.isFrom(channelMonitor)){
			if (channelMonitor.isOn()){
				channel1Label.show();
				channel2Label.show();
				channel3Label.show();
				channel4Label.show();
				channel5Label.show();
				channel6Label.show();
				channel7Label.show();
				channel8Label.show();
			} else {
				channel1Label.hide();
				channel2Label.hide();
				channel3Label.hide();
				channel4Label.hide();
				channel5Label.hide();
				channel6Label.hide();
				channel7Label.hide();
				channel8Label.hide();
			}
		}
	}

	private void setMidiIO() {
		int i = 0;
		for (String string : MidiBus.availableInputs()) {
			myController.addButton("in: " + string)
			.setValue(i)
			.setPosition(0, (i * 35))
			.setSize(100, 30)
			.setSwitch(true);
//			.setGroup(midiInButtons);
			i++;
		}
		i = 0;
		for (String string : MidiBus.availableOutputs()) {
			myController.addButton("out: " + string)
			.setValue(i)
			.setPosition(0, (i * 35))
			.setSize(160, 30)
			.setSwitch(true);
//			.setGroup(midiOutButtons);
			i++;
		}
	}

	@Override
	public void handleOscEvent(OscMessage message) {
		// TODO Auto-generated method stub
		
	}
}
