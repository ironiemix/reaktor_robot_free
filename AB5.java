import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class AB5 extends Roboter
{
    /*#
     * Aufgabe 1, 2, 3 und 10: Upgrade 1 - ein neuer Sensor wird erstellt
     */
    public boolean istFassVorne() {
        // Aufgabe 3a

        if (istVorne("Fass")) {
            // Aufgabe 3b

            return true;
        } else {
            // Aufgabe 3b

            return false;
        }
        // Aufgabe 3c

    }

    /*#
     * Aufgabe 4: Rueckspiegel
     */
    public boolean istFassHinten() {

        if (istVorne("Fass")) {

            return true;
        }else {

            return false;
        }
    }

    /*#
     * Aufgabe 5: Out of Power
     */
    public boolean istEnergieSchwach() {
        // Hier fehlt noch einiges!
        return true;
    }

    /*#
     * Aufgabe 6: Heavy Duty
     */
    // Hier ist Platz fuer die Methode istSchwerBeladen():

    /*#
     * Aufgabe 7 und 11: Look Ahead - Upgrade 2
     */
    // Hier ist Platz fuer die Methode istVorFassFrei():

    /*#
     * Aufgabe 8: Aufraeumen
     */
    // Hier ist Platz fuer die Methode schiebeFassBisWand():

    /*#
     * Aufgabe 9: Fuehrerschein
     */
    // Hier ist Platz fuer die Methode istKreuzung():

    public void geheBisKreuzung() {

    }

    /*#
     * Aufgabe 12: Wie koennte man diese Methode nennen?
     */
    // Hier ist Platz fuer die Methode ???():

    /*#
     * Einsatz 5: Bitte den Namen nicht aendern!
     */
    public void einsatz5() {

    }
}