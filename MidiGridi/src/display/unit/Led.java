package display.unit;
import main.StateGridMain;
import state.led.LedState;
import util.Input;
import util.PinType;
import cc.arduino.Arduino;


public class Led implements DisplayUnit{
	public StateGridMain parent;
	PinType pinType;
	int pinNumber;
	int xPosition;
	int yPosition;
	LedState state;
	Arduino arduino;
	
	public Led(StateGridMain parent, int x, int y, PinType pinType, int pinNumber, Arduino arduino) {
		this.parent = parent;
		this.xPosition = x;
		this.yPosition = y;
		this.pinType = pinType;
		this.pinNumber = pinNumber;
		this.arduino = arduino;
	}

	@Override
	public void draw() {
		switch (pinType){
		case ANALOG:
			arduino.analogWrite(pinNumber, state.getValue());
			break;
		case DIGITAL:
			arduino.digitalWrite(pinNumber, Arduino.LOW);
			break;
		default:
			break;
		}
	}

	@Override
	public void handleInput(Input input, int channel) {
		// TODO Auto-generated method stub
		
	}

	public LedState getState() {
		return state;
	}

	public void setState(LedState state) {
		this.state = state;
	}

}
