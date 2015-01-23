package state.block;
import display.Settings;
import display.unit.Block;



public class FadingThread extends Thread{
	int fromColor;
	int toColor;
	int currColor;
	Block block;
	boolean fading;
	int fadeFrames = 50;
	private int redFadeAmt;
	private int greenFadeAmt;
	private int blueFadeAmt;
	int fadeTime = Settings.getFadeSleepTime();
	
	
	public FadingThread(Block block, int fromCol, int toCol){
		this.fromColor = fromCol;
		this.toColor = toCol;
		this.block = block;
		this.fading = true;
	}
	
	public void run() {
		calculateFadeAmounts();
		currColor = fromColor;
		int fadeCounter = 0;
		while (fading && fadeCounter < fadeFrames){
			currColor = calculateCurrentFade(fadeCounter);
			try {
				Thread.sleep(fadeTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
			fadeCounter++;
		}
		if (fading){
			block.state = new RestingState(block.blockRestingColor);
		}
    }
	
	private int calculateCurrentFade(int fadeCounter) {
		int currRed = (currColor >> 16) & 0xFF;
		int currGreen = (currColor >> 8) & 0xFF;
		int currBlue = currColor & 0xFF;
		int newColor = block.parent.color(currRed + redFadeAmt,
				currGreen + greenFadeAmt, currBlue
						+ blueFadeAmt);
		return newColor;
	}
	
	private void calculateFadeAmounts(){
		redFadeAmt = (((toColor >> 16) & 0xFF) - ((fromColor >> 16) & 0xFF))
				/ fadeFrames;
		greenFadeAmt = (((toColor >> 8) & 0xFF) - ((fromColor >> 8) & 0xFF)) / fadeFrames;
		blueFadeAmt = ((toColor & 0xFF) - (fromColor & 0xFF))
				/ fadeFrames;
	}

	public void stopFading(){
		this.fading = false;
	}
	
	public int getColor(){
		return currColor;
	}
}
