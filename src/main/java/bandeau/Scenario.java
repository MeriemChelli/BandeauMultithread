package bandeau;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.LinkedList;

/**
 * Classe utilitaire pour représenter la classe-association UML
 */
class ScenarioElement {

    Effect effect;
    int repeats;

    ScenarioElement(Effect e, int r) {
        effect = e;
        repeats = r;
    }
}

public class Scenario {

    private final List<ScenarioElement> myElements = new LinkedList<>();
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    /**
     * Ajouter un effect au scenario.
     *
     * @param e l'effet à ajouter
     * @param repeats le nombre de répétitions pour cet effet
     */
    public void addEffect(Effect e, int repeats) {
    	 readWriteLock.writeLock().lock();
         try {
             Thread.sleep(500);
         } catch (InterruptedException ex) {
             Thread.currentThread().interrupt();
         }
    	
        myElements.add(new ScenarioElement(e, repeats));
        readWriteLock.writeLock().unlock();
    }

    /**
     * Jouer ce scenario sur un bandeau
     *
     * @param b le bandeau ou s'afficher.
     */
    public void playOn(Bandeau b) {
        for (ScenarioElement element : myElements) {
            for (int repeats = 0; repeats < element.repeats; repeats++) {
                element.effect.playOn(b);
            }
        }
    }
}
