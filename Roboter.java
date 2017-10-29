import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;
import java.util.*;
import javax.swing.JOptionPane;

/**
 * Diese Klasse ist die Oberklasse fuer die Roboter "Robita", "Robson" und "Robby". 
 * Programme sollen nur in den Unterklassen 
 * implementiert werden, da diese Klasse fuer Java-Beginner sehr komplex ist.
 * Ein Roboter kann sich felderweise in die vier Himmelsrichtungen im Szenario bewegen. 
 * Ueber Sensoren kann er Informationen ueber seine Umwelt erhalten.
 */

public class Roboter extends Actor
{
    private int energie; //Energie in Prozent
    private ArrayList<Gegenstand> inventar;
    private GreenfootImage originalImage;

    /**
     * Ein Objekt der Klasse Roboter wird exempliert bzw. instanziert.
     */
    public Roboter()
    {
        inventar = new ArrayList<Gegenstand>();
        energie=100;
        originalImage = new GreenfootImage(this.getImage());
        zeichneRobotImage();
    }

    /**
     * Gibt die Anzahl der Gegenstaende, die Roboter traegt, zurueck.
     * @return Anzahl der Gegenstaende
     */
    public int getAnzahl() {
        return inventar.size();
    }

    /**
     * Gibt die Anzahl der Gegenstaende einer bestimmten Art zurueck.
     * @param name Art der Gegenstands (z.B. "Schluessel")
     * @return Anzahl der Gegenstaende
     */
    public int getAnzahl(String name) {
        int anz = 0;
        if (Gegenstand.istGegenstand(name)) {
            for (Gegenstand g : inventar) {
                if (g.getName().equals(name)) {
                    anz++;
                }
            }
        } else 
        {
            melde(""+name+" ist keine gültige Bezeichnung für einen Gegenstand", true);
        }
        return anz;
    }

    /**
     * Gibt die Restenergie des Roboters zurück
     * @return Energiereserve (0-100)
     */
    public int getEnergie() {
        return energie;
    }

    /**
     * Reduziert die Restenergie des Roboters um den angegebenen Wert.
     * @param verlust Anzahl der verbrauchten Energiepunkte
     */
    public void verbraucheEnergie(int verlust) {
        energie = energie-verlust;
        if (energie < 0) energie=0;

        zeichneRobotImage();
    }

    /**
     * Gibt zurueck, ob die Energie des Roboters verbraucht ist.
     * @return true, wenn Restenergie =0, sonst false
     */
    public boolean istEnergieLeer() {
        if (getEnergie()==0) return true;
        else return false;
    }

    /**
     * Gibt die x-Koordinate zurück, wenn der Roboter einen Schritt nach vorne macht.
     * @return x-Koordinate
     */
    private int xVor() {
        switch(this.getRotation())
        {
            case 0 : return getX()+1; 
            case 180 : return getX()-1; 
        }
        return getX();
    }

    /**
     * Gibt die y-Koordinate zurück, wenn der Roboter einen Schritt nach vorne macht.
     * @return y-Koordinate
     */

    private int yVor() {
        switch(this.getRotation())
        {
            case 90 : return getY()+1;  
            case 270 : return getY()-1; 
        }
        return getY();
    }

