import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Die Random Strategie spielt immer zufällige Karten aus.
 * Diese Strategie ist also nicht vorhersehbar, jedoch auch relativ leicht zu schlagen.
 *
 * @author Till N.
 * @version 1.0
 */
public class Randomized extends Strategie {

    //Liste der Karten auf der Hand
    private List<Integer> karten = new ArrayList<>();

    /**
     * Beim Aufrufen des Konstruktor wird die Liste karten mit 15 Karten aufgefüllt.
     */
    public Randomized(){
        kartenAuffuellen();
    }

    // Füllt karten mit allen Karten von 1 bis 15 auf.
    private void kartenAuffuellen(){
        for(int i=1; i<=15; i++) {
            karten.add(i);
        }
    }

    // Wählt zufällig eine der 15 Karten.
    @Override
    public int gibKarte(int letzteKarte) {
        Random random = new Random();
        int index = random.nextInt(karten.size());
        return karten.remove(index);
    }

    // Setzt die Strategie zurück. Die Karten werden sicherheitshalber geleert und neu aufgefüllt.
    @Override
    public void reset() {
        karten.clear();
        kartenAuffuellen();
    }
}
