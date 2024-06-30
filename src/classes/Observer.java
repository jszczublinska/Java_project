package youtube.classes;

/**
 * The Observer interface represents an object that can observe changes in a subject.
 * It defines a method for updating the observer when a change occurs in the subject.
 */

public interface Observer {
    
    /**
     * Updates the observer with information about the changed watchable item.
     *
     * @param watchable The watchable item associated with the update.
     */
    void update(Watchable watchable);
}
