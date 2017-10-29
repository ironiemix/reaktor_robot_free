import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


import java.util.Random;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Die einzigen aktiven Akteure in der Roboterwelt sind die Roboter.
 * Die Welt besteht aus 14 * 10 Feldern.
 */
public class RoboterWelt extends World
{
    private static int zellenGroesse = 60;
    private ReadWrite secret = new ReadWrite();
    private GreenfootImage[] backgroundimages;

    /**
     * Erschaffe eine Welt mit 14 * 12 Zellen.
     */
    public RoboterWelt()
    {
        super(14, 12, zellenGroesse);
        backgroundimages = new GreenfootImage[7];
        backgroundimages[0] = new GreenfootImage("images/Bodenplatte.png");
        backgroundimages[1] = new GreenfootImage("images/warnung1.png");
        backgroundimages[2] = new GreenfootImage("images/bluerock1.png");
        backgroundimages[3] = new GreenfootImage("images/Kontaktplatte.png");
        backgroundimages[4] = new GreenfootImage("images/Bodenplatte1.png");
        backgroundimages[5] = new GreenfootImage("images/bluerock2.png");
        backgroundimages[6] = new GreenfootImage("images/bluerock3.png");

        setBackground("images/Bodenplatte.png");

        setActOrder(Roboter.class);
        setPaintOrder(Roboter.class, Gegenstand.class, Wand.class);

        Greenfoot.setSpeed(25);

        if (secret.getLevel()==1) ab1_roboterSteuern();
        if (secret.getLevel()==2) ab2_roboterProgrammieren();
        if (secret.getLevel()==3) ab3_wiederholer();
        if (secret.getLevel()==4) ab4_verzweiger();
        if (secret.getLevel()==5) ab5_funktionen();
        if (secret.getLevel()==6) ab6_attribute();
        if (secret.getLevel()==7) ab7_vererbung();
        if (secret.getLevel()==8) ab8_lokale_variablen();
        if (secret.getLevel()==9) ab9_parameter();
        if (secret.getLevel()==10) ab10_zaehlschleifen();
        if (secret.getLevel()==11) ab11_finalBild();
    }

    /**
     * Das Roboterszenario auf die Anfangswerte (Level 1) zurücksetzen
     */
    public void neuStart() {
        secret.init();
        ab1_roboterSteuern();
    }

    /**
     * In ein niedrigeres Level wechseln
     * @param int   Nummer des Levels, in das gewechselt werden soll
     */
    public void geheZuLevel (int i) {
        if (i>0 && i<=secret.getMaxLevel()) {

            secret.writeLevel(i,true);
            Greenfoot.setWorld(new RoboterWelt());
        } else {
            melde("Du hast erst Level "+secret.getMaxLevel()+ " erreicht. ");
        }
    }

    /**
     * Die Welt wird geleert
     */
    private void weltLeeren()
    {
        removeObjects(getObjects(null));
    }

    /** 
     * Überschrift oben links ausgeben.
     */
    private void zeichneUeberschrift(String text) {
        this.getBackground().setColor(Color.LIGHT_GRAY);
        this.getBackground().fillRect(0,0,300,20);
        this.getBackground().setColor(Color.BLACK);
        this.getBackground().setFont(new Font("Verdana",12));
        this.getBackground().drawString(text, 2, 14);
    }

    /**
     * Generiert eine Welt aus dem angegeben 2-dim. Array
     * Dabei werden sowohl die Hintergrundbilder gesetzt, als auch Objekte
     * in die Welt eingefügt.
     * @param backg 2-dim. Array, das die Welt beschreibt.
     */
    private void generateWorld(int[][] backg)
    {
        weltLeeren();
        GreenfootImage back = new GreenfootImage(840,720);
        for(int x=0; x<14; x++) {
            for(int y=0; y<12; y++) {
                if (backg[y][x] <10) {
                    back.drawImage(backgroundimages[backg[y][x]], x*zellenGroesse, y*zellenGroesse);
                } else {
                    back.drawImage(backgroundimages[0],x*zellenGroesse, y*zellenGroesse);
                    Actor a = null;
                    switch(backg[y][x]) {
                        case 10: a = new Wand(); break;
                        case 11: a = new Wand("SchalterEin"); break;
                        case 12: a = new Wand("SchalterAus"); break;

                        case 13: a = new Wand("Stromquelle1"); break;
                        case 14: a = new Wand("Stromquelle2"); break;
                        case 15: a = new Wand("Stromquelle3"); break;
                        case 16: a = new Wand("Stromquelle4"); break;
                        case 17: a = new Wand("Schloss"); break;

                        case 18: a = new Wand("Tuer1"); break;
                        case 19: a = new Wand("Tuer2"); break;
                        case 20: a = new Wand("Stein"); break;
                        case 21: a = new Wand("Steine"); break;

                        case 31: a = new Gegenstand("Kontaktplatte"); break;
                        case 32: a = new Gegenstand("Strom"); break;
                        case 33: a = new Gegenstand("Portal"); break;
                        case 34: a = new Gegenstand("Schraube"); break;
                        case 35: a = new Gegenstand("Brennstab"); break;
                        case 36: a = new Gegenstand("Schluessel"); break;
                        case 37: a = new Gegenstand("Oelfleck"); break;
                        case 38: a = new Gegenstand("Akku"); break;
                        case 40: a = new Gegenstand("Feuer"); break;
                        case 41: a = new Gegenstand("Feuerloescher"); break;
                        case 42: a = new Gegenstand("Atommuell"); break;
                        case 43: a = new Gegenstand("Fass"); break;
                        case 44: a = new Gegenstand("Bombe"); break;

                    }
                    if (a != null) addObject(a,x,y);
                }
            }
        }
        this.setBackground(back);
    }

    /**
     * Zeigt die Koordinaten aller Felder an
     */
    public void zeigeKoordinaten(){
        int delta;
        for (int x=0; x<getWidth(); x++){
            for (int y=0; y<getHeight(); y++){
                this.getBackground().setColor(Color.BLACK);
                this.getBackground().setFont(new Font("Verdana",12));
                if (x>9 || y>9) {
                    delta = 4;
                    if (x>9 && y>9) delta = 8;
                }
                else delta = 0;
                this.getBackground().drawString("("+x+"|"+y+")", x*60+14-delta, y*60+35);
            }
        }
    }

    /**
     * Erzeugt die Welt neu, damit die Koordinaten wieder verschwinden
     */
    public void versteckeKoordinaten(){
        Greenfoot.setWorld(new RoboterWelt());
    }

    /* Es folgen nun die Definitionen der Level und Einsätze                      */
    /* ---------------------------------------------------------------------------*/

