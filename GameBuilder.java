import java.util.Scanner;

/**
 * Die Klasse GameBuilder ist zuständig für die Erstellung eines Spiels.
 *
 * @author Till N.
 * @version 0.1
 */

public class GameBuilder {

    HolsDerGeier aktuellesSpiel = new HolsDerGeier();
    private HolsDerGeierSpieler[] spieler = new HolsDerGeierSpieler[2];
    int anzahlDerZuSpielendenRunden;
    int aktuelleRunde;
    Scanner scanner = new Scanner(System.in);


    public void baueNeuesSpiel() throws Exception {
        spielerErstellen();
        rundenAnzahlAbfragen();
        aktuellesSpiel.neueSpieler(spieler[0], spieler[1]);
    }

    public HolsDerGeierSpieler spielertypAbfragen(int spielerNummer) {
        String aufforderung = "Typ des " + spielerNummer + ". Spielers:\n"
                + "1 = Mensch als Spieler \n"
                + "2 = Geier \n"
                + "3 = Intelligenterer Geier \n"
                + "4 = Random \n"
                + "5 = AdaptiverBot mit Strategie\n"
                + "6 = AdaptiverBot Standart\n";

        System.out.println(aufforderung);

        String spielerTypAlsString;
        HolsDerGeierSpieler spieler;

        while (true) {
            spielerTypAlsString = scanner.next();

            switch (spielerTypAlsString) {
                case "1":
                    spieler = new Mensch();
                    break;
                case "2":
                    spieler = new Geier();
                    break;
                case "3":
                    spieler = new IntelligentererGeier();
                    break;
                case "4":
                    spieler = new Random();
                    break;
                case "5":
                    spieler = new AdaptiverBot(new LinearMapped(5));
                    break;
                case "6":
                    spieler = new AdaptiverBot();
                    break;
                default:
                    System.out.println("Die Eingabe ist nicht gültig! Bitte wählen Sie erneut.");
                    continue;
            }

            return spieler;
        }
    }

    private void spielerErstellen() {
        for (int i = 1; i<=2; i++) {
                spieler[i-1] = spielertypAbfragen(i);
        }
    }

    private void rundenAnzahlAbfragen() {
        System.out.println("Wie viele Runden sollen gespielt werden?");
        anzahlDerZuSpielendenRunden = scanner.nextInt();
    }
}
