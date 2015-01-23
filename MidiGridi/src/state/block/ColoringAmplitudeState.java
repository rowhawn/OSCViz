package state.block;
import util.Input;
import display.unit.Block;


public class ColoringAmplitudeState implements BlockState {
	int currColor; // color when block is fully 'on'
	Block block;
	int inputChannel;

	public ColoringAmplitudeState(Block block, int currentColor, int amplitudeChannel) {
		this.block = block;
		this.currColor = currentColor;
		this.inputChannel = amplitudeChannel;
	}

	@Override
	public void handleInput(Block block, Input input, int channel) {
		switch (input){
		case ACTIVATECOLOR:
			block.state = new ColoringState(block.parent.properties.yellow1);
			break;
		case ACTIVATEFADE:
			block.state = new ColoringState(block.parent.properties.blue1);
			break;
		case DEACTIVATEFADE:
			block.state = new FadingState(block, currColor, block.blockRestingColor);
			break;
		case DEACTIVATECOLOR:
			block.state = new RestingState(block.blockRestingColor);
		default:
			break;
		}
	}

	@Override
	public int getColor() {
		int channel = block.parent.vid.getBrightnessAt(block.xPos, block.yPos);
		float amplitude = block.parent.properties.getChannelValue(channel);
		int restRed = (block.blockRestingColor >> 16) & 0xFF;
		int restGreen = (block.blockRestingColor >> 8) & 0xFF;
		int restBlue = block.blockRestingColor & 0xFF;
		int currRed = (currColor >> 16) & 0xFF;
		int currGreen = (currColor >> 8) & 0xFF;
		int currBlue = currColor & 0xFF;
		int redDiff = restRed - currRed;
		int greenDiff = restGreen - currGreen;
		int blueDiff = restBlue - currBlue;
		float displayRed = (float) restRed - ((float) redDiff * amplitude);
		float displayGreen = (float) restGreen - ((float) greenDiff * amplitude);
		float displayBlue = (float) restBlue - ((float) blueDiff * amplitude);
		return block.parent.color(displayRed, displayGreen, displayBlue);
	}
}
