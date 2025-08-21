package org.xper.joseph.drawing;

import org.lwjgl.opengl.GL11;
import org.xper.drawing.object.Circle;
import org.xper.drawing.object.Rectangle;
import org.xper.drawing.object.Square;

import java.util.HashMap;
import java.util.Map;

public class GLUtil {
	private enum Color {
		WHITE   ("white",   new float[]{1.0f, 1.0f, 1.0f}),
		BLACK   ("black",   new float[]{0.0f, 0.0f, 0.0f}),
		RED     ("red",     new float[]{1.0f, 0.0f, 0.0f}),
		GREEN   ("green",   new float[]{0.0f, 1.0f, 0.0f}),
		BLUE    ("blue",    new float[]{0.0f, 0.0f, 1.0f}),
		YELLOW  ("yellow",  new float[]{1.0f, 1.0f, 0.0f}),
		CYAN    ("cyan",    new float[]{0.0f, 1.0f, 1.0f}),
		MAGENTA ("magenta", new float[]{1.0f, 0.0f, 1.0f}),
		GRAY    ("gray",    new float[]{0.5f, 0.5f, 0.5f}),
		ORANGE  ("orange",  new float[]{1.0f, 0.647f, 0.0f}),
		PURPLE  ("purple",  new float[]{0.5f, 0.0f, 0.5f}),
		PINK    ("pink",    new float[]{1.0f, 0.753f, 0.796f}),
		BROWN   ("brown",   new float[]{0.647f, 0.165f, 0.165f});

		private final String name;
		private final float[] rgb;

		private static final Map<String, Color> nameToRGB = new HashMap<>();

		static {
			for (Color c : values()) {
				nameToRGB.put(c.name, c);
			}
		}

		Color(String name, float[] rgb) {
			this.name = name;
			this.rgb = rgb;
		}

		static float[] getRGB(String name) {
			Color c = nameToRGB.get(name.toLowerCase());
			return (c != null) ? c.rgb : null;
		}
	}

	public static float[] getRGB(String name) {
		return Color.getRGB(name);
	}

	public static void drawCircle (Circle circle, double size, boolean solid, String color, double tx, double ty, double tz) {
		GL11.glPushMatrix();
		GL11.glTranslated(tx, ty, tz);
		float[] rgb = getRGB(color);
		GL11.glColor3f(rgb[0], rgb[1], rgb[2]);
		circle.setRadius(size);
		circle.setSolid(solid);
		circle.draw(null);
		GL11.glPopMatrix();
	}
	
	public static void drawSquare (Square square, double size, boolean solid, double tx, double ty, double tz) {
		square.setSize(size);
		square.setSolid(solid);
		GL11.glPushMatrix();
		GL11.glTranslated(tx, ty, tz);
		square.draw(null);
		GL11.glPopMatrix();
	}
	
	public static void drawRectangle(Rectangle rect, double tx, double ty, double tz, float r, float g, float b) {
		GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT);
		GL11.glColor3f(r, g, b);
		GL11.glPushMatrix();
		GL11.glTranslated(tx, ty, tz);
		rect.draw(null);
		GL11.glPopMatrix();
		GL11.glPopAttrib();
	}
}
