
import java.util.HashMap;
import java.util.Map;

class HashMap_Doubt {
    public static void main(String[] args) {
        // Create a HashMap
        HashMap<String, Integer> map = new HashMap<>();

        // Add some key-value pairs to the HashMap
        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);
        map.put("Four", 4);
        map.put("Five", 5);

        // Retrieve a value using a key
        int value = map.get("Two");
        System.out.println("Value for key 'Two': " + value);

        // Check if a key exists in the HashMap
        boolean containsKey = map.containsKey("Three");
        System.out.println("Does the key 'Three' exist? " + containsKey);

        // Remove a key-value pair from the HashMap
        map.remove("One");

        // Check the size of the HashMap
        int size = map.size();
        System.out.println("Size of the HashMap: " + size);

        // key Traversal
        System.out.println("Keys in the HashMap:");
        for (String key : map.keySet()) {
            System.out.println(key);
        }

        // value Traversal
        System.out.println("Values in the HashMap:");
        for (Integer val : map.values()) {
            System.out.println(val);
        }

        // key-value pair Traversal
        System.out.println("Key-Value pairs in the HashMap:");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
