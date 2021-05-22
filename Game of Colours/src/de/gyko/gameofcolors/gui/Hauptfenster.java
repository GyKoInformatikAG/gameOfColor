package de.gyko.gameofcolors.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author barna
 *
 */
@SuppressWarnings("serial")
public class Hauptfenster extends JFrame {
	public Hauptfenster() {
		
		JPanel panel = new PlayingSurface();
		getContentPane().add(panel, BorderLayout.CENTER);
	}

}
