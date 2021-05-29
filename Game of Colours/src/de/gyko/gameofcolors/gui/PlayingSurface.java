package de.gyko.gameofcolors.gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

/**
 * 
 * @author barna
 *
 */

@SuppressWarnings("serial")
public class PlayingSurface extends JPanel {

	/**
	 * Create the frame.
	 */
	public PlayingSurface() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Hallo");
			}
		});
		//TODO
		
	setBackground(Color.BLUE);
	}

}
