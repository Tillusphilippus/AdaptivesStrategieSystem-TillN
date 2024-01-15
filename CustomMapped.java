import java.util.ArrayList;

/**
 * Die CustomMapped Strategie beschreibt Spieler, die auf eine Geier-/Mauskarte immer denselben Kartenwert spielen.
 * Beim CustomMapping wird für jede Karte ein Kartenwert festgelegt.
 * Die gespielte Karte lässt sich also einfach vorhersagen.
 *
 * @author Till N.
 * @version 1.0
 */

public class CustomMapped extends Strategie {

    private int[] kartenWert = new int[15];

    /**
     * Im Konstruktor der CustomMapped Strategie wird mit Einzelwerden die Kartenwerte für die jeweiligen Karten festgelegt.
     * m steht hier für minus und p für plus.
     * Die Zahl ist der jeweilige Kartenwert.
     * Auf die Geierkarte -1 wird also immer der in m1 festgelegte Wert gespielt.
     */
    public CustomMapped(int m1, int m2, int m3, int m4, int m5, int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8, int p9, int p10){

        /**
         * Die Positiven Werte sind an dem jeweiligen Index einsortiert.
         * Die Negativen Werte wurden absteigend an das Ende angefügt.
         * Folgendes ergibt sich aus der Art der Speicherung im Array:
         * Aus dem Kartenwert lässt sich der Index ablesen.
         * Die Indexe der negativen Karten lassen sich mit dem Modulo-Operator berechnen.
         * Die m1 am Anfang ist bedingt durch die Abweichung zwischen Stelle und Index.
         */

        kartenWert[0] = m1;
        kartenWert[1] = p1;
        kartenWert[2] = p2;
        kartenWert[3] = p3;
        kartenWert[4] = p4;
        kartenWert[5] = p5;
        kartenWert[6] = p6;
        kartenWert[7] = p7;
        kartenWert[8] = p8;
        kartenWert[9] = p9;
        kartenWert[10] = p10;
        kartenWert[11] = m5;
        kartenWert[12] = m4;
        kartenWert[13] = m3;
        kartenWert[14] = m2;
    }

    /**
     * Als Alternative gibt es auch den Konstruktor mit einer fertigen Liste als Parameter.
     * Hier muss darauf geachtet werden, dass die Formatierung eingehalten wird!
     */
    public CustomMapped(ArrayList<Integer> mapping) {
        kartenWert = new int[mapping.size()];

        for (int i = 0; i < mapping.size(); i++) {
            kartenWert[i] = mapping.get(i);
        }
    }


    @Override
    public int gibKarte(int naechsteKarte) {

        int ergebnis;

        /**
         * Hier wird der Index der jeweiligen Zahl berechnet.
         * Bei den positiven Zahlen ist Kartenwert gleich Index.
         * Bei den negativen Zahlen wird 16 aufaddiert, damit das Ergebnis immer positiv bleibt.
         * Dann lässt sich der Index mit dem Modulo berechnen.
         */

        if (naechsteKarte < 0) {
            ergebnis = kartenWert[(naechsteKarte + 16) % 15];
        } else {
            ergebnis = kartenWert[naechsteKarte];
        }

        return ergebnis;
    }

    @Override
    public void reset() {

    }
}
