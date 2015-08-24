package net.buddat.ludumdare.ld33.util;

import org.newdawn.slick.Color;
import org.newdawn.slick.UnicodeFont;

public class FontUtils {

	public static final int ALIGN_LEFT = 0, ALIGN_CENTER = 1, ALIGN_RIGHT = 2;

	public static void renderString(UnicodeFont font, String s, int startX,
			int startY, int maxWidth, int align, boolean wrap, Color c) {
		if (wrap) {
			String[] split = s.split(" ");
			String[] lines = new String[font.getWidth(s) / maxWidth + 2];

			int currentLine = 0;
			lines[currentLine] = "";
			for (String s1 : split) {
				if (font.getWidth(lines[currentLine] + s1) < maxWidth
						&& !s1.equals("\n")) {
					if (!lines[currentLine].equals(""))
						lines[currentLine] += " ";
					lines[currentLine] += s1;
				} else {
					lines[++currentLine] = "";
					if (!s1.equals("\n"))
						lines[currentLine] += s1 + " ";
				}
			}

			for (int i = 0; i <= currentLine; i++) {
				int width = font.getWidth(lines[i]);
				int xPos = startX;
				int yPos = startY;

				if (align == ALIGN_CENTER)
					xPos -= width / 2;
				else if (align == ALIGN_RIGHT)
					xPos -= width;

				yPos += font.getLineHeight() * i;

				font.drawString(xPos, yPos, lines[i], c);
			}
		} else {
			int width = font.getWidth(s);
			int xPos = startX;
			int yPos = startY;

			if (align == ALIGN_CENTER)
				xPos -= width / 2;
			else if (align == ALIGN_RIGHT)
				xPos -= width;

			font.drawString(xPos, yPos, s, c);
		}
	}
}
