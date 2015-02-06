package state.led;

import util.Input;
import display.unit.Led;

public class AmplitudeState implements LedState{
	Led led;
	int channel;
	
	public AmplitudeState(Led led, int channel){
		this.led = led;
		this.channel = channel;
	}
	
	@Override
	public void handleInput(Led led, Input input, int channel) {
		// TODO Auto-generated method stub
	}

	@Override
	public int getValue() {
		return (int) (255.0 * led.parent.properties.getChannelValue(channel));
	}

}
