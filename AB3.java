import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class AB3 extends Roboter
{

    /*#
     * Aufgabe 1: Feuer loeschen
     */
    public void loesche() {
        einsVor();
        aufnehmen();
        // Hier fehlt noch was:

    }

    /*#
     * Aufgaben 3 & 4: Vor bis zum Feuer (wo auch immer das ist)
     */
    public void vorBisFeuer() 
    {
        while(!istVorne("Feuer")) {
            einsVor();
        }
    }

    /*#
     * Aufgabe 5: Feuersbrunst loeschen
     */
    // Hier kommt deine Methode loescheReihe() hin:

    /*#
     * Aufgabe 6: Teste Reichweite eines Feuerloeschers
     */
    // Hier kommt die Methode testeReichweite() hin:

    /*#
     * Aufgabe 7: Feuerloescher einsammeln
     */
    public void sammleLoescher() 
    {

    }  

    /*#
     * Hilfsmethode fuer Aufgabe 8
     */
    public void laufeBisWand() {

    }

    /*#
     * Aufgabe 8: Homerun
     */
    public void laufeBisPortal() {

    }

    /*#
     * Einsatz 3: Bitte den Namen nicht aendern!
     */
    public void einsatz3() {

    }
}