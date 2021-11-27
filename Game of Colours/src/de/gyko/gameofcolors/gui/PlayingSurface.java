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


	/**
	 * Create the frame.
	 */
	public PlayingSurface() {
		initComponents();
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
	}

	/**

	* In dieser Methode werden die Komponenten der „PlayingSurface“
	initialisiert. Die Methode wird also von ihren Konstruktoren aufgerufen.

	*/

	private void initComponents() {

		addMouseListener(new MouseAdapter() {
			@Override
			
			public void mouseClicked(MouseEvent e) {
				System.out.println("Hallo");
			}
		});
		//TODO

		setBackground(Color.BLUE);
	}
	
	public PlayingSurface(Battlefield field) {
		
		this.field=field;
		
		initComponents();
	}

}
