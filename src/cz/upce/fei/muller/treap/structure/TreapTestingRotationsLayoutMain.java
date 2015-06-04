package cz.upce.fei.muller.treap.structure;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import cz.commons.layoutManager.BinaryTreeLayoutManager;
import cz.commons.layoutManager.MoveElementEvent;
import cz.commons.layoutManager.RepairmanLayoutManager;
import cz.commons.layoutManager.helpers.ITreeStructure;
import cz.upce.fei.common.events.RotationEvent;
import cz.upce.fei.muller.treap.TreapPresetItem;
import cz.upce.fei.muller.treap.TreapPresets;
import cz.upce.fei.muller.treap.graphics.TreapGraphicElement;
import cz.upce.fei.muller.treap.gui.TreeLayoutSettingBuilder;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vojtěch Müller
 */
public class TreapTestingRotationsLayoutMain {

    private static Treap<Integer, TreapNodeImpl> treap;

    private static EventBus bus = new EventBus();

    private static BinaryTreeLayoutManager manager;

    public static void main(String[] args) {
        treap = new Treap<>(bus);
        Pane pane = new Pane();
        pane.setMinWidth(1000);
        pane.setMaxWidth(1000);
        pane.setPrefWidth(1000);
        pane.setMinHeight(1000);
        pane.setMaxHeight(1000);
        pane.setPrefHeight(1000);
        manager = new BinaryTreeLayoutManager(TreeLayoutSettingBuilder.getSetting(), new Pane());
        TreapPresets presets = new TreapPresets();
        ArrayList<TreapPresetItem> items = presets.getAll();
        TreapPresetItem treapPresetItem = items.get(0);

        Map<Integer, TreapNodeImpl> nodes = new HashMap<>();
        for (int i = 0; i < treapPresetItem.getItems().length; i++) {
            TreapNodeImpl data = treapPresetItem.getItems()[i];
            treap.insert(data);
            nodes.put(data.getId(), data);
        }

        treap.printTree();

        treap.find(21);
        System.out.println("\n ____________________________________________________ \n");
        treap.setActualRoot();
        for (ITreeStructure structure : treap.getFromActual()) {
            TreapGraphicElement element = new TreapGraphicElement(nodes.get(structure.getId()), 0, 0);
            manager.addElement(element, structure.getIdParent(), structure.isLeftChild());
            System.out.println(structure.getIdParent());
            System.out.println(structure);
        }

        System.out.println("\n ____________________________________________________ \n");
        treap.printTree();
        manager.printDebug();

        System.out.println("\n ____________________________________________________ ");
        System.out.println(" ____________________________________________________ \n");

        bus.register(new TreapTestingRotationsLayoutMain());

        System.out.println("\n ____________________________________________________ ");
        System.out.println(" ____________________________________________________ \n");

        treap.printTree();
        manager.printDebug();

    }

    @Subscribe
    public void test(RotationEvent event) {
        System.out.println("test rotation event");
        try {
            RepairmanLayoutManager repairmanLayoutManager = new RepairmanLayoutManager(manager, event.getTreeRestructure());
            List<MoveElementEvent> reconstruction = repairmanLayoutManager.reconstruction();

            System.out.println("tt");

        } catch (Exception ex) {
            for (int i = 0; i < ex.getStackTrace().length; i++) {
                System.err.println(ex.getStackTrace()[i]);
            }
        }
    }

}
