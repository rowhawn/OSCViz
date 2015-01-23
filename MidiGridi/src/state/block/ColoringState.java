package state.block;
import util.Input;
import display.unit.Block;


public class ColoringState implements BlockState{
	int currColor;
	
	public ColoringState(int currentColor){
		this.currColor = currentColor;
	}

	@Override
	public void handleInput(Block block, Input input, int channel) {
		switch (input){
		case ACTIVATEFADE:
			break;
		case DEACTIVATEFADE:
			block.state = new FadingState(block, currColor, block.blockRestingColor);
			break;
		case DEACTIVATECOLOR:
			block.state = new RestingState(block.blockRestingColor);
		case ACTIVATECOLORAMPLITUDE:
			block.state = new ColoringAmplitudeState(block, block.parent.properties.green1, channel);
			break;
		default:
			break;
		}
	}

	@Override
	public int getColor() {
		return currColor;
	}
}
