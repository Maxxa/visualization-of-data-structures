package cz.upce.fei.muller.trie.core;

import cz.upce.fei.common.core.Controller;
import cz.upce.fei.common.core.UIControl;
import cz.upce.fei.muller.treap.events.HuffmanTreeEventQueue;
import cz.upce.fei.muller.treap.graphics.TreapGraphics;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

/**
 * Ridici trida pro animaci Huffmanova kodovaciho stromu.
 * @author Martin Šára
 */
public class TrieController extends Controller<TreapGraphics, HuffmanTreeEventQueue> {
    

    public TrieController(Pane pane, ScrollPane scrollPane, UIControl uiControls) {
    }

    /**
     * {@inheritDoc }
     */
    @Override
    protected void processEvent() {  
        }

    
    
    /**
     * Zajistuje davkove nahravani predpripravenych sad dat.
     * @param text
     * @param animate 
     */
    public void loadPreset(String text, boolean animate) {
//        loadingPreset = true;
//        uiControls.info(text);
//
//        if (animate == false && autoNextStep == true) {
////            graphics.setMinDuration();
////            progressDialog = new ProgressDialog();
//            progressDialog.show();
//        }
//
//        String bits = huffmanTree.encode(text);
//        String decode = huffmanTree.decode(bits);
//        System.out.println(decode);
//
//        loadingPreset = false;
//        queue.add(new HuffmanTreeEvent(EventType.LOADING_PRESET_FINISHED));
//        if (animate == false && autoNextStep == true) progressDialog.setTotalEvents(queue.size());
//
//        step();
    }
    
    public void reset() {
//        graphics.reset();
//        queue.clear();
    }
    
    public void setPrefWidth(double width) {
//        graphics.setPrefWidth(width);
    }
    
    public void printHuffmanTree() {
//        System.out.println(huffmanTree);
    }
           
}
