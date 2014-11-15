package cz.upce.fei.common.events;

import cz.upce.fei.common.core.Controller;
import java.util.LinkedList;

/**
 * Rodicovska trida pri implementace front udalosti.
 * @author Martin Šára
 * @param <T> typ udalosti
 * @param <C> typ controlleru
 */
public abstract class EventQueue<T extends Event, C extends Controller> implements EventListener<T> {
    
    /**
     * Fronta.
     */
    protected LinkedList<T> queue = new LinkedList<>();
    /**
     * Reference na ridici tridu.
     * Umoznuje zahajit vyber udalosti.
     */
    protected C control;

    
    
    protected EventQueue(C control) {
        this.control = control;
    }

    
    
    /**
     * Prida udalost do fronty.
     * @param event udalost
     */
    public final void add(T event) {
        queue.addLast(event);
    }
    
    /**
     * Prida udalost na zacatek fronty.
     * @param event udalost
     */
    public final void addFirst(T event) {
        queue.addFirst(event);
    }
    
    /**
     * Odebere a vrati udalost ze zacatku fronty.
     * @return udalost
     */
    public final T poll() {
        return queue.poll();
    }
    
    /**
     * Zpristupni udalost na zacatku fronty.
     * @return udalost
     */
    public final T peek() {
        return queue.peek();
    }
    
    /**
     * Test prazdnosti fronty.
     * @return true pokud je fronta prazdna
     */
    public final boolean isEmpty() {
        return queue.isEmpty();
    }
    
    /**
     * Vrati pocet udalosti ve fronte.
     * @return pocet udalosti ve fronte
     */
    public final int size() {
        return queue.size();
    }
    
    /**
     * Vyprazdni frontu.
     */
    public final void clear() {
        queue.clear();
    }
    
}
