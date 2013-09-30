/**
 * Created with IntelliJ IDEA.
 * User: xiangji
 * Date: 9/29/13
 * Time: 7:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class Insertion {
    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] a = in.readAllStrings();
        Insertion.sort(a);
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
    }

    private Insertion(){};

    public static void sort (Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++)
            for(int j = i + 1; j < N; j++) {
                while(j > 0 && a[j].compareTo(a[j-1]) < 0) {
                    exch(a, j-1, j);
                    j--;
                }
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
