package cz.upce.fei.muller.treap;

import com.google.common.eventbus.EventBus;
import cz.commons.layoutManager.helpers.ITreeStructure;
import cz.upce.fei.muller.treap.structure.Treap;
import cz.upce.fei.muller.treap.structure.TreapNodeImpl;

import java.util.ArrayList;

/**
 * @author Vojtěch Müller
 */
public class TreapTestingRotationsMain {

    private static Treap<Integer, TreapNodeImpl> treap;


    public static void main(String[] args) {
        treap = new Treap<>(new EventBus());

        TreapPresets presets = new TreapPresets();
        ArrayList<TreapPresetItem> items = presets.getAll();
        TreapPresetItem treapPresetItem = items.get(0);

        for (int i = 0; i < treapPresetItem.getItems().length; i++) {
            TreapNodeImpl data = treapPresetItem.getItems()[i];
            treap.insert(data);
        }

        treap.find(4);
        treap.rotateRight();
        treap.printTree();
        System.out.println("\n ____________________________________________________ \n");

        for (ITreeStructure structure : treap.getFromActual()) {
            System.out.println(structure);
        }

    }
}
