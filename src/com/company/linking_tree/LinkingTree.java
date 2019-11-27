package com.company.linking_tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * l1Node = linkingTree.find(key, type)
 * l1Node.addChild(key2, type2)
 * List<Type2Nodes> l2Nodes = l1Node.getChildren()
 *
 * l2Node = linkingTree.find(key1, type1, key2, type2) or l1Node.find(key2, type2)
 *
 */
public class LinkingTree {

    private Node<Void, Void> root = new Node<>();

    private HashMap<Class, Map> levelMap = new HashMap<>();
    private List<Class> levelType = new ArrayList<>();


    public void addLevel(Class<?> keyType, Class<?> valueType) {
        levelMap.put(valueType, new HashMap<>());
        levelType.add(valueType);
    }

    public <K, V>void add(K key, V value) {
        Node<K, V> node = new Node<>(key, value);
        root.children.add(node);
    }

    public void find(Object... args) {

    }

    public static class Node<K, V> {
        Class<K> keyType;
        Class<V> valueType;
        K key;
        V value;

        List<Node> children = new ArrayList<>();

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        private Node() {
        }
    }
}
