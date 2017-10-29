import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class AB4 extends Roboter
{
    /*#
     * Aufgabe 1: Spur invertieren
     */
    /** 
     * Der Roboter tauscht aus. Wo eine Schraube liegt, hebt er sie auf.
     * Wo keine liegt, legt er eine ab, wenn er noch Schrauben 
     * in seinem Vorrat hat.
     */
    public void tausche() {
        if (istAufGegenstand("Schraube")) { 
            aufnehmen();
        } 
        else {
            ablegen("Schraube");
        }
    }

    /** 
     * Der Roboter macht mehrere Dinge. Beschreibe in diesem Kommentar:
     * 
     */
    public void tauscheUndVor() {
        tausche();
        if (istVorneFrei()) { 
            einsVor();
        }
        // else ist nicht noetig, weil in dem Fall nichts gemacht wird!                 
    }

    /*#
     * Aufgabe 2: Tausche die Schraubenreihe bis zur Wand
     */
    public void tauscheBisWand() {

    }

    /*#
     * Aufgabe 3: Fleckenfrei
     */
    // Hier kommt die Methode umgeheOelfleck() hin:

    /*#
     * Aufgabe 4: Schluessel aufheben
     */
    // Hier kommt die Methode sammleSchluessel() hin:

    /*#
     * Aufgabe 5: Sesam oeffne dich
     */
    // Hier kommt die Methode oeffneTuer() hin:

    /*#
     * Hilfsmethode fuer Aufgabe 9
     */
    public void laufeBisWand() {
        while(istVorneFrei()) {
            einsVor();
        }
    }

    /*#
     * Aufgabe 9: Labyrinth
     */
    // Hier kommt die Methode labyrinth() hin:

    /*#
     * Einsatz 4: Bitte den Namen nicht aendern!
     */
    public void einsatz4() {

    }
}
