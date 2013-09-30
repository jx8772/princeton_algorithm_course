/**
 * Created with IntelliJ IDEA.
 * User: xiangji
 * Date: 9/29/13
 * Time: 8:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class Selection {
    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] a = in.readAllStrings();
        Selection.sort(a);
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
    }

    private Selection(){};

    public static void sort (Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if(less(a[j], a[min]))
                    min = j;
            }
            exch (a, i, min);
        }
    }

    private static void exch (Object[] a, int i, int j) {
        Object temp  = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static boolean less (Comparable a, Comparable b) {
        return (a.compareTo(b) < 0);
    }
}
