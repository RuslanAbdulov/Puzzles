package com.company.linking_tree;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Currently deletion of levels and nodes is not supported
 *
 * //TODO check that this works for subtypes
 */
public class LinkingTree {

    private Map<Class, LevelMap> classLevelMap = new HashMap<>();
    private int levelsCount;//not sure if this is needed

    /**
     * Build LinkingTree from sequence of pairs of key and value types
     * new LinkingTree(Class<Key1>, Class<Value1>, Class<Key2>, Class<Value2>, ...)
     * LinkingTree lTree = new LinkingTree(
     *             String.class, CobRequest.class,  //1st level
     *             String.class, Arrangement.class, //2nd level
     *             String.class, Party.class);      //3rd level
     * @param args - sequence of class types, must be even
     */
    public LinkingTree(Class<?>... args) {
        for (int i = 0; i < args.length; i += 2) {
            if (args.length - i > 1) {
                addLevel(args[i], args[i + 1]);
            } else {
                throw new IllegalArgumentException("Number of arguments must be even");
            }
        }
    }

    /**
     * Fluent API to build LinkingTree
     * LinkingTree lTree = new LinkingTree()
     *             .addLevel(String.class, CobRequest.class)    //1st level
     *             .addLevel(String.class, Arrangement.class)   //2nd level
     *             .addLevel(String.class, Party.class);        //3rd level
     */
    public <K, V>  LinkingTree addLevel(Class<K> keyType, Class<V> valueType) {
        if (classLevelMap.get(valueType) != null) {
            throw new IllegalArgumentException("Multiple levels of the same type are not currently supported");
        }

        LevelMap<K, V> newLevel = new LevelMap<>(++levelsCount, keyType, valueType);
        classLevelMap.put(valueType, newLevel);

        return this;
    }

    /**
     * Add node
     * When key and value are known
     */
    public <K, V> LevelMap.Node<K, V> add(K key, V value) {
        LevelMap levelMap = classLevelMap.get(value.getClass());
        LevelMap.Node<K, V> node = levelMap.getWithTypeCheck(key);
        if (node != null) {
            node.value = value;
        } else {
            node = new LevelMap.Node<>(key, value);
            levelMap.putWithTypeCheck(key, node);
        }

        return node;
    }

    /**
     * Add node
     * When only key is known
     */
    public <K, V> LevelMap.Node<K, V> add(K key, Class<V> valueType) {
        LevelMap levelMap = classLevelMap.get(valueType);
        LevelMap.Node<K, V> node = levelMap.getWithTypeCheck(key);
        if (node == null) {
            node = new LevelMap.Node<>(key, null);
            levelMap.putWithTypeCheck(key, node);
        }

        return node;
    }

    /**
     * Add parent-child reference
     */
    public <K, V, CK, CV> LevelMap.Node<CK, CV> add(K parentKey, Class<V> parentValueType, CK childKey, Class<CV> childValueType) {
        requireLevelExistence(parentValueType);

        LevelMap levelMap = classLevelMap.get(parentValueType);
        LevelMap.Node<K, V> parentNode = (LevelMap.Node<K, V>) levelMap.getWithTypeCheck(parentKey);
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
        LevelMap.Node<CK, CV> childNode = add(parentKey, parentValueType, childKey, (Class<CV>) childValue.getClass());
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


    public <K, V> V find(K key, Class<V> valueType) {
        return findNode(key, valueType).value;
    }

    public <V> List<V> findAll(Class<V> valueType) {
        return findAllNodes(valueType).stream()
                .map(node -> node.value)
                .collect(Collectors.toList());
    }

    public <V> List<LevelMap.Node> findEmptyNodes(Class<V> valueType) {
        return findAllNodes(valueType).stream()
                .filter(node -> node.value == null)
                .collect(Collectors.toList());
    }

    public Map<Class, List<LevelMap.Node>> findEmptyNodes() {
        return classLevelMap.keySet().stream()
                .filter(clazz -> !findEmptyNodes(clazz).isEmpty())
                .collect(Collectors.toMap(Function.identity(), this::findEmptyNodes));
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

        LevelMap(int level, Class<K> keyType, Class<V> valueType) {
            this.level = level;
            this.keyType = keyType;
            this.valueType = valueType;
        }

        @SuppressWarnings("unchecked")
        LevelMap.Node<K, V> putWithTypeCheck(Object key, Object node) {
            checkKeyType(key);
            if (node instanceof LevelMap.Node) {
                checkValueType(((Node<K, V>) node).value);
            } else {
                throw new IllegalArgumentException(
                        String.format("Wrong node argument type %s, should be %s", Node.class, keyType));
            }

            this.put((K) key, (Node<K, V>) node);
            return (Node<K, V>) node;
        }

        LevelMap.Node<K, V> getWithTypeCheck(Object key) {
            checkKeyType(key);

            return this.get(key);
        }

        private void checkKeyType(Object key) {
            if (key == null) {
                return;
            }
            if (!keyType.isInstance(key)) {
                throw new IllegalArgumentException(
                        String.format("Wrong key type %s, should be %s", key.getClass(), keyType));
            }
        }

        private void checkValueType(Object value) {
            if (value == null) {
                return;
            }
            if (!valueType.isInstance(value)) {
                throw new IllegalArgumentException(
                        String.format("Wrong value type %s, should be %s", value.getClass(), valueType));
            }
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
}
