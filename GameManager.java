public class GameManager {

    public static int punkte1 = 0;
    public static int punkte2 = 0;
    public static void main(String[] args) {
        GameBuilder gameBuilder = new GameBuilder();
        HolsDerGeier aktuellesSpiel = gameBuilder.aktuellesSpiel;
        int aktuelleRunde;
        try {
            gameBuilder.baueNeuesSpiel();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for(int i = 1; i <= gameBuilder.anzahlDerZuSpielendenRunden; i++){
            try {
                System.out.println("Gewinner: " + aktuellesSpiel.ganzesSpiel());
                System.out.println("Neuer Spielstand: " + punkte1 + ":" + punkte2);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }
}