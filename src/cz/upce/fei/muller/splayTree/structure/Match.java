package cz.upce.fei.muller.splayTree.structure;

import cz.upce.fei.common.core.AbstractStructureElement;

/**
 * The result of searching for a given node using
 * <code>findMatch</code>.
 * @author Vojtěch Müller
 */
public class Match<K extends Comparable<K>,T extends AbstractStructureElement & ISplayData<K>> {

        /**
         * Did the search find a matching node?
         */
        public boolean matchFound;

        /**
         * In case of a match this is the matching node, otherwise it
         * is the last accessed node (or the sentinel if the tree is
         * empty).
         */
        public SplayNode<K,T> node;

        /**
         * Is the thing searched for smaller than
         * <code>node.contents</code>? (This field is used to avoid an
         * extra comparison in <code>insert</code>.) Special case: If
         * <code>node</code> is the sentinel, then this field is set to
         * <code>true</code>.
         */
        public boolean smallerThanNode;

        /**
         * Creates a match. The <code>Node</code> must be non-null.
         */
        public Match(boolean matchFound, SplayNode<K,T> node, boolean smallerThanNode) {
            this.matchFound = matchFound;
            this.node = node;
            this.smallerThanNode = smallerThanNode;
        }
}
