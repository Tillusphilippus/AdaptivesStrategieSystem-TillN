/**
 * Die LinearMapped Strategie beschreibt Spieler, die auf eine Geier-/Mauskarte immer denselben Kartenwert spielen.
 * Beim LinearMapping wird auf den Kartenwert einfach ein Betrag von 1 bis 15 aufaddiert.
 * Die 0 als Ergebnis wird übersprungen, deshalb wird auf negative Zahlen der Betrag +1 addiert.
 * Die gespielte Karte lässt sich also einfach vorhersagen.
 * Ein Beispiel wäre ein +5 Algorithmus, der immer die Geier-/Mauskarte +5 ausspielt.
 *
 * @author Till N.
 * @version 1.0
 */

public class LinearMapped extends Strategie {

    //betrag beschreibt die Menge, die auf die Geierkarte aufaddiert wird.
    int betrag;

    /**
     * Im Konstruktor der CustomMapped Strategie wird ber Betrag des Inkrementierens mit angegeben.
     * 
     * @param betrag Betrag des Inkrementierens
     */
    public LinearMapped(int betrag){
        this.betrag = betrag;
    }

    @Override
    public int gibKarte(int naechsteKarte) {

        int ergebnis;

        /*
         * Auf die Karte wird der vorher festgelegte Betrag aufaddiert.
         * Modulo 15, damit wir immer nur Karten auslegen bis maximal 15.
         * Bei negativen Zahlen wir noch 1 aufaddiert.
         */

        if (naechsteKarte < 0) {
            ergebnis = (15 + (naechsteKarte + betrag + 1)) % 15 ;
        } else {
            ergebnis = (naechsteKarte + betrag) % 15;
        }

        /*
         * 15 mod 15 ergibt 0.
         * Es ist also notwendig die 0 wieder zurück in eine 15 umzutauschen, da es eine 0 als Karte nicht gibt.
         */

        if(ergebnis == 0) {
            ergebnis=15;
        }

        return ergebnis;
    }

    @Override
    public void reset() {

    }
}