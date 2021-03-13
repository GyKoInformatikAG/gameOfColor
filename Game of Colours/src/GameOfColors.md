https://github.com/anonymous123-code/gameOfColor


Das Spiel:
=============

Beim Spielen soll man Kaestchen in der Spielerfarbe setzen.
Jeder Spieler hat eine Farbe.
Dabei sollen leere Kaestchen gefüllt werden.
Schon gefüllte Kaestchen sollen übermalt werden.
Die größe der Spielflaeche richtet sich nach der Anzahl der Spieler.
Es gibt eine Minimalanzahl von Spielern und es gibt eine Maximalanzahl von Spielern für ein Spiel.


GUI (Graphical User Interface)
===============================
* Fenster auf dem die Kaestchen gesetzt werden
* Horcht auf die Anwendung und stellt sie dar.
* Zoomfunktion

Welche "Fenster" gibt es und wie haengen sie zusammen?

Fenster
-------
* HAUPTFENSTER: Hier laeuft das eigentliche Spiel ab
* VERBINDEN: Dialog, um sich mit dem Server zu verbinden.

Entwurf der GUI-Elemente auf den Fenstern.

HAUPTFENSTER
------------
* Bereich, in den man hineinklicken kann und bei dem dann ein Kaestchen in der Spielerfarbe gesetzt wird.
* Liste mit den Spielernamen und welche Farbe die haben
* Spielstandsanzeige
* Zeitlimit / Zeitanzeige
* Buttons:
    * Spiel beenden

VERBINDEN
--------
* Auswahlmoeglichkeit, ob man ein Spiel fuer andere starten moechte, oder ob man an einem Spiel teilnehmen moechte.
* Farbauswahl(Initialzustand zufällig)
* Konfigurationsmoeglichkeiten fuer das Spiel:
    * Anzahl der Spieler.
    * Auswahl:
        * Spielende nach Zeit -> Spieldauer eingeben -> Wer nach Ablauf der Zeit die meiste Flaeche hat, gewinnt.
        * Spielende nach "Gewinn ueber Flaeche"
            (-> Wenn die Spielflaeche voll ist, wird geprueft, wer die meiste Flaeche hat.)


Anwendung
=========
* Logik des Programms
* Ablauf
* Nutzung des Netzwerks


-----------
Hier sind wir noch nicht fertig mit ueberlegen

Netz
====



