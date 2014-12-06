package cz.upce.fei.common.core;

import cz.commons.utils.GeneratorElementsNumbers;

/**
 * @author Vojtěch Müller
 */
public class AbstractStructureElement implements Comparable {

    private final Integer id = GeneratorElementsNumbers.getNextId();

    public Integer getId(){
        return id;
    }

    @Override
    public int compareTo(Object o){
        return this.compareTo(o);
    }
}
