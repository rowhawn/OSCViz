package display;
import main.StateGridMain;
import oscP5.OscMessage;
import processing.core.PConstants;
import util.Constants;
import util.Input;
import controlP5.ControlEvent;
import display.unit.Block;

public class Grid implements Display {
	StateGridMain parent;
	int xBlocks;
	int yBlocks;
	Block[][] grid;

	public Grid(StateGridMain pparent) {
		parent = pparent;
		xBlocks = parent.properties.xBlocks;
		yBlocks = parent.properties.yBlocks;
		grid = new Block[xBlocks][yBlocks];
		int blockWidth = parent.width / xBlocks;
		int blockHeight = parent.height / yBlocks;
		for (int x = 0; x < xBlocks; x++) {
			for (int y = 0; y < yBlocks; y++) {
				grid[x][y] = new Block(parent, blockWidth, blockHeight,
						(blockWidth * x) + (blockWidth / 2), (blockHeight * y) + (blockHeight / 2), x, y);
			}
		}
	}

	public void draw() {
		parent.rectMode(PConstants.CENTER);
		for (int x = 0; x < xBlocks; x++) {
			for (int y = 0; y < yBlocks; y++) {
				grid[x][y].draw();
			}
		}
	}

	void handleInput(Input input, int xLeftBound, int xRightBound,
			int yUpperBound, int yLowerBound, int channel) {
		for (int x = xLeftBound; x < xRightBound; x++) {
			for (int y = yUpperBound; y < yLowerBound; y++) {
				grid[x][y].handleInput(input, channel);
			}
		}
	}

	void changeRestingColor(int newRestingCol) {
		for (int x = 0; x < xBlocks; x++) {
			for (int y = 0; y < yBlocks; y++) {
				grid[x][y].setRestingColor(newRestingCol);
			}
		}
	}

	@Override
	public void handleKeyPressed(char key, int keyCode) {
		if (key == 'a') {
			handleInput(Input.ACTIVATECOLORAMPLITUDE, 0, 32, 0, 24, Constants.CHANNEL1);
		}
//		if (key == 's') {
//			handleInput(Input.ACTIVATECOLORAMPLITUDE, 0, 16, 4, 8, Constants.CHANNEL2);
//		}
//		if (key == 'd') {
//			handleInput(Input.ACTIVATECOLORAMPLITUDE, 0, 16, 8, 12, Constants.CHANNEL3);
//		}
//		if (key == 'f') {
//			handleInput(Input.ACTIVATEFADE, 3, 4, 0, 12);
//		}
		if (key == 'z') {
			changeRestingColor(parent.properties.yellow1);
		}
		if (key == 'x') {
			changeRestingColor(parent.properties.baseCol);
		}

		if (key == PConstants.CODED) {
			if (keyCode == PConstants.RIGHT) {
				parent.background(0);
				parent.screen = parent.settings;
			}
		}
	}

	@Override
	public void handleKeyRelease(char key, int keyCode) {
//		if (key == 'a') {
//			handleInput(Input.DEACTIVATEFADE, 0, 1, 0, 12);
//		}
//		if (key == 's') {
//			handleInput(Input.DEACTIVATEFADE, 1, 2, 0, 12);
//		}
//		if (key == 'd') {
//			handleInput(Input.DEACTIVATEFADE, 2, 3, 0, 12);
//		}
//		if (key == 'f') {
//			handleInput(Input.DEACTIVATEFADE, 3, 4, 0, 12);
//		}
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
		if(message.checkAddrPattern("/ampltiudelo")){
			if(message.checkTypetag("f")){
				parent.properties.setChannelValue(message.get(0).floatValue(), 0);	
			}
		} else if (message.checkAddrPattern("/ampltiudemid")){
			if(message.checkTypetag("f")){
				parent.properties.setChannelValue(message.get(0).floatValue(), 1);	
			}
		} else if (message.checkAddrPattern("/ampltiudehi")){
			if(message.checkTypetag("f")){
				parent.properties.setChannelValue(message.get(0).floatValue(), 2);	
			}
		}
	}
}
