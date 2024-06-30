package youtube.classes;

/**
 * The Watchable interface represents a content that can be watched by user
 * It defines methods for obtaining information about the item, such as its current view,
 * general information, version status, duration, and index.
 */

public interface Watchable {
    
    String printInfoCurrView();
    
    String printInfo();
    
    String version();
    
    int duration();
    
    String index();
    
}
