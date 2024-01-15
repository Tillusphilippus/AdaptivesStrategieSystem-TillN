import java.util.ArrayList;

/**
 * Die Klasse StrategieCounterPicker ist dafür zuständig auf gegnerische Strategien zu prüfen und diese zu kontern.
 * Dazu wertet sie nach jeder Runde die Spielzüge des Gegners aus.
 * Außerdem stellt sie Methoden zur Verfügung, die effektive Konter für Strategien erstellen.
 * 
 * Die Version 1 des StrategieCounterPicker erkennt und kontert Mapped, sowie Aligned Strategien.
 * Dafür müssen mindestens zwei Runden mit dieser Strategie gespielt sein.
 *
 * @author Till N.
 * @version 1.0
 */
public class StrategieCounterPicker {

    //Zwischenspeicher für die Ergebnisse der letzten Runde
    private final ArrayList<Integer> mappingLastRound = new ArrayList<>();
    private final ArrayList<Integer> alignedLastRound = new ArrayList<>();

    /**
     * sucheAlignedStrategie(ArrayList<Integer> gegnerKartenLetztesSpiel) wertet die gegnerischen Karten so aus, dass Alignment erkannt werden kann.
     * 
     * @param gegnerKartenLetztesSpiel Die Karten, die der Gegner in der letzten Runde gespielt hat.
     * @return boolean Alignment erkannt.
     */
    public boolean sucheAlignedStrategie(ArrayList<Integer> gegnerKartenLetztesSpiel) {
        /*
        * Da in den gegnerischen Karten des letzten Spiels die Karte der letzten Runde fehlt, müssen wir die letzte Karte des Gegners berechnen.
        * Dies passiert, indem wie für jede Karte überprüfen, ob sie in den Karten enthalten ist.
        * Die fehlende Karte muss die Karte sein, die zuletzt ausgespielt wurde.
        */
        for (int i = 1; i <= 15; i++) {
            if (!gegnerKartenLetztesSpiel.contains(i)) {        // Prüfen, ob die Karte enthalten ist.
                gegnerKartenLetztesSpiel.add(i);                // Die fehlende Karte ergänzen.
                break;                                          // Die Schleife beenden, nachdem die fehlende Zahl gefunden wurde, um Ressourcen zu sparen.
            }
        }

        //Prüfen, ob die Alignments in dieser Runde und in der letzten Runde identisch sind.
        if(!alignedLastRound.isEmpty() && alignedLastRound.equals(gegnerKartenLetztesSpiel) ) {         // Wenn ja:
            alignedLastRound.clear();                                                                   //      Alignment zurücksetzen.
            alignedLastRound.addAll(gegnerKartenLetztesSpiel);                                          //      neues Alignment speichern.
            return true;                                                                                //      Zurückgeben, dass Alignment erkannt wurde.
        } else {                                                                                        // wenn nein:
            alignedLastRound.clear();                                                                   //      Alignment zurücksetzen.
            alignedLastRound.addAll(gegnerKartenLetztesSpiel);                                          //      Alignment für nächste Runde speichern.
            return false;                                                                               //      Zurückgeben, dass kein Alignment erkannt wurde.
        }
    }

