package state.block;
import util.Input;
import display.unit.Block;


public class FadingState implements BlockState{
	FadingThread fadeThread;
	
	public FadingState(Block block, int fromCol, int toCol){
		fadeThread = new FadingThread(block, fromCol, toCol);
		fadeThread.start();
	}

	@Override
	public void handleInput(Block block, Input input, int channel) {
		switch (input){
		case ACTIVATEFADE:
			block.state = new ColoringState(block.parent.properties.blue1);
			fadeThread.stopFading();
			break;
		case DEACTIVATEFADE:
			break;
		case ACTIVATECOLOR:
			block.state = new ColoringState(block.parent.properties.yellow1);
			break;
		case ACTIVATECOLORAMPLITUDE:
			block.state = new ColoringAmplitudeState(block, block.parent.properties.green1, channel);
			break;
		default:
			break;
		}
	}

	@Override
	public int getColor() {
		return fadeThread.getColor();
	}
}
