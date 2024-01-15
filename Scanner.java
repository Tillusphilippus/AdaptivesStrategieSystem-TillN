import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Die Klasse Scanner ist dafür zuständig die beste Strategie für den Spieler auszuwählen.
 * Dazu speichert sie alle Informationen über das Spiel, den Gegner und den Spieler.
 * Am Ende jeder Runde wird der Gegner analysiert und die Strategie angepasst, wenn nötig.
 *
 * @author Till N.
 * @version 2.0
 */
public class Scanner {
    
    //spiel speichert die Referenz auf das Spiel des Scanners.
    public HolsDerGeier spiel;

    //spieler speichert die Referenz auf den Spieler des Scanners.
    private final AdaptiverBot spieler;

    //strategieCounterPicker und strategieRandomPicker ermöglichen es Gegenstrategien erstellen zu lassen.
    private final StrategieCounterPicker strategieCounterPicker = new StrategieCounterPicker();
    private final StrategieRandomPicker strategieRandomPicker = new StrategieRandomPicker();

    //strategieAnpassenTracker speichert die Zeit der Runden bis zum nächsten Strategiewechsel.
    private int strategieAnpassenTracker = 5;

    //mappedFound und alignedFound speichern die Information, ob in der gegnerischen Strategie ein Mapping oder ein Alignment gefunden wurden.
    private boolean mappedFound;
    private boolean alignedFound;

    //Die Handkarten und Geierkarten Listen speichern die Karten auf Hand und die ausgespielten Karten der Spielteilnehmer.
    private final ArrayList<Integer> handkartenGegner = new ArrayList<>();
    private final ArrayList<Integer> handkartenGegnerAusgespielt = new ArrayList<>();
    private final ArrayList<Integer> handkarten = new ArrayList<>();
    private final ArrayList<Integer> handkartenAusgespielt = new ArrayList<>();
    private final ArrayList<Integer> geierKartenRunde = new ArrayList<>();
    private final ArrayList<Integer> geierKartenRundeAusgespielt = new ArrayList<>();

    /**
     * Scanner(AdaptiverBot spieler, HolsDerGeier spiel). Im Konstruktor des Scanners müssen Referenzen auf den Spieler und das Spiel mitgegeben werden.
     */
    public Scanner(AdaptiverBot spieler, HolsDerGeier spiel) {
        this.spieler = spieler;
        this.spiel = spiel;
        handKartenZuruecksetzen();
    }

    /**
     * starteNeuenZug (int letzteGeierKarte) startet die Überprüfung des letzten Zuges.
     * 
     * @param letzteGeierKarte Die letzte ausgespielte Geierkarte.
     */
    public void starteNeuenZug (int letzteGeierKarte) {

        /* In der if-Bedingung wird überprüft ob der Spieler in der letzten Runde eine Karte gespielt hat. (Ausnahme ist die erste und Runde eines neuen Spiels.)
        *  Danach wird die zuletzt gespielte Karte den Handkarten entfernt und den ausgespielten Karten hinzugefügt.
        */
        if(spiel.letzterZug(spieler.getNummer()) != -99) {
            int nummer = spieler.getNummer();
            int letzteKarte = spiel.letzterZug(nummer);
            handkarten.remove((Integer) letzteKarte);
            handkartenAusgespielt.add(letzteKarte);
        }

        /* In der if-Bedingung wird überprüft ob der Gegner in der letzten Runde eine Karte gespielt hat. (Ausnahme ist die erste Runde eines neuen Spiels.)
        *  Danach wird die zuletzt gespielte Karte den Handkarten entfernt und den ausgespielten Karten hinzugefügt.
        */
        if(spiel.letzterZug(spieler.getNummerGegner()) != -99) {
            int nummerGegner = spieler.getNummerGegner();
            int letzteKarteGegner = spiel.letzterZug(nummerGegner);
            handkartenGegner.remove((Integer) letzteKarteGegner);
            handkartenGegnerAusgespielt.add(letzteKarteGegner);
        }

        /* 
        *  Zuletzt wird die gespielte Geierkarte den Geierkarten entfernt und den ausgespielten Geierkarten hinzugefügt.
        */
        geierKartenRunde.remove((Integer) letzteGeierKarte);
        geierKartenRundeAusgespielt.add(letzteGeierKarte);
    }