    /**
     * Der Roboter geht einen Schritt in die Pfeilrichtung.
     * Das macht er nur, wenn sich keine Wand vor ihm befindet oder
     * er nicht an der Grenze der Welt zur Wand blickt.
     */
    public void einsVor()
    {
        boolean possible;
        possible = true;
        if (!this.istVorneFrei()) possible = false;
        if (energie < 1) possible = false;
        if (istVorne("Fass") || istVorne("Atommuell")) {
            if(this.getWorld().getObjectsAt(getX()+(xVor()-getX())*2,getY()+(yVor()-getY())*2, null).size() > 0)  {
                possible = false;
            }  else {
                energie -= 5;
                List actors = this.getWorld().getObjectsAt(xVor(), yVor(), null);
                Gegenstand fass = (Gegenstand) (actors.get(0));
                fass.setLocation(getX()+(xVor()-getX())*2,getY()+(yVor()-getY())*2);
            }
        }

        if (possible)
        {   
            Schalter s=null; boolean on=false;
            if (istAufGegenstand("Kontaktplatte")) {
                s = gibGegenstandUnterRoboter().getSchalter();
                on = true;
            }
            energie--;
            this.setLocation(xVor(), yVor());
            if (istAufGegenstand("Portal")) {
                if (this instanceof AB1) {
                    if (this.getAnzahl("Schraube")>=2) {
                        ReadWrite secret = new ReadWrite();
                        secret.writeLevel(2);
                        Greenfoot.delay(1);
                        melde("Sehr gut. Level 1 abgeschlossen. Gehe zu Level 2.",false);
                        Greenfoot.setWorld(new RoboterWelt());
                    }
                    else {
                        melde("Du musst erst noch mind. 2  Schrauben einsammeln, um das AB 1 abzuschließen.",false);
                    } // end of if-else

                }
                energie = 100;
            }
            if (istAufGegenstand("Kontaktplatte") ) {
                s = gibGegenstandUnterRoboter().getSchalter();
                on = false;
            }
            if (s != null) {
                if (on) s.anschalten(); else s.ausschalten();
            }
            if (istAufGegenstand()) {
                if (energie > gibGegenstandUnterRoboter().getSchaden()) energie -= gibGegenstandUnterRoboter().getSchaden();
                else energie = 0;
            }
            zeichneRobotImage();
            Greenfoot.delay(1);
        }
        else
        {
            //System.out.println("Ich kann keinen Schritt machen!\nEntweder ist ein Hindernis vor mir\noder ich habe keine Kraft mehr!");
            //melde("Ich kann keinen Schritt machen!\nEntweder ist ein Hindernis vor mir\noder ich habe keine Kraft mehr!");
            warne("Ich kann keinen Schritt machen!\nEntweder ist ein Hindernis vor mir\noder ich habe keine Kraft mehr!",this);
        }
    }

    /**
     * Der Roboter dreht sich um 90 Grad nach rechts (aus der Sicht des Roboters).
     */
    public void dreheRechts()
    {
        setRotation((getRotation()+90)%360);
        zeichneRobotImage();
        Greenfoot.delay(1);
    }

    /**
     * Der Roboter dreht sich um 180 Grad nach rechts (aus der Sicht des Roboters).
     */
    public void dreheUm()
    {
        dreheRechts();
        dreheRechts();
    }

    /**
     * Der Roboter dreht sich um 90 Grad nach links (aus der Sicht des Roboters).
     */
    public void dreheLinks()
    {
        setRotation((getRotation()+270)%360);
        zeichneRobotImage();
        Greenfoot.delay(1);
    }

    /**
     * Gibt das erste Gegenstandsobjekt zurück, das der angegebenen Art entspricht.
     * @param name Art des gesuchten Gegenstands
     * @return Gegenstandsobjekt
     */
    private Gegenstand getInventar(String name) {
        if (Gegenstand.istGegenstand(name)) {
            for(Gegenstand g: inventar) {
                if (g.getName().equals(name)) {
                    return g;
                }
            }
        }
        else {
            melde(""+name+" ist kein gültiger Gegenstand.",true);
        }
        return null;
    }

    /**
     * Gibt zurueck, ob der Roboter mindestens einen Gegenstand der angegeben Art besitzt.
     * @param name Art des gesuchten Gegenstands
     * @return true, wenn der Roboter einen derartigen Gegenstand hat, sonst false
     */
    public boolean hatGegenstand(String name) {
        if (Gegenstand.istGegenstand(name)) {
            for(Gegenstand g: inventar) {
                if (g.getName().equals(name)) {
                    return true;
                }
            }
        }
        else {
            melde(""+name+" ist kein gültiger Gegenstand.",true);
        }
        return false;
    }

    /**
     * Gibt dem Roboter eine bestimmte Anzahl von Gegenstaenden einer Art
     * @param name Art des Gegenstands
     * @anzahl gewuenschte Anzahl
     */
    public void setAnzahlVonGegenstand(String name, int anzahl) {
        if (Gegenstand.istGegenstand(name)) { 
            for (int i=0; i<anzahl; i++) {
                inventar.add(new Gegenstand(name));
            }
            zeichneRobotImage();
        }
        else {
            melde(""+name+" ist kein gültiger Gegenstand.",true);
        }
    }

