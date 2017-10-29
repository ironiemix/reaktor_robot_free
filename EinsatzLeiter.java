import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class EinsatzLeiter extends Actor
{
    // Hier werden alle Roboter aufgelistet, denen der Einsatzleiter Befehle erteilen kann:
    AB9 legeRoboter; //Legeroboter
    AB9 sprengRoboter1; //Sprengroboter 1
    AB9 sprengRoboter2; //Sprengroboter 2
    AB9 sprengRoboter3; //Sprengroboter 3

    /*#
     * Aufgabe 8 und 9: Anweisung 1
     * Hier werden Methoden am sprengRoboter1 aufgerufen.
     * Tipp: Schreibe sprengRoboter1. und druecke STRG+Leertaste --> Schau mal, welche Methoden du alle zur Verfuegung hast ...
     */
    public void holeBombeUndSprenge (int bx, int by, int sx, int sy){
        // Der sprengRoboter1 bekommt den Befehl holeBombeUndKehreZurueck(bx, by), en du vorher beim AB9-Roboter programmiert hast.
        // (bx|by) sind die Koordinaten der Bombe --> das muss dann der Methodenaufrufer eingeben (also du spaeter, wenn du am EinsatzLeiter diese Methode aufrufst):
        sprengRoboter1.holeBombeUndKehreZurueck(bx, by);
        // Gib dem sprengRoboter1 den Befehl zum Sprengen an der Position (sx,sy):

    }

    /*#
     * Aufgabe 10: Anweisung 2
     */
    public void holeBombeSprengeLegeAb (int bx, int by, int sx, int sy){
        // sprengRoboter2: Bombe holen und sprengen

        Greenfoot.delay(2); // Dieser Befehl ist nur dazu da, dass du nachher auch sehen kannst was passiert, sonst wuerde alles zu schnell gehen ...
        // legeRoboter: Brennstaebe ablegen und Batterien sammeln

    }

    /*#
     * Ab hier kannst du Hilfs-Methoden fuer den Einsatz 9 implementieren:
     */

    /*#
     * Einsatz 9:
     */
    public void einsatz9() {

    }

    /*#
     * -------------------------------------------------------------------
     * Bitte so lassen - diese Methode wird nur von euren Lehrer verwendet
     */
    public void nehmeKontaktAuf() {
        List<AB9> l = ((RoboterWelt)getWorld()).getObjects(AB9.class);
        if (l != null) {
            legeRoboter = l.get(0);
            sprengRoboter1 = l.get(1);
            sprengRoboter2 = l.get(2);
            sprengRoboter3 = l.get(3);
        }
    }
}
