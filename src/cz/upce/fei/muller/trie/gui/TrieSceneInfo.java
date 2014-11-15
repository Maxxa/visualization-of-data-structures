package cz.upce.fei.muller.trie.gui;

import cz.upce.fei.common.gui.utils.SceneInfo;

/**
 * @author Vojtěch Müller
 */
public class TrieSceneInfo implements SceneInfo {

    @Override
    public double getWidth() {
        return 600;
    }

    @Override
    public double getHeight() {
        return 930;
    }

    @Override
    public String getTitle() {
        return "Znakový strom";
    }

    @Override
    public Integer getMinSceneWith() {
        return 200;
    }
}
