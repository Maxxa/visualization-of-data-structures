package cz.upce.fei.muller.trie.manager;

import com.google.common.eventbus.EventBus;
import cz.upce.fei.muller.trie.graphics.ITrieNodesSetting;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vojtěch Müller
 */
public class BlockRowManagerTmp {

    private Map<Character, Point2DProperty> pointsCharaters = new HashMap<>();
    private Character[] listBlock = new Character[TrieUtils.LOWER_CASE_END - TrieUtils.LOWER_CASE_BEGIN];

    private final EventBus eventBus;

    private final LayoutManagerSetting setting;
    private final Double yPosition;

    public BlockRowManagerTmp(EventBus eventBus, Double row, LayoutManagerSetting setting) {
        this.eventBus = eventBus;
        this.yPosition = setting.getPaddingTop()+row*setting.getVerticalSpace()+row*setting.getNodeHeight();
        this.setting = setting;
    }

    public void addToBlock(Character block,double xParentPosition){
        int index = TrieUtils.getCharacterPositionAtArray(block);
        if(pointsCharaters.isEmpty()){
            insert(block,index,xParentPosition);
        }else{
            double diffX;
            if(listBlock[index]==null){
                insert(block,index,calculatePosition(index));
                diffX = ITrieNodesSetting.KEY_WIDTH/2+2*setting.getMinNodeWidth();
            }else{
                diffX = ITrieNodesSetting.KEY_WIDTH/2;
            }
            moving(0,index,-diffX);
            moving(index,listBlock.length,diffX);
        }
    }

    private double calculatePosition(int characterPositionAtArray) {
        return 0;
    }

    private void insert(Character block,int index,double position) {
        listBlock[index]=block;
        pointsCharaters.put(block,new Point2DProperty(position,yPosition));
    }


    public void removeCharachter(Character block,Character inserted){

    }

    public void moving(int from,int to,double xPoint){
        for (int i = from; i <= to; i++) {
            Character character = listBlock[i];
            if(character!=null && pointsCharaters.containsKey(character)){
                Point2DProperty p = pointsCharaters.get(character);
                p.setX(p.getX() + xPoint);
                //TODO event
            }
        }
    }





}
