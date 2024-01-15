/**
 * Die AdaptiveStrategy ist eine selbst entwickelte Strategie, die sich immer bestmöglich an das Spiel anpassen soll.
 * Sie orientiert sich daran, wie ich ein Spiel spielen würde.
 * Mithilfe des Scanners kann sie jedoch den Spielverlauf analysieren und die Spielweise anpassen.
 *
 * @author Till N.
 * @version 2.0
 */

public class AdaptiveStrategy extends Strategie {
    
    //Es wird ein Scanner benötigt, um die aktuellen Karten im Spiel abfragen zu können.
    private final Scanner scanner;

    //Im Konstruktor der AdaptivenStrategie muss ein Scanner mitgegeben werden, damit dieser abgefragt werden kann.
    public AdaptiveStrategy(Scanner scanner){
        this.scanner = scanner;
        scanner.reset(); // Scanner wird resettet, damit neue Kartenstapel erstellt werden.
    }

    @Override
    public int gibKarte(int naechsteKarte) {

        // Zwischenspeicher für die Karte die ausgespielt wird.
        int auszuspielendeKarte;  

        // Die Karten aus dem letzten Zug werden vom Scanner abgefragt.
        int letzteKarte = scanner.berechneLetzteKarte();
        int letzteKarteGegner = scanner.berechneLetzteGegnerKarte();
        int letzteKarteGeier = scanner.berechneLetzteGeierKarte();

        // Wenn der letzte Zug unentschieden war, dann soll versucht werden die Punkte aus den beiden Runden zu bekommen, indem die höchste Karte gespielt wird.
        if( letzteKarte == letzteKarteGegner && letzteKarteGeier != 97) {
            auszuspielendeKarte = scanner.berechneHoechsteKarte();
            return auszuspielendeKarte;
        }

        // Um eine Einschätzung zu haben, welche Karte gut sein könnte kommt ein +5 Mapping zum Einsatz.
        auszuspielendeKarte = berechneEmpfohlenenKartenwert(naechsteKarte);

        // Danach wird geprüft, ob nicht auch eine niedrigere Karte gespielt werden kann, um trotzdem zu gewinnen.
        auszuspielendeKarte = pruefeObNiedrigereKarteMoeglichIst(auszuspielendeKarte);

        // Zuletzt wird noch geprüft, ob die Karte, die Empfohlen wurde, auch noch auf der Hand ist. Falls nicht, wird eine verfügbare Karte ausgewählt.
        auszuspielendeKarte = pruefeObKarteAufHand(auszuspielendeKarte);

        // Die Karte wird ausgespielt
        return auszuspielendeKarte;
    }

    /**
     * berechneEmpfohlenenKartenwert(int naechsteKarte) mapped die Karte zur Geierkarte +5.
     * 
     * @return empfohlene Karte
     */
    private int berechneEmpfohlenenKartenwert(int naechsteKarte) {
        if (naechsteKarte<0) {
            return naechsteKarte+6;
        } else {
            return naechsteKarte+5;
        }
    }

    /**
     * berechneEmpfohlenenKartenwert(int naechsteKarte) testet, ob eine niedrigere Karte denselben effekt hat.
     * Dazu wird geschaut ob der Gegner überhaupt eine höhere Karte auf der Hand hat.
     * Wenn nicht, wird nie niedrigste Karte gewählt, mit der trotzdem gewonnen wird.
     * 
     * @param karte zu überprüfende Karte
     * @return empfohlene Karte
     */
    private int pruefeObNiedrigereKarteMoeglichIst(int karte) {
        if(karte > scanner.berechneHoechsteGegnerKarte()+1) {
            karte = scanner.berechneHoechsteGegnerKarte()+1;
        }
        return karte;
    }

    /**
     * pruefeObKarteAufHand(int karte) testet, ob sich die ausgewählte Karte überhaupt auf der Hand befindet.
     * Wenn die Karte höher ist als die höchste Karte auf der Hand, wird die nächst niedrigere Karte genommen.
     * Wenn nicht, wird die niedrigste Karte genommen.
     * 
     * ! - Diese Methode ist nicht gut gelungen und müsste für eine aktualisierte Version überarbeitet werden.
     * 
     * @param karte zu überprüfende Karte
     * @return empfohlene Karte
     */
    private int pruefeObKarteAufHand(int karte) {
        if(!scanner.getHandkarten().contains(karte)) {
            if(karte > scanner.berechneHoechsteKarte()) {
                karte = scanner.berechneHoechsteKarte();
            } else {
                karte = scanner.berechneNiedrigsteKarte();
            }
        }

        return karte;
    }

    @Override
    public void reset() {

    }
}
