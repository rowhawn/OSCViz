package vid;

import main.StateGridMain;
import processing.core.PConstants;
import processing.core.PImage;
import processing.video.Capture;
import processing.video.Movie;
import blobDetection.Blob;
import blobDetection.BlobDetection;

public class VideoInput implements Runnable{
	StateGridMain parent;
	PImage videoSource;
//	boolean flag = true;
	BlobDetection blobDetection;

	public VideoInput(StateGridMain pparent, PImage videoSource) {
		this.parent = pparent;
		this.videoSource = videoSource;
		if (videoSource instanceof Movie) {
			((Movie) videoSource).loop();
			((Movie) videoSource).volume(0);
		} else if (videoSource instanceof Capture) {
			((Capture) videoSource).start();
		}
		blobDetection = new BlobDetection(parent.properties.expectedVideoWidth, parent.properties.expectedVideoHeight);
		blobDetection.setPosDiscrimination(true);
		blobDetection.setBlobMaxNumber(1);
		blobDetection.setThreshold(0.25f);
	}
	
	@Override
	public void run() {
		while (true){
			if (videoSource instanceof Movie) {
				if (((Movie) videoSource).available()) {
					((Movie) videoSource).read();
					//				flag = false;
				}
			} else if (videoSource instanceof Capture) {
				if (((Capture) videoSource).available()) {
					((Capture) videoSource).read();
				}
			}
			//		 parent.image(videoSource, 0, 0);
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void processVideo() {
		if (videoSource instanceof Movie) {
			if (((Movie) videoSource).available()) {
				((Movie) videoSource).read();
//				flag = false;
			}
		} else if (videoSource instanceof Capture) {
			if (((Capture) videoSource).available()) {
				((Capture) videoSource).read();
			}
		}
//		 parent.image(videoSource, 0, 0);
	}

	public boolean isReady() {
		return videoSource.width != 0;
	}

	public int getBrightnessAt(float x, float y) {
		int brightness = 0;
		int localX = (int) (x / ((float) parent.width / (float) videoSource.width));
		int localY = (int) (y / ((float) parent.height / (float) videoSource.height));
		parent.colorMode(PConstants.HSB, 5);
		brightness = (int) parent.brightness(videoSource.get(localX, localY));
		parent.colorMode(PConstants.RGB, 255);
		return brightness;
	}
	
	public Blob getBlob(){
		videoSource.loadPixels();
		blobDetection.computeBlobs(videoSource.pixels);
		return blobDetection.getBlob(0);
	}

}
