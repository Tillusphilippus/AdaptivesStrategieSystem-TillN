import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Die Defensive Strategie beschreibt Spieler, die zu Spielbeginn eher niedrige Karten spielen.
 * Im Laufe der Partie steigen dann die Kartenwerte.
 * Somit wird das frühe angreifen vermieden und eher dann gespielt, wenn andere Spieler schon hohe karten ausgespielt haben.
 * Es ist also eine abwartende Strategie.
 *
 * @author Till N.
 * @version 0.1
 */

public class Defensive extends Strategie {

    /**
     * kartenReihenfolge speichert die Reihenfolge der Karten ab, in der sie ausgespielt werden sollen.
     * Um die Taktik etwas zu verschleiern und auch auf hohe Karten besser antworten zu können gibt es die option anKarteAnpassen.
     * Wenn beim Erstellen eines Defensive Objects im Konstruktor true mitgegeben wird, greift eine andere Funktion zur Auswahl der gespielten Karte.
     */

    private final List<Integer> kartenReihenfolge = new ArrayList<>();
    private final boolean anKarteAnpassen;

    /**
     * @param anKarteAnpassen Die Karten passen sich an die Höhe der Karte auf dem Feld an. Das bedeutet, wenn sehr hohe Karten gespielt werden kann es trotzdem sein, dass hohe karten früher gespielt werden.
     */

    public Defensive(boolean anKarteAnpassen){
        this.anKarteAnpassen = anKarteAnpassen;
        mischeKarten();
    }

    /**
     * mischeKarten() fügt der Liste mit der Kartenreihenfolge alle Karten von klein nach groß sortiert hinzu.
     */

    private void mischeKarten(){
            kartenReihenfolge.clear();
            for(int i=1; i<=15; i++) {
                kartenReihenfolge.add(i);
            }
    }

    /**
     * gibDefensiveKarte() ist der Algorithmus für die einfache Variante der Defensiven Spielweise.
     * Es wird lediglich immer die erste, also niedrigste Karte auf der Hand gelegt.
     */

    private int gibDefensiveKarte() {
        return kartenReihenfolge.removeFirst();
    }

    /**
     * gibAngepassteDefensiveKarte() ist der Algorithmus für die erweiterte Variante der Defensiven Spielweise.
     * Es wird eine zufällige Zahl zwischen 0 und 1 erstellt.
     * Hat die aufgedeckte Karte einen hohen Wert, also über 5 wird eine zufällige Karte gelegt.
     * Das soll dazu führen, dass um hohe Kartenwerte auch mit einer hohen Zahl gespielt werden kann.
     * Ist es keine hohe Zahl, wird eine der drei niedrigsten Karten gespielt.
     * Dadurch ist der Algorithmus nicht mehr so einfach zu erkennen.
     */

    private int gibAngepassteDefensiveKarte(int letzteKarte) {

        Random random = new Random();
        int index;

        if(letzteKarte > 5) {
            index = random.nextInt(kartenReihenfolge.size());
        } else {
            index = random.nextInt(Math.min(3, kartenReihenfolge.size()));
        }

        return kartenReihenfolge.remove(index);
    }

    /**
     * gibKarte() wählt abhängig von der ausgewählten Spielweise die entsprechende Methode aus.
     * Danach gibt es die jeweils ausgewählte Karte zurück.
     */

    @Override
    public int gibKarte(int letzteKarte) {
        int karte;
        if(anKarteAnpassen) {
            karte = gibAngepassteDefensiveKarte(letzteKarte);
        } else {
            karte = gibDefensiveKarte();
        }
        return karte;
    }

    /**
     * reset() sorgt dafür, dass am Ende der Runde die Liste wieder neu aufgefüllt wird.
     */

    @Override
    public void reset() {
        kartenReihenfolge.clear();
        mischeKarten();
    }
}
