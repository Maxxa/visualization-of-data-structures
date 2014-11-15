package cz.upce.fei.common.events;

/**
 * Rozhrani pro od/registraci posluchacu a oznamovani udalosti.
 * @author Martin Šára
 * @param <T> typ udalosti
 */
public interface EventTarget<T> {
    
    /**
     * Registrace posluchace.
     * @param listener 
     */
    void addEventListener(EventListener<T> listener);
    
    /**
     * Zruseni registrace posluchace.
     * @param listener 
     */
    void removeEventListener(EventListener<T> listener);
    
    /**
     * Oznameni udalosti posluchacum.
     * @param event 
     */
    void notifyListeners(T event);
    
}
