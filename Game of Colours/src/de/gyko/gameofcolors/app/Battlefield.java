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
   public String height = "";
   
   /**
    * @return the height
    */
   public String getHieght() {
	   return height;
   }
   
   /**
    * @param height the height to set
    */
   public void setHeight(String height ) {
	   this.height = height;
   }
   
   /**
    * Die Weite des Battlefields in der Einheit Anzahl Kästchen.
    */
   public String width = "";
   
   /**
    * @return the width
    */
   public String getWidth() {
	   return width;
   }
   
   /**
    * @param width the width to set
    */
   public void setWidth (String width ) {
	   this.width = width;
   }
   
   
}
