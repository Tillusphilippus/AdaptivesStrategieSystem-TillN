import java.util.ArrayList;

/**
 * Die Klasse Random ist ein Spieler, der eine zufällige Karte ausspielt.
 * Das heißt, er spielt nach einer random Strategie.
 *
 * @author THM
 * @version 0.1
 */

public class Random extends HolsDerGeierSpieler {
    /**
    * Hier definieren Sie die Attribute Ihrer Klasse
    * Beispiel: private <Typ> Name_des_Attributs
    */
    /* Hier stehen die Karten, die noch nicht gespielt wurden */
    private ArrayList<Integer> nochNichtGespielt=new ArrayList<Integer>();

    public Random() {
        //  Meine Karten
    }
    
    public void reset() {
        nochNichtGespielt.clear();
        for (int i=1;i<=15;i++)            
            nochNichtGespielt.add(i);                     
    }


    // Spiele zufaellig eine Karte

    private int spieleZufallskarte() {
        int nochVorhanden=nochNichtGespielt.size();            
        int index=(int) (Math.random()*nochVorhanden);
        int ret=nochNichtGespielt.remove(index);
        return ret;
    }
    
    public int gibKarte(int naechsteKarte) {
        
        return spieleZufallskarte();
        
    }
        
    
    
    
}
