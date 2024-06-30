package youtube.classes;

/**
 * The Subject interface represents an object that can be observed by observers.
 * It defines methods for registering, removing, and notifying observers when a change occurs.
 */

public interface Subject {
    
    /**
     * Registers an observer to receive updates from this subject.
     *
     * @param observer The observer to be registered.
     */
    void registerObserver(Observer observer);
        
    
    /**
     * Notifies all registered observers about a change in the subject.
     *
     * @param watchable The watchable item associated with the update.
     */
    void notifyObservers(Watchable watchable);
}
