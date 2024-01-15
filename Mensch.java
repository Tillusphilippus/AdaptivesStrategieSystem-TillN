import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Die Klasse Mensch ist ein Spieler, der von einem Benutzer gespielt wird.
 * Das heißt, ein Benutzer wählt selbst die Eingaben für das Spiel.
 *
 * @author THM
 * @version 0.1
 */

public class Mensch extends HolsDerGeierSpieler {

    private ArrayList<Integer> nochNichtGespielt=new ArrayList<Integer>();

    private static Integer liesInteger(){
             BufferedReader B =
             new BufferedReader(new InputStreamReader(System.in));
             try {
                 return Integer.parseInt(B.readLine());                 
             } catch (Exception e) {
                 return -99;
             }
       }
    
    public void reset() {
        nochNichtGespielt.clear();
        for (int i=1;i<=15;i++)            
            nochNichtGespielt.add(i);                 
    }
       
    public int gibKarte(int naechsteKarte) {
        int ret=-99;
        do {
            System.out.println("Es geht um die Kaerte: "+naechsteKarte);
            System.out.println("Sie haben noch die folgenden Karten zur Verf�gung: "+nochNichtGespielt);
            System.out.print("Welche Karte spielen Sie aus?> ");
            ret=liesInteger();
        } while (!nochNichtGespielt.contains(ret));
        nochNichtGespielt.remove(nochNichtGespielt.indexOf(ret));
        return ret;
    }       
        
}
