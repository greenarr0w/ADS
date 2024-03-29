package ch.zhaw.ads.praktikum9;

import java.util.*;


public class MyHashtable<K, V> implements java.util.Map<K, V> {
    private K[] keys = (K[]) new Object[10];
    private V[] values = (V[]) new Object[10];

    private int hash(Object k) {
        int h = Math.abs(k.hashCode());
        return h % keys.length;
    }

    public MyHashtable() {
        // just a default size. overflow is possible!
        this.keys = (K[]) new Object[100];
        this.values = (V[]) new Object[100];
    }

    public MyHashtable(int size) {
        this.keys = (K[]) new Object[size];
        this.values = (V[]) new Object[size];
        // todo to be done
    }

    //  Removes all mappings from this map (optional operation).
    public void clear() {
        Arrays.stream(this.keys).forEach(k -> k = null);
        Arrays.stream(this.values).forEach(v -> v = null);
    }

    //  Returns true if this map contains a mapping for the specified key. 
    public boolean containsKey(Object key) {
        // todo to be done
        for (K k : this.keys) {
            if (k.hashCode() == key.hashCode()) {
                return true;
            }
        }
        return false;
    }

    //  Returns true if this map maps one or more keys to the specified value.
    public boolean containsValue(Object value) {
        // todo to be done
        for (K k : this.keys) {
            if (k.hashCode() == value.hashCode()) {
                return true;
            }
        }
        return false;
    }

    //  Returns a set view of the mappings contained in this map.
    public Set entrySet() {
        throw new UnsupportedOperationException();
    }

    //  Compares the specified object with this map for equality.
    public boolean equals(Object o) {
        throw new UnsupportedOperationException();
    }

    //  Returns the value to which this map maps the specified key.
    public V get(Object key) {
        // todo to be done
        int h = hash(key);
        if (keys[h] == key) {
            return values[h];
        }
        return null;
    }

    //  Returns the hash code value for this map.
    public int hashCode() {
        throw new UnsupportedOperationException();
    }

    //  Returns true if this map contains no key-value mappings.
    public boolean isEmpty() {
        // todo to be done
        return this.size() == 0;
    }

    //  Returns a set view of the keys contained in this map.
    public Set keySet() {
        throw new UnsupportedOperationException();
    }

    //  Associates the specified value with the specified key in this map (optional operation).
    public V put(K key, V value) {
        // todo to be done
        int h = hash(key);
        if (keys[h] == null) {
            keys[h] = key;
            values[h] = value;
        } else {
            /* COLLISION "*/
            while (keys[h] != null) {
                h = h + 1 % keys.length;
                if(h == keys.length) h = 0;
            }
            keys[h] = key;
            values[h] = value;

            // do nothing
        }
        return values[h];

    }

    //  Copies all of the mappings from the specified map to this map (optional operation).
    public void putAll(Map t) {
        throw new UnsupportedOperationException();
    }

    //  Removes the mapping for this key from this map if present (optional operation).
    public V remove(Object key) {
        // todo to be done (Aufgabe 3)
        V value = null;
        int h = hash(key);
        if (keys[h] != null) {
            keys[h] = null;
            value = values[h];
            values[h] = null;
        } else {
            // do nothing
        }
        return value;
    }

    //  Returns the number of key-value mappings in this map.
    public int size() {
        // todo to be done
        return (int) Arrays.stream(keys).filter(Objects::nonNull).count();
    }

    //  Returns a collection view of the values contained in this map.
    public Collection values() {
        throw new UnsupportedOperationException();
    }

}