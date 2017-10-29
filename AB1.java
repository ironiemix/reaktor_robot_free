import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Die Klasse AB1 ist eine Unterklasse von Roboter.
 * Sie erbt damit alle Attribute und Methoden der Klasse Roboter.
 */

public class AB1 extends Roboter
{
    public void einsVor() {
        this.verbraucheEnergie(4);
        super.einsVor();
    }

    public void dreheRechts() {
        super.dreheRechts();
    }

    public void dreheLinks() {
        super.dreheLinks();
    }

    public void schraubeAufnehmen() {
        if (istAufSchraube()) super.aufnehmen();
    }

    public void schraubeAblegen() {
        super.ablegen("Schraube");
    }

    public int getAnzahlSchrauben() {
        return super.getAnzahl("Schraube");
    }

    public boolean istAufSchraube() {
        return super.istAufGegenstand("Schraube");
    }

    public boolean istVorratLeer() {
        return super.getAnzahl("Schraube")==0;
    }

    public void akkuAufnehmen() {
        if (istAufGegenstand("Akku")) super.aufnehmen();
    }

    public void benutzeAkku() {
        if (this.getAnzahl("Akku")>0) benutze("Akku");
    }
}