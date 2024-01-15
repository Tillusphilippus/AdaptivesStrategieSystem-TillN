import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Die Offensive Strategie beschreibt Spieler, die zu Spielbeginn eher hohe Karten spielen.
 * Im Laufe der Partie sinken dann die Kartenwerte.
 * Durch das frühe kämpfen um Karten werden schnell viele Punkte angesammelt.
 * Jedoch lässt die Stärke am Ende deutlich nach.
 * Sie stellt das Gegenstück zur Defensiven Strategie dar.
 *
 * @author Till N.
 * @version 1.0
 */

public class Offensive extends Strategie {

    /*
     * kartenReihenfolge speichert die Reihenfolge der Karten ab, in der sie ausgespielt werden sollen.
     * Um die Taktik etwas zu verschleiern und auch auf niedrige Karten besser antworten zu können gibt es die option anKarteAnpassen.
     * Wenn beim Erstellen eines Defensive Objects im Konstruktor true mitgegeben wird, greift eine andere Funktion zur Auswahl der gespielten Karte.
     */
    private final List<Integer> kartenReihenfolge = new ArrayList<>();

    // anKarteAnpassen sagt aus, ob die hohe der Geierkarte einen Einfluss auf die ausgespielte Karte haben soll.
    private final boolean anKarteAnpassen;

    /**
     * Im Konstruktor der Offensiven Strategie wird angegeben, ob die hohe der Geierkarte einen Einfluss auf die ausgespielte Karte haben soll.
     * 
     * @param anKarteAnpassen Die Karten passen sich an.
     */
    public Offensive(boolean anKarteAnpassen){
        this.anKarteAnpassen = anKarteAnpassen;
        mischeKarten();
    }

    /**
     * mischeKarten() fügt der Liste mit der Kartenreihenfolge alle Karten von groß nach klein sortiert hinzu.
     */
    private void mischeKarten(){
        kartenReihenfolge.clear();
        for(int i=15; i>=1; i--) {
            kartenReihenfolge.add(i);
        }
    }

    /**
     * gibOffensiveKarte() ist der Algorithmus für die einfache Variante der Offensiven Spielweise.
     * Es wird lediglich immer die letzte, also höchste Karte auf der Hand gelegt.
     * 
     * @return int Karte
     */
    private int gibOffensiveKarte() {
        return kartenReihenfolge.removeLast();
    }

    /**
     * gibAngepassteOffensiveKarte() ist der Algorithmus für die erweiterte Variante der Offensiven Spielweise.
     * Es wird eine zufällige Zahl zwischen 0 und 1 erstellt.
     * Hat die aufgedeckte Karte einen niedrigen Wert, also über unter 3 oder unter -3 wird eine zufällige Karte gelegt.
     * Das soll dazu führen, dass um niedrige Kartenwerte auch mit einer niedrigen Zahl gespielt werden kann.
     * Ist es keine niedrige Zahl, wird eine der drei höchsten Karten gespielt.
     * Dadurch ist der Algorithmus nicht mehr so einfach zu erkennen und damit auch zu kontern.
     */

    private int gibAngepassteOffensiveKarte(int letzteKarte) {
        Random random = new Random();
        int index;

        if((letzteKarte < 3 && letzteKarte > 0) || (letzteKarte > -3 && letzteKarte < 0)) {
            index = random.nextInt(kartenReihenfolge.size());
        } else {
            index = kartenReihenfolge.size() -1 - random.nextInt(Math.min(3, kartenReihenfolge.size()));
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
            karte = gibAngepassteOffensiveKarte(letzteKarte);
        } else {
            karte = gibOffensiveKarte();
        }

        return karte;
    }

    /**
     * reset() sorgt dafür, dass am Ende der Runde die Liste wieder neu aufgefüllt wird.
     */

    @Override
    public void reset() {
        mischeKarten();
    }
}
