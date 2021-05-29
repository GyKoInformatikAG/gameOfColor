package de.gyko.gameofcolors.app;

import java.awt.Color;

/**
 * Diese Klasse stellt das Spielfeld/ Battlefield dar.
 * 
 * @author sophia
 *
 */
public class Battlefield {

	/**
	 * Diese Methode setzt auf die Koordinate die Farbe.
	 * @param x
	 * @param y
	 * @param color
	 */
   public void setColorAt(int x, int y, Color color){	
	}
   
   
   /**
    * Die Höhe des Battlefields in der Einheit Anzahl Kästchen.
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
    * Die Weite des Battlefields in der Einheit Anzahl Kästchen.
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
   
   
}
