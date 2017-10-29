import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class AB6_AB7 extends Roboter
{  
    // Eigenschaften eines AB6 - Objektvariablen (Attribute) werden deklariert:
    private int schritte;
    // AB6 - Aufgabe 8: Hier wird das Attribut drehungen deklariert:

    /*#
     * Konstruktor eines AB6: Der Startzustand wird definiert
     */
    public AB6_AB7() {
        // Startzustand eines AB6 - Objektvariablen werden initialiaiert:
        this.schritte = 0;

        // AB6 - Aufgabe 8: Initialisiere das Attribut drehungen hier:

    }

    /*#
     * AB6 - Beispiel Zählen, 
     * AB7 - Aufgabe 4: Super
     */
    public void einsVorMitZaehlen() {
        // Das Attribut Schritte muss um eins erhoeht werden und dann die alte einsVor-Methode des AB6-Roboters aufgerufen werden: 
        this.schritte++;
        einsVor();
    }

    /*#
     * AB6 - Aufgabe 7: Schrittezaehler
     * AB7 - Aufgabe 5: Brennstaebe zaehlen
     */
    public void bisWandMitZaehlen() {

    }

    /*#
     * AB6 - Aufgabe 8: Drehzaehler
     * AB7 - Aufgabe 6: Standardmethoden überschreiben
     */
    public void dreheRechtsMitZaehlen() {

    }

    public void dreheLinksMitZaehlen() {

    }

    /*#
     * AB6 - Aufgabe 9: Eine get-Methode
     */
    public int getSchritte() {
        return 0;
    }

    /*#
     * AB6 - Aufgabe 10: Deine get-Methode
     */
    // Hier kommt deine get-Methode fuer die Anzahl der Drehungen hin.
    // Benenne sie sinnvoll:

    /*#
     * Liefert die Anzahl der gezählten Brennstäbe zurück
     * AB7 - Aufgabe 5: Brennstaebe zaehlen
     */
    public int getBrennstaebe() {
        return 0;
    }

    /*#
     * AB6 - Einsatz 6:
     */
    public void einsatz6() {

    }

    /*#
     * Weitere Methoden zum AB7:
     */

    /*#
     * AB7 - Aufgabe 1: Kreuzungen zaehlen (aus AB3)
     */

    /*#
     * AB7 - Aufgabe 7: Fass links
     */
    // Hier kommt die Methode istFassLinks() hin:

    /*#
     * AB7 - Aufgabe 8: Standardmethoden überschreiben (istWandVorne(), istWandLinks())
     */

    /*#
     * AB7 - Hilfsmethode für Einsatz 7:
     */

    public boolean istWandLinks() {
        // Diese Methode muss so erweitert werden, dass sie auch Fässer auf der linken Seite erkennt.
        return super.istWandLinks();
    }

    /*#
     * AB7 - Einsatz 7:
     */
    public void einsatz7(){

    }
}