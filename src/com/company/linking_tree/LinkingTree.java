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
    //TODO in general should be a Tree
    private List<LevelMap> levels = new ArrayList<>();
    private int levelsCount;//not sure if this is needed

    //@SuppressWarnings("unchecked")
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
        levelMap.put(key, node);
        return node;
    }

    /**
     * Add parent-child reference
     */
    public <K, V, CK, CV> LevelMap.Node<CK, CV> add(K parentKey, Class<V> parentValueType, CK childKey, Class<CV> childValueType) {
        requireLevelExistence(parentValueType);

        LevelMap levelMap = classLevelMap.get(parentValueType);
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
        requireLevelExistence(valueType);

        LevelMap<K, V> levelMap = (LevelMap<K, V>) classLevelMap.get(valueType);
        return levelMap.get(key);
    }

    public <K, V> Set<LevelMap.Node<K, V>> findAllNodes(Class<V> valueType) {
        requireLevelExistence(valueType);

        LevelMap<K, V> levelMap = (LevelMap<K, V>) classLevelMap.get(valueType);
        return new HashSet<>(levelMap.values());
    }

    /**
     * Return all nodes of type {@code valueType} reachable from a lower level node of type {@code parentValueType}
     * with key = {@code parentKey}
     * Parent level does not have to be an immediate predecessor
     * @param parentKey
     * @param parentValueType
     * @param valueType
     */
    public <K, V, CK, CV> Set<LevelMap.Node<CK, CV>> findAllNodesFrom(K parentKey, Class<V> parentValueType, Class<CV> valueType) {
        requireParentChildRelation(parentValueType, valueType);

        LevelMap.Node<K, V> parentNode = findNode(parentKey, parentValueType);
        if (parentNode.children.isEmpty()) {
            return Collections.emptySet();
        }

        int distance = classLevelMap.get(valueType).level - classLevelMap.get(parentValueType).level;

        LinkedList<LevelMap.Node> queue = new LinkedList<>();
        queue.add(parentNode);
        for (int i = 0; i < distance; i++) {
            for (int j = 0; j < queue.size(); j++) {
                queue.addAll(queue.pop().children);
            }
        }

        return new HashSet(queue);
    }

    public <K, V, CV> Set<CV> findAllFrom(K parentKey, Class<V> parentValueType, Class<CV> valueType) {
        return findAllNodesFrom(parentKey, parentValueType, valueType)
                .stream()
                .map(node -> node.value)
                .collect(Collectors.toSet());
    }

    //TODO reduce complexity
//    private <CK, CV> Set<LevelMap.Node<CK, CV>> collectChildNodesOfType(Set<LevelMap.Node> nodes, Class<CV> valueType) {
//        if (nodes.isEmpty()) {
//            return Collections.emptySet();
//        }
//
//        return nodes.stream()
//                .map(child -> {
//                    if (valueType.isInstance(child.value)) {
//                        return Collections.singleton(child);
//                    } else {
//                        return collectChildNodesOfType(child.children, valueType);
//                    }
//                })
//                .flatMap(Collection::stream)
//                .map(node -> (LevelMap.Node<CK, CV>) node)
//                .collect(Collectors.toSet());
//    }


    public <K, V> V find(K key, Class<V> valueType) {
        return findNode(key, valueType).value;
    }

    public <V> List<V> findAll(Class<V> valueType) {
        return findAllNodes(valueType).stream()
                .map(node -> node.value)
                .collect(Collectors.toList());
    }


    private <V> void requireLevelExistence(Class<V> valueType) throws IllegalArgumentException {
        if (classLevelMap.get(valueType) == null) {
            throw new IllegalArgumentException(String.format("Level of type %s not found", valueType));
        }
    }

    private <V, CV> void requireParentChildRelation(Class<V> parentValueType, Class<CV> childValueType)
            throws IllegalArgumentException {
        requireLevelExistence(parentValueType);
        requireLevelExistence(childValueType);

        if (classLevelMap.get(parentValueType).level >= classLevelMap.get(childValueType).level) {
            throw new IllegalArgumentException(
                    String.format("%s is not a parent of %s ", parentValueType, childValueType));
        }
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
    //TODO sanity check - every node should be reachable from one of the 1st level nodes
    //TODO build LinkingTree using reflection, traverse fields/methods from root to child classes

    //TODO check if with Class<?>... args all levels can be defined with child types right away
    //    public LinkingTree(Class<?>... args) {
    //        for (int i = 0; i < args.length; i +=2) {
    //            addLevel(args[i], args[i+1]);
    //        }
    //
    //    }
}
