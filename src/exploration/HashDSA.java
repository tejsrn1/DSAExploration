package exploration;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class HashDSA {


    /**
     * This method demonstrates the usage of HashMap and HashSet in Java.
     */
    void learnHash() {
    /*
    Idea:

    HashMap:
    A HashMap is a data structure that uses a hash function to map identifying values, known as keys, to their associated values.
    It contains values based on the key. For example, if we have a key "Tejpal" and its hash is calculated as 429, we can use the modulus operator to get an index in the range of the array size.
    If there is a collision (more than one key generates the same index), we can handle it by storing a linked list at that index in the array.

    HashSet:
    A HashSet is similar to a HashMap but it does not store values, instead, it stores unique keys at the generated index. This ensures no duplicate entries are allowed.

    */

        // Create a HashMap
        HashMap<Integer, String> myMap = new HashMap<>();
        myMap.put(1, "Tejpal");
        myMap.put(2, "Montu");

        // Create a HashSet
        HashSet<String> mySet = new HashSet<>();
        mySet.add("Tejpal");
        mySet.add("Tej");
        mySet.add("Man");
    }

    /**
     * This method counts the frequency of each element in a given array.
     * It uses a HashMap to store the frequency of each element, where the element is the key and its frequency is the value.
     *
     * @param  givenArray The input array.
     *                   <p>
     *                   Example:
     *                   Input: givenArray = [1, 2, 2, 3, 3, 3]
     *                   Output:
     *                   Key 1 => Value 1
     *                   Key 2 => Value 2
     *                   Key 3 => Value 3
     *                   Explanation: The number 1 appears 1 time, the number 2 appears 2 times, and the number 3 appears 3 times.
     */
    public void CountFreq(int[] givenArray) {
        HashMap<Integer, Integer> myMap = new HashMap<>();

        for (int i = 0; i < givenArray.length; i++) {
            int key = givenArray[i];

            if (myMap.containsKey(key)) {
                int storedVal = myMap.get(key);
                storedVal = storedVal + 1;
                myMap.put(key, storedVal);
            } else {
                myMap.put(key, 1);
            }
        }

        for (Map.Entry<Integer, Integer> kvPair : myMap.entrySet()) {
            System.out.println("Key " + kvPair.getKey() + " => Value " + kvPair.getValue());
        }
    }

    /**
     * This method finds the maximum and minimum frequency of elements in an array.
     *
     * @param  givenArray The input array
     *                   <p>
     *                   Example:
     *                   Input: givenArray = [1, 2, 2, 3, 3, 3]
     *                   Output: Max Freq 3 => Max Ele 3
     *                   Min Freq 1 => Min Ele 1
     */
    public void MaxMinFreq(int[] givenArray) {
        HashMap<Integer, Integer> myMap = new HashMap<>();

        // Count the frequency of each element in the array
        for (int i = 0; i < givenArray.length; i++) {
            int key = givenArray[i];
            if (myMap.containsKey(key)) {
                int storedVal = myMap.get(key);
                storedVal++;
                myMap.put(key, storedVal);
            } else {
                myMap.put(key, 1);
            }
        }

        // Initialize the maximum and minimum frequency
        int maxFreq = 0, minFreq = givenArray.length, maxEle = 0, minEle = 0;

        // Find the element with maximum and minimum frequency
        for (Map.Entry<Integer, Integer> kvPair : myMap.entrySet()) {
            var key = kvPair.getKey();
            var val = kvPair.getValue();

            if (val > maxFreq) {
                maxFreq = val;
                maxEle = key;
            }

            if (val < minFreq) {
                minEle = key;
                minFreq = val;
            }
        }

        // Print the element with maximum and minimum frequency
        System.out.println("Max Freq " + maxFreq + " => Max Ele " + maxEle);
        System.out.println("Min Freq " + minFreq + " => Min Ele " + minEle);
    }

}
