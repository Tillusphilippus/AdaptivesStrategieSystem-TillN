/**
 * Die Klasse Geier ist ein Spieler, der immer die ausgelegte Karte +1 ausgibt.
 * Das heißt, er spielt nach einer +5 mapped Strategie.
 *
 * @author THM
 * @version 0.1
 */

public class Geier extends HolsDerGeierSpieler {

   public void reset () {
    }
   
    public int gibKarte(int naechsteKarte) {
        //System.out.println(this.getClass());
        if (naechsteKarte<0)
            return naechsteKarte+6;
        return naechsteKarte+5;
        
    }
    
    
}
