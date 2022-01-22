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


	private GameOfColors game;
	
	
	public MainWindow(GameOfColors game) {
		this();
		
		this.game = game;
	}
	
	public MainWindow() {

		// Hallo Barnabas, die nächste Zeile führt zu einer "NullPointerException". 
		// Finde als erstes heraus, was genau "null" ist; welche Variable den Wert "null" enthält und das ist der Grund,
		// warum die Objekterzeugung abbricht.
		// Du brauchst nur in diese Klasse hier zu gucken, um die Ursache des Problems zu sehen.
		// Um die Ursache zu beheben, kannst Du dieselbe Lösungsmöglichkeit nutzen, wie 
		// am 27.11. in der AG in der Schule.
		JPanel panel = new PlayingSurface(game.getBattlefield()); 
		getContentPane().add(panel, BorderLayout.CENTER);
		setBounds(100, 100, 900, 700);
	}



}

