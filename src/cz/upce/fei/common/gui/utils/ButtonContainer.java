package cz.upce.fei.common.gui.utils;

import javafx.scene.control.Button;

import java.security.InvalidKeyException;
import java.util.HashMap;

/**
 * @author Vojtěch Müller
 */
public class ButtonContainer {

    private final HashMap<String,Button> container = new HashMap<>();

    public boolean isButton(String key){
        return container.containsKey(key);
    }

    public void add(String key, Button button){
        container.put(key,button);
    }

    public Button get(String key) throws InvalidKeyException {
        if(!isButton(key))
            throw new InvalidKeyException();
        return container.get(key);
    }

}
