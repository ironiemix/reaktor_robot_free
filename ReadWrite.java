import java.io.*;
import java.util.*;

/**
 * Die Klasse ReadWrite erstellt geheimCodes und steuert damit den Ablauf
 * und die Reihenfolge der Einsaetze
 */
class ReadWrite
{
    private String dateiName;

    /** Erzeugt neues Dateizugriffsobjekt
     */
    public ReadWrite () {
        dateiName = "secret";
    }

    /** Schreibt einen neuen Levelstand in die Datei, wenn dieser höher als der bisherige ist
     * (dies verhindert ein Zurückfallen auf ein niedrigeres Level, wenn ein Einsatz erneut ausgeführt wird.)
     * @param level neuer Level
     */
    public void writeLevel(int level) {
        writeLevel(level,false);
    }

    /** Schreibt einen neuen Levelstand in die Datei. 
     * @param level neuer Level
     * @param down  darf der neue Level auch niedriger als der alte sein (true = gezieltes Zurücksetzen)
     */
    public void writeLevel(int level, boolean down) {
        if (level > getLevel() || down) {  // Damit nicht wieder in einen kleineren Level zurückgefallen wird
            Random r = new Random();
            Scanner s = new Scanner(this.read());
            long z1 = s.nextLong();
            long z2 = r.nextInt()+362734617;
            z2 = z2 - (z2 % 131 - z1% 131);
            while (z2 % 31 != level) {
                z2 += 131;
            }
            this.append(""+z2);
        }
    }

    /** Erzeugt eine neue Secret-Datei mit Level 1
     */
    public void init() {
        Random r = new Random();
        long z1 = r.nextInt()+212312312;
        long z2 = r.nextInt()+362734617;
        z2 = z2 - (z2 % 131 - z1% 131);
        while (z2 % 31 != 1) {
            z2 += 131;
        }
        write(""+z1);
        append(""+z2);
    }

    /** Liefert den zuletzt gespeicherten Level
     * @return Level
     */
    public int getLevel() {
        int level = 1;
        String z = read();
        if (z.equals("")) {
            init();
        }

        Scanner s = new Scanner(read());
        long z1 = s.nextLong();
        s = new Scanner(readLastLine());
        long z2 = s.nextLong();

        if (z1 % 131 != z2 %131) {
            init();
            level = 1;
        } else {
            level = (int) (z2 % 31);
        }

        return level;
    }

    /** Liefert den höchsten bisher gespeicherten Level
     * @return maximal erreichter Level
     */
    public int getMaxLevel() {
        int level = 1;
        String z = read();
        if (z.equals("")) {
            init();
            return 1;
        }

        try {
            FileReader fr = new FileReader(dateiName);
            BufferedReader br = new BufferedReader(fr);
            String zeile = br.readLine();
            Scanner s = new Scanner(zeile);
            long z1 = s.nextLong();
            while(br.ready()) {
                zeile = br.readLine();
                s = new Scanner(zeile);
                long z2 = s.nextLong();

                if (z1 % 131 != z2 %131) {
                    init();
                    level = 1;
                    break;
                } else {
                    if (level < (int) (z2 % 31)) {
                        level = (int) (z2 % 31);
                    }
                }
            }
            br.close();
            return level;
        }
        catch (IOException e) {
            return level;
        }

    }

    /** Liest die erste Zeile aus der Secretdatei
     * @return erste Zeile
     */
    public String read()
    {
        try {
            FileReader fr = new FileReader(dateiName);
            BufferedReader br = new BufferedReader(fr);
            String zeile = br.readLine();
            br.close();
            return zeile;
        }
        catch (IOException e) {
            return "";
        }
    }

    /** Liest die letzte Zeile aus der Secretdatei
     * @return letzte Zeile
     */  
    public String readLastLine()
    {
        try {
            FileReader fr = new FileReader(dateiName);
            BufferedReader br = new BufferedReader(fr);
            String zeile = br.readLine();
            while(br.ready()) zeile = br.readLine();
            br.close();
            return zeile;
        }
        catch (IOException e) {
            return "";
        }
    }

    /** Schreibt eine neue Datei mit einer Zeile
     * @param text 1. Zeile der Datei
     */
    public boolean write(String text)
    {
        try {
            FileWriter fw = new FileWriter(dateiName);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(text);
            bw.newLine();
            bw.close();
            return true;
        }
        catch (IOException e) {
            return false;
        }
    }

    /** Hängt eine Zeile an die Secret-Datei an
     * @param text anzuhängende Zeile
     */
    public boolean append(String text)
    {
        try {
            FileWriter fw = new FileWriter(dateiName, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(text);
            bw.newLine();
            bw.close();
            return true;
        }
        catch (IOException e) {
            return false;
        }
    }
}