    private void ab1_roboterSteuern()
    {
        int[][] backg = {{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {10,10,10,10,10,10,10,10,10,10,10,10,10,10},
                {10, 0, 0, 0, 0, 0,10, 0,34, 0, 0, 0, 0,10},
                {10, 0,34,34,34, 0,10,38, 0, 0, 0, 0, 0,10},
                {10, 0, 0, 0, 0, 0,10,34, 0,10,10,10, 0,10},
                {10,10,10, 0,10,10,10, 0, 0,10, 0, 0, 0,10},
                {31, 3, 0, 0,10,10,10,31, 0,10,33, 0, 0,10},
                {10,10,10, 0,10,10,10, 0, 0,10, 0, 0, 0,10},
                {10, 0, 0,34, 0, 0,10, 0, 0,10,10,10, 0,10},
                {10,34,34,34, 0, 0,10, 0, 0,34, 0, 0, 0,10},
                {10, 0, 0, 0, 0, 0,10, 0,38, 0,34, 0, 0,10},
                {10,10,10,10,10,10,10,10,10,10,10,10,10,10}};

        weltLeeren();
        generateWorld(backg);
        zeichneUeberschrift("AB1 - Roboter von Hand steuern");

        AB1 starter = new AB1();
        addObject(starter, 0, 6);
        AB1 starter1 = new AB1();
        addObject(starter1, 7, 6);
        starter1.verbraucheEnergie(50);
        Schalter s = new Schalter(this);
        int[][] on={{19}}; 
        int[][] off={{31}};
        s.setPosition(1, 6, on, off);
        s.ausschalten();
        Gegenstand g = (Gegenstand) (this.getObjectsAt(0, 6, Gegenstand.class).get(0));
        g.setSchalter(s);
    }

    private void ab2_roboterProgrammieren()
    {
        int[][] backg = {{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {10,10,10,10,10,10,10,10,10,10,10,10,10,10},
                {10, 0, 0, 0, 0, 0,10, 0,34,38,38,34, 0,10},
                {10, 0,34,34,34, 0,10, 0, 0, 0, 0, 0, 0,10},
                {10, 0, 0, 0, 0, 0,10, 0, 0,10,10,10, 0,10},
                {10,10,10, 0,10,10,10, 0, 0,10, 0, 0, 0,10},
                { 0, 0, 0, 0, 0, 0, 0, 0, 0,10, 0,10, 0,10},
                {10,10,10, 0,10,10,10, 0, 0,10, 0, 0, 0,10},
                {10, 0, 0, 0, 0, 0,10, 0, 0,10,10,10, 0,10},
                {10, 0, 0, 0, 0, 0,10, 0, 0, 0, 0, 0, 0,10},
                {10, 0, 0, 0, 0, 0,10, 0, 0, 0, 0, 0, 0,10},
                {10,10,10,10,10,10,10,10,10,10,10,10,10,10}};

        weltLeeren();
        generateWorld(backg);
        zeichneUeberschrift("AB2 - Methoden erstellen");
        AB2 ab21 = new AB2();
        addObject(ab21, 1, 3);
        AB2 ab22 = new AB2();
        addObject(ab22, 7, 2);
        AB2 ab23 = new AB2();
        addObject(ab23, 10, 6);
        AB2 ab24 = new AB2();
        addObject(ab24,  1, 10);
    }

    public void einsatz_02()
    {
        int[][] backg = {{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {10,10,10,10,10,10,10, 0, 0, 0, 0, 0, 0, 0},
                {10, 0, 0,35, 0,33,10, 0, 0, 0, 0, 0, 0, 0},
                {10, 0, 0,38, 0, 0,10, 0, 0, 0, 0, 0, 0, 0},
                {10, 0, 0,35, 0, 0,10, 0, 0, 0, 0, 0, 0, 0},
                {10, 0, 0, 0, 0, 0,10, 0, 0, 0, 0, 0, 0, 0},
                {10, 0, 0,10, 0, 0,10, 0, 0, 0, 0, 0, 0, 0},
                {10, 0, 0,38, 0, 0,10, 0, 0, 0, 0, 0, 0, 0},
                {10,10, 1, 1, 1,10,10, 0, 0, 0, 0, 0, 0, 0},
                { 0, 0, 0, 0, 0,10,10, 0, 0, 0, 0, 0, 0, 0},
                {10,10,10,10,10,10,10, 0, 0, 0, 0, 0, 0, 0},
                {10,10,10,10,10,10,10, 0, 0, 0, 0, 0, 0, 0}};

        weltLeeren();
        generateWorld(backg);
        zeichneUeberschrift("Einsatz 2: Brennstäbe sichern");
        AB2 ab2 = new AB2();
        addObject(ab2,  0, 9);
        ab2.einsatz2();
        Greenfoot.delay(2);
        if (ab2.istAufGegenstand("Portal") && ab2.getAnzahl("Brennstab")==2) {
            secret.writeLevel(3);
            melde("Super! Du hast die Brennstäbe gesichert");
        } else {
            melde("Du hast die Aufgabe noch nicht erfüllt");
        }
        Greenfoot.setWorld(new RoboterWelt());
    }

    private void ab3_wiederholer()
    {
        int[][] backg = {{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {10,10,10,10,10,10,10,10,10,10,10,10,10,10},
                {10, 0,41, 0, 0,40, 0,10, 0, 0, 0, 0, 0,10},
                {10, 0,41, 0, 0, 0,40,10, 0, 0, 0, 0, 0,10},
                {10, 0,41, 0, 0,40,40,10, 0,10,10,10, 0,10},
                {10, 0,41, 0,40,40,40,10, 0,10,33,10, 0,10},
                {10, 0, 0, 0, 0, 0, 0,10, 0, 0, 0,10, 0,10},
                {10, 0,10,10,10,10,10,10,10,10,10,10, 0,10},
                {10, 0,10,41,10,41,10,41,10,41,10,10, 0,10},
                {10, 0,31, 0,31, 0,31, 0,31, 0,31,10, 0,10},
                {10, 0,10,40,40,40,40,40,40,40,10, 0, 0,10},
                {10,10,10,10,10,10,10,10,10,10,10,10,10,10}};

        weltLeeren();
        generateWorld(backg);
        zeichneUeberschrift("AB3 - Wiederholungen mit while-Schleifen");
        AB3 ab;
        ab= new AB3();
        addObject(ab, 1, 2);
        ab= new AB3();
        addObject(ab, 1, 3);
        ab= new AB3();
        addObject(ab, 1, 4);
        ab= new AB3();
        addObject(ab, 1, 5);
        ab= new AB3();
        addObject(ab, 2, 9);
        ab= new AB3();
        addObject(ab, 11, 10);
    }

    public void einsatz_03()
    {
        int[][] backg = {{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {10,10,10,10,10,10,10,10,10,10,10,10,10,10},
                {10,41,10,41,10,41,10,41,10,41,10,41,10,10},
                {38, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,31,10},
                {10,10,10,10,10,10,10,10,10,10,10,10, 3,10},
                {10,10,10,10, 0, 0, 0, 0, 0, 0, 0, 0,31,10},
                {10,10,10,10, 0, 1, 1, 1, 1, 1, 1, 1, 0,10},
                {10,10,10,10, 0, 1, 0, 0, 0, 0, 0, 1, 0,10},
                {10,10,10,10, 0, 1, 0, 0, 0, 0, 0, 1, 0,10},
                {10,10,10,10, 0, 1, 1, 1, 1, 1, 1, 1, 0,10},
                {10,33, 3,31, 0, 0, 0, 0, 0, 0, 0, 0, 0,10},
                {10,10,10,10,10,10,10,10,10,10,10,10,10,10}};

        weltLeeren();
        generateWorld(backg);
        zeichneUeberschrift("Einsatz 3:Feuer löschen");
        AB3 ab3 = new AB3();
        addObject(ab3,  0, 3);
        Schalter s = new Schalter(this);
        int[][] on={{19}}; 
        int[][] off={{31}};
        s.setPosition(2, 10, on, off);
        s.anschalten();
        Gegenstand g = (Gegenstand) (this.getObjectsAt(3, 10, Gegenstand.class).get(0));
        g.setSchalter(s);
        s = new Schalter(this);
        int[][] on2={{18}}; 
        s.setPosition(12, 4, on2, off);
        s.anschalten();
        g = (Gegenstand) (this.getObjectsAt(12, 3, Gegenstand.class).get(0));
        g.setSchalter(s);
        g = (Gegenstand) (this.getObjectsAt(12, 5, Gegenstand.class).get(0));
        g.setSchalter(s);
        int y = 10;
        int maxx = Greenfoot.getRandomNumber(4)+8;
        while (y>5) {
            for (int x=4; x<=maxx; x++) {
                addObject(new Gegenstand("Feuer"), x,y);
            }
            int newx = 20;
            while(newx > maxx) newx=Greenfoot.getRandomNumber(4)-3+y;
            maxx = newx;
            y = y-1;
        }

        ab3.einsatz3();
        Greenfoot.delay(2);
        boolean feueraus = true;
        List l = this.getObjects(Gegenstand.class);
        for (Object o : l) {
            if (((Gegenstand) o).getName().equals("Feuer")) {
                feueraus = false;
            }
        }
        if (ab3.istAufGegenstand("Portal") && feueraus) {
            secret.writeLevel(4);
            melde("Hurra! Das Feuer ist gelöscht. Die Katastrophe wurde verhindert.");
        } else {
            melde("Du hast die Aufgabe noch nicht erfüllt");
        }
        Greenfoot.setWorld(new RoboterWelt());

    }

    private void ab4_verzweiger()
    {
        int[][] backg = {{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {10,10,10,10,10,10,10,10,10,10,10,10,10,10},
                {10,36,10,10, 0,10, 0, 0, 0, 0, 0,10,10,10},
                {10, 0,10,10, 0,10, 0,10,10,10, 0, 0, 0,10},
                {10, 0,10,10, 0,10, 0, 0, 0,10,10,10, 0,10},
                {10, 0,10,10, 0,10,10,10, 0,10, 0, 0, 0,10},
                {10, 0,10,10, 0,10, 0, 0, 0,10,33,10,10,10},
                {10, 0,10,10, 0,10, 0,10,10,10,10,10,10,10},
                {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,10},
                {10, 0,37, 0, 0,37, 0,37, 0, 0, 0,37, 0,10},
                {10, 0, 0,34,34, 0,34, 0,34, 0, 0,34, 0,10},
                {10,10,10,10,10,10,10,10,10,10,10,10,10,10}};

        weltLeeren();
        generateWorld(backg);
        zeichneUeberschrift("AB4 - Verzweigungen nutzen");
        // Schluesselloch und Tuere werden gekoppelt:
        Schalter s = new Schalter(this);
        int[][] on ={{-1,17},{18,-1}};
        int[][] off={{-1,17},{0,-1}};
        s.setPosition(1,5,on,off);
        s.anschalten();

        // Schalter und Stromquelle werden gekoppelt:
        s = new Schalter(this);
        int[][] on1 = {{-1,-1,12},{13,32,14}};
        int[][] off1 = {{-1,-1,11},{15,0,16}};
        s.setPosition(3,5,on1,off1);
        s.anschalten();

        AB4 ab41 = new AB4();
        addObject(ab41, 1, 2);
        ab41.dreheRechts();
        AB4 ab42 = new AB4();
        addObject(ab42, 4, 2);
        ab42.dreheRechts();
        AB4 ab43 = new AB4();
        addObject(ab43, 1, 9);
        AB4 ab44 = new AB4();
        addObject(ab44, 1,10);
        for(int i=0; i<10; i++) {
            addObject(new Gegenstand("Schraube"),1,10);
            ab44.aufnehmen();
        }

        AB4 ab45 = new AB4();
        addObject(ab45, 6, 7);
        ab45.dreheLinks();

    }

    public void einsatz_04()
    {
        int[][] backg = {{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {10,10,10,10,10,10,10,10,10,10,10,10,10,10},
                { 0, 0, 0, 0, 0, 0,10,10,10,10,10,10,10,10},
                {10,10,10,10,10, 0,10, 4, 4, 4, 4, 4, 4,10},
                {10,10,10,10,10, 0,10, 4, 4, 4, 4, 4, 4,10},
                {10,10,10,10,10,10,10,10,10,10,10,10,18,10},
                {10,10,10,10,10,10, 0, 0, 0,10,10,10, 0,10},
                {10,10,10,10,10,10, 0,10, 0,10,10,10, 0,10},
                {10,10,10,10,10,10, 0,10, 0,10,10,10, 0,10},
                {10,10,10,10,10,10, 0,10, 0,10,10,10, 0,10},
                {10,10,10,10,10, 0, 0,10, 0, 0, 0, 0, 0,10},
                {10,10,10,10,10,10,10,10,10,10,10,10,10,10}};

        weltLeeren();
        generateWorld(backg);
        zeichneUeberschrift("Einsatz 4: Reaktor abschalten");
        AB4 ab4 = new AB4();
        addObject(ab4,  0, 2);

        int x = Greenfoot.getRandomNumber(3)+1;
        int y = Greenfoot.getRandomNumber(5)+6;
        Wand w;
        for (int xx=4; xx>= x; xx--) {
            w = (Wand) (this.getObjectsAt(xx, 4, Wand.class).get(0));
            this.removeObject(w);
        }
        for (int yy=5; yy<= y; yy++) {
            w = (Wand) (this.getObjectsAt(x, yy, Wand.class).get(0));
            this.removeObject(w);
        }

        for (int xx=x+1; xx<=4; xx++) {
            w = (Wand) (this.getObjectsAt(xx, y, Wand.class).get(0));
            this.removeObject(w);
        }
        for (int yy=y+1; yy<= 10; yy++) {
            w = (Wand) (this.getObjectsAt(4, yy, Wand.class).get(0));
            this.removeObject(w);
        }
        for(int xx=7;xx<=12;xx++) {
            if (Greenfoot.getRandomNumber(2)==0) {
                Schalter s = new Schalter(this);
                int[][] on ={{11}};
                int[][] off={{12}};
                s.setPosition(xx,2,on,off);
                s.anschalten();
            }
        }
        Schalter s = new Schalter(this);
        if (Greenfoot.getRandomNumber(2)==0) {
            int[][] on ={{-1,18},{11,-1}};
            int[][] off={{-1,0},{12,-1}};
            s.setPosition(5,7,on,off);
        } else {
            int[][] on ={{-1,18},{17,-1}};
            int[][] off={{-1,0},{17,-1}};
            s.setPosition(5,7,on,off);
            addObject(new Gegenstand("Schluessel"),0,2);
            ab4.aufnehmen();
        } 
        s.anschalten();
        s = new Schalter(this);
        if (Greenfoot.getRandomNumber(2)==0) {
            int[][] on ={{-1,11},{18,-1}};
            int[][] off={{-1,12},{0,-1}};
            s.setPosition(8,8,on,off);
        } else {
            int[][] on ={{-1,17},{18,-1}};
            int[][] off={{-1,17},{0,-1}};
            s.setPosition(8,8,on,off);
            addObject(new Gegenstand("Schluessel"),0,2);
            ab4.aufnehmen();
        } 
        s.anschalten();
        s = new Schalter(this);
        if (Greenfoot.getRandomNumber(2)==0) {
            int[][] on ={{-1,18},{11,-1}};
            int[][] off={{-1,0},{12,-1}};
            s.setPosition(11,5,on,off);
        } else {
            int[][] on ={{-1,18},{17,-1}};
            int[][] off={{-1,0},{17,-1}};
            s.setPosition(11,5,on,off);
            addObject(new Gegenstand("Schluessel"),0,2);
            ab4.aufnehmen();
        } 
        s.anschalten();

        ab4.einsatz4();
        Greenfoot.delay(2);
        boolean geschafft = true;

        for(int xx=7;xx<=12;xx++) {
            s = ((Wand) (this.getObjectsAt(xx, 2, Wand.class).get(0))).getSchalter();

            if (s != null && s.getAn()) {
                geschafft = false;
            }
        }

        if (geschafft) {
            secret.writeLevel(5);
            melde("In letzter Minute wurde das Kraftwerk abgeschaltet. Der Super-GAU wurde dadurch verhindert.");
        } else {
            melde("Du hast die Aufgabe noch nicht erfüllt");
        }
        Greenfoot.setWorld(new RoboterWelt());
    }

    private void ab5_funktionen()
    {
        int[][] backg = {{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {10,10,10,10,10,10,10,10,10,10,10,10,10,10},
                {10,10,10,10, 0,10,10,10, 0,10,10,10,10,10},
                {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,10},
                {10,10,10,10,10,10, 0,10, 0,10,10,10,10,10},
                {10, 0,43,10, 0, 0, 0,10, 0,43, 0, 0,43,10},
                {10, 0, 0, 0, 0, 0, 0,10, 0, 0, 0, 0, 0,10},
                {10,43, 0, 0,43, 0,10,10, 0,42, 0, 0, 0,10},
                {10, 0, 0, 0, 0, 0, 0,10, 0, 0, 0, 0, 0,10},
                {10, 0, 0, 0, 0, 0,43,10, 0, 0, 0, 0, 0,10},
                {10, 0, 0, 0, 0, 0, 0,10, 0, 0, 0, 0, 4,10},
                {10,10,10,10,10,10,10,10,10,10,10,10,10,10}};

        weltLeeren();
        generateWorld(backg);

        zeichneUeberschrift("AB5 - Methoden mit Rückgabewert");
        AB5 ab5;
        ab5 = new AB5();
        addObject(ab5,  1, 3);
        for(int i=0; i<10; i++) {
            addObject(new Gegenstand("Schraube"),1,3);
            ab5.aufnehmen();
        }

        ab5 = new AB5();
        addObject(ab5,  1, 5);
        for(int i=0; i<5; i++) {
            addObject(new Gegenstand("Schraube"),1,5);
            ab5.aufnehmen();
        }
        ab5.verbraucheEnergie(40);
        ab5 = new AB5();
        addObject(ab5,  2, 7);
        for(int i=0; i<2; i++) {
            addObject(new Gegenstand("Schraube"),2,7);
            ab5.aufnehmen();
        }
        ab5.verbraucheEnergie(60);

        ab5 = new AB5();
        addObject(ab5,  8, 5);
        ab5 = new AB5();
        addObject(ab5,  8, 7);
        ab5 = new AB5();
        addObject(ab5,  5, 9);
        ab5.verbraucheEnergie(85);

    }

    public void einsatz_05()
    {
        int[][] backg = {{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {10,10,10,10,10,10,10, 0,10,10,10,10,10,10},
                {10,10,10,10,10,10,10, 0,10,10,10,10,10,10},
                {10,10,10,10,10,10,10, 0,10,10,10,10,10,10},
                {10,10,10,10,10,10,10, 0,10,10,10,10,10,10},
                {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,10},
                {10, 0, 0, 0, 0, 0,10, 0,10, 0, 0, 0, 0,10},
                {10, 0, 0, 0, 0, 0,10, 0,10, 0, 0, 0, 0,10},
                {10, 1, 1, 0, 0, 0,10, 0,10, 0, 0, 0, 0,10},
                {10, 4, 1, 0, 0, 0,10, 0,10, 1, 1, 1, 1,10},
                {10, 4, 1, 0,10, 0,10, 0,10, 4, 4, 4, 4,10},
                {10,10,10,10,10,10,10,10,10,10,10,10,10,10}};

        int z = Greenfoot.getRandomNumber(4);
        for (int y = 0; y <z; y++) {
            backg[4-y] = backg[5-y];
            backg[5-y] = backg[6-y];
        }

        weltLeeren();
        generateWorld(backg);
        zeichneUeberschrift("Einsatz 5 - Ordnung schaffen");
        AB5 ab5 = new AB5();
        addObject(ab5,  7, 1);
        ab5.dreheRechts();
        for (int y = 6-z; y < 11; y++) {
            addObject(new Gegenstand("Akku"), 7, y);
        }

        int x = 10;
        int y = 10- Greenfoot.getRandomNumber(5+z);
        Gegenstand fass1 = new Gegenstand("Atommuell");
        addObject(fass1, x,y);
        x = 12;
        y = 10- Greenfoot.getRandomNumber(5+z);
        Gegenstand fass2 = new Gegenstand("Atommuell");
        addObject(fass2, x,y);
        x = 2;
        y = Greenfoot.getRandomNumber(4+z)+6-z;
        Gegenstand fass3 = new Gegenstand("Atommuell");
        addObject(fass3, x,y);
        x = 4;
        y = Greenfoot.getRandomNumber(4+z)+6-z;
        Gegenstand fass4 = new Gegenstand("Atommuell");
        addObject(fass4, x,y);

        ab5.einsatz5();
        Greenfoot.delay(2);
        if ((backg[fass1.getY()][fass1.getX()] == 4)&&(backg[fass2.getY()][fass2.getX()] == 4) &&(backg[fass3.getY()][fass3.getX()] == 4)&&(backg[fass4.getY()][fass4.getX()] == 4)) {
            secret.writeLevel(6);
            melde("Es herrscht wieder Ordnung im Zwischenlager. So ein Aufräumroboter wäre auch für zu Hause praktisch...");
        } else {
            ab5.warne("Du hast die Aufgabe noch nicht erfüllt", ab5);
        }

        Greenfoot.setWorld(new RoboterWelt());
    }

    private void ab6_attribute()
    {
        int[][] backg = {{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {10,10,10,10,10,33,10,10,10,10,10,10,10,10},
                {10, 0, 0, 0,10, 0,10, 0, 0, 0, 0, 0, 0,10},
                {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,10},
                {10, 0, 0,10,10,10, 0,10,10,10, 0,10,10,10},
                {10, 0, 0,10, 0, 0, 0,10, 0,10, 0,10, 0,10},
                {10, 0, 0,10, 0,10, 0,10, 0,10, 0,10, 0,10},
                {10, 0, 0,10, 0,10, 0, 0, 0,10, 0,10, 0,10},
                {10, 0, 0,10,10,10, 0,10,10,10, 0,10, 0,10},
                {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,10, 0,10},
                {10, 0, 0, 0, 0, 0, 0,10, 0, 0, 0, 0, 0,10},
                {10,10,10,10,10,10,10,10,10,10,10,10,10,10}};

        weltLeeren();
        generateWorld(backg);

        zeichneUeberschrift("AB6 - Eigene Attribute");
        AB6_AB7 ab6;
        ab6 = new AB6_AB7();
        addObject(ab6,  4, 5);
        ab6 = new AB6_AB7();
        addObject(ab6,  8, 5);
        ab6.setRotation(90);
        ab6 = new AB6_AB7();
        addObject(ab6,  3, 9);
        ab6.setRotation(270);
    }

    public void einsatz_06()
    {
        int[][] backg = {{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {10,10,10,10,10,33,10,10,10,10,10,10,10,10},
                {10, 0, 0, 0,10, 0,10, 0, 0, 0, 0, 0, 0,10},
                {10, 0, 0, 0, 0, 0, 0, 0, 0,10, 0, 0, 0,10},
                {10, 0, 0,10,10,10,10,10, 0, 0, 0,10, 0,10},
                {10, 0, 0,10, 0, 0, 0,10,10,10, 0,10, 0,10},
                {10, 0, 0,10, 0,10,10,10, 0,10, 0,10, 0,10},
                {10,10, 0,10, 0, 0, 0, 0, 0,10, 0,10, 0,10},
                {10, 0, 0,10,10,10, 0,10,10,10, 0,10, 0,10},
                {10, 0,10, 0, 0, 0, 0, 0, 0, 0, 0,10, 0,10},
                {10, 0, 0, 0, 0, 0, 0,10, 0, 0, 0, 0, 0,10},
                {10,10,10,10,10,10,10,10,10,10,10,10,10,10}};

        weltLeeren();
        generateWorld(backg);

        zeichneUeberschrift("Einsatz 6: Notfallprogramm");
        AB6_AB7 ab6;
        ab6 = new AB6_AB7();
        addObject(ab6,  6, 5);
        ab6.setRotation(90);
        ab6.einsatz6();
        Greenfoot.delay(2);
        if (ab6.istAufGegenstand("Portal")) {
            if(ab6.getSchritte()==65) {
                secret.writeLevel(7);
                melde("Notfallprogramm aktiviert! Suche Ausgang... Suche Ausgang... Ausgang nach 65 Schritten erreicht!");
            } else {
                ab6.warne("Du hast die Aufgabe noch nicht korrekt erfüllt. Der Pledge-Algorithmus sollte für dieses Labyrinth 65 Schritte benötigen.",ab6);
            }
        } else {
            ab6.warne("Du hast die Aufgabe noch nicht erfüllt", ab6);
        }
        Greenfoot.setWorld(new RoboterWelt());
    }

    private void ab7_vererbung()
    {
        int[][] backg = {{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {10,10,10,10,10,10,33,10,10,10,10,10,10,10},
                {10,10,10, 0,10,10, 0,10, 0,10, 0,10, 0,10},
                {10, 0,43, 0,35, 0, 0,35, 0, 0, 0, 0,35,10},
                {10,10,10, 0,10,10, 0,10,10,10, 0,10, 0,10},
                {10,10,10, 0,10,43,43,43, 0,10, 0,10, 0,10},
                {10,10,10, 0,10,43,43,43,35,10,43,10, 0,10},
                {10, 0, 0, 0,10, 0, 0, 0,35,10,43,10, 0,10},
                {10, 0,43, 0,10,10, 0,10,10,10, 0,10, 0,10},
                {10, 0,43, 0, 0,43, 0, 0, 0, 0, 0,10, 0,10},
                {10, 0, 0, 0,43,43, 0,43, 0, 0, 0, 0, 0,10},
                {10,10,10,10,10,10,10,10,10,10,10,10,10,10}};

        weltLeeren();
        generateWorld(backg);

        zeichneUeberschrift("AB7 - Vererbung");
        AB6_AB7 ab6;
        ab6 = new AB6_AB7();
        addObject(ab6,  8, 5);
        ab6.setRotation(90);
        ab6 = new AB6_AB7();
        addObject(ab6,  4, 9);
        ab6.setRotation(90);

        ab6 = new AB6_AB7();
        addObject(ab6,  1, 3);
        ab6.setRotation(0);

    }

    public void einsatz_07()
    {
        int[][] backg = {{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {10,10,10,10,10,10,10,10,10,10,10,10,10,10},
                {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,10},
                {10, 0,10,10,10,10,10,10,10,10,10,10, 0,10},
                {10, 0,10,10,10,10,10,10,10,10,10,10, 0,10},
                {10, 0,10,10,10,10,10,10,10,10,10,10, 0,10},
                {33, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,31,38,10},
                {10, 0,10,10,10,10,10,10,10,10,10,10, 0,10},
                {10, 0,10,10,10,10,10,10,10,10,10,10, 0,10},
                {10, 0,10,10,10,10,10,10,10,10,10,10, 0,10},
                {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,10},
                {10,10,10,10,10,10,10,10,10,10,10,10,10,10}};

        int[][] on = {
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,},
                {21,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,}};

        weltLeeren();
        int x = 3;
        int pos = 1;
        int brennstaebe=0;
        while (x< 11) {
            for (int y = 3; y <=9 ; y++) {
                backg[y][x] = 0;
            }
            if (Greenfoot.getRandomNumber(2)==0) {
                backg[3][x] = 42;
                if (Greenfoot.getRandomNumber(2)==0) { backg[4][x] = 35; brennstaebe++;}
                if (Greenfoot.getRandomNumber(2)==0) { backg[5][x] = 35; brennstaebe++;}
            } else {
                if (pos ==1) {
                    pos = 2;
                    on[0][x-1] = 21;
                    on[8][x-1] = 21;
                } else {
                    if (pos == 2) {
                        pos = 1;
                        on[4][x-1] = 21;
                        on[8][x-1] = 21;
                    }
                }
            }

            if (Greenfoot.getRandomNumber(2)==0) {
                backg[9][x] = 42;
                if (Greenfoot.getRandomNumber(2)==0) {backg[7][x] = 35;brennstaebe++;}
                if (Greenfoot.getRandomNumber(2)==0) {backg[8][x] = 35;brennstaebe++;}
            }else {
                if (pos ==3) {
                    pos = 2;
                    on[8][x-1] = 21;
                    on[0][x-1] = 21;
                    on[4][x-1] = 0;
                } else {
                    if (pos == 2) {
                        on[4][x-1] = 21;
                        on[0][x-1] = 21;
                        on[8][x-1] = 0;
                        pos = 3;
                    }
                }
            }

            x += Greenfoot.getRandomNumber(2)+2;
        }

        generateWorld(backg);

        zeichneUeberschrift("Einsatz 7: Brennstäbe inventarisieren");
        AB6_AB7 ab7;
        ab7 = new AB6_AB7();
        addObject(ab7,  1, 6);
        Schalter s = new Schalter(this);
        s.setPosition(2,2,on,on);
        Gegenstand g = (Gegenstand) (this.getObjectsAt(11, 6, Gegenstand.class).get(0));
        g.setSchalter(s);

        ab7.einsatz7();
        Greenfoot.delay(2);
        if (ab7.istAufGegenstand("Portal")) {
            if(ab7.getBrennstaebe()==brennstaebe) {
                secret.writeLevel(8);
                melde("Jetzt sind auch noch die Stollen eingestürtzt. Zum Glück hat das Notfallprogramm funktioniert. Gut gemacht, Robi!");
            } else {
                ab7.warne("Du hast die Aufgabe noch nicht korrekt erfüllt. Es hätten "+brennstaebe+" Brennstaebe sein müssen.",ab7);
            }
        } else {
            ab7.warne("Du hast die Aufgabe noch nicht erfüllt", ab7);
        }
        Greenfoot.setWorld(new RoboterWelt());
    }

    private void ab8_lokale_variablen()
    {
        int[][] backg = {{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {10,10,10,10,10,10,10,10,10,10,10,10,10,10},
                {10, 0, 0,34,34, 0,38,41,36, 0,36,41,34,10},
                {10, 0, 0, 0,38, 0, 0,38,41, 0,41, 0, 0,10},
                {10,10,10,10,10,18,10,10,10,10,18,10,10,10},
                {10, 0, 0, 0, 0, 0, 0, 0,10, 0, 0, 0, 0,10},
                {10, 0,43,43,43,43,43, 0,10, 0, 0, 0, 0,10},
                {10, 0,43,43,43,43,43, 0,10,10,18,10,10,10},
                {10, 0,43,43,43,43,43, 0,10, 0, 0, 0, 0,10},
                {10, 0,43,43,43,43,43, 0,10, 0, 0, 0, 0,10},
                {10, 0, 0, 0, 0, 0, 0, 0,10, 0, 0, 0, 0,10},
                {10,10,10,10,10,10,10,10,10,10,10,10,10,10}};

        weltLeeren();
        generateWorld(backg);

        zeichneUeberschrift("AB8 - Lokale Variablen");
        AB8 ab8;
        ab8 = new AB8();
        addObject(ab8,  1, 2);
        ab8 = new AB8();
        addObject(ab8,  1, 3);
        ab8 = new AB8();
        addObject(ab8,  1, 5);
        ab8 = new AB8();
        addObject(ab8,  9, 5);
        ab8 = new AB8();
        addObject(ab8,  9, 8);

    }

    public void einsatz_08()
    {
        int[][] backg = {{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {10,10,10,10,10,10,10,10,10,10,10,10,10,10},
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,33,10},
                {10,10,10,10,10,10,10,10,10,10,10,10,10,10},
                {10,10,10,10,10,10,10,10,10,10,10,10,10,10},
                {10,10,10,10,10,10,10,10,10,10,10,10,10,10},
                {10,10,10,10,10,10,10,10,10,10,10,10,10,10},
                {10,10,10,10,10,10,10,10,10,10,10,10,10,10},
                {10,10,10,10,10,10,10,10,10,10,10,10,10,10},
                {10,10,10,10,10,10,10,10,10,10,10,10,10,10},
                {10,10,10,10,10,10,10,10,10,10,10,10,10,10},
                {10,10,10,10,10,10,10,10,10,10,10,10,10,10}};

        weltLeeren();
        int x = 1;
        int pos = 1;
        int qm=0;
        while(x<11) {
            int breite = Greenfoot.getRandomNumber(4)+2;
            if ((breite+x)>13) {
                breite = 13-x;
            }
            int hoehe = Greenfoot.getRandomNumber(5)+3;
            for(int xx = 0; xx<breite; xx++) {
                backg[4][xx+x] = 0;
                backg[5][xx+x] = 1;
                for(int yy=2; yy<hoehe; yy++) {
                    backg[yy+4][xx+x] = 4;
                }
            }
            qm += breite*(hoehe-2);
            backg[3][x]=0;
            x = x + breite+1;
        }

        generateWorld(backg);

        zeichneUeberschrift("Einsatz 8: Platz berechnen");
        AB8 ab8;
        ab8 = new AB8();
        addObject(ab8,  0, 2);
        int anz = ab8.einsatz8();
        Greenfoot.delay(2);
        if (ab8.istAufGegenstand("Portal") && anz == qm) {
            secret.writeLevel(9);
            melde("Nur noch "+qm+" Plätze? Na viel Platz ist das ja nicht mehr. Aber dafür kannst du ja nichts. Auftrag erfüllt!");
        } else {
            ab8.warne("Du hast die Aufgabe noch nicht erfüllt. Berechneter Platz:"+anz, ab8);
        }
        Greenfoot.setWorld(new RoboterWelt());
    }

    private void ab9_parameter()
    {
        int[][] backg = {{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {20,20,20,20,20,20,20,20,20,20,20,20,20,20},
                {20,20,20,20,20,20, 0,20, 0, 0, 0,20, 0,20},
                {20,20,20,20,20,20,20,20,20,20,20,20,20,20},
                {20,20, 0,20,20,20,20,20,20,20,20,20,20,20},
                {20,20,20,20,20,20,20,38,20,20,31, 0,31,20},
                {20,20,20,20,20,20,20,31,20,20, 0,31,38,20},
                {20,20,20,20,20,20,20,31,20,20,20,20,20,20},
                {20,20,20,20,20,20,20,31,20, 0, 0, 0,31,20},
                {20,20,20, 0,20,20,20,31,20, 0, 0, 0,31,20},
                {20,20,20,20,20,20,20,31,20, 0,38, 0,31,20},
                {20,20,20,20,20,20,20,20,20,20,20,20,20,20}};

        weltLeeren();

        // Bombe in dem ersten Bereich x = 10 bis 12 und y = 5 bis 6
        int x = 10+Greenfoot.getRandomNumber(3);
        int y = 5+Greenfoot.getRandomNumber(2);
        if (x==12 && y==6) x=11;
        backg[y][x] = 44;

        // Bombe in dem ersten Bereich x = 9 bis 12 und y = 8 bis 10
        x = 9+Greenfoot.getRandomNumber(4);
        y = 8+Greenfoot.getRandomNumber(3);
        if (x==10 && y==10) y=9;
        backg[y][x] = 44;

        // Bombe in dem ersten Bereich x = 7 und y = 6 bis 10
        x = 7;
        y = 6+Greenfoot.getRandomNumber(5);
        backg[y][x] = 44;

        generateWorld(backg);

        zeichneUeberschrift("AB9 - Methoden mit Parametern");
        // zeigeKoordinaten();
        AB9 legeRoboter, sprengRoboter1, sprengRoboter2, sprengRoboter3;
        legeRoboter = new AB9();
        addObject(legeRoboter,  6, 2);
        // robo1.setAnzahlVonGegenstand("Bombe", 3); //Zu Testzwecken
        sprengRoboter1 = new AB9();
        addObject(sprengRoboter1,  8, 2);

        sprengRoboter2 = new AB9();
        addObject(sprengRoboter2,  9, 2);

        sprengRoboter3 = new AB9();
        addObject(sprengRoboter3,  10, 2);

        legeRoboter.setAnzahlVonGegenstand("Brennstab", Greenfoot.getRandomNumber(5)+9);
        sprengRoboter1.setAnzahlVonGegenstand("Bombe", Greenfoot.getRandomNumber(9)+6);
        sprengRoboter2.setAnzahlVonGegenstand("Bombe", Greenfoot.getRandomNumber(3)+3);
        // sprengRoboter3.setAnzahlVonGegenstand("Bombe", Greenfoot.getRandomNumber(12)+5);

        EinsatzLeiter el = new EinsatzLeiter();
        addObject(el,  12, 2);
        el.nehmeKontaktAuf();
    }

    public void einsatz_09()
    {
        int[][] backg = {{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {20,20,20,20,20,20,20,20,20,20,20,20,20,20},
                {20, 0,20, 0,20, 0, 0, 0,20,33,33,33,33,20},
                {20,20,20,20,20,20,20,20,20,20,20,20,20,20},
                {20,20,20,20,20,20,20,20,20,20,20,20,20,20},
                {20,20,20,20,20,20,20,20,20,20,20,20,20,20},
                {20,20,20,20,20,20,20,20,20,20,20,20,20,20},
                {20,20,20,20,20,20,20,20,20,20,20,20,20,20},
                {20,20,20,20,20,20,20,20,20,20,20,20,20,20},
                {20,20,20,20,20,20,20,20,20,20,20,20,20,20},
                {20,20,20,20,20,20,20,20,20,20,20,20,20,20},
                {20,20,20,20,20,20,20,20,20,20,20,20,20,20}};

        weltLeeren();

        // Luecke in dem ersten Drittel x = 2 und y = 5 bis 9
        int x = 2;
        int y = 5;
        backg[y][x] = 0;

        // Luecke in dem ersten Drittel x = 6 bis 7 und y = 5 bis 9
        x = 6;
        y = 7;
        backg[y][x] = 0;

        // Luecke in dem ersten Drittel x = 11 und y = 5 bis 9
        x = 11;
        y = 8;
        backg[y][x] = 0;

        generateWorld(backg);

        zeichneUeberschrift("Einsatz 9: Das Endlager wird bestückt");
        zeigeKoordinaten();

        AB9 legeRoboter, sprengRoboter1, sprengRoboter2, sprengRoboter3;
        legeRoboter = new AB9();
        addObject(legeRoboter,  3, 2);
        int zufall = Greenfoot.getRandomNumber(9);
        legeRoboter.setAnzahlVonGegenstand("Brennstab", 12+zufall);

        sprengRoboter1 = new AB9();
        addObject(sprengRoboter1,  5, 2);
        sprengRoboter1.setAnzahlVonGegenstand("Bombe", 1);

        sprengRoboter2 = new AB9();
        addObject(sprengRoboter2,  6, 2);
        sprengRoboter2.setAnzahlVonGegenstand("Bombe", 1);

        sprengRoboter3 = new AB9();
        addObject(sprengRoboter3,  7, 2);
        sprengRoboter3.setAnzahlVonGegenstand("Bombe", 1);

        EinsatzLeiter el = new EinsatzLeiter();
        addObject(el,  1, 2);
        el.nehmeKontaktAuf();
        el.einsatz9();
        Greenfoot.delay(2);
        if (legeRoboter.istAufGegenstand("Portal") && sprengRoboter1.istAufGegenstand("Portal") && sprengRoboter2.istAufGegenstand("Portal") && sprengRoboter3.istAufGegenstand("Portal") && !legeRoboter.hatGegenstand("Brennstab")) {
            secret.writeLevel(10);
            melde("Prima gemacht. Auftrag erfüllt!");
        } else {
            melde("Du hast die Aufgabe noch nicht erfüllt!");
        }
        Greenfoot.setWorld(new RoboterWelt());
    }

    private void ab10_zaehlschleifen()
    {
        int[][] backg = {{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {20,20,20,20,20,20,20,20,20,20,20,20,20,20},
                {20, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,20},
                {20, 5, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 5,20},
                {20, 5, 2, 2, 2, 2, 2, 2, 2, 5, 2, 2, 5,20},
                {20, 5, 2, 5, 5, 5, 2, 2, 5, 5, 5, 2, 5,20},
                {20, 5, 2, 5, 5, 5, 2, 5, 5, 5, 5, 5, 5,20},
                {20, 5, 2, 5, 5, 5, 2, 2, 5, 5, 5, 2, 5,20},
                {20, 5, 2, 5, 5, 5, 2, 2, 2, 5, 2, 2, 5,20},
                {20, 5, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 5,20},
                {20, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,20},
                {20,20,20,20,20,20,20,20,20,20,20,20,20,20}};

        weltLeeren();
        generateWorld(backg);
        zeichneUeberschrift("AB10 - Zählschleifen");
        Greenfoot.setSpeed(40);

        AB10 r1 = new AB10();
        addObject(r1,1,10);
        r1.setAnzahlVonGegenstand("Akku", 6);

        AB10 r2 = new AB10();
        addObject(r2,9,4);
        r2.setAnzahlVonGegenstand("Brennstab", 26);
        r2.setAnzahlVonGegenstand("Akku", 4);

        AB10 r3 = new AB10();
        addObject(r3,3,5);
        r3.setAnzahlVonGegenstand("Brennstab", 16);
        r3.setAnzahlVonGegenstand("Akku", 4);

        AB10 r4 = new AB10();
        addObject(r4,7,9);
    }

    public void einsatz_10()
    {
        int[][] backg = {{ 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
                {20,20,20,20,20,20,20,20,20,20,20,20,20,20},
                {20,20,20, 6, 6,20,20,20, 6, 6, 6, 6,20,20},
                {20,20, 6, 6, 6, 6,20, 6, 6, 6, 6, 6, 6,20},
                {20, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,20},
                { 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
                { 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,20},
                {20, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,20},
                {20,20, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,20},
                {20,20,20, 6, 6, 6, 6, 6, 6, 6, 6, 6,20,20},
                {20,20,20, 6, 6, 6, 6,20, 6, 6, 6,20,20,20},
                {20,20,20,20,20,20,20,20,20,20,20,20,20,20}};

        weltLeeren();
        generateWorld(backg);
        zeichneUeberschrift("Einsatz 10: Das große Finale");
        Greenfoot.setSpeed(40);

        AB10 r1 = new AB10();
        addObject(r1,3,2);
        r1.setAnzahlVonGegenstand("Akku", 1);
        r1.setAnzahlVonGegenstand("Brennstab", 51);

        r1.einsatz10();
        Greenfoot.delay(2);
       
        int[][] loesung = {{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {20,20,20,20,20,20,20,20,20,20,20,20,20,20},
                {20,20,20, 0, 0,20,20,20, 0,35, 0, 0,20,20},
                {20,20, 0,35, 0, 0,20, 0,35,35,35, 0, 0,20},
                {20, 0,35,35,35, 0, 0,35,35,35,35,35, 0,20},
                { 0,35,35,35,35,35,35,35,35,35,35,35,35, 0},
                { 0, 0,35,35,35, 0, 0,35,35,35,35,35, 0,20},
                {20, 0, 0,35, 0, 0, 0, 0,35,35,35, 0, 0,20},
                {20,20, 0, 0,35,35,35,35,35,35,35, 0, 0,20},
                {20,20,20, 0,35,35,35,35,35,35,35, 0,20,20},
                {20,20,20, 0, 0, 0, 0,20, 0, 0, 0,20,20,20},
                {20,20,20,20,20,20,20,20,20,20,20,20,20,20}};
    
        boolean ok = true;
        if (r1.getAnzahl("Brennstab")>0) {
            ok = false;
        }
        for(int x=0; x<14; x++) {
            for(int y = 0; y<12; y++) {
                if (loesung[y][x] == 0 && this.getObjectsAt(x, y, Gegenstand.class).size()>0) {
                    ok = false;
                }
                if (loesung[y][x] ==  35 && this.getObjectsAt(x, y, Gegenstand.class).size()==0) {
                    ok = false;
                }
            }
        }
                
        
        if(ok){
            secret.writeLevel(11);
            melde("Prima gemacht. Auftrag erfüllt!");
        } else {
            r1.melde("Du hast die Aufgabe noch nicht erfüllt!",false);
        }
        Greenfoot.setWorld(new RoboterWelt());
    }

    private void ab11_finalBild()
    {
        int[][] backg = {{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {10,10,10,10,10,10,10,10,10,10,10,10,10,10},
                {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,10},
                {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,10},
                {10,21,21,21, 0,21, 0, 0,21, 0, 0,21, 0,10},
                {10, 0,21, 0,21, 0,21, 0,21, 0, 0,21, 0,10},
                {10, 0,21, 0,21, 0,21, 0,21, 0, 0,21, 0,10},
                {10, 0,21, 0,21, 0,21, 0,21, 0, 0,21, 0,10},
                {10, 0,21, 0, 0,21, 0, 0,21,21, 0,21,21,10},
                {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,10},
                {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,10},
                {10,10,10,10,10,10,10,10,10,10,10,10,10,10}};

        weltLeeren();

        generateWorld(backg);
        zeichneUeberschrift("Bravo, jetzt kannst du Programmieren!");

    }

    private void melde(String text) {
        JOptionPane.showMessageDialog(null, text, "Meldung!",
            JOptionPane.INFORMATION_MESSAGE);
    }
}