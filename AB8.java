import greenfoot.*;

public class AB8 extends AB5
{
    private int zaehler1;
    private double wert;

    public AB8() {
        this.zaehler1 = 0;
        this.wert = 0.0;
    }

    /*#
     * Aufgabe 1: Zwei Methoden, die fast das gleiche machen ...
     */
    public int zaehleSchritteBisWand1() {
        while(!istWandVorne()) {
            this.zaehler1++;
            einsVor();
        }
        return this.zaehler1;
    }

    public int zaehleSchritteBisWand2() {
        int zaehler2;
        zaehler2 = 0;
        while(!istWandVorne()){
            zaehler2++;
            einsVor();
        }
        return zaehler2;
    }

    /*#
     * Aufgabe 5: Summe der x- und y-Koordinate
     */
    public int gibPositionssumme() {
        int x;
        int y;
        int summe;
        x = getX();
        y = getY();
        summe = x + y;
        return summe;
    }

    /*#
     * Aufgabe 6: Abstabdsberechnungen
     */
    public int gibLaenge() {
        // Loesche die Return-Zeile und beginne mit Aufgabe 5
        return 0;
    }

    /*#
     * Aufgabe 7: Flaeche
     */
    public int gibRaumgroesse() {
        // Loesche die Return-Zeile und beginne mit Aufgabe 6
        return 0;
    }

    /*#
     * Aufgabe 8: 
     */
    public boolean istFassRechts() {
        // Loesche die Return-Zeile und beginne mit Aufgabe 7
        return false;
    }

    public int gibLaengeFassReihe() {
        // Loesche die Return-Zeile und mache weiter mit Aufgabe 7
        return 0;
    }

    public int gibAnzahlFaesser() {
        // Loesche die Return-Zeile und vervollstaendige Aufgabe 7
        return 0;
    }

    /*#
     * Aufgabe 10: Sammler
     */
    public void sammeln() {

    }

    // Hier muss noch eine get-Methode hin:

    /*#
     * Aufgabe 11: Finanzamt
     */
    public double getMehrwertSteuer() {
        // Loesche die Return-Zeile und vervollstaendige Aufgabe 10
        return 0.0;
    }

    /*#
     * Einsatz 8: Den Namen bitte nicht aendern!
     */
    public int einsatz8() {
        int anzahlGelb;
        anzahlGelb = 0;
        // Hier kommen deine Anweisungen hin:

        return anzahlGelb;
    }
}
