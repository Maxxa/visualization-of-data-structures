package cz.upce.fei.muller.splayTree.core;

import cz.upce.fei.common.gui.FlashMessageViewer;
import cz.upce.fei.muller.splayTree.animations.FindPlacePreparation;
import cz.upce.fei.muller.splayTree.animations.FlashMessageViewerHelper;
import cz.upce.fei.muller.splayTree.animations.builders.BuilderShowFindElement;
import cz.upce.fei.muller.splayTree.graphics.SplayGraphicsNodeElement;
import cz.upce.fei.muller.splayTree.structure.SplayNodeImpl;

/**
 * @author Vojtěch Müller
 */
public class Finding extends AbstractEventPreparation {

    protected final Integer key;

    public Finding(Data data,Integer key) {
        super(data);
        this.key = key;
    }

    public void findEnd(SplayNodeImpl findNode, boolean isFind) {
        FindPlacePreparation preparation = searching.get(key);
        if(isFind){
            SplayGraphicsNodeElement node = data.manager.getElementInfo(findNode.getId()).getElement();
            preparation.addTransition(new BuilderShowFindElement(node).getAnimation());
        }else{
            FlashMessageViewer flashMessageViewer = FlashMessageViewerHelper.buildViewer("Zadaný klíč nenalezen.", data.flashMessagePosition, data.manager.getCanvas());
            preparation.addTransition(FlashMessageViewerHelper.showViewer(flashMessageViewer));
        }
        super.matchFind(key);
    }

    @Override
    public void matchFind(Object key) {
    }

    @Override
    protected void clear() {

    }
}
