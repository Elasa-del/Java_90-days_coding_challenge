package edu.haramaya.cosc3053.day11;

public class GenericBoxContainer {
    // Generic Box<T> class enforcing compile-time type safety
    public static class Box<T> {
        private T value;
        // TODO 11.1: Implement set(T content) and get() methods
        public void set(T value) {
            this.value = value;
        }

        public T get() {
            return value;
        }
    }

    public static void main(String[] args) {
        Box<String> strBox = new Box<>();
        strBox.set("Haramaya University");
        // 3. Compile-Time Type Safety in action:
        // intBox.set("Wrong Type"); // <-- COMPILER ERROR: incompatible types!
        System.out.println("Box Value: " + strBox.get());
    }
}

