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
public class MainWindow extends JFrame {
	public MainWindow() {
		
		JPanel panel = new PlayingSurface();
		getContentPane().add(panel, BorderLayout.CENTER);
	}

}
