package display.unit;
import util.Input;


public interface DisplayUnit {

	public void draw();
	public void handleInput(Input input, int channel);
}
