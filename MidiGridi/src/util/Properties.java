package util;
import processing.core.PApplet;


public class Properties extends PApplet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int defaultFadeSleepTime = 15;
	public int xBlocks = 32;
	public int yBlocks = 24;
	public int xLeds = 2;
	public int yLeds = 2;
	private static int baseGap = 5;
	public int baseCorner = 3;
	private static float[] channelValues = new float[8];
	public int maxParticles = 4000;
	public int particleLifetime = 10;
	
	public int baseCol = color(12, 25, 43);
	public int blue1 = color(64, 89, 143);
	int blue2 = color(78, 122, 217);
	public int blue3 = color(173, 199, 255);
	int blue4 = color(64, 89, 143, 127);
	public int green1 = color(0, 255, 0);
	public int yellow1 = color(255, 255, 87);
	int xMarker = 0;
	public int expectedVideoWidth = 480;
	public int expectedVideoHeight = 320;
	
	public synchronized void increaseBaseGap() {
		baseGap++;
	}

	public synchronized void decreaseBaseGap() {
		if (baseGap > 0){
			baseGap--;
		}
	}
	
	public int getBaseGap(){
		return baseGap;
	}
	
	public synchronized void setChannelValue(float value, int channel){
		channelValues[channel] = value;
	}
	
	public float getChannelValue(int channel){
		return channelValues[channel];
//		switch (channel){
//		case 0:
//			return 1.0f;
//		case 1:
//			return 1.0f;
//		case 2:
//			return 1.0f;
//		case 3:
//			return 1.0f;
//		case 4:
//			return 1.0f;
//		case 5:
//			return 0.6f;
//		case 6:
//			return 0.7f;
//		case 7:
//			return 0.8f;
//		case 8:
//			return 0.9f;
//		case 9:
//			return 1.0f;
//		default:
//			return 1.0f;
//		}
	}
}
