/**
 * Die abstrakte Klasse HolsDerGeierSpieler ist das Grundgerüst für Spieler.
 * Jeder Spieler verfügt über die Attribute nummer und einem HolsDerGeier Objekt. Also dem aktuellen Spiel.
 * Jeder Spieler muss die Methoden reset(), und gibKarte() implementieren.
 *
 * @author THM
 * @version 0.1
 */

public abstract class HolsDerGeierSpieler {

    private int nummer;
    private HolsDerGeier hdg;

    
    public int getNummer() {
        return nummer;
    }
    public HolsDerGeier getHdg()  {
        return hdg;
    }

    public int letzterZug() {
        return hdg.letzterZug(nummer);
    }
    
    public void register(HolsDerGeier hdg,int nummer) {
        this.hdg=hdg;
        this.nummer=nummer;
    }
    
    public abstract void reset();    
    public abstract int gibKarte(int naechsteKarte);
        
}
