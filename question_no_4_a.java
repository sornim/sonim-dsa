import java.util.*;

class LFUCache {
    int capacity;
    Map<Integer, Node> map;
    Map<Integer, LinkedList<Node>> frequencyMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.frequencyMap = new HashMap<>();
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        Node node = map.get(key);
        updateFrequency(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }

        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            updateFrequency(node);
        } else {
            if (map.size() == capacity) {
                evict();
            }

            Node node = new Node(key, value);
            map.put(key, node);
            addToFrequencyMap(node);
        }
    }

    private void updateFrequency(Node node) {
        LinkedList<Node> list = frequencyMap.get(node.frequency);
        list.remove(node);

        if (list.isEmpty()) {
            frequencyMap.remove(node.frequency);
        }

        node.frequency++;
        addToFrequencyMap(node);
    }
addToFrequencyMap(node);
    }

    private void addToFrequencyMap(Node node) {
        LinkedList<Node> list = frequencyMap.getOrDefault(node.frequency, new LinkedList<>());
        list.addFirst(node);
        frequencyMap.put(node.frequency, list);
    }

    private void evict() {
        int minFrequency = Collections.min(frequencyMap.keySet());
        LinkedList<Node> list = frequencyMap.get(minFrequency);
        Node node = list.removeLast();
        map.remove(node.key);

        if (list.isEmpty()) {
            frequencyMap.remove(minFrequency);
        }
    }

    private static class Node {
        int key;
        int value;
        int frequency;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.frequency = 1;
        }
    }