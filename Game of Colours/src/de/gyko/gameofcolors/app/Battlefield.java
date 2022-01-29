package de.gyko.gameofcolors.app;


import java.awt.Color;

import de.gyko.gameofcolors.net.packet.ClickPacket;
import de.gyko.netLib.packet.Packet;

/**
 * Diese Klasse stellt das Spielfeld/ Battlefield dar.
 * 
 * Auf der x- und y-Achse des Koodrdinatensystems befinden sich 		
 * Kästchen, wobei das erste Kästchen direkt am Ursprung 		
 * die Koordinate (0/0) hat.
 * Daher wird auf den Achsen von Null bis getWidth()-1 und getHeigth()-1 "nummeriert".
 * Der Ursprung liegt unten links im "Battlefield".
 * 
 * @author sophia
 *
 */
public class Battlefield {
	
	private GameOfColors einSpiel;
	
	public Battlefield (GameOfColors einSpiel) {
		this.einSpiel  = einSpiel;
	}
	
	private Color[][]color=new Color[10][10];

	/**
	 * Diese Methode setzt auf die Koordinate die Farbe.
	 * @param x
	 * @param y
	 * @param color
	 */
   public void setColorAt(int x, int y, Color color){	
	   
	   this.color[x][y]= color;
	   
	   Packet p = new ClickPacket(x,y,color);
	   

	   einSpiel.getClient().sendPacket(p);
   }
   
   /**
    * Diese Methode liefert die Farbe zurueck, die das 
    * Spielfeld auf der Koordinate (x/y) hat.
    * @param x
    * @param y
    * @return
    */
   public Color getColorAt(int x, int y) {
	   return color[x][y];
   }
   
   
   /**
    * Die Hoehe des Battlefields in der Einheit Anzahl Kaestchen.
    */
   private int height = 0;
   
   /**
    * @return the height
    */
   public int getHeight() {
	   return height;
   }
   
   /**
    * @param height the height to set
    */
   public void setHeight(int height ) {
	   this.height = height;
   }
   
   /**
    * Die Weite des Battlefields in der Einheit Anzahl Kaestchen.
    */
   private int width = 0;
   
   /**
    * @return the width
    */
   public int getWidth() {
	   return width;
   }
   
   /**
    * @param width the width to set
    */
   public void setWidth (int width ) {
	   this.width = width;
   }
   
   /**
    * Die Methose gibt true zurueck, wenn das Spielfeld komplett belegt ist.
    * @return
    */
		   
   public boolean finished() {

	   return false;
	   
   }
}
