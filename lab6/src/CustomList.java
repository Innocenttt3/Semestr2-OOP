public class CustomList<T> {
    public class Node {
        T value;
        Node next;
        public Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }§
        private Node head;
        private Node tail;
    }
}
