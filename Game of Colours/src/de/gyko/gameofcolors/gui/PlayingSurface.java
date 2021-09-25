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
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Hallo");
			}
		});
		//TODO

		setBackground(Color.BLUE);
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
	}


	public PlayingSurface(Battlefield field) {
		this();

		this.field=field;
	}

}
