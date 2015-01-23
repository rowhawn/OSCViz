package display;

import cc.arduino.Arduino;
import main.StateGridMain;
import oscP5.OscMessage;
import state.led.AmplitudeState;
import util.Constants;
import util.PinType;
import controlP5.ControlEvent;
import display.unit.Led;

public class LedArray implements Display {
	StateGridMain parent;
	int xLeds;
	int yLeds;
	Led[][] ledArray;
	Arduino arduino;

	public LedArray(StateGridMain pparent, Arduino arduino) {
		parent = pparent;
		this.arduino = arduino;
		xLeds = parent.properties.xLeds;
		yLeds = parent.properties.yLeds;
		ledArray = new Led[xLeds][yLeds];
		// for (int x = 0; x < xLeds; x++) {
		// for (int y = 0; y < yLeds; y++) {
		// ledArray[x][y] = new Led(parent, x, y, PinType.ANALOG, this.arduino);
		// }
		// }
		ledArray[0][0] = new Led(parent, 0, 0, PinType.ANALOG, Constants.PIN9,
				arduino);
		ledArray[0][0].setState(new AmplitudeState(ledArray[0][0],
				Constants.CHANNEL1));
		ledArray[0][1] = new Led(parent, 0, 1, PinType.ANALOG, Constants.PIN10,
				arduino);
		ledArray[0][1].setState(new AmplitudeState(ledArray[0][1],
				Constants.CHANNEL2));
		ledArray[1][0] = new Led(parent, 1, 0, PinType.ANALOG, Constants.PIN11,
				arduino);
		ledArray[1][0].setState(new AmplitudeState(ledArray[1][0],
				Constants.CHANNEL3));
	}

	@Override
	public void draw() {
		for (int x = 0; x < xLeds; x++) {
			for (int y = 0; y < yLeds; y++) {
				if (ledArray[x][y] != null) {
					ledArray[x][y].draw();
				}
			}
		}
	}

	@Override
	public void handleKeyPressed(char key, int keyCode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleKeyRelease(char key, int keyCode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleNoteOn(int channel, int pitch, int velocity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleNoteOff(int channel, int pitch, int velocity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleControlEvent(ControlEvent theEvent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleControllerChange(int channel, int number, int value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleOscEvent(OscMessage message) {
		// TODO Auto-generated method stub

	}

}