    /**
     * Benutzt den angegebenen Gegenstand. Es koennen nur Schluessel, Schalter, Bombe, Feuerloescher und Akku benutzt werden.
     * Dabei kann ein Schluessel nur direkt vor einem Schloss benutzt werden. Eine Bombe sprengt einen Bereich frei. Der Feuerloescher 
     * kann ein direkt vor dem Roboter befindliches Feuer loeschen. Der Akku erhoeht die Restenergie des Roboters.
     * @param name Art des zu benutzenden Gegenstands ("Schluessel", "Schalter", "Bombe", "Feuerloescher", "Akku")
     */
    public void benutze(String name) {
        if (!Gegenstand.istGegenstand(name) && !Wand.istWandtyp(name)) { 
            melde(""+name+" ist keine gültige Bezeichnung.",true);
            return;
        } 
        if (name.equals("Schalter")) {
            if (istVorne("Schalter")) {
                List actors = this.getWorld().getObjectsAt(xVor(), yVor(), Wand.class);
                Wand w = (Wand) (actors.get(0));
                if (w.getSchalter() != null) w.getSchalter().umschalten();
                Greenfoot.delay(1);
            } else {
                warne("Du stehst gar nicht vor einem Schalter. Wie willst du ihn benutzen?",this);
            }
        }

        if (name.equals("Akku") || name.equals("Feuerloescher") || name.equals("Schluessel") || name.equals("Bombe")) {
            if (this.getAnzahl(name)>0) {
                Gegenstand g = this.getInventar(name);
                g.benutze();
                if (g.getLebensdauer() == 0) inventar.remove(g);
                if (name.equals("Akku")) {
                    energie += 40;
                    if (energie >100) energie = 100;
                }
                if (name.equals("Feuerloescher")) {
                    if (istVorne("Feuer")) {
                        List actors = this.getWorld().getObjectsAt(xVor(), yVor(), Gegenstand.class);
                        this.getWorld().removeObject((Actor) (actors.get(0)));
                    }
                }
                if (name.equals("Schluessel")) {
                    if (istVorne("Schloss")) {
                        // g.benutze();         // Dirk: ist doch schon vorher aufgerufen worden - oder?
                        if (g.getLebensdauer() == 0) inventar.remove(g);
                        List actors = this.getWorld().getObjectsAt(xVor(), yVor(), Wand.class);
                        Wand w = (Wand) (actors.get(0));
                        if (w.getSchalter() != null) w.getSchalter().umschalten();
                    } else {
                        inventar.add(new Gegenstand("Schluessel"));
                    }
                }
                if (name.equals("Bombe")) {
                    Schalter s1 = new Schalter(getWorld());
                    int[][] level1 = {{ -1, -1, -1 , -1, -1},{ -1, -1, -1, -1, -1},{ -1, -1, 40, -1, -1},{ -1, -1, -1, -1, -1},{ -1, -1, -1 ,-1, -1}};
                    int[][] level2 = {{ -1, -1, -1 ,-1, -1},{ -1, 40, 40 , 40, -1},{ -1, 40, 0 , 40, -1},{ -1, 40, 40 , 40, -1},{ -1, -1, -1 ,-1, -1}};
                    int[][] level3 = {{ -1,-1, -1 ,-1, -1},{ -1, 0, 40 , 0, -1},{ -1, 0, 40 , 0, -1},{-1, 0, 40 , 0, -1},{ -1, -1, -1 ,-1, -1}};
                    int[][] level4 = {{ -1, -1, -1 ,-1, -1},{ -1, 0, 0 , 0, -1},{ -1, 0, 0 , 0, -1},{ -1, 0, 0 , 0, -1},{ -1, -1, -1 ,-1, -1}};
                    int platzFuerAkku = Greenfoot.getRandomNumber(9);
                    switch (platzFuerAkku) {
                        case 0: level4[1][1]=38; break;
                        case 1: level4[1][2]=38; break;
                        case 2: level4[1][3]=38; break;
                        case 3: level4[2][1]=38; break;
                        case 4: level4[2][2]=38; break;
                        case 5: level4[2][3]=38; break;
                        case 6: level4[3][1]=38; break;
                        case 7: level4[3][2]=38; break;
                        case 8: level4[3][3]=38; break;
                    }
                    for (int i=0; i<2; i++){
                        int platzFuerKontaktplatte=0;
                        do {
                            platzFuerKontaktplatte = Greenfoot.getRandomNumber(9);
                        } while(platzFuerKontaktplatte == platzFuerAkku);
                        switch (platzFuerKontaktplatte) {
                            case 0: level4[1][1]=31; break;
                            case 1: level4[1][2]=31; break;
                            case 2: level4[1][3]=31; break;
                            case 3: level4[2][1]=31; break;
                            case 4: level4[2][2]=31; break;
                            case 5: level4[2][3]=31; break;
                            case 6: level4[3][1]=31; break;
                            case 7: level4[3][2]=31; break;
                            case 8: level4[3][3]=31; break;
                        }
                    }
                    int x = getX()-2;
                    int y = getY()-2;
                    s1.setPosition(x,y, level2, level1);
                    s1.ausschalten();
                    //                   energie = energie - 20;
                    //                   if (energie < 0) energie = 0;
                    //                   zeichneRobotImage();
                    //getWorld().removeObject(this);

                    Greenfoot.delay(1);
                    s1.anschalten();
                    //                    setRotation((getRotation()+180)%360);
                    //                    this.setLocation(xVor(), yVor());
                    //                    myrota((myrotation+180)%360);
                    //                    energie = energie - 20;
                    //                    if (energie < 0) energie = 0;
                    //                    zeichneRobotImage();
                    Greenfoot.delay(1);

                    s1.setPosition(x,y, level4, level3);
                    s1.ausschalten();
                    //                    myrotation = ((myrotation+180)%360);
                    //                    this.setLocation(xVor(), yVor());
                    //                    myrotation = ((myrotation+180)%360);
                    Greenfoot.delay(1);
                    s1.anschalten();
                    //                    energie = energie - 20;
                    //                    if (energie < 0) energie = 0;

                }

                zeichneRobotImage();
                Greenfoot.delay(1);

            } else {
                warne("Du hast gar kein(e) "+name+", den/die du benutzen könntest.", this);
            }
        }
    }

