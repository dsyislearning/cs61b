package lab9;

import java.util.*;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (key == null) {
            throw new IllegalArgumentException("calls get() with a null key");
        }
        if (p == null) {
            return null;
        }
        int cmp = key.compareTo(p.key);
        if (cmp < 0) {
            return getHelper(key, p.left);
        } else if (cmp > 0) {
            return getHelper(key, p.right);
        } else {
            return p.value;
        }
//        throw new UnsupportedOperationException();
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
//        throw new UnsupportedOperationException();
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            size++;
            return new Node(key, value);
        }
        int cmp = key.compareTo(p.key);
        if (cmp < 0) {
            p.left = putHelper(key, value, p.left);
        } else if (cmp > 0) {
            p.right = putHelper(key, value, p.right);
        } else {
            p.value = value;
        }
        return p;
//        throw new UnsupportedOperationException();
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("calls put() with a null key");
        }
        if (value == null) {
            throw new IllegalArgumentException("calls put() with a null value");
        }
        root = putHelper(key, value, root);
//        throw new UnsupportedOperationException();
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
//        throw new UnsupportedOperationException();
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    private Set<K> keySet(Node p) {
        Set<K> set = new HashSet<>();
        if (p == null) {
            return set;
        }
        set.add(p.key);
        set.addAll(keySet(p.left));
        set.addAll(keySet(p.right));
        return set;
    }

    @Override
    public Set<K> keySet() {
        return keySet(root);
//        throw new UnsupportedOperationException();
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */

    private Node removeMin(Node p) {
        if (p.left == null) {
            size--;
            return p.right;
        }
        p.left = removeMin(p.left);
        return p;
    }

    @Override
    public V remove(K key) {
        if (key == null) throw new IllegalArgumentException("calls remove() with a null key");
        V value = get(key);
        if (value == null) return null;
        else {
            root = remove(root, key);
            return value;
        }
//        throw new UnsupportedOperationException();
    }

    private Node remove(Node p, K key) {
        if (p == null) return null;
        int cmp = key.compareTo(p.key);
        if (cmp < 0) {
            p.left = remove(p.left, key);
        } else if (cmp > 0) {
            p.right = remove(p.right, key);
        } else {
            if (p.right == null) {
                size--;
                return p.left;
            }
            if (p.left == null) {
                size--;
                return p.right;
            }
            Node t = p;
            p = min(t.right);
            p.right = removeMin(t.right);
            p.left = t.left;
        }
        return p;
    }

    private Node min(Node p) {
        if (p.left == null) return p;
        else return min(p.left);
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        if (key == null) throw new IllegalArgumentException("calls remove() with a null key");
        if (value == null) throw new IllegalArgumentException("calls remove() with a null value");
        V v = get(key);
        if (!v.equals(value)) return null;
        else {
            root = remove(root, key);
            return value;
        }
//        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
//        throw new UnsupportedOperationException();
    }
}
