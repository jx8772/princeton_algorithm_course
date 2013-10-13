/**
 * Created with IntelliJ IDEA.
 * User: xiangji
 * Date: 9/29/13
 * Time: 9:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class Shell {
    //shell sort is not stable
    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] a = in.readAllStrings();
        Shell.sort(a);
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
    }

    private Shell(){};

    public static void sort (Comparable[] a) {
        int N = a.length;

        int h = 1;
        while(h < N/3)
            h = 3*h + 1;

        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h)
                    exch(a, j, j-h);
            }
            h = h/3;
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
