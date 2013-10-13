/**
 * The MergeBU class provides static methods for sorting an array using bottom-up mergesort
 * Created with IntelliJ IDEA.
 * User: xiangji
 * Date: 10/12/13
 * Time: 2:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class MergeBU {
    private MergeBU() {}

    // stably merge a[lo .. mid] with a[mid+1 .. hi] using aux[lo .. hi]
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        //precondition
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid+1, hi);

        //copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        //merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                a[k] = aux[j++];
            else if (j > hi)
                a[k] = aux[i++];
            else if (less(aux[j], aux[i]))
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }

        assert isSorted(a, lo, hi);
    }

    //
    public static void sort(Comparable[] a) {
        int N = a.length;
        Comparable[] aux = new Comparable[N];
        for (int n = 1; n < N; n = n+n) {
            for (int i = 0; i < N-n; i += n+n) {
                int lo = i;
                int m = i+n-1;
                int hi = Math.min(i+n+n-1, N-1);
                merge(a, aux, lo, m, hi);
            }
        }
        assert isSorted(a);
    }

    //helper sorting function
    private static boolean less (Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }

    //check if array is sorted
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length-1);
    }

    //check if part of array is sorted
    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if(less(a[i], a[i-1])) return false;
        return true;
    }

    //print array to standard output
    private static void show (Comparable[] a) {
        for(int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] a = in.readAllStrings();
        MergeBU.sort(a);
        show(a);
    }
}
