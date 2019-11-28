package com.company.linking_tree;

import java.util.*;

/**
 * l1Node = linkingTree.find(key, type)
 * l1Node.addChild(key2, type2)
 * List<Type2Nodes> l2Nodes = l1Node.getChildren()
 *
 * l2Node = linkingTree.find(key1, type1, key2, type2) or l1Node.find(key2, type2)
 *
 * Case 1: CobRequests with keys and values, but no Arrangement keys
 * Case 1.a: Only CobRequests keys
 * Case 1.b: CobRequests keys and Arrangement keys
 */
public class LinkingTree {

    private Node<Void, Void> root = new Node<>();
    private int levelsCount;

    private LinkedHashMap<Class, LevelMap> classLevelMap = new LinkedHashMap<>();
    private List<LevelMap> levels = new ArrayList<>();//not sure if this is needed


    public LinkingTree addLevel(Class<?> keyType, Class<?> valueType) {
        if (classLevelMap.get(valueType) != null) {
            throw new IllegalStateException("Multiple levels of the same type are not currently supported");
        }


        if (levelsCount > 0) {
            levels.get(levelsCount - 1).setChildType(keyType, valueType);
        }
        LevelMap newLevel = new LevelMap<>(++levelsCount, keyType, valueType);
        classLevelMap.put(valueType, newLevel);
        levels.add(newLevel);

        return this;
    }

    public <K, V> void add(K key, V value) {
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

    public static class LevelMap<K, V> extends HashMap<K, V>{
        int level;
        Class<?> keyType;
        Class<?> valueType;

        Class<?> childKeyType;
        Class<?> childValueType;

        public LevelMap(int level, Class<?> keyType, Class<?> valueType) {
            this.level = level;
            this.keyType = keyType;
            this.valueType = valueType;
        }

        public LevelMap setChildType(Class<?> childKeyType, Class<?> childValueType) {
            this.childKeyType = childKeyType;
            this.childValueType = childValueType;

            return this;
        }
    }



    public String print() {
        StringBuilder stringBuilder = new StringBuilder();
        classLevelMap.values().stream()
                .sorted(Comparator.comparingInt(lMap -> lMap.level))
                .forEach(levelMap -> {
                    stringBuilder.append(String.format("Level %d (%s, %s): %s",
                            levelMap.level, levelMap.keyType.getSimpleName(), levelMap.valueType.getSimpleName(), levelMap.toString()));
                    stringBuilder.append(System.lineSeparator());
                });
        return stringBuilder.toString();
    }

    //TODO accept Entity::method reference instead of key
    //TODO method checking if there is a key without value
    //TODO method populating nodes without values using pair(key, value) or collection of values and keyExtractor method
    //TODO method linking Nodes/keys of one level with child keys/values
}
