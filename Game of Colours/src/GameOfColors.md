https://github.com/anonymous123-code/gameOfColor


Das Spiel:
=============

Beim Spielen soll man Kästchen in der Spielerfarbe setzen.
Jeder Spieler hat eine Farbe.
Dabei sollen leere Kästchen gefüllt werden.
Schon gefüllte Kästchen sollen übermalt werden.
Die Größe des Spielfeldes richtet sich nach der Anzahl der Spieler.
Es gibt eine Minimalanzahl von Spielern und es gibt eine Maximalanzahl von Spielern für ein Spiel.


GUI (Graphical User Interface)
===============================
* Fenster auf dem die Kästchen gesetzt werden
* Horcht auf die Anwendung und stellt sie dar.
* Zoomfunktion

Welche "Fenster" gibt es und wie hängen sie zusammen?

Fenster
-------
* ConnectingDialog: Dialog, um sich mit dem Server zu verbinden.
* MainWindow: Hier läuft das eigentliche Spiel ab

Entwurf der GUI-Elemente auf den Fenstern.

MainWindow
------------
* Bereich, in den man hineinklicken kann und bei dem dann ein Kästchen in der Spielerfarbe gesetzt wird. (Spielfeld)
* Liste mit den Spielernamen und welche Farbe die haben
* Spielstandsanzeige
* Zeitlimit / Zeitanzeige
* Buttons:
    * Spiel beenden

ConnectingDialog
----------------
* Auswahlmöglichkeit, ob man ein Spiel für andere starten möchte, oder ob man an einem Spiel teilnehmen möchte.
* Farbauswahl(Initialzustand zufällig)
* Konfigurationsmöglichkeiten für das Spiel:
    * Anzahl der Spieler.
    * Auswahl:
        * Spielende nach Zeit -> Spieldauer eingeben -> Wer nach Ablauf der Zeit die meiste Fläche hat, gewinnt.
        * Spielende nach "Gewinn über Fläche"
            (-> Wenn die Spielfläche voll ist, wird geprüft, wer die meiste Fläche hat.)


Anwendung
=========
* Spieler und seine Eigenschaften (Felder) und Aktionen/Tätigkeiten (Methoden)
* Spielfeld und seine Eigenschaften und was man mit dem Spielfeld machen kann
* Logik des Programms
* Ablauf
* Nutzung des Netzwerks
@Sophia
* Das Battlefield (Spielfeld) besteht aus einem Koordinatensystem mit 	insegesammt 225 Kästchen 
		->Auf der x- und y-Achse des Koodrdinatensystems befinden sich jeweils 		insgesammt 15 Kästchen, wobei das erste Kästchen direkt am Ursprung 		die Koordinate (0/0) hat.
		Daher wird auf den Achsen von Null bis 14 "nummeriert".
		Der Ursprung liegt unten links im "Battlefield".


-----------
Hier sind wir noch nicht fertig mit überlegen

Netz
====



