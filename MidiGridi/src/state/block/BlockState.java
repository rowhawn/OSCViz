package state.block;
import util.Input;
import display.unit.Block;



public interface BlockState {
	
	
	void handleInput(Block block, Input input, int channel);
	
	int getColor();
}
