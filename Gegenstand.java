import greenfoot.*;
import java.util.*;
import javax.swing.JOptionPane;

/** Die Klasse Gegenstand fasst alle in der Welt liegenden Actors zusammen,
 * die bewegt werden können.
 */
public class Gegenstand extends Actor
{
    private int typ;
    private static String[] typen = {"Akku","Schraube","Brennstab","Schluessel","Oelfleck","Portal","Strom","Feuer","Feuerloescher","Fass","Atommuell","Bombe", "Kontaktplatte"};
    private boolean[] moveable = {true, true, true, true, false, false, false, false, true, false, false, true, false};
    private int[] schaden = {0, 0, 0, 0, 20, 0, 50, 40, 0, 0, 0, 0, 0};
    private int[] lebensdauer = {1, 1, 1, 1, 0, 1000, 0, 0, 5, 1000, 1000,1, 1000 }; 
    private Schalter schalter = null;

    /** erzeugt einen neuen Gegenstand vom angegeben Typ
     * @param name Typ des Gegenstands ("Akku","Schraube","Brennstab","Schluessel","Oelfleck","Portal","Strom","Feuer","Feuerloescher","Fass","Atommuell","Bombe", "Kontaktplatte")
     */
    public Gegenstand(String name) {
        super();
        this.typ = -1;

        for(int i = 0; i < typen.length; i++) {
            if (name.equals(typen[i])) {
                this.typ = i;
            }
        }
        if(this.typ == -1) {
            JOptionPane.showMessageDialog(null, "Ich kenne kein(e) "+name+". Daher erzeuge ich einen Akku.", "Meldung!",
                JOptionPane.INFORMATION_MESSAGE);
            this.typ = 0;
        }
        this.setImage("images/"+typen[typ]+".png");
    }

    /** erzeugt einen Standardgegenstand (Akku)
     */
    public Gegenstand() {
        super();
        this.typ = 0;
        this.setImage("images/Akku.png");
    }

    /** untersucht, ob name eine korrekte Bezeichnug für einen Gegenstand ist
     * @return erg true, wenn name korrekt ist
     */
    public static boolean istGegenstand(String name) {
        boolean erg = false;
        for(int i = 0; i < typen.length; i++) {
            if (name.equals(typen[i])) {
                erg = true;
            }
        }
        return erg;
    }

    /** liefert den Typ des Gegenstands
     * @return Typ ("Akku","Schraube","Brennstab","Schluessel","Oelfleck","Portal","Strom","Feuer","Feuerloescher","Fass","Atommuell","Bombe", "Kontaktplatte")
     */
    public String getName() {
        return this.typen[typ];
    }

    /** liefert, ob der Gegenstand vom Roboter aufgenommen werden kann
     * @return true, wenn der Gegenstand weggetragen werden darf.
     */
    public boolean istBeweglich() {
        return this.moveable[typ];
    }

    /** liefert den Schaden an der Energieversorgung, wenn der Roboter mit dem Gegenstand
     * auf einem Feld ist.
     * @return abgezogen Energiepunkte
     */
    public int getSchaden() {
        return this.schaden[typ];
    }

    /** liefert die Anzahl der Einsätze bevor der Gegenstand verbraucht ist
     * @return z.B. 1 für Akku, 5 für Feuerlöscher
     */
    public int getLebensdauer() {
        return this.lebensdauer[typ];
    }

    /** liefert einen Schalter, der mit diesem Gegenstand verbunden ist (z.B. bei Kontaktplatten)
     * @return Schalterobjekt
     */
    public Schalter getSchalter() {
        return this.schalter;
    }

    /** Verbindet diese Objekt mit einem Schalter (z.B. bei Kontaktplatten)
     * @param s Schalterobjekt
     */
    public void setSchalter(Schalter s) {
        this.schalter = s;
    }

    /** Benutzt das Objekt und setzt dadurch die Lebensdauer herunter
     */
    public void benutze() {
        this.lebensdauer[typ]--;
    }

    // Systembedingte Routinen    
    /**
     * Wird aufgerufen, wenn der Roboter in die Welt gesetzt wird; 
     * erweitert nach Vorschlag P.Henriksen 29.6.08
     */
    protected void addedToWorld(World world) {
        // Roboter kann nicht auf eine Wand oder einen anderem Roboter gesetzt werden
        Actor wand = getOneObjectAtOffset(0, 0, Wand.class);
        if (wand != null) {
            world.removeObject(this);
        }
    }

    /**
     * setLocation(x,y) fuer Roboter wird ueberschrieben, um nicht auf Wand oder
     * anderen Roboter ziehen zu koennen!
     * -- setzt den Roboter auf eine Kachel, deren Spalte x sowie Reihe y du 
     * hier vorgibst. Die Nummerierung beginnt jeweils bei 0 !!) 
     */
    public void setLocation(int x, int y) {
        List wand = getWorld().getObjectsAt(x, y, null);
        if (wand.isEmpty()) {
            super.setLocation(x, y);
        }
    }
    /* setLocation(x,y) und setRotation(x) koennen nicht verborgen werden, 
     * weil sie schon in der Oberklasse Actor public sind. 
     */

    /**
     * setRotation(x) fuer Roboter wird verhindert! 
     * Es ist unnoetig, da Roboter sich nur in vier Richtungen drehen koennen!
     */
    public void setRotation(int x) {
        // nichts machen!!
    }
}