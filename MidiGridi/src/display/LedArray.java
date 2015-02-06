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
		
		arduino.pinMode(Constants.PIN2, Arduino.OUTPUT);
		arduino.pinMode(Constants.PIN3, Arduino.OUTPUT);
		arduino.pinMode(Constants.PIN3, Arduino.OUTPUT);
		arduino.pinMode(Constants.PIN4, Arduino.OUTPUT);
		arduino.pinMode(Constants.PIN5, Arduino.OUTPUT);
		arduino.pinMode(Constants.PIN6, Arduino.OUTPUT);
		arduino.pinMode(Constants.PIN7, Arduino.OUTPUT);
		arduino.pinMode(Constants.PIN8, Arduino.OUTPUT);
		arduino.pinMode(Constants.PIN9, Arduino.OUTPUT);
		arduino.pinMode(Constants.PIN10, Arduino.OUTPUT);
		arduino.pinMode(Constants.PIN11, Arduino.OUTPUT);
		arduino.pinMode(Constants.PIN12, Arduino.OUTPUT);
		arduino.pinMode(Constants.PIN13, Arduino.OUTPUT);
		arduino.pinMode(Constants.PIN44, Arduino.OUTPUT);
		arduino.pinMode(Constants.PIN45, Arduino.OUTPUT);
		arduino.pinMode(Constants.PIN46, Arduino.OUTPUT);
		
		// for (int x = 0; x < xLeds; x++) {
		// for (int y = 0; y < yLeds; y++) {
		// ledArray[x][y] = new Led(parent, x, y, PinType.ANALOG, this.arduino);
		// }
		// }
		ledArray[0][0] = new Led(parent, 0, 0, PinType.ANALOG, Constants.PIN13,
				arduino);
		ledArray[0][0].setState(new AmplitudeState(ledArray[0][0],
				Constants.CHANNEL1));
		ledArray[0][1] = new Led(parent, 0, 1, PinType.ANALOG, Constants.PIN12,
				arduino);
		ledArray[0][1].setState(new AmplitudeState(ledArray[0][1],
				Constants.CHANNEL2));
		ledArray[0][2] = new Led(parent, 0, 2, PinType.ANALOG, Constants.PIN11,
				arduino);
		ledArray[0][2].setState(new AmplitudeState(ledArray[0][2],
				Constants.CHANNEL3));
		
		ledArray[1][0] = new Led(parent, 1, 0, PinType.ANALOG, Constants.PIN10,
				arduino);
		ledArray[1][0].setState(new AmplitudeState(ledArray[1][0],
				Constants.CHANNEL1));
		ledArray[1][1] = new Led(parent, 1, 1, PinType.ANALOG, Constants.PIN9,
				arduino);
		ledArray[1][1].setState(new AmplitudeState(ledArray[1][1],
				Constants.CHANNEL2));
		ledArray[1][2] = new Led(parent, 1, 2, PinType.ANALOG, Constants.PIN8,
				arduino);
		ledArray[1][2].setState(new AmplitudeState(ledArray[1][2],
				Constants.CHANNEL3));
		
		ledArray[2][0] = new Led(parent, 2, 0, PinType.ANALOG, Constants.PIN7,
				arduino);
		ledArray[2][0].setState(new AmplitudeState(ledArray[2][0],
				Constants.CHANNEL1));
		ledArray[2][1] = new Led(parent, 2, 1, PinType.ANALOG, Constants.PIN6,
				arduino);
		ledArray[2][1].setState(new AmplitudeState(ledArray[2][1],
				Constants.CHANNEL2));
		ledArray[2][2] = new Led(parent, 2, 2, PinType.ANALOG, Constants.PIN5,
				arduino);
		ledArray[2][2].setState(new AmplitudeState(ledArray[2][2],
				Constants.CHANNEL3));
		
		ledArray[3][0] = new Led(parent, 3, 0, PinType.ANALOG, Constants.PIN4,
				arduino);
		ledArray[3][0].setState(new AmplitudeState(ledArray[3][0],
				Constants.CHANNEL1));
		ledArray[3][1] = new Led(parent, 3, 1, PinType.ANALOG, Constants.PIN3,
				arduino);
		ledArray[3][1].setState(new AmplitudeState(ledArray[3][1],
				Constants.CHANNEL2));
		ledArray[3][2] = new Led(parent, 3, 2, PinType.ANALOG, Constants.PIN2,
				arduino);
		ledArray[3][2].setState(new AmplitudeState(ledArray[3][2],
				Constants.CHANNEL3));
		
		ledArray[4][0] = new Led(parent, 4, 0, PinType.DIGITAL, Constants.PIN44,
				arduino);
		ledArray[4][0].setState(new AmplitudeState(ledArray[4][0],
				Constants.CHANNEL1));
		ledArray[4][1] = new Led(parent, 4, 1, PinType.DIGITAL, Constants.PIN45,
				arduino);
		ledArray[4][1].setState(new AmplitudeState(ledArray[4][1],
				Constants.CHANNEL2));
		ledArray[4][2] = new Led(parent, 4, 2, PinType.DIGITAL, Constants.PIN46,
				arduino);
		ledArray[4][2].setState(new AmplitudeState(ledArray[4][2],
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
