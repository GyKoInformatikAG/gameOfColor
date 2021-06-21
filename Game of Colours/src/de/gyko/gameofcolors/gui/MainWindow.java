package de.gyko.gameofcolors.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import de.gyko.gameofcolors.app.GameOfColors;


/**
 * 
 * @author barna
 *
 */
@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	//TODO Kontrolle--Variable zum Abspeichern des Spiels erstellen. (Welcher Typ?; Das MainWndow oder das gesamte Spiel speichern?)
	private GameOfColors Save;

	public MainWindow() {

		JPanel panel = new PlayingSurface();
		getContentPane().add(panel, BorderLayout.CENTER);
		setBounds(100, 100, 900, 700);
	}

	//TODO Warum tritt hier beim Ausführen von MainWindowtest ein Fehler auf?
	//MainWindow MainWindowObject = new MainWindow();

	public MainWindow(GameOfColors Game) {
		this();

		//TODO Kontrolle
		Save = Game;



	}

}

