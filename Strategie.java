/**
 * Die abstrakte Klasse Strategie stellt eine abgespeckte Version eines Spielers dar.
 * Alle strategien müssen auch gibKarte() und reset() implementieren, aber müssen keinem Spiel zugeordnet sein um.
 * Das macht es einfach einem Spieler eine neue Strategie zu geben.
 * Wenn gibKarte() oder reset() des Spielers aufgerufen werden, kann er dies an jede der Strategien weitergeben.
 * Außerdem macht es die Strategie Objekte wesentlich kleiner als viele Spieler Objekte.
 * Und das ohne auf die Fähigkeiten eines Spielers zu verzichten, da mit einem Verweis auf einen Scanner alles abgefragt werden kann.
 * Auch werden so die für das Spiel relevanten Informationen lediglich im Scanner gespeichert und müssen nur dort bearbeitet werden.
 *
 * @author Till N.
 * @version 1.0
 */
public abstract class Strategie {
    public abstract int gibKarte(int letzteKarte);
    public abstract void reset();
}