package cz.upce.fei.muller.binaryHeap.core;

import cz.upce.fei.common.core.Controller;
import cz.upce.fei.common.core.UIControl;
import cz.upce.fei.muller.binaryHeap.graphics.BinaryHeapGraphics;
import cz.upce.fei.muller.treap.events.HuffmanTreeEventQueue;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

/**
 * Ridici trida pro animaci Huffmanova kodovaciho stromu.
 * @author Martin Šára
 */
public class BinaryHeapController extends Controller<BinaryHeapGraphics, HuffmanTreeEventQueue> {
    
    /**
//     * Huffmanuv kodovaci strom.
//     */
//    private final AnimatableHuffmanTree huffmanTree;
    
    
    
    public BinaryHeapController(Pane pane, ScrollPane scrollPane, UIControl uiControls) {
      //  this.queue = new HuffmanTreeEventQueue(this);
     //   this.graphics = new HuffmanTreeGraphics(pane, scrollPane, this);
        this.uiControls = uiControls;        




//        this.huffmanTree = new HuffmanTreeImpl();
//        this.huffmanTree.addEventListener(queue);
    }
    
    
    
    public void animateHuffmanCoding(String text) {
        reset();
//        String bits = huffmanTree.encode(text);
//        String decode = huffmanTree.decode(bits);
//        System.out.println(decode);
    }
    
        
        
    /**
     * {@inheritDoc }
     */
    @Override
    protected void processEvent() {  
//        HuffmanTreeEvent e = queue.poll();
//
//        //System.out.println(e.getType());
//
//        switch (e.getType()) {
//            case CREATE_LEAVES_STARTED:
//                graphics.createLeavesStarted();
//                break;
//
//            case CREATE_LEAF:
//                graphics.createLeaf(e.getId(), e.getCharacter(), e.getFrequency());
//                break;
//
//            case CREATE_LEAVES_FINISHED:
//                graphics.createLeavesFinished();
//                break;
//
//            case SORT:
//                graphics.sort();
//                break;
//
//            case MERGE:
//                graphics.merge(e.getId(), e.getLeftId(), e.getRightId());
//                break;
//
//            case SHOW_CODING_TABLE:
//                graphics.showCodingTable();
//                break;
//
//            case SHOW_WAY_TO_LEAF:
//                graphics.showWayToLeaf(e.getNodes());
//                break;
//
//            case ADD_CODE:
//                graphics.addCode(e.getId(), e.getCharacter(), e.getString());
//                break;
//
//            case HIDE_WAY_TO_LEAF:
//                graphics.hideWayToLeaf();
//                step();
//                break;
//
//            case ENCODE_START:
//                graphics.startEncoding(e.getString());
//                break;
//
//            case ENCODE_CHAR:
//                graphics.encodeChar(e.getCharacter(), e.getString(), e.getId());
//                break;
//
//            case DECODE_START:
//                graphics.startDecoding(e.getString());
//                break;
//
//            case DECODE_GO_LEFT:
//            case DECODE_GO_RIGHT:
//                graphics.decodeGo(e.getId());
//                break;
//
//            case DECODE_ADD_CHAR:
//                graphics.decodeAddCharacter(e.getCharacter());
//                break;
//
//            case DECODE_RESET:
//                graphics.decodeReset();
//                break;
//
//            case DECODE_FINISH:
//                graphics.setStatus("konec");
//                nextStep();
//                break;
//
//            case LOADING_PRESET_FINISHED:
//                uiControls.disableControls(false);
//                uiControls.disableStepping(true);
//                graphics.resetDuration();
//                if (progressDialog != null) progressDialog.close();
//                progressDialog = null;
//                break;
//        }
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
//            graphics.setMinDuration();
//            progressDialog = new ProgressDialog();
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
