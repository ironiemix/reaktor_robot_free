import greenfoot.*;
import java.util.*;

/**
 * Die Klasse Schalter ist eine Hilfsklasse für die Schlösser und Schalthebel 
 * Sie hat zwei zweidimensionale Arrays, die für den Schalterzustand on bzw. off
 * festlegen, wie ein Bereich der Welt aussehen soll. startx und starty speichern
 * die linke obere Ecke dieses Bereichs.
 */

public class Schalter
{
    World   world;
    private boolean an;
    private int startx;
    private int starty;
    private int[][] on;
    private int[][] off;

    /** Der Konstruktor erstellt einen neuen Schalter
     * @param w Die Welt, die manipuliert werden soll
     */
    public Schalter(World w) {
        super();
        an = true;
        startx = 0;
        starty = 0;
        on = null;
        off = null;
        world = w;
    }

    /** Die Methode setze verändert die Welt entsprechend den Vorgaben
     * von backg. Dabei werden alle Objekte in diesem Bereich entfernt 
     * (außer den Robotern) und gegen neue Objekte ausgetauscht.
     * @param backg Zweidimensionales Array, das die neuen Actors für den Bereich festlegt.
     */

    private void setze(int[][] backg) {

        for(int x=0; x<backg[0].length; x++) {
            for(int y=0; y<backg.length; y++) {
                if (backg[y][x]>=0 && (startx+x>0) && (startx+x<world.getWidth()-1) &&
                (starty+y>1) && (starty+y<world.getHeight()-1)) {
                    List al = world.getObjectsAt(x+startx,y+starty, null);
                    for (int i = 0; i< al.size(); i++) {
                        Actor da = (Actor) (al.get(i));
                        if (! (da instanceof Roboter))  world.removeObject(da);
                    }
                    Actor a = null;
                    switch(backg[y][x]) {
                        case 10: a = new Wand(); break;
                        case 11: a = new Wand("SchalterEin"); ((Wand) a).setSchalter(this); break;
                        case 12: a = new Wand("SchalterAus"); ((Wand) a).setSchalter(this); break;

                        case 13: a = new Wand("Stromquelle1"); break;
                        case 14: a = new Wand("Stromquelle2"); break;
                        case 15: a = new Wand("Stromquelle3"); break;
                        case 16: a = new Wand("Stromquelle4"); break;
                        case 17: a = new Wand("Schloss"); ((Wand) a).setSchalter(this); break;      // hinzugefuegt von Dirk
                        case 18: a = new Wand("Tuer1"); break;
                        case 19: a = new Wand("Tuer2"); break;
                        case 20: a = new Wand("Stein"); break;
                        case 21: a = new Wand("Steine"); break;

                        case 31: a = new Gegenstand("Kontaktplatte"); ((Gegenstand) a).setSchalter(this); break;
                        case 32: a = new Gegenstand("Strom"); break;
                        case 33: a = new Gegenstand("Portal"); break;
                        case 34: a = new Gegenstand("Schraube"); break;
                        case 35: a = new Gegenstand("Brennstab"); break;
                        case 36: a = new Gegenstand("Schluessel"); break;
                        case 37: a = new Gegenstand("Oelfleck"); break;
                        case 38: a = new Gegenstand("Akku"); break;
                        case 40: a = new Gegenstand("Feuer"); break;
                        case 41: a = new Gegenstand("Feuerloescher"); break;
                        case 42: a = new Gegenstand("Atommuell"); break;
                        case 43: a = new Gegenstand("Fass"); break;                        
                        case 44: a = new Gegenstand("Bombe"); break;                        
                    }
                    if (a!= null) world.addObject(a,x+startx,y+starty);
                }
            }
        }
    }

    /** Setzt den Schalterzustand auf on
     */  
    public void anschalten() {
        an = true;
        if (on != null) setze(on);
    }

    /** Setzt den Schalterzustand auf off
     */  

    public void ausschalten() {
        an = false;
        if (off != null) setze(off);
    }

    /** Schaltet den Schalter um, d.h. von on auf off oder umgekehrt
     */
    public void umschalten() {
        if (an) ausschalten(); else anschalten();
    }

    /** Legt fest, wie der Schalter die Welt verändern soll.
     * @param x Koordinate der linke oberen Ecke des zu verändernden Bereichs
     * @param y Koordinate der linke oberen Ecke des zu verändernden Bereichs
     * @param on Zweidimensionales Array zu Festlegung, wie der Bereich bei angeschaltetem Schalter ausssehen soll
     * @param off Zweidimensionales Array zu Festlegung, wie der Bereich bei ausgeschaltetem Schalter ausssehen soll
     */
    public void setPosition(int x, int y, int[][] on, int[][] off) {
        startx = x;
        starty = y;
        this.on = on;
        this.off = off;
    }

    /** Liefert den Schalterzustand
     * @return true, wenn der Schalter an ist, sonst false
     */
    public boolean getAn() {
        return an;
    }
}
