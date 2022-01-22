package de.gyko.gameofcolors.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import de.gyko.gameofcolors.app.Battlefield;

/**
 * 
 * @author barna
 *
 */

@SuppressWarnings("serial")
public class PlayingSurface extends JPanel {

	private Battlefield field;


	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		g.setColor(Color.YELLOW);
		
		g.fillRect(300, 400, 20, 20);
	}

	/**

	* In dieser Methode werden die Komponenten der „PlayingSurface“
	initialisiert. Die Methode wird also von ihren Konstruktoren aufgerufen.

	*/

	
	public PlayingSurface(Battlefield field) {
		
		this.field=field;
		
		addMouseListener(new MouseAdapter() {
			@Override
			
			public void mouseClicked(MouseEvent e) {
				//System.out.println(e.getX() + " " + e.getY());
				//System.out.println("Der rechte untere Pixel hat die Koordinate: (" + getWidth() + "|" + getHeight() + ")" );
			
			int WidthInCoordinates = getWidth() / field.getWidth();
			
			int HeightInCoordinates = getHeight() / field.getHeight();
			
			
			
			}
		});
		//TODO

		setBackground(Color.BLUE);
	}

}
