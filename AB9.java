import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class AB9 extends Roboter
{

    /*#
     * Aufgabe 4: Bombe sichern
     */
    public void holeBombeUndKehreZurueck (int xPos, int yPos){
        // Startpositionen merken:
        int x = getX(); // Aktuelle x-Position des Roboters wird fuer spaeter gemerkt
        int y = getY(); // Aktuelle y-Position des Roboters wird fuer spaeter gemerkt

        setLocation(xPos, yPos); // Hier wird der Befehl gegeben, dass der Roboter sich an die als Parameter uebergebenen Positionen xPos und yPos bewegt
        /*#
         * Die folgenden zwei Befehle sind von dir zu vervollstaendigen
         */
        if (istAufGegenstand("Bombe"))   ; //Hier fehlt was!
        setLocation(0,0); // Das sind wohl nicht die richtigen Koordinaten! Veraendere so, dass die Roboter auf ihre Startposition zurueckkehren!
    }

    /*#
     * Aufgabe 5: Sprengkommando
     */
    public void sprenge (int xPos, int yPos) {
        // Merke dir die Startposition:

        // gehe an die Sprengposition und benutze die Bombe:

        // Kehre wieder zur Startposition zurueck:

    }

    /*#
     * Aufgabe 6 & 7: Stollen fuellen ... und raeumen
     * Hier darfst du mal ganz alleine eine Methode mit Parametern implementieren ;-)
     */
    // Implementiere hier die Methode legeBrennstab(...)

    /*#
     * Aufgabe 9: Die Methode legeQuadratUndSammleBatterie
     */
    public void legeQuadratUndSammleBatterie(int startX, int startY){
        // Benutze mehrfach deine Methode legeBrennstab von oben!

    }

    /*#
     * Platz fuer Weitere Methode(n), die du eventuell fuer den Einsatz 9 benoetigst:
     */

    /*#
     * -------------------------------------------------------------------------------------------------------
     * Die kommende Methode ist nur fuer deinen Lehrer gedacht. Hier brauchst und sollst du nichts veraendern!
     */
    /*
     * Die Methode ablegen veranlasst den Roboter einen Gegenstand,
     * dessen Name als Parameter angegeben wird, abzulegen.
     * 
     * @param   name    Der Name des abzulegenden Gegenstands
     */
    public void ablegen(String name)
    {
        super.ablegen(name);
        if (name.equals("Brennstab"))verbraucheEnergie(12);
    }
}