    /**
     * findeBesteStrategie() fragt die Strategie-Picker-Klassen, ob eine änderung der Strategie notwendig ist.
     * Zuerst wird abgefragt, ob in der Spielweise des Gegners ein Mapping entdeckt wurde und ggf. gekontert.
     * Danach wird abgefragt, ob in der Spielweise des Gegners ein Alignment entdeckt wurde und ggf. gekontert.
     * Im Anschluss wird abgefragt, ob die Strategie geändert werden sollte, um die Spielstrategie zu verschleiern und nicht berechenbar zu sein.
     * Sollte dem so sein, wird eine zufällige neue Strategie gewählt und ein neuer Timer für die nächste Anpassung gesetzt. (Zwischen 0 und 10 Runden)
     * Falls nicht, kehrt der Bot zur Ausgangsstrategie zurück und der Timer wird dekrementiert.
     */
    private void findeBesteStrategie() {
        if(mappedFound) {
            spieler.setCurrentStrategie(strategieCounterPicker.kontereGegenstrategieMapped());
        } else if(alignedFound) {
            spieler.setCurrentStrategie(strategieCounterPicker.kontereGegenstrategieAligned());
        } else if (strategieAnpassenTracker == 0) {
            spieler.setCurrentStrategie(strategieRandomPicker.getRandomStrategie());
            Random random = new Random();
            strategieAnpassenTracker = random.nextInt(11);
        } else {
            spieler.ausgangsStrategie();
            strategieAnpassenTracker--;
        }
    }

    /**
     * reset() startet die Vorbereitung auf die nächste Runde. 
     */
    public void reset() {

        //Es wird geprüft, ob in der letzten Runde Karten gespielt wurden.
        if(!handkartenGegnerAusgespielt.isEmpty()) {

            //Wenn Karten gespielt wurden, kann der strategieCounterPicker basierend auf diesen Karten auf Muster in der Spielweise des Gegners prüfen.
            mappedFound = strategieCounterPicker.sucheMappedStrategie(geierKartenRundeAusgespielt, handkartenGegnerAusgespielt);
            alignedFound = strategieCounterPicker.sucheAlignedStrategie(handkartenGegnerAusgespielt);

            //Danach wird geschaut, ob eine neue Strategie für die nächste Runde sinnvoll ist.
            findeBesteStrategie();
        }

        //Am Ende werde noch alle Kartenstapel zurückgesetzt.
        handKartenZuruecksetzen();
        geierKartenZuruecksetzen();
    }

    /**
     * handKartenZuruecksetzen() setzte alle Handkartenstapel zurück und füllt sie wieder von 1 bis 15 auf.
     */
    private void handKartenZuruecksetzen() {
        handkarten.clear();
        handkartenAusgespielt.clear();
        handkartenGegner.clear();
        handkartenGegnerAusgespielt.clear();
        for (int i = 1; i <= 15; i++) {
            handkartenGegner.add(i);
            handkarten.add(i);
        }
    }

    /**
     * geierKartenZuruecksetzen() setzte alle Stapel mit Geierkarten zurück und füllt sie wieder von -5 bis 10 auf.
     */
    private void geierKartenZuruecksetzen () {
        geierKartenRunde.clear();
        geierKartenRundeAusgespielt.clear();
        for (int i = -5; i <= 10; i++) {
            if(i != 0) {
                geierKartenRunde.add(i);
            }
        }
    }

    /**
     * geierKartenZuruecksetzen() berechnet die höchste Karte auf der Hand des Gegners.
     * 
     * @return int höchste Karte Gegner
     */
    public int berechneHoechsteGegnerKarte() {
        return Collections.max(handkartenGegner);
    }

    /**
     * berechneNiedrigsteGegnerKarte() berechnet die niedrigste Karte auf der Hand des Gegners.
     * 
     * @return int niedrigste Karte Gegner
     */
    public int berechneNiedrigsteGegnerKarte() {
        return Collections.min(handkartenGegner);
    }

    /**
     * berechneLetzteGegnerKarte() berechnet letzte Karte des Gegners.
     * 
     * @return int letzte Karte Gegner
     */
    public int berechneLetzteGegnerKarte() {
        if(!handkartenGegnerAusgespielt.isEmpty()) {
            return handkartenGegnerAusgespielt.getLast();
        }
        return 99;
    }

    /**
     * berechneLetzteGeierKarte() berechnet letzte Geierkarte.
     * 
     * @return int letzte Geierkarte
     */    
    public int berechneLetzteGeierKarte() {
        if(!geierKartenRundeAusgespielt.isEmpty()) {
            return geierKartenRundeAusgespielt.getLast();
        }
        return 97;
    }

    /**
     * berechneLetzteKarte() berechnet letzte Karte des Spielers.
     * 
     * @return int letzte Karte
     */
    public int berechneLetzteKarte() {
        if(!handkartenAusgespielt.isEmpty()) {
            return handkartenAusgespielt.getLast();
        }
        return 98;
    }