    /**
     * sucheMappedStrategie(ArrayList<Integer> geierKartenLetztesSpiel, ArrayList<Integer> gegnerKartenLetztesSpiel) wertet die gegnerischen Karten so aus, dass Mapping erkannt werden kann.
     * 
     * @param gegnerKartenLetztesSpiel Die Karten, die der Gegner in der letzten Runde gespielt hat.
     * @return boolean Mapping erkannt.
     */
    public boolean sucheMappedStrategie(ArrayList<Integer> geierKartenLetztesSpiel, ArrayList<Integer> gegnerKartenLetztesSpiel) {
        
        /*
        * Da in den gegnerischen Karten des letzten Spiels die Karte der letzten Runde fehlt, müssen wir die letzte Karte des Gegners berechnen.
        * Dies passiert, indem wie für jede Karte überprüfen, ob sie in den Karten enthalten ist.
        * Die fehlende Karte muss die Karte sein, die zuletzt ausgespielt wurde.
        */
        for (int i = 1; i <= 15; i++) {
            if (!gegnerKartenLetztesSpiel.contains(i)) {                                               // Prüfen, ob die Karte enthalten ist.
                gegnerKartenLetztesSpiel.add(i);                                                       // Die fehlende Karte ergänzen.
                break;                                                                                 // Die Schleife beenden, nachdem die fehlende Zahl gefunden wurde
            }
        }
        
        //mapping speichert das ausgewertete Mapping ab.
        ArrayList<Integer> mapping = new ArrayList<>();

        //Es wird geschaut, welche Karte der Gegner auf die jeweilige Geierkarte gespielt hat und in einer festgelegten Struktur abgespeichert.
        mapping.add(gegnerKartenLetztesSpiel.get(geierKartenLetztesSpiel.indexOf(-1)));
        mapping.add(gegnerKartenLetztesSpiel.get(geierKartenLetztesSpiel.indexOf(1)));
        mapping.add(gegnerKartenLetztesSpiel.get(geierKartenLetztesSpiel.indexOf(2)));
        mapping.add(gegnerKartenLetztesSpiel.get(geierKartenLetztesSpiel.indexOf(3)));
        mapping.add(gegnerKartenLetztesSpiel.get(geierKartenLetztesSpiel.indexOf(4)));
        mapping.add(gegnerKartenLetztesSpiel.get(geierKartenLetztesSpiel.indexOf(5)));
        mapping.add(gegnerKartenLetztesSpiel.get(geierKartenLetztesSpiel.indexOf(6)));
        mapping.add(gegnerKartenLetztesSpiel.get(geierKartenLetztesSpiel.indexOf(7)));
        mapping.add(gegnerKartenLetztesSpiel.get(geierKartenLetztesSpiel.indexOf(8)));
        mapping.add(gegnerKartenLetztesSpiel.get(geierKartenLetztesSpiel.indexOf(9)));
        mapping.add(gegnerKartenLetztesSpiel.get(geierKartenLetztesSpiel.indexOf(10)));
        mapping.add(gegnerKartenLetztesSpiel.get(geierKartenLetztesSpiel.indexOf(-5)));
        mapping.add(gegnerKartenLetztesSpiel.get(geierKartenLetztesSpiel.indexOf(-4)));
        mapping.add(gegnerKartenLetztesSpiel.get(geierKartenLetztesSpiel.indexOf(-3)));
        mapping.add(gegnerKartenLetztesSpiel.get(geierKartenLetztesSpiel.indexOf(-2)));

        //Prüfen, ob die Mappings in dieser Runde und in der letzten Runde identisch sind.
        if(!mappingLastRound.isEmpty() && mappingLastRound.equals(mapping) ) {              // Wenn ja:
            mappingLastRound.clear();                                                       //      Mapping zurücksetzen.
            mappingLastRound.addAll(mapping);                                               //      neues Mapping speichern.
            return true;                                                                    //      Zurückgeben, dass Mapping erkannt wurde.
        } else {                                                                            // wenn nein:
            mappingLastRound.clear();                                                       //      Mapping zurücksetzen.
            mappingLastRound.addAll(mapping);                                               //      erkanntes Mapping abspeichern.
            return false;                                                                   //      Zurückgeben, dass kein Mapping erkannt wurde.
        }
    }

    /**
     * kontereGegenstrategieAligned() erstellt eine Gegenstrategie auf das gespeicherte Alignment.
     * 
     * @return Gegenstrategie 
     */
    public Strategie kontereGegenstrategieAligned() {
        ArrayList<Integer> alignedTemp = new ArrayList<>(alignedLastRound);
        return waehleGegenstrategieAligned(alignedTemp);
    }

    /**
     * kontereGegenstrategieMapped() erstellt eine Gegenstrategie auf das gespeicherte Mapping.
     * 
     * @return Gegenstrategie 
     */
    public Strategie kontereGegenstrategieMapped() {
        ArrayList<Integer> mappedTemp = new ArrayList<>(mappingLastRound);
        return waehleGegenstrategieMapped(mappedTemp);
    }

    /**
     * waehleGegenstrategieAligned(ArrayList<Integer> alignedTemp) erstellt eine Gegenstrategie auf das mitgegebene Alignment.
     * Diese Hilfsmethode ist notwendig, da das gespeicherte Alignment nicht bearbeitet werden soll.
     * Deshalb wird eine Kopie des Alignments an diese Methode übergeben.
     * 
     * @return Gegenstrategie 
     */
    private Strategie waehleGegenstrategieAligned(ArrayList<Integer> alignedTemp) {
        alignedTemp.set(alignedTemp.indexOf(15), 0);                    // Tauscht die 15 mit der 0, da die gegnerische 15-Karte nicht besiegt werden kann.
        alignedTemp.replaceAll(integer -> integer + 1);                 // Erhöht alle Werte um 1, um immer 1 höher zu spielen als der Gegner
        return new CustomMapped(alignedTemp);
    }

    /**
     * private Strategie waehleGegenstrategieMapped(ArrayList<Integer> mappingTemp) erstellt eine Gegenstrategie auf das mitgegebene Mapping.
     * Diese Hilfsmethode ist notwendig, da das gespeicherte Mapping nicht bearbeitet werden soll.
     * Deshalb wird eine Kopie des Mappings an diese Methode übergeben.
     * 
     * @return Gegenstrategie 
     */
    private Strategie waehleGegenstrategieMapped(ArrayList<Integer> mappingTemp) {
        mappingTemp.set(mappingTemp.indexOf(15), 0);                    // Tauscht die 15 mit der 0, da die gegnerische 15-Karte nicht besiegt werden kann.
        mappingTemp.replaceAll(integer -> integer + 1);                 // Erhöht alle Werte um 1, um immer 1 höher zu spielen als der Gegner
        return new CustomMapped(mappingTemp);
    }
}
