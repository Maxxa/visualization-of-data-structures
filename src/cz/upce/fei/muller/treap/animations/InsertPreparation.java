package cz.upce.fei.muller.treap.animations;

import cz.upce.fei.common.core.IAnimationBuilder;
import cz.upce.fei.common.core.IPreparation;

//import cz.upce.fei.muller.treap.events.InsertNodeEvent;

/**
 * @author Vojtěch Müller
 */
public class InsertPreparation implements IPreparation {
    @Override
    public IAnimationBuilder getBuilder() {
        return null;
    }

//    private final WorkBinaryNodeInfo currentInformation;
////    private final InsertNodeEvent event;
//    private final BinaryTreeLayoutManager manager;
//    private final Point2D creatingPoint;
//    private final boolean isFindingPlace;
//
//    public InsertPreparation(Object event, BinaryTreeLayoutManager manager, Point2D creatingPoint,boolean isFindingPlace) {
////        this.event = event;
//        this.manager = manager;
//        this.creatingPoint = creatingPoint;
//        this.isFindingPlace = isFindingPlace;
////        this.currentInformation = WorkBinaryNodeInfoBuilder.getWorkInfo(event.getNewNode().getId(), manager);
//        preparation();
//    }
//
//    private void preparation() {
//        TreapGraphicElement parent = currentInformation.getParent().getElement();
////        LineElement element = parent.getChildLine(event.isLeftChild() ? NodePosition.LEFT : NodePosition.RIGHT);
////        element.setOpacity(0);/
//        TreapGraphicElement current = currentInformation.get().getElement();
//        current.setOpacity(0);
//    }
//
//    @Override
//    public IAnimationBuilder getBuilder() {
//        return null;
////                !isFindingPlace?
////                        new BuilderAddElement(manager.getNodePosition(event.getNewNode().getId()), creatingPoint, currentInformation, event.isLeftChild()) :
////                        new BuilderMoveToChild(
////                                manager.getNodePosition(event.getNewNode().getId()),
////                                creatingPoint,
////                                currentInformation,
////                                event.isLeftChild());
//
//    }
}
