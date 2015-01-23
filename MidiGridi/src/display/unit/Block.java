package display.unit;
import state.block.BlockState;
import state.block.RestingState;
import util.Input;
import main.StateGridMain;


public class Block implements DisplayUnit{
	public StateGridMain parent;
	int cornerRadius;
	public int blockRestingColor;
	int blockWidth;
	int blockHeight;
	int blockEffectiveWidth;
	int blockEffectiveHeight;
	int blockXPosition;
	int blockYPosition;
	public int xPos;
	public int yPos;
	public BlockState state;

	public Block(StateGridMain pparent, int blockWth, int blockHgt,
			int blockXPixelPos, int blockYPixelPos, int xPos, int yPos) {
		this.parent = pparent;
		this.cornerRadius = parent.properties.baseCorner;
		this.blockRestingColor = parent.properties.baseCol;
		this.blockWidth = blockWth;
		this.blockHeight = blockHgt;
		this.blockXPosition = blockXPixelPos;
		this.blockYPosition = blockYPixelPos;
		this.xPos = xPos;
		this.yPos = yPos;
		this.blockEffectiveWidth = blockWidth - parent.properties.getBaseGap();
		this.blockEffectiveHeight = blockHeight - parent.properties.getBaseGap();
		this.state = new RestingState(blockRestingColor);
	}

	public void draw() {
		parent.fill(state.getColor());
		parent.rect(blockXPosition, blockYPosition,
				blockEffectiveWidth, blockEffectiveHeight, cornerRadius);
	}

	public void handleInput(Input input, int channel) {
		state.handleInput(this, input, channel);
	}

	public void setRestingColor(int newRestingCol) {
		blockRestingColor = newRestingCol;
		if (this.state.getClass() == RestingState.class) {
			this.state = new RestingState(blockRestingColor);
		}
	}
}
