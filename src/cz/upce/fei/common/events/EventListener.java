package cz.upce.fei.common.events;

/**
 * Rozhrani pro obsluhu udalosti.
 * @author Martin Šára
 * @param <T> typ udalosti
 */
public interface EventListener<T> {
    
    /**
     * Obsluha udalosti.
     * @param event 
     */
    void handle(T event);
    
}
