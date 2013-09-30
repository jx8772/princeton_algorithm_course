import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: xiangji
 * Date: 9/29/13
 * Time: 3:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResizingArrayQueue<Item> implements Iterable<Item> {
    private Item[] q;
    private int N = 0;
    private int first = 0;
    private int last = 0;

    public ResizingArrayQueue() {
        q = (Item[]) new Object[2];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private void resize (int max) {
        assert max >= N;
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = q[(first + i) % q.length];
        }
        q = temp;
        first = 0;
        last = N;
    }

    public void enqueue(Item item) {
        if (N == q.length)
            resize(2*q.length);
        q[last++] = item;
        if(last == q.length) last =0;
        N++;
    }

    public Item dequeue () {
        if (isEmpty()) throw  new NoSuchElementException("queue underflow");
        Item item = q[first];
        q[first] = null;
        N--;
        first++;
        if(first == q.length) first = 0;
        if(N > 0 && N == q.length/4)
            resize(q.length/2);
        return item;
    }

    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("queue underflow");
        return q[first];
    }

    public Iterator<Item> iterator() {
         return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;
        public boolean hasNext() {
            return i < N;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = q[(i + first) % q.length];
            i++;
            return item;
        }
    }

    public static void main(String[] args) {
        ResizingArrayQueue<String> q = new ResizingArrayQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                q.enqueue(item);
            else if(!q.isEmpty())
                StdOut.print(q.dequeue() + " ");
        }
        StdOut.println("(" + q.size() + " left on queue");
    }
}
