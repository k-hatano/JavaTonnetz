import java.awt.*;
import java.awt.event.*;

public class TonnetzCanvas extends Canvas {
	int iGridSize = 48;
	int iShift = 8;
	int noteIndices[] = {-1, 0, -1, 1, -1, 2, -1, 3, -1, 4, -1, 5, -1, 6, -1, 7, -1, 8, -1, 9, -1, 10, -1, 11, -1};
	String noteNames[] = {"C", "C#", "D", "Eb", "E", "F", "F#", "G", "Ab", "A", "Bb", "B"};
	int notes[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

	public TonnetzCanvas(){
		super();
	}

	public void paint(final Graphics g){
		final int deltaX[] = {1, -1, 1, -1};
		final int deltaY[] = {1, 1, -1, -1};

		int width = getSize().width;
		int height = getSize().height;
		int midwidth = width / 2;
		int midheight = height / 2;

		Image img = createImage(width, height);
		Graphics grp = img.getGraphics();

		grp.setColor(Color.white);
		grp.fillRect(0, 0, width, height);

		int x, y, xi, yi;
		grp.setColor(Color.black);

		x = iGridSize / 2;
		y = 0;
		while (x < width) {
			grp.drawLine(midwidth + x - (height / 4), 0, midwidth + x + (height / 4), height);
			grp.drawLine(midwidth - x - (height / 4), 0, midwidth - x + (height / 4), height);
			grp.drawLine(midwidth + x + (height / 4), 0, midwidth + x - (height / 4), height);
			grp.drawLine(midwidth - x + (height / 4), 0, midwidth - x - (height / 4), height);

			x += iGridSize;
		}

		x = 0;
		y = 0;
		while (y < height / 2) {
			grp.drawLine(0, midheight + y, width, midheight + y);
			grp.drawLine(0, midheight - y, width, midheight - y);

			y += iGridSize;
		}

		x = 0;
		y = 0;
		xi = 0;
		yi = 0;
		while (y < height) {
			x = 0;
			xi = 0;
			while (x < width) {
				if ( (xi + yi) % 2 == 1 ) {
					for (int d = 0; d < 4; d++) {
						int noteIndex = noteIndices[(xi * deltaX[d] * 7 + yi * deltaY[d] + iShift + 12000) % 24];

						if (notes[noteIndex] > 0) {
							grp.setColor(Color.black);
							grp.fillOval(midwidth + x * deltaX[d] - iGridSize / 4, midheight + y * deltaY[d] - iGridSize / 4,
										 iGridSize / 2 , iGridSize / 2);
							grp.setColor(Color.white);
							grp.drawString(noteNames[noteIndex],
										   midwidth + x * deltaX[d] - iGridSize / 8,
										   midheight + y * deltaY[d] + iGridSize / 8);
						} else {
							grp.setColor(Color.white);
							grp.fillOval(midwidth + x * deltaX[d] - iGridSize / 4, midheight + y * deltaY[d] - iGridSize / 4,
										 iGridSize / 2 , iGridSize / 2);
							grp.setColor(Color.black);
							grp.drawOval(midwidth + x * deltaX[d] - iGridSize / 4, midheight + y * deltaY[d] - iGridSize / 4,
										 iGridSize / 2 , iGridSize / 2);
							grp.setColor(Color.black);
							grp.drawString(noteNames[noteIndex],
										   midwidth + x * deltaX[d] - iGridSize / 8,
										   midheight + y * deltaY[d] + iGridSize / 8);
						}
					}
				}
				x += iGridSize / 2;
				xi++;
			}
			y += iGridSize;
			yi++;
		}

		g.drawImage(img, 0, 0, this);
	}

	public void noteOn(int note) {
		notes[note] = 1;
		repaint();
	}

	public void noteOff(int note) {
		notes[note] = 0;
		repaint();
	}
}
