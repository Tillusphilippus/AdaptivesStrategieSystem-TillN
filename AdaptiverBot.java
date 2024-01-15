import java.util.ArrayList;

/**
 * Die Klasse AdaptiverBot ist ein Bot, der einen Spieler simuliert.
 * Der AdaptiveBot ist mit einem Scanner ausgestattet.
 * Der Scanner übernimmt das "sehen" für den Bot und wählt für ihm passende Strategien aus.
 * Damit der Spieler seine Strategie dynamisch anpassen kann hat er keine eigenen Strategiemethoden.
 * Er besitzt lediglich ein Strategie Object, dass für ihn eine Karte aussucht.
 * Dies hat den Vorteil, dass der Bot sehr flexibel ist in seiner Kartenauswahl, und der Scanner für ihn die Strategie aussuchen kann.
 * Die Hauptaufgabe des AdaptivenBots ist die Weitergabe von Daten an den Scanner und seine Strategie.
 * Dies sorgt vor allem für Übersichtlichkeit und eine einfache Erweiterbarkeit des Bots.
 *
 * @author Till
 * @version 2.0
 */
public class AdaptiverBot extends HolsDerGeierSpieler{

    //Der Scanner überwacht das Spiel und passt die Strategie des Bots an.
    private final Scanner scanner = new Scanner(this, this.getHdg());

    //Die currentStrategie ist die aktuell ausgewählte Strategie des Bots. Standardmäßig ist dies die AdaptiveStrategie.
    private Strategie currentStrategie = new AdaptiveStrategy(scanner);

    //Die Liste ausgelagerteStrategien speichert die vergangenen Strategien des Bots. Dadurch kann er zu seiner Ursprungsstrategie zurückkehren, ohne ein neues Objekt erstellen zu müssen.
    private final ArrayList<Strategie> ausgelagerteStrategien = new ArrayList<>();

    /**
     * AdaptiverBot() ist der Standartkonstruktor. Das heißt, die Startstrategie ist die adaptive Strategie.
     */
    public AdaptiverBot() {
        setCurrentStrategie(new AdaptiveStrategy(scanner));
    }

    /**
     * AdaptiverBot(Strategie strategie) ermöglicht es die Startstrategie individuell zu setzen.
     */
    public AdaptiverBot(Strategie strategie) {
        setCurrentStrategie(strategie);
    }

    /**
     * reset() gibt den reset() befehl an den Scanner und die Strategie weiter.
     * Der Bot selbst muss nicht nach einer Runde resettet werden.
     * Es ist jedoch erforderlich, den Scanner und die Strategie zu resetten, damit diese die Spielzüge der letzten Runde erfassen können.
     */
    @Override
    public void reset() {
        scanner.reset();
        currentStrategie.reset();
    }

    /**
     * gibKarte(int naechsteKarte) ist die Hauptmethode des Bots, hier wird eine Karte zum ausgewählt.
     * Zuerst gibt der Bot alle relevanten Informationen an den Scanner weiter.
     * Danach lässt er seine aktuelle Strategie eine Karte auswählen.
     * 
     * @return int Karte
     */
    @Override
    public int gibKarte(int naechsteKarte) {
        scanner.spiel = super.getHdg();
        scanner.starteNeuenZug(naechsteKarte);
        return currentStrategie.gibKarte(naechsteKarte);
    }

    /**
     * setCurrentStrategie(Strategie currentStrategie) ändert die Strategie des Bots.
     * Die noch aktive Strategie wird in den Speicher gelegt.
     * Dann wird die aktuelle Strategie mit der neuen Strategie überschrieben.
     */
    public void setCurrentStrategie(Strategie currentStrategie) {
        ausgelagerteStrategien.add(this.currentStrategie);
        this.currentStrategie = currentStrategie;
    }

    /**
     * getNummerGegner() berechnet die Nummer des Gegnerischen spielers.
     * 
     * @return int Nummer des gegnerischen Spielers
     */
    public int getNummerGegner() {
        return (this.getNummer() == 1) ? 0 : 1;
    }

    /**
     * ausgangsStrategie() ändert die Strategie zur Ausgangsstrategie.
     */
    public void ausgangsStrategie() {
        this.currentStrategie = ausgelagerteStrategien.getFirst();
    }

    /**
     * letzteStrategie() ändert die Strategie zur letzten gespielten Strategie.
     */
    public void letzteStrategie() {
        this.currentStrategie = ausgelagerteStrategien.getLast();
    }
}