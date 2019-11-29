package com.company.linking_tree;

import java.util.*;
import java.util.stream.Collectors;

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
 *
 * Currently deletion of levels and nodes is not supported
 *
 * //TODO check that this works for subtypes
 */
public class LinkingTree {

    private Map<Class, LevelMap> classLevelMap = new HashMap<>();
    private List<LevelMap> levels = new ArrayList<>();//not sure if this is needed
    private int levelsCount;//not sure if this is needed

    @SuppressWarnings("unchecked")
    public <K, V>  LinkingTree addLevel(Class<K> keyType, Class<V> valueType) {
        if (classLevelMap.get(valueType) != null) {
            throw new IllegalArgumentException("Multiple levels of the same type are not currently supported");
        }

        if (levelsCount > 0) {
            levels.get(levelsCount - 1).setChildType(keyType, valueType);
        }
        LevelMap<K, V> newLevel = new LevelMap<>(++levelsCount, keyType, valueType);
        classLevelMap.put(valueType, newLevel);
        levels.add(newLevel);

        return this;
    }

    /**
     * Add first level node
     * When key and value are known
     */
    public <K, V> LevelMap.Node<K, V> add(K key, V value) {
        LevelMap levelMap = classLevelMap.get(value.getClass());
        LevelMap.Node<K, V> node = new LevelMap.Node<>(key, value);
        //(LevelMap.Node<K, V>) levelMap.put(key, node);
        levelMap.put(key, node);
        return node;
    }

    /**
     * Add first level node
     * When only key is known
     */
    public <K, V> LevelMap.Node<K, V> add(K key, Class<V> valueType) {
        LevelMap levelMap = classLevelMap.get(valueType);
        LevelMap.Node<K, V> node = new LevelMap.Node<>(key, null);
        //(LevelMap.Node<K, V>) levelMap.put(key, node);
        levelMap.put(key, node);
        return node;
    }

    /**
     * Add parent-child reference
     */
    public <K, V, CK, CV> LevelMap.Node<CK, CV> add(K parentKey, Class<V> parentValueType, CK childKey, Class<CV> childValueType) {
        LevelMap levelMap = classLevelMap.get(parentValueType);
        if (levelMap == null) {
            throw new IllegalArgumentException(String.format("Level for type %s not found", parentValueType));
        }

        LevelMap.Node<K, V> parentNode = (LevelMap.Node<K, V>) levelMap.get(parentKey);
        if (parentNode == null) {
            throw new IllegalArgumentException(String.format("Parent node %s of type %s not found", parentKey, parentValueType));
        }

        //TODO check if child is already linked to the parent
        LevelMap.Node<CK, CV> childNode = add(childKey, childValueType);
        parentNode.children.add(childNode);

        return childNode;
    }

    /**
     * Add child along with parent-child reference
     * TODO can be rewritten as add(parentKey, parentValueType, childKey, (Class<CV>)childValue.getClass()).value = childValue;
     */
    public <K, V, CK, CV> LevelMap.Node<CK, CV> add(K parentKey, Class<V> parentValueType, CK childKey, CV childValue) {
        LevelMap.Node<CK, CV> childNode = add(parentKey, parentValueType, childKey, (Class<CV>)childValue.getClass());
        childNode.value = childValue;
        return childNode;
    }

    /**
     * Add child along with parent-child reference
     */
    public <K, V, CK, CV> LevelMap.Node<CK, CV> add(K parentKey, V parentValue, CK childKey, CV childValue) {
        return add(parentKey, parentValue.getClass(), childKey, childValue);
    }

    public <K, V> LevelMap.Node<K, V> findNode(K key, Class<V> valueType) {
        LevelMap<K, V> levelMap = (LevelMap<K, V>) classLevelMap.get(valueType);
        if (levelMap == null) {
            throw new IllegalArgumentException(String.format("Level for type %s not found", valueType));
        }
        return levelMap.get(key);
    }

    public <K, V> Collection<LevelMap.Node<K, V>> findAllNodes(Class<V> valueType) {
        LevelMap<K, V> levelMap = (LevelMap<K, V>) classLevelMap.get(valueType);
        if (levelMap == null) {
            throw new IllegalArgumentException(String.format("Level for type %s not found", valueType));
        }
        return levelMap.values();
    }

    public <K, V> V find(K key, Class<V> valueType) {
        return findNode(key, valueType).value;
    }

    public <V> List<V> findAll(Class<V> valueType) {
        return findAllNodes(valueType).stream()
                .map(node -> node.value)
                .collect(Collectors.toList());
    }



    public static class LevelMap<K, V> extends HashMap<K, LevelMap.Node<K, V>>{
        int level;
        Class<K> keyType;
        Class<V> valueType;

        Class<?> childKeyType;
        Class<?> childValueType;

        public LevelMap(int level, Class<K> keyType, Class<V> valueType) {
            this.level = level;
            this.keyType = keyType;
            this.valueType = valueType;
        }

        public LevelMap setChildType(Class<?> childKeyType, Class<?> childValueType) {
            this.childKeyType = childKeyType;
            this.childValueType = childValueType;

            return this;
        }

        //TODO can this class not be static?
        public static class Node<K, V> {
            K key;
            V value;
            Set<Node> children = new HashSet<>();

            Node(K key, V value) {
                this.key = key;
                this.value = value;
            }

            @Override
            public String toString() {
                return "Node{" +
                        "key=" + key.toString() +
                        ", value=" + (value != null ? value.toString() : null) +
                        ", children=" + children +
                        '}';
            }
        }
    }



    public String print() {
        StringBuilder stringBuilder = new StringBuilder();
        classLevelMap.values().stream()
                .sorted(Comparator.comparingInt(lMap -> lMap.level))
                .forEach(levelMap -> {
                    stringBuilder.append(String.format("Level %d (%s, %s): %s",
                            levelMap.level,
                            levelMap.keyType.getSimpleName(),
                            levelMap.valueType.getSimpleName(),
                            levelMap.toString()));
                    stringBuilder.append(System.lineSeparator());
                });
        return stringBuilder.toString();
    }

    //TODO accept Entity::method reference instead of key
    //TODO method checking if there is a key without value
    //TODO method populating nodes without values using pair(key, value) or collection of values and keyExtractor method
    //TODO method linking Nodes/keys of one level with child keys/values
    //TODO public void find(Object... args)
    //TODO implement add child with/out value first, add parent later
}
