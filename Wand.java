import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Die Klasse Wand fast alle Actors zusammen, durch die der Roboter nicht hindurchlaufen kann.
 * Dazu gehören neben den normalen Wänden auch Steine, Türen (vertikal/horizontal) usw.
 */
  
public class Wand extends Actor
{
  private int typ;
  private static String[] typen = {"Wand","Tuer","Tuer","Schalter","Schalter","Stromquelle","Stromquelle","Stromquelle","Stromquelle","Schloss","Stein","Steine"};
  private String[] bilder = {"Wand","Tuer2","Tuer1","SchalterEin","SchalterAus","Stromquelle1","Stromquelle2","Stromquelle3","Stromquelle4","Schloss","Stein","Steine"};
  private Schalter schalter;    
    
  /** Erzeugt eine neue Wand vom angegeben Typ
    * @param name Name des Typs ("Wand","Tuer2","Tuer1","SchalterEin","SchalterAus","Stromquelle1","Stromquelle2","Stromquelle3","Stromquelle4","Schloss","Stein","Steine")
    */
  
  public Wand(String name) {
    super();
    this.typ = 0;
    
    for(int i = 0; i < bilder.length; i++) {
      if (name.equals(bilder[i])) {
        this.typ = i;
      }
    }
    this.setImage("images/"+bilder[typ]+".png");
    schalter = null;
  }
  
  /** Erzeugt eine neue Standardwand
    */
    
  public Wand() {
    super();
    this.typ = 0;
    this.setImage("images/"+bilder[typ]+".png");
    schalter = null;
  }
  
  /** untersucht, ob name eine korrekte Bezeichnug für einen Wandtyp ist
     * @return erg true, wenn name korrekt ist
     */
    public static boolean istWandtyp(String name) {
        boolean erg = false;
        for(int i = 0; i < typen.length; i++) {
            if (name.equals(typen[i])) {
                erg = true;
            }
        }
        return erg;
    }
  
  /** Liefert den Typ der Wand 
  * @return Typname (Wand, Tuer, Stromquelle, Schalter, Schloss, Stein, Steine)
  */
  public String getName() {
    return this.typen[typ];
  }
  
  /** Liefert einen mit diesem Objekt verbunden Schalter
    * @return Schalter, wenn vorhanden, sonst null
  */
  public Schalter getSchalter() {
    return this.schalter;
  }
  
  /** Verbindet dieses Objekt mit einem Schalter, der
  * die Welt verändern kann.
  * @param s Schalterobjekt
  */
  public void setSchalter(Schalter s) {
    this.schalter = s;
  }

  // Systembedingte Routinen    
  /**
    * Wird aufgerufen, wenn der Roboter in die Welt gesetzt wird; 
    * erweitert nach Vorschlag P.Henriksen 29.6.08
    */
  protected void addedToWorld(World world) {
    // Roboter kann nicht auf eine Wand oder einen anderem Roboter gesetzt werden
    Actor wand = getOneObjectAtOffset(0, 0, Wand.class);
    Actor roboter = getOneObjectAtOffset(0, 0, Roboter.class);
    if (wand != null || roboter != null) {
      world.removeObject(this);
    }
  }

  /**
   * setLocation(x,y) fuer Roboter wird ueberschrieben, um nicht auf Wand oder
   * anderen Roboter ziehen zu koennen!
   * -- setzt den Roboter auf eine Kachel, deren Spalte x sowie Reihe y du 
   * hier vorgibst. Die Nummerierung beginnt jeweils bei 0 !!) 
   */
  public void setLocation(int x, int y) {
    List wand = getWorld().getObjectsAt(x, y, null);
    if (wand.isEmpty()) {
      super.setLocation(x, y);
    }
  }
   
  /* setLocation(x,y) und setRotation(x) koennen nicht verborgen werden, 
   * weil sie schon in der Oberklasse Actor public sind. 
   */
  
  /**
   * setRotation(x) fuer Roboter wird verhindert! 
   * Es ist unnoetig, da Roboter sich nur in vier Richtungen drehen koennen!
   */
  public void setRotation(int x) {
    // nichts machen!!
  }
}