/**
 * Created with IntelliJ IDEA.
 * User: xiangji
 * Date: 9/29/13
 * Time: 3:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResizingArrayStackOfStrings {
    private String[] s;
    private int N = 0;

    public static void main(String[] args) {
        ResizingArrayStackOfStrings stack = new ResizingArrayStackOfStrings();

        while(!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if(s.equals("-"))
                StdOut.print(stack.pop());
            else
                stack.push(s);

        }
        StdOut.println();
    }

    public ResizingArrayStackOfStrings () {
        s = new String[1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push (String item) {
        if(N == s.length)
            resize (2 * s.length);
        s[N++] = item;
    }

    public String pop () {
        String item = s[--N];
        s[N] = null;
        if(N > 0 && N == s.length/4)
            resize (s.length/2);
        return item;
    }

    private void resize (int capacity) {
        String[] copy = new String[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = s[i];
        }
        s = copy;
    }
}
