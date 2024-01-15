import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Die Klasse StrategieRandomPicker ist dafür zuständig zufällige Strategien zu erstellen.
 *
 * Die Version 2 des StrategieRandomPicker kann für jede Strategie ein zufälliges Strategieobjekt erstellen.
 * Außerdem kann sie zufällig eine zufällige Strategie erstellen.
 *
 * @author Till N.
 * @version 2.0
 */
public class StrategieRandomPicker {

    
    /**
     * getRandomLinearMappedStrategie() erstellt eine Linear Mapped Strategie, mit einem zufälligen Parameterwert.
     * 
     * @return Linear Mapped Strategie 
     */
    public Strategie getRandomLinearMappedStrategie() {
        Random random = new Random();
        int amount = random.nextInt(15)+1;
        return new LinearMapped(amount);
    }

    /**
     * getRandomCustomMappedStrategie() erstellt eine Custom Mapped Strategie, mit einem zufälligen Mapping.
     * 
     * @return Custom Mapped Strategie 
     */
    public Strategie getRandomCustomMappedStrategie() {
        ArrayList<Integer> random = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            random.add(i);
        }
        Collections.shuffle(random);
        return new CustomMapped(random);
    }

    /**
     * getRandomDefensiveStrategie() erstellt eine Defensive Strategie, mit einem zufälligen Parameter.
     * 
     * @return Defensive Strategie
     */
    public Strategie getRandomDefensiveStrategie() {
        Random random = new Random();
        boolean bool = random.nextBoolean();
        return new Defensive(bool);
    }

    /**
     * getRandomOffensiveStrategie() erstellt eine Offensive Strategie, mit einem zufälligen Parameter.
     * 
     * @return Offensive Strategie
     */
    public Strategie getRandomOffensiveStrategie() {
        Random random = new Random();
        boolean bool = random.nextBoolean();
        return new Offensive(bool);
    }

    /**
     * getRandomizedStrategie() erstellt eine Randomized Strategie.
     * 
     * @return Randomized Strategie
     */
    public Strategie getRandomizedStrategie() {
        return new Randomized();
    }

    /**
     * getRandomStrategie() wählt zufällig eine der Methoden zur Erstellung einer zufälligen Strategie.
     * 
     * @return Zufällige Strategie
     */
    public Strategie getRandomStrategie() {
        Random random = new Random();
        int strategie = random.nextInt(5);
        switch (strategie) {
            case 0:
                return getRandomLinearMappedStrategie();
            case 1:
                return getRandomCustomMappedStrategie();
            case 2:
                return getRandomDefensiveStrategie();
            case 3:
                return getRandomOffensiveStrategie();
            case 4:
                return getRandomizedStrategie();
            default:
                return getRandomStrategie();
        }
    }
}
