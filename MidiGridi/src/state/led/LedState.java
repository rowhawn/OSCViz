package state.led;

import util.Input;
import display.unit.Led;

public interface LedState {
	void handleInput(Led led, Input input, int channel);

	int getValue();
}
