package state.block;
import util.Input;
import display.unit.Block;


public class RestingState implements BlockState{
	int restingColor;
	
	public RestingState(int restingCol){
		this.restingColor = restingCol;
	}

	@Override
	public void handleInput(Block block, Input input, int channel) {
		switch (input){
		case ACTIVATEFADE:
			block.state = new ColoringState(block.parent.properties.blue1);
			break;
		case DEACTIVATEFADE:
			break;
		case ACTIVATECOLOR:
			block.state = new ColoringState(block.parent.properties.yellow1);
			break;
		case ACTIVATECOLORAMPLITUDE:
			block.state = new ColoringAmplitudeState(block, block.parent.properties.blue3, channel);
			break;
		default:
			break;
		}
	}

	@Override
	public int getColor() {
		return restingColor;
	}
}
