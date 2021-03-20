https://github.com/anonymous123-code/gameOfColor


Das Spiel:
=============

Beim Spielen soll man Kästchen in der Spielerfarbe setzen.
Jeder Spieler hat eine Farbe.
Dabei sollen leere Kästchen gefüllt werden.
Schon gefüllte Kästchen sollen übermalt werden.
Die größe der Spielfläche richtet sich nach der Anzahl der Spieler.
Es gibt eine Minimalanzahl von Spielern und es gibt eine Maximalanzahl von Spielern für ein Spiel.


GUI (Graphical User Interface)
===============================
* Fenster auf dem die Kästchen gesetzt werden
* Horcht auf die Anwendung und stellt sie dar.
* Zoomfunktion

Welche "Fenster" gibt es und wie hängen sie zusammen?

Fenster
-------
* HAUPTFENSTER: Hier läuft das eigentliche Spiel ab
* VERBINDEN: Dialog, um sich mit dem Server zu verbinden.

Entwurf der GUI-Elemente auf den Fenstern.

HAUPTFENSTER
------------
* Bereich, in den man hineinklicken kann und bei dem dann ein Kästchen in der Spielerfarbe gesetzt wird.
* Liste mit den Spielernamen und welche Farbe die haben
* Spielstandsanzeige
* Zeitlimit / Zeitanzeige
* Buttons:
    * Spiel beenden

VERBINDEN
--------
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
* Logik des Programms
* Ablauf
* Nutzung des Netzwerks


-----------
Hier sind wir noch nicht fertig mit überlegen

Netz
====



