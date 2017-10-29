import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class AB10 extends AB3
{
    /*#
     * Aufgaben 1, 2 und 3: Patrouille
     */
    public void rundeDrehen() {
        int anzGemacht;
        anzGemacht = 0;
        while (anzGemacht < 3) {
            laufeBisWand();
            dreheLinks();
            anzGemacht++;
        }
    }

    /*#
     * Aufgabe 4: Zu Befehl!
     */
    public void dreheAnzahlRunden(int anz) {
        for ( ; ; ) {
            if(getEnergie()<=60 && hatGegenstand("Akku")) benutze("Akku");
            rundeDrehen();
        }
    }

    /*#
     * Aufgabe 5: Drehwurm
     */
    public void drehwurm() {

    }

    public void mirWirdsGanzSchwindlig(int x) {

    }

    /*#
     * Aufgabe 6: Hartes Training
     */
    public void gehe3Schritte() {

    }

    public void geheSchritte(int anz) {

    }

    // Hier kommen die Methoden lege7Brennstaebe() und legeBrennstaebe(int anz) hin:

    /*#
     * Aufgabe 7: Der letzte Einsatz naht!
     */
    public void legeRechteck(int breite, int hoehe) {

    }

    // Achtung: Hoehe muss eine ungerade Zahl sein, sonnst funktioniert die Methode nicht!
    public void legeRaute(int hoehe) {

    }

    /*#
     * Einsatz 10: Bitte Namen nicht aendern!
     */
    public void einsatz10() {

    }
}