    /**
     * Liefert genau dann true, wenn auf der Kachel, auf der sich der
     * aufgerufene Roboter gerade befindet, ein beliebiger Gegenstand befindet.
     * @return true, wenn der Roboter auf einem Gegenstand steht, sonst false
     */
    public boolean istAufGegenstand() {
        List g = getWorld().getObjectsAt(getX(), getY(), Gegenstand.class);
        if (g.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Liefert genau dann true, wenn auf der Kachel, auf der sich der
     * aufgerufene Roboter gerade befindet, ein Gegenstand mit dem angegebnen Namen befindet.
     * @param name Einer der Gegenstaende ("Schluessel", "Schalter", "Bombe", "Feuerloescher", "Akku", "Schraube", "Kontaktplatte", "Oelfleck", "Portal", "Strom", "Feuer")
     * @return true, wenn der Roboter auf einem Gegenstand steht, sonst false
     */
    public boolean istAufGegenstand(String name) {
        if (!Gegenstand.istGegenstand(name)) { 
            melde(""+name+" ist kein gültiger Gegenstand.",true);
            return false;
        } 
        List g = getWorld().getObjectsAt(getX(), getY(), Gegenstand.class);
        if (g.size() > 0) {
            return ((Gegenstand) (g.get(0))).getName().equals(name);
        } else {
            return false;
        }
    }

    /**
     * Gibt den Gegenstand zurueck, auf dem sich ein Roboter befindet 
     * @return Gegenstand (entweder "Schluessel", "Schalter", "Bombe", "Feuerloescher", "Akku", "Schraube", "Kontaktplatte", "Oelfleck", "Portal", "Strom", "Feuer")
     */
    private Gegenstand gibGegenstandUnterRoboter() {
        List g = getWorld().getObjectsAt(getX(), getY(), Gegenstand.class);
        if (g.size() > 0) {
            return (Gegenstand) (g.get(0));
        } else {
            return null;
        }
    }

    /**
     * Liefert genau dann true, wenn der aufgerufene Roboter keinen Gegenstand bei sich hat.
     * @return true, wenn der Roboter keinen Gegenstand mit sich traegt, sonst false
     */
    public boolean istInventarLeer() {
        return inventar.size() == 0;
    }

    /**
     * Der Roboter legt einen Gegenstand aus seinem Vorrat auf dem Feld ab.
     * @param name Einer der Gegenstaende ("Schluessel", "Schalter", "Bombe", "Feuerloescher", "Akku", "Schraube", "Kontaktplatte", "Oelfleck", "Portal", "Strom", "Feuer")
     */      
    public void ablegen(String name)
    {
        if (!Gegenstand.istGegenstand(name)) { 
            melde(""+name+" ist kein gültiger Gegenstand.",true);
            return;
        } 
        if (getAnzahl(name)>0) {
            if (!istAufGegenstand() || istAufGegenstand("Oelfleck")|| istAufGegenstand("Kontaktplatte")) { // Dirk: Ablegen auf Kontaktplatte geaendert! 
                int aktuellesX = this.getX();
                int aktuellesY = this.getY();
                for(Gegenstand g: inventar) {
                    if (g.getName().equals(name)) {
                        inventar.remove(g);
                        this.getWorld().addObject(g, aktuellesX, aktuellesY);
                        zeichneRobotImage();
                        break;
                    }
                }
            }
            else warne("Hier liegt schon etwas oder man darf hier nichts ablegen!",this); //melde("Hier liegt schon eine Schraube!");
        }
        else warne("Ich habe kein(e) "+name+" zum Ablegen!",this); //melde("Ich habe keine Schrauben zum ablegen!");
    }

    /**
     * Der Roboter nimmt einen Gegenstand, der sich auf seinem Feld befindet in seinen Vorrat auf.
     * Falls er nicht auf einem Gegenstand steht, gibt er eine Warnung aus.
     * Roboter haben nur eine begrenzte Kapazitaet. Sie koennen maximal 99 Gegenstaende mit sich tragen.
     * Falls er einen 100. Gegenstand aufnehmen moechte, wird auch eine entsprechende Warnung ausgegeben.
     */      
    public void aufnehmen ()
    {
        if (getAnzahl()<=99) // von Dirk von < auf <= veraendert!
        {
            Gegenstand g = (Gegenstand)this.getOneObjectAtOffset(0, 0, Gegenstand.class);
            if(g != null)
            {
                if (g.istBeweglich()) {
                    this.getWorld().removeObject(g);
                    inventar.add(g);
                    zeichneRobotImage();
                } else {
                    warne("Ein(e) "+g.getName()+" kann man nicht aufheben", this);
                }
            }
            else
            {
                warne("Hier ist nichts zum Aufheben!",this);
            }
        }
        else warne("Hab keinen Platz für weitere Gegenstände!",this); 
    }

    /**
     * Diese Methode setzt einen Roboter vor den Roboter, der diese Methode aufruft.
     * Ist vor ihm kein Platz, wird eine Warnung ausgegeben.
     * @param r der Hilfsroboter, der eingesetzt werden soll
     */
    public void gehilfeEinsetzen(Roboter r) {
        if (istVorneFrei() && getWorld().getObjectsAt(xVor(), yVor(), Roboter.class).isEmpty()) {
            this.getWorld().addObject(r, xVor(), yVor());
        } else {
            warne("Vor dir ist kein Platz für einen Gehilfen!",this);
        }
    }

    /**
     * Der Sensor ueberprueft, ob vor dem Roboter Hindernisse stehen oder nicht
     * @return true, wenn vor dem Roboter frei ist, sonst false
     */     
    public boolean istVorneFrei()
    {
        return !istWandVorne();   
    }

    /**
     * Liefert genau dann true, wenn sich vor dem Roboter ein Gegenstand mit dem angegebnen Namen befindet.
     * @param name Einer der Gegenstaende ("Schluessel", "Schalter", "Bombe", "Feuerloescher", "Akku", "Schraube", "Kontaktplatte", "Oelfleck", "Portal", "Strom", "Feuer")
     * @return true, wenn der Roboter vor dem Gegenstand mit dem Namen name steht, sonst false
     */
    public boolean istVorne(String name) {
        if (!Gegenstand.istGegenstand(name) && !Wand.istWandtyp(name)) {
            melde(name+" ist keine gültige Bezeichnung", true);
            return false;
        }
        List actors = this.getWorld().getObjectsAt(xVor(), yVor(), null);
        boolean found = false;
        for(int i = 0; i < actors.size(); i++) {
            Object o = actors.get(i);
            if (o instanceof Gegenstand) {
                if (((Gegenstand) o).getName().equals(name)) {
                    found = true;
                    break;
                }
            }
            if (o instanceof Wand) {
                if (((Wand) o).getName().equals(name)) {
                    found = true;
                    break;
                }
            }
        }
        return found;
    }

    /**
     * Der Sensor ueberprueft, ob sich in Laufrichtung des Roboters eine Wand befindet.
     * @return true, wenn der Roboter vor einer Wand steht, sonst false
     */
    public boolean istWandVorne()
    {
        return (this.getWorld().getObjectsAt(xVor(),yVor(), Wand.class).size() > 0);
    } 

    /**
     * Der Sensor ueberprueft, ob sich rechts der Laufrichtung eine Wand befindet.
     * @return true, wenn sich rechts von dem Roboter eine Wand befindet, sonst false
     */    
    public boolean istWandRechts()
    {
        int lRichtung = getRotation();

        if (lRichtung == 0 && this.getOneObjectAtOffset(0, 1, Wand.class)!= null)
        {
            return true;     
        }

        if (lRichtung == 90 && this.getOneObjectAtOffset(-1, 0, Wand.class)!= null)
        {
            return true;     
        }

        if (lRichtung == 180 && this.getOneObjectAtOffset(0, -1, Wand.class)!= null)
        {
            return true;     
        }

        if (lRichtung == 270 && this.getOneObjectAtOffset(1, 0, Wand.class)!= null)
        {
            return true;     
        }

        return false;
    }

    /**
     * Der Sensor ueberprueft, ob sich links der Laufrichtung eine Wand befindet.
     * @return true, wenn sich links von dem Roboter eine Wand befindet, sonst false
     */  
    public boolean istWandLinks()
    {
        int lRichtung = getRotation();

        if (lRichtung == 0 && this.getOneObjectAtOffset(0, -1, Wand.class)!= null)
        {
            return true;     
        }

        if (lRichtung == 90 && this.getOneObjectAtOffset(1, 0, Wand.class)!= null)
        {
            return true;     
        }

        if (lRichtung == 180 && this.getOneObjectAtOffset(0, 1, Wand.class)!= null)
        {
            return true;     
        }

        if (lRichtung == 270 && this.getOneObjectAtOffset(-1, 0, Wand.class)!= null)
        {
            return true;     
        }

        return false;
    }

    /**
     * Eine private Methode, die dafuer sorgt, dass der "Aufdruck" auf dem Roboter (Anzahl der Gegenstaende), der Akkustandsbalken und
     * der Richtungspfeil aktualisiert werden
     */
    private void zeichneRobotImage()
    {
        final int x=17;
        final int y=18;
        final int breite = 14;
        final int hoehe = 12;
        this.setImage(new GreenfootImage(originalImage));
        this.getImage().setColor(Color.WHITE);
        this.getImage().fillRect(x, y, breite, hoehe);
        this.getImage().setColor(Color.BLACK);
        this.getImage().drawRect(x, y, breite, hoehe);
        this.getImage().setFont(new Font("Arial",Font.PLAIN,10));
        String ziffern;
        int anz = inventar.size();
        if (anz>=0 && anz<10) ziffern="0"+anz;
        else if (anz>=100) ziffern=">>";
        else ziffern=""+anz;
        this.getImage().drawString(ziffern, x+2, y+hoehe-2);
        zeichneEnergie();

        this.getImage().drawImage(new GreenfootImage("images/pfeil"+getRotation()+".png"), 40, 2);
        this.getImage().rotate(-getRotation());
    }

    /**
     * Zeichne / Aktualisiere die Energie (Akkustandsbalken), die der Roboter hat
     */
    private void zeichneEnergie()
    {
        final int x=2;
        final int y=1;
        final int breite = 4;
        final int hoehe = 46;
        this.getImage().setColor(Color.GREEN);
        this.getImage().fillRect(x, y, breite, hoehe);
        int hoeheNeu = hoehe-(int)(Math.floor(hoehe*(energie/100.0)));
        this.getImage().setColor(Color.RED);
        this.getImage().fillRect(x, y, breite, hoeheNeu);
        this.getImage().setColor(Color.BLACK);
        this.getImage().drawRect(x, y, breite, hoehe);
        this.getImage().drawRect(x, y, breite, hoehe/2);
    }

    // Meldungen und Warnungen
    /**
     * Gibt den uebergebenen String in einer Dialogbox auf dem Bildschirm aus
     * @param text Der Text, der angezeit werden soll
     * @param istWichtig bei true wird der Text "Achtung! Besonders wichtige Meldung:" in die Kopfzeile geschrieben, sonst nur der Text "Meldung!"
     */
    public void melde(String text, boolean istWichtig) {
        String meldeText;
        if (istWichtig) meldeText = "Achtung! Besonders wichtige Meldung:";
        else meldeText = "Meldung!";
        JOptionPane.showMessageDialog(null, text, meldeText,
            JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Gibt den uebergebenen String und eine Zahl in einer Dialogbox auf dem Bildschirm aus
     * @param text Der Text, der angezeit werden soll
     * @param z eine Zahl, die hinter dem Text text ausgegeben wird
     */
    private void melde(String text, int z) {
        JOptionPane.showMessageDialog(null, text + ": " + z, "Meldung!",
            JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Gibt den uebergebenen String in einer Dialogbox als Warnung auf dem Bildschirm aus.
     * Damit man Endlosschleifen unterbrechen kann, kann man auf die Frage "Soll dennoch weiter gemacht werden?"
     * mit Nein antworten. Dadurch wird der uebergebene Actor actor von der Roboterwelt entfernt und diese neu gezeichnet.
     * @param text Der Text, der angezeit werden soll
     * @param actor Der Actor, der ggf. entfernt werden soll
     */
    public void warne(String text, Actor actor) {
        int erg = JOptionPane.showConfirmDialog(null, text+"\n\nBestätige diese Warnung \noder brich das laufende Programm ab.", "Warnung!",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        if (erg == JOptionPane.CANCEL_OPTION) {
            Greenfoot.stop();    // nur falls Animation laeuft
            World w = actor.getWorld();
            if (actor != null) w.removeObject(actor); // Notbremse
            Greenfoot.setWorld(new RoboterWelt());
            // System.err.print("\u000C");
            // System.err.flush();
            System.err.close();
            // System.out.close();
            // System.out.println("Ablauf wurde vom Benutzer unterbrochen!");
            // System.exit(0); //Das waere eigentlich sauber
        }                                
    }

    // Systembedingte Routinen    
    /**
     * Wird aufgerufen, wenn der Roboter in die Welt gesetzt wird; 
     * erweitert nach Vorschlag P.Henriksen 29.6.08
     * @param world Die Welt in die ein Roboter gesetzt wurde
     */
    protected void addedToWorld(World world) {
        // Roboter kann nicht auf eine Wand oder einen anderem Roboter gesetzt werden
        Actor wand = getOneObjectAtOffset(0, 0, Wand.class);
        Actor roboter = getOneObjectAtOffset(0, 0, Roboter.class);
        if (wand != null || roboter != null) {
            world.removeObject(this);
        }
    }

    /* setLocation(x,y) und setRotation(x) koennen nicht verborgen werden, 
     * weil sie schon in der Oberklasse Actor public sind. 
     */

    /**
     * setLocation(x,y) fuer Roboter wird ueberschrieben, um nicht auf Wand oder
     * anderen Roboter ziehen zu koennen!
     * -- setzt den Roboter auf eine Kachel, deren Spalte x sowie Reihe y du 
     * hier vorgibst. Die Nummerierung beginnt jeweils bei 0 !!) 
     * @param x die x-Koordinate
     * @param y die y-Koordinate
     */
    public void setLocation(int x, int y) {
        List wand = getWorld().getObjectsAt(x, y, Wand.class);
        List roboter = getWorld().getObjectsAt(x, y, Roboter.class);
        if (wand.isEmpty() && roboter.isEmpty()) {
            super.setLocation(x, y);
        }
        else melde ("Ich kann nicht an diesen Ort. Da ist kein Platz fuer mich!",false);
    }

    /**
     * setRotation(x) fuer Roboter wird ueberschrieben, damit nur vier Rotationsrichtungen moeglich sind!
     * @param x die Richtung (0=Osten, 90=Norden, 180=Westen, 270=Sueden)
     */
    public void setRotation(int x) {
        if (x==0 || x==90 || x==180 || x==270) super.setRotation(x);
        this.zeichneRobotImage();
    }

}