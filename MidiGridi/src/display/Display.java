package display;
import oscP5.OscMessage;
import controlP5.ControlEvent;


public interface Display {
	public void draw();
	public void handleKeyPressed(char key, int keyCode);
	public void handleKeyRelease(char key, int keyCode);
	public void handleNoteOn(int channel, int pitch, int velocity);
	public void handleNoteOff(int channel, int pitch, int velocity);
	public void handleControlEvent(ControlEvent theEvent);
	public void handleControllerChange(int channel, int number, int value);
	public void handleOscEvent(OscMessage message);
}
