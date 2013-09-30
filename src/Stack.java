import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.StringBuilder;

/**
 * Created with IntelliJ IDEA.
 * User: xiangji
 * Date: 9/29/13
 * Time: 4:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class Stack<Item> implements Iterable<Item> {
    private int N;
    private Node<Item> first;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    public Stack() {
        first = null;
        N = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    public void push(Item item) {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        N++;
    }

    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException("stack underflow");
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item item : this)
            sb.append(item + " ");
        return sb.toString();
    }

    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }

    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {return current != null;};
        public void remove () {throw new UnsupportedOperationException();};

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }


    }

    public static void main(String[] args) {
        Stack<String> s = new Stack<String>();
        while(!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if(!item.equals("-"))
                s.push(item);
            else if (!s.isEmpty())
                StdOut.print(s.pop() + " ");
        }
        StdOut.println("(" + s.size() + " left on stack");
    }
}


