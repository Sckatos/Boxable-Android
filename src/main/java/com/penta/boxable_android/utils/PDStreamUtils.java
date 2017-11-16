package com.penta.boxable_android.utils;


import android.graphics.Color;

import com.penta.boxable_android.line.LineStyle;
import com.tom_roush.pdfbox.pdmodel.PDPageContentStream;
import com.tom_roush.pdfbox.pdmodel.font.PDFont;

import java.io.IOException;
import java.util.Map;

/**
 * <p>
 * Utility methods for {@link PDPageContentStream}
 * </p>
 * 
 * @author hstimac
 * @author mkuehne
 *
 */
public final class PDStreamUtils {

	private PDStreamUtils() {
	}

	/**
	 * <p>
	 * Provides ability to write on a {@link PDPageContentStream}. The text will
	 * be written above Y coordinate.
	 * </p>
	 * 
	 * @param stream
	 *            The {@link PDPageContentStream} where writing will be applied.
	 * @param text
	 *            The text which will be displayed.
	 * @param font
	 *            The font of the text
	 * @param fontSize
	 *            The font size of the text
	 * @param x
	 *            Start X coordinate for text.
	 * @param y
	 *            Start Y coordinate for text.
	 * @param color
	 *            Color of the text
	 */
	public static void write(final PDPageContentStream stream, final String text, final PDFont font,
							 final float fontSize, final float x, final float y, final int color) {
		try {
			stream.beginText();
			stream.setFont(font, fontSize);
			// we want to position our text on his baseline
			stream.newLineAtOffset(x, y - FontUtils.getDescent(font, fontSize) - FontUtils.getHeight(font, fontSize));
			Map<String, Integer> colour = ColorUtils.parse(color);
			stream.setNonStrokingColor(colour.get("r"), colour.get("g"), colour.get("b"));
			stream.showText(text);
			stream.endText();
		} catch (final IOException e) {
			throw new IllegalStateException("Unable to write text", e);
		}
	}

	/**
	 * <p>
	 * Provides ability to draw rectangle for debugging purposes.
	 * </p>
	 * 
	 * @param stream
	 *            The {@link PDPageContentStream} where drawing will be applied.
	 * @param x
	 *            Start X coordinate for rectangle.
	 * @param y
	 *            Start Y coordinate for rectangle.
	 * @param width
	 *            Width of rectangle
	 * @param height
	 *            Height of rectangle
	 * @param color
	 *            Color of the text
	 */
	public static void rect(final PDPageContentStream stream, final float x, final float y, final float width,
			final float height, final int color) {
		try {
			Map<String, Integer> colour = ColorUtils.parse(color);
			stream.setNonStrokingColor(colour.get("r"), colour.get("g"), colour.get("b"));
			// negative height because we want to draw down (not up!)
			stream.addRect(x, y, width, -height);
			stream.fill();
			stream.closePath();

			// Reset NonStroking Color to default value
			stream.setNonStrokingColor(Color.BLACK);
		} catch (final IOException e) {
			throw new IllegalStateException("Unable to draw rectangle", e);
		}
	}

	/**
	 * <p>
	 * Provides ability to draw font metrics (font height, font ascent, font
	 * descent).
	 * </p>
	 * 
	 * @param stream
	 *            The {@link PDPageContentStream} where drawing will be applied.
	 * @param x
	 *            Start X coordinate for rectangle.
	 * @param y
	 *            Start Y coordinate for rectangle.
	 * @param font
	 *            {@link PDFont} from which will be obtained font metrics
	 * @param fontSize
	 *            Font size
	 */
	public static void rectFontMetrics(final PDPageContentStream stream, final float x, final float y,
			final PDFont font, final float fontSize) {
		// height
		PDStreamUtils.rect(stream, x, y, 3, FontUtils.getHeight(font, fontSize), Color.BLUE);
		// ascent
		PDStreamUtils.rect(stream, x + 3, y, 3, FontUtils.getAscent(font, fontSize), Color.CYAN);
		// descent
		PDStreamUtils.rect(stream, x + 3, y - FontUtils.getHeight(font, fontSize), 3, FontUtils.getDescent(font, 14),
				Color.GREEN);
	}

	/**
	 * <p>
	 * Provides ability to set different line styles (line width, dotted line,
	 * dashed line)
	 * </p>
	 * 
	 * @param stream
	 *            The {@link PDPageContentStream} where drawing will be applied.
	 * @param line
	 *            The {@link LineStyle} that would be applied
	 * @throws IOException If the content stream could not be written or the line color cannot be retrieved.
	 */
	public static void setLineStyles(final PDPageContentStream stream, final LineStyle line) throws IOException {
		Map<String, Integer> colour = ColorUtils.parse(line.getColor());
		stream.setNonStrokingColor(colour.get("r"), colour.get("g"), colour.get("b"));
		stream.setStrokingColor(colour.get("r"), colour.get("g"), colour.get("b"));
		stream.setLineWidth(line.getWidth());
		stream.setLineCapStyle(0);
		if (line.getDashArray() != null) {
			stream.setLineDashPattern(line.getDashArray(), line.getDashPhase());
		} else {
			stream.setLineDashPattern(new float[] {}, 0.0f);
		}
	}
}
