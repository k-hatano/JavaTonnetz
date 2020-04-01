import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JavaTonnetz extends JFrame implements KeyListener {
	TonnetzCanvas cCanvas;

	public JavaTonnetz(){
		super();

		setTitle("JavaTonnetz");
		setSize(400, 300);
		setLocation(32,48);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});

		cCanvas = new TonnetzCanvas();
		add(cCanvas);

		addKeyListener(this);
	}

	public static void main(String[] args) {
		JavaTonnetz mainWindow = new JavaTonnetz();
		mainWindow.show();
	}

	public void keyPressed(KeyEvent arg0) {
		final char noteKeys[] = {'a', 'w', 's', 'e', 'd', 'f', 't', 'g', 'y', 'h', 'u', 'j', 'k', 'o', 'l', 'p', ';', ':'};
		char key = arg0.getKeyChar();
		int code = arg0.getKeyCode();

		for (int i = 0; i < noteKeys.length; i++) {
			if (key == noteKeys[i]) {
				cCanvas.noteOn(i % 12);
				break;
			}
		}
	}

	public void keyReleased(KeyEvent arg0) {
		final char noteKeys[] = {'a', 'w', 's', 'e', 'd', 'f', 't', 'g', 'y', 'h', 'u', 'j', 'k', 'o', 'l', 'p', ';', ':'};
		char key = arg0.getKeyChar();
		int code = arg0.getKeyCode();

		for (int i = 0; i < noteKeys.length; i++) {
			if (key == noteKeys[i]) {
				cCanvas.noteOff(i % 12);
				break;
			}
		}
	}

	public void keyTyped(KeyEvent arg0) {

	}
}
