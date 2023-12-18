package com.example.classicmodelonline.models;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Cart<K, V extends CartItem> {
    // can use every value just names extends....
    private Map<K, V> map; //  Map name map

    public Cart() {
        this.map = new HashMap<>();
    }

    public int getNoOfItem() {
        return map.size();
    }

    public int getQuantity() {
        return map.values().stream().mapToInt(o -> o.getQuantity()).sum();
    }

    public Collection<V> getAllItem() {
        return Collections.unmodifiableCollection(map.values());
    }

    public double getTotalPrice() {
        return map.values().stream().mapToDouble(o -> o.getTotal()).sum();
    }

    public void addItem(K key, V value) {
        V item = map.get(key);
        if (item == null) {
            map.put(key, value);
        } else {
            item.setQuantity(item.getQuantity() + value.getQuantity());
        }
    }

    public V removeItem(K key) {
        return map.remove(key);
    }

    public void clear() {
        map.clear();
    }


}
