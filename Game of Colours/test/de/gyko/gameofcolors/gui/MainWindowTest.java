package de.gyko.gameofcolors.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import de.gyko.gameofcolors.app.GameOfColors;

public class MainWindowTest extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		GameOfColors einTestSpiel = new GameOfColors();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//MainWindow frame = new MainWindow();
					MainWindow frame = new MainWindow(einTestSpiel);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


}
