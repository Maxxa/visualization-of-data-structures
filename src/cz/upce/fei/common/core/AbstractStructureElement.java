package cz.upce.fei.common.core;

import cz.commons.utils.GeneratorElementsNumbers;

/**
 * @author Vojtěch Müller
 */
public class AbstractStructureElement {

    private final Integer id = GeneratorElementsNumbers.getNextId();

    public Integer getId(){
        return id;
    }

}
