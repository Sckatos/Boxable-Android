package com.penta.boxable_android.utils;

/**
 * <p>
 * Utility methods for images
 * </p>
 * 
 * @author mkuehne
 * @author hstimac
 */
public class ImageUtils {

	// utility class, no instance needed
	private ImageUtils() {
	}

	/**
	 * <p>
	 * Provide an ability to scale {@link } on desired
	 * </p>
	 * 
	 * @param imageWidth Original image width
	 * @param imageHeight Original image height
	 * @param boundWidth Desired image width
	 * @param boundHeight Desired image height
	 * @return {@code Array} with image dimension. First value is width and second is height. 
	 */
	public static float[] getScaledDimension(float imageWidth, float imageHeight, float boundWidth, float boundHeight) {
		float newImageWidth = imageWidth;
		float newImageHeight = imageHeight;

		// first check if we need to scale width
		if (imageWidth > boundWidth) {
			newImageWidth = boundWidth;
			// scale height to maintain aspect ratio
			newImageHeight = (newImageWidth * imageHeight) / imageWidth;
		}

		// then check if the new height is also bigger than expected
		if (newImageHeight > boundHeight) {
			newImageHeight = boundHeight;
			// scale width to maintain aspect ratio
			newImageWidth = (newImageHeight * imageWidth) / imageHeight;
		}

		float[] imageDimension = { newImageWidth, newImageHeight };
		return imageDimension;
	}
}