    /**
     * berechneHoechsteKarte() berechnet höchste Karte des Spielers.
     * 
     * @return int höchste Karte
     */
    public int berechneHoechsteKarte() {
        return Collections.max(handkarten);
    }

    /**
     * berechneNiedrigsteKarte() berechnet niedrigste Karte des Spielers.
     * 
     * @return int niedrigste Karte
     */
    public int berechneNiedrigsteKarte() {
        return Collections.min(handkarten);
    }
    
    /**
     * getHandkartenGegner() gibt die Handkarten des Gegners wieder.
     * 
     * @return ArrayList<Integer> Handkarten Gegner
     */
    public ArrayList<Integer> getHandkartenGegner() {
        return handkartenGegner;
    }
    
    /**
     * removeHandkartenGegner(int ausgespielteKarte) entfernt eine Karte von den Handkarten des Gegners.
     * 
     * @param ausgespielteKarte Die zu entfernende Karte.
     */
    public void removeHandkartenGegner(int ausgespielteKarte) {
        this.handkartenGegner.remove(ausgespielteKarte);
    }

    /**
     * getHandkartenGegnerAusgespielt() gibt die ausgespielten Karten des Gegners zurück.
     * 
     * @return ArrayList<Integer> ausgespielte Karten des Gegners
     */
    public ArrayList<Integer> getHandkartenGegnerAusgespielt() {
        return handkartenGegnerAusgespielt;
    }

    /**
     * addHandkartenAusgespielt(int ausgespielteKarte) fügt den ausgelegten Karten des Gegners eine Karte hinzu.
     * 
     * @param ausgespielteKarte Die ausgelegte Karte des Gegners.
     */
    public void addHandkartenGegnerAusgespielt(int ausgespielteKarte) {
        this.handkartenGegnerAusgespielt.add(ausgespielteKarte);
    }

    /**
     * getHandkarten() gibt die Handkarten des Spielers wieder.
     * 
     * @return ArrayList<Integer> Handkarten
     */
    public ArrayList<Integer> getHandkarten() {
        return handkarten;
    }

    /**
     * removeHandkarten(int ausgespielteKarte) entfernt eine Karte von den Handkarten.
     * 
     * @param ausgespielteKarte Die zu entfernende Karte.
     */
    public void removeHandkarten(int ausgespielteKarte) {
        this.handkarten.remove(ausgespielteKarte);
    }

    /**
     * getHandkartenAusgespielt() gibt die ausgespielten Karten des Spielers zurück.
     * 
     * @return ArrayList<Integer> ausgespielte Handkarten
     */
    public ArrayList<Integer> getHandkartenAusgespielt() {
        return handkartenAusgespielt;
    }

    /**
     * addHandkartenAusgespielt(int ausgespielteKarte) fügt den ausgelegten Karten eine Karte hinzu.
     * 
     * @param ausgespielteKarte Die ausgelegte Karte.
     */
    public void addHandkartenAusgespielt(int ausgespielteKarte) {
        this.handkartenAusgespielt.add(ausgespielteKarte);
    }

    /**
     * getGeierKartenRunde() gibt die Geierkarten, die noch offen sind, wieder.
     * 
     * @return ArrayList<Integer> Geierkarten
     */
    public ArrayList<Integer> getGeierKartenRunde() {
        return geierKartenRunde;
    }

    /**
     * removeGeierKartenRunde(int ausgespielteKarte) entfernt eine Geierkarte.
     * 
     * @param ausgespielteKarte Die zu entfernende Geierkarte.
     */
    public void removeGeierKartenRunde(int ausgespielteKarte) {
        this.geierKartenRunde.remove(ausgespielteKarte);
    }

    /**
     * getGeierKartenRundeAusgespielt() gibt die ausgespielten Geierkarten wieder.
     * 
     * @return ArrayList<Integer> ausgespielte Geierkarten
     */
    public ArrayList<Integer> getGeierKartenRundeAusgespielt() {
        return geierKartenRundeAusgespielt;
    }
    
    /**
     * addGeierKartenRundeAusgespielt(int ausgespielteKarte) fügt den ausgelegten Geierkarten eine Karte hinzu.
     * 
     * @param ausgespielteKarte Die ausgelegte Geierkarte.
     */
    public void addGeierKartenRundeAusgespielt(int ausgespielteKarte) {
        this.geierKartenRundeAusgespielt.add(ausgespielteKarte);
    }
}
