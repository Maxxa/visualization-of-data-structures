package cz.upce.fei.muller.splayTree.core;

import cz.commons.animation.StepEventHandler;
import cz.commons.graphics.LineElement;
import cz.commons.graphics.NodePosition;
import cz.commons.layoutManager.BinaryTreeLayoutManager;
import cz.commons.layoutManager.RepairmanLayoutManager;
import cz.commons.layoutManager.WorkBinaryNodeInfo;
import cz.commons.layoutManager.WorkBinaryNodeInfoBuilder;
import cz.commons.layoutManager.helpers.ITreeStructure;
import cz.commons.utils.FadesTransitionBuilder;
import cz.upce.fei.common.animations.RemovePreparation;
import cz.upce.fei.common.gui.FlashMessageViewer;
import cz.upce.fei.muller.splayTree.animations.FlashMessageViewerHelper;
import cz.upce.fei.muller.splayTree.animations.builders.BuilderRemoveRoot;
import cz.upce.fei.muller.splayTree.graphics.SplayGraphicsNodeElement;
import cz.upce.fei.muller.splayTree.structure.UnificationSubTreeEvent;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.util.Duration;

/**
 * @author Vojtěch Müller
 */
public class Removing extends Finding {

    RemovePreparation removePreparation;

    public Removing(Data data, Integer key) {
        super(data,key);
    }

    @Override
    protected void clear() {
        removePreparation=null;
    }

    public void removeRoot(Integer idElement) {
        removePreparation = new RemovePreparation(idElement, (BinaryTreeLayoutManager) data.manager);
        WorkBinaryNodeInfo rootInfo = WorkBinaryNodeInfoBuilder.getWorkInfo(idElement,data.manager);
        BuilderRemoveRoot builderRemoveRoot = new BuilderRemoveRoot(rootInfo);
        data.insertTransition(builderRemoveRoot);
    }

    public void showFindingMax() {
        FlashMessageViewer flashMessageViewer = FlashMessageViewerHelper.buildViewer("Hledání maxima v levém podstromě.", data.flashMessagePosition, data.manager.getCanvas());
        data.animationControl.getTransitions().add(FlashMessageViewerHelper.showViewer(flashMessageViewer));
    }

    public void unificationSubTree(UnificationSubTreeEvent event) {
        ITreeStructure newStructure =event.getNewTreeStructure();

        if(newStructure!=null){
            RepairmanLayoutManager repairman = new RepairmanLayoutManager((BinaryTreeLayoutManager) data.manager, newStructure);
            data.animationControl.getTransitions().add(getElementMovings(repairman.reconstruction()));
        }else if(event.getNewRightConnect()!=null){
            SplayGraphicsNodeElement node = data.manager.getElementInfo(event.getNewRoot()).getElement();
            SplayGraphicsNodeElement right = data.manager.getElementInfo(event.getNewRightConnect()).getElement();

            SequentialTransition st = new SequentialTransition();
            st.getChildren().addAll(reconnect(node, right),fadeLine(node));
            data.animationControl.getTransitions().add(st);
        }

    }

    private Transition fadeLine(final SplayGraphicsNodeElement node) {
        return FadesTransitionBuilder.getTransition(node.getChildLine(NodePosition.RIGHT), Duration.seconds(1), 0, 1);
    }

    private ParallelTransition reconnect(final SplayGraphicsNodeElement node, final SplayGraphicsNodeElement right) {
        ParallelTransition pt = new ParallelTransition();
        pt.setDelay(Duration.ONE);
        final LineElement element = node.getChildLine(NodePosition.RIGHT);
        pt.setOnFinished(new StepEventHandler() {
            @Override
            protected void handleForward(ActionEvent actionEvent) {
                element.setEnd(right);
            }

            @Override
            protected void handleBack(ActionEvent actionEvent) {
                element.setEnd(node.getLeftChildConnector());
                element.setVisible(false);
                element.setOpacity(0);
            }
        });
        return pt;
    }
}
