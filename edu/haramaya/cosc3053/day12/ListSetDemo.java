package edu.haramaya.cosc3053.day12;

import java.util.*;

public class ListSetDemo {
    public static void main(String[] args) {

        // TODO 12.1: Create ArrayList and LinkedList, compare O(1) random access vs O(n) positional lookup
        List<String> studentList = new ArrayList<>(List.of("Elias", "Ebisa", "Firomsa", "Elias"));
        System.out.println("Ordered Student List (ArrayList): " + studentList); // List allows duplicates
        System.out.println("First item: " + studentList.getFirst());
        System.out.println("Item at index 2: " + studentList.get(2));

        List<String> fruitList = new LinkedList<>(List.of("Mango", "Orange", "Mango"));
        System.out.println("\nFruit List (LinkedList): " + fruitList);
        System.out.println("Item at index 1: " + fruitList.get(1));

        // Explicit comparison of O(1) vs O(n) lookup behavior
        System.out.println("\n--- Access Complexity Comparison ---");
        System.out.println("ArrayList supports O(1) RandomAccess: " + (studentList instanceof RandomAccess));
        System.out.println("LinkedList supports O(1) RandomAccess: " + (fruitList instanceof RandomAccess));

        // Quick timing check on larger lists to see the difference
        List<Integer> largeArrayList = new ArrayList<>();
        List<Integer> largeLinkedList = new LinkedList<>();
        for (int i = 0; i < 100_000; i++) {
            largeArrayList.add(i);
            largeLinkedList.add(i);
        }

        long startArray = System.nanoTime();
        largeArrayList.get(50_000); // O(1) direct jump
        long arrayDuration = System.nanoTime() - startArray;

        long startLinked = System.nanoTime();
        largeLinkedList.get(50_000); // O(n) node traversal
        long linkedDuration = System.nanoTime() - startLinked;

        System.out.println("ArrayList get(50,000) time:  " + arrayDuration + " ns");
        System.out.println("LinkedList get(50,000) time: " + linkedDuration + " ns");

        // TODO 12.2: Add duplicate elements to HashSet and verify uniqueness filtering
        System.out.println("\n--- HashSet Uniqueness Filtering ---");
        Set<String> uniqueNames = new HashSet<>(List.of("Abebe", "Kebede", "Abebe", "Almaz")); // Duplicates not allowed
        System.out.println("Original input had 4 names (with duplicate 'Abebe').");
        System.out.println("HashSet Unique Elements (size " + uniqueNames.size() + "): " + uniqueNames);

        // TODO 12.3: Demonstrate natural sorting using TreeSet
        System.out.println("\n--- TreeSet Natural Sorting ---");
        Set<Integer> numbers = new TreeSet<>();
        numbers.add(40);
        numbers.add(10);
        numbers.add(30);
        numbers.add(10); // Duplicate is ignored
        System.out.println("Sorted Numbers (Ascending): " + numbers);

        // Natural alphabetical sorting with Strings
        Set<String> sortedNames = new TreeSet<>(uniqueNames);
        System.out.println("Sorted Unique Names (Alphabetical): " + sortedNames);
    }
